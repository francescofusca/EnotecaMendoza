import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Prodotto } from '../modelli/prodotto.model';
import { ProdottoService } from '../servizi/prodotto.service';
import { OrdineService } from '../servizi/ordine.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  elencoProdotti: Prodotto[] = [];
  formProdotto: FormGroup;
  prodottoInModifica: Prodotto | null = null;
  mostraForm = false;
  messaggioSuccesso = '';
  messaggioErrore = '';

  fileSelezionato: File | null = null;
  private readonly backendUrl = 'http://localhost:8080';

  // gestione ordini
  tuttiOrdini: any[] = [];
  mostraOrdini = false;
  caricandoOrdini = false;

  constructor(
    private fb: FormBuilder,
    private servizio: ProdottoService,
    private ordineService: OrdineService
  ) {
    this.formProdotto = this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(2)]],
      descrizione: [''],
      prezzo: [0, [Validators.required, Validators.min(0.01)]],
      quantita: [0, [Validators.required, Validators.min(0)]],
      categoriaId: [1, Validators.required]
    });
  }

  ngOnInit(): void {
    this.caricaProdotti();
  }

  caricaProdotti(): void {
    this.servizio.ottieniProdotti().subscribe({
      next: (prodotti) => this.elencoProdotti = prodotti,
      error: (err) => this.messaggioErrore = 'Errore nel caricamento dei prodotti'
    });
  }

  mostraNascondiForm(): void {
    this.mostraForm = !this.mostraForm;
    if (!this.mostraForm) {
      this.resetForm();
    }
  }

  modificaProdotto(prodotto: Prodotto): void {
    this.prodottoInModifica = prodotto;
    this.mostraForm = true;
    this.formProdotto.patchValue({
      nome: prodotto.nome,
      descrizione: prodotto.descrizione,
      prezzo: prodotto.prezzo,
      quantita: prodotto.quantita,
      categoriaId: prodotto.categoriaId
    });
  }

  onFileSelected(event: any): void {
    if (event.target.files && event.target.files[0]) {
      this.fileSelezionato = event.target.files[0];
    }
  }

  salvaProdotto(): void {
    if (!this.formProdotto.valid) {
      this.messaggioErrore = 'Compila tutti i campi obbligatori';
      this.nascondiMessaggiDopoDelay();
      return;
    }
    
    const datiProdotto = this.formProdotto.value;
    
    if (this.prodottoInModifica) {
      this.servizio.aggiornaProdotto(this.prodottoInModifica.id, datiProdotto).subscribe({
        next: (prodottoAggiornato) => {
          this.messaggioSuccesso = 'Dati prodotto aggiornati!';
          if (this.fileSelezionato) {
            this.caricaImmagine(prodottoAggiornato.id);
          } else {
            this.finalizzaOperazione();
          }
        },
        error: (err) => this.gestisciErrore(err, 'aggiornamento')
      });
    } else {
      this.servizio.creaProdotto(datiProdotto).subscribe({
        next: (nuovoProdotto) => {
          this.messaggioSuccesso = 'Prodotto creato!';
          if (this.fileSelezionato) {
            this.caricaImmagine(nuovoProdotto.id);
          } else {
            this.finalizzaOperazione();
          }
        },
        error: (err) => this.gestisciErrore(err, 'creazione')
      });
    }
  }

  caricaImmagine(prodottoId: number): void {
    if (!this.fileSelezionato) return;
    this.servizio.uploadImmagine(prodottoId, this.fileSelezionato).subscribe({
      next: () => {
        this.messaggioSuccesso += ' Immagine caricata!';
        this.finalizzaOperazione();
      },
      error: (err) => this.gestisciErrore(err, 'caricamento immagine')
    });
  }

  eliminaProdotto(id: number): void {
    const conferma = confirm('Sei sicuro di voler eliminare questo prodotto?');
    if (conferma) {
      this.servizio.eliminaProdotto(id).subscribe({
        next: () => {
          this.messaggioSuccesso = 'Prodotto eliminato con successo!';
          this.finalizzaOperazione();
        },
        error: (err) => this.gestisciErrore(err, 'eliminazione')
      });
    }
  }
  
  getUrlImmagine(prodotto: Prodotto): string {
    if (!prodotto.urlImmagine) return 'assets/immagini/no-image.png'; 
    if (prodotto.urlImmagine.startsWith('http')) return prodotto.urlImmagine;
    return `${this.backendUrl}${prodotto.urlImmagine}`;
  }

  resetForm(): void {
    this.formProdotto.reset({ prezzo: 0, quantita: 0, categoriaId: 1 });
    this.prodottoInModifica = null;
    this.mostraForm = false;
    this.fileSelezionato = null;
    this.messaggioSuccesso = '';
    this.messaggioErrore = '';
  } 
  
  private finalizzaOperazione(): void {
    this.caricaProdotti();
    this.resetForm();
    this.nascondiMessaggiDopoDelay();
  }

  private gestisciErrore(err: any, operazione: string): void {
    this.messaggioErrore = `Errore durante l'operazione di ${operazione}.`;
    this.nascondiMessaggiDopoDelay();
  }
  
  private nascondiMessaggiDopoDelay(): void {
    setTimeout(() => {
      this.messaggioSuccesso = '';
      this.messaggioErrore = '';
    }, 4000);
  }

  // gestione ordini
  mostraOrdiniAdmin(): void {
    this.mostraOrdini = !this.mostraOrdini;
    if (this.mostraOrdini) {
      this.caricaOrdini();
    }
  }

  caricaOrdini(): void {
    this.caricandoOrdini = true;
    this.ordineService.getTuttiOrdini().subscribe({
      next: (ordini) => {
        this.tuttiOrdini = ordini;
        this.caricandoOrdini = false;
      },
      error: (error) => {
        console.error('Errore caricamento ordini:', error);
        this.caricandoOrdini = false;
      }
    });
  }

  cambiaStatoOrdine(ordineId: number, nuovoStato: string): void {
    this.ordineService.cambiaStatoOrdine(ordineId, nuovoStato).subscribe({
      next: () => {
        this.messaggioSuccesso = 'Stato ordine aggiornato!';
        this.caricaOrdini(); // ricarica la lista
        this.nascondiMessaggiDopoDelay();
      },
      error: (error) => {
        this.messaggioErrore = 'Errore aggiornamento stato ordine';
        this.nascondiMessaggiDopoDelay();
      }
    });
  }

  getStatoOrdineColore(stato: string): string {
    switch (stato) {
      case 'IN_ATTESA': return 'warning';
      case 'CONFERMATO': return 'primary';
      case 'SPEDITO': return 'info';
      case 'ARRIVATO': return 'success';
      default: return 'secondary';
    }
  }

  getStatoOrdineDescrizione(stato: string): string {
    switch (stato) {
      case 'IN_ATTESA': return 'In Attesa';
      case 'CONFERMATO': return 'Confermato';
      case 'SPEDITO': return 'Spedito';
      case 'ARRIVATO': return 'Arrivato';
      default: return stato;
    }
  }
}