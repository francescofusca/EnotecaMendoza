import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Prodotto } from '../modelli/prodotto.model';
import { ProdottoService } from '../servizi/prodotto.service';

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

  constructor(
    private fb: FormBuilder,
    private servizio: ProdottoService
  ) {
    this.formProdotto = this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(2)]],
      descrizione: [''],
      prezzo: [0, [Validators.required, Validators.min(0.01)]],
      quantita: [0, [Validators.required, Validators.min(0)]],
      urlImmagine: [''],
      categoriaId: [1]
    });
  }

  ngOnInit(): void {
    console.log('Admin component inizializzato');
    this.caricaProdotti();
  }

  caricaProdotti(): void {
    console.log('Caricando lista prodotti per admin...');
    this.servizio.ottieniProdotti().subscribe({
      next: (prodotti) => {
        this.elencoProdotti = prodotti;
        console.log('Prodotti caricati:', prodotti.length);
      },
      error: (err) => {
        console.error('Errore caricamento:', err);
        this.messaggioErrore = 'Errore nel caricamento dei prodotti';
      }
    });
  }

  mostraNascondiForm(): void {
    this.mostraForm = !this.mostraForm;
    if (!this.mostraForm) {
      this.resetForm();
    }
    console.log('Form mostrato:', this.mostraForm);
  }

  modificaProdotto(prodotto: Prodotto): void {
    console.log('Modificando prodotto:', prodotto.id);
    this.prodottoInModifica = prodotto;
    this.mostraForm = true;
    
    this.formProdotto.patchValue({
      nome: prodotto.nome,
      descrizione: prodotto.descrizione,
      prezzo: prodotto.prezzo,
      quantita: prodotto.quantita,
      urlImmagine: prodotto.urlImmagine,
      categoriaId: prodotto.categoriaId
    });
  }

  salvaProdotto(): void {
    if (this.formProdotto.valid) {
      const datiProdotto = this.formProdotto.value;
      console.log('Salvando prodotto:', datiProdotto);
      
      if (this.prodottoInModifica) {
        this.servizio.aggiornaProdotto(this.prodottoInModifica.id, datiProdotto).subscribe({
          next: (prodotto) => {
            console.log('Prodotto aggiornato con successo');
            this.messaggioSuccesso = 'Prodotto aggiornato con successo!';
            this.caricaProdotti();
            this.resetForm();
            this.nascondiMessaggiDopoDelay();
          },
          error: (err) => {
            console.error('Errore aggiornamento:', err);
            this.messaggioErrore = 'Errore nell\'aggiornamento del prodotto';
            this.nascondiMessaggiDopoDelay();
          }
        });
      } else {
        this.servizio.creaProdotto(datiProdotto).subscribe({
          next: (prodotto) => {
            console.log('Nuovo prodotto creato:', prodotto.id);
            this.messaggioSuccesso = 'Prodotto creato con successo!';
            this.caricaProdotti();
            this.resetForm();
            this.nascondiMessaggiDopoDelay();
          },
          error: (err) => {
            console.error('Errore creazione:', err);
            this.messaggioErrore = 'Errore nella creazione del prodotto';
            this.nascondiMessaggiDopoDelay();
          }
        });
      }
    } else {
      console.log('Form non valido');
      this.messaggioErrore = 'Compila tutti i campi obbligatori';
      this.nascondiMessaggiDopoDelay();
    }
  }

  eliminaProdotto(id: number): void {
    const conferma = confirm('Sei sicuro di voler eliminare questo prodotto? L\'operazione non può essere annullata.');
    if (conferma) {
      console.log('Eliminando prodotto ID:', id);
      this.servizio.eliminaProdotto(id).subscribe({
        next: () => {
          console.log('Prodotto eliminato');
          this.messaggioSuccesso = 'Prodotto eliminato con successo!';
          this.caricaProdotti();
          this.nascondiMessaggiDopoDelay();
        },
        error: (err) => {
          console.error('Errore eliminazione:', err);
          this.messaggioErrore = 'Errore nell\'eliminazione del prodotto';
          this.nascondiMessaggiDopoDelay();
        }
      });
    }
  }

  resetForm(): void {
    this.formProdotto.reset();
    this.prodottoInModifica = null;
    this.mostraForm = false;
    this.formProdotto.patchValue({
      prezzo: 0,
      quantita: 0,
      categoriaId: 1
    });
    this.messaggioSuccesso = '';
    this.messaggioErrore = '';
  }
  
  private nascondiMessaggiDopoDelay(): void {
    setTimeout(() => {
      this.messaggioSuccesso = '';
      this.messaggioErrore = '';
    }, 3000);
  }
}