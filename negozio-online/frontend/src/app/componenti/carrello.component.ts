import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { take } from 'rxjs/operators';
import { Router } from '@angular/router';
import { AppState } from '../store/app.state';
import { AuthService } from '../servizi/auth.service';
import { OrdineService } from '../servizi/ordine.service';
import { Utente } from '../modelli/utente.model';
import { ElementoCarrello } from '../modelli/elemento-carrello.model';
import { Prodotto } from '../modelli/prodotto.model';
import { selezionaCarrello, selezionaTotaleCarrello } from '../store/selectors/prodotto.selectors';
import { rimuoviDalCarrello, svuotaCarrello, aggiornaQuantitaCarrello } from '../store/actions/prodotto.actions';

@Component({
  selector: 'app-carrello',
  templateUrl: './carrello.component.html',
  styleUrls: ['./carrello.component.css']
})
export class CarrelloComponent implements OnInit {
  
  carrello$: Observable<ElementoCarrello[]>;
  totale$: Observable<number>;
  utenteCorrente: Utente | null = null;
  
  mostraCheckout = false;
  indirizzoSpedizione = '';
  telefono = '';
  metodoPagamento = 'carta';
  messaggioErrore = '';
  
  private readonly backendUrl = 'http://localhost:8080';

  constructor(
    private store: Store<AppState>,
    private authService: AuthService,
    private ordineService: OrdineService,
    private router: Router
  ) {
    this.carrello$ = this.store.select(selezionaCarrello);
    this.totale$ = this.store.select(selezionaTotaleCarrello);
  }

  ngOnInit(): void {
    this.authService.utenteCorrente$.subscribe(utente => {
      this.utenteCorrente = utente;
      if (utente?.indirizzo) {
        this.indirizzoSpedizione = utente.indirizzo;
      }
      if (utente?.telefono) {
        this.telefono = utente.telefono;
      }
    });
  }
  
  getUrlImmagine(prodotto: Prodotto): string {
    if (!prodotto.urlImmagine) return 'assets/immagini/no-image.png'; 
    if (prodotto.urlImmagine.startsWith('http')) return prodotto.urlImmagine;
    return `${this.backendUrl}${prodotto.urlImmagine}`;
  }

  rimuoviElemento(prodottoId: number): void {
    this.store.dispatch(rimuoviDalCarrello({ prodottoId }));
  }

  svuotaTutto(): void {
    if (confirm('Sei sicuro di voler svuotare il carrello?')) {
      this.store.dispatch(svuotaCarrello());
    }
  }

  aumentaQuantita(prodottoId: number): void {
    this.carrello$.pipe(take(1)).subscribe(carrello => {
      const elemento = carrello.find(el => el.prodotto.id === prodottoId);
      if (elemento) {
        const nuovaQuantita = elemento.quantita + 1;
        if (nuovaQuantita > elemento.prodotto.quantita) {
          this.mostraNotifica(`Non puoi aggiungere più di questo prodotto. Disponibili: ${elemento.prodotto.quantita}, stai provando ad aggiungerne: ${nuovaQuantita}`);
        } else {
          this.store.dispatch(aggiornaQuantitaCarrello({ prodottoId, quantita: 1 }));
        }
      }
    });
  }

  diminuisciQuantita(prodottoId: number): void {
    this.store.dispatch(aggiornaQuantitaCarrello({ prodottoId, quantita: -1 }));
  }

  aggiornaQuantita(prodottoId: number, nuovaQuantita: string): void {
    const qta = parseInt(nuovaQuantita, 10);
    if (!isNaN(qta) && qta > 0) {
      this.carrello$.pipe(take(1)).subscribe(carrello => {
        const elemento = carrello.find(el => el.prodotto.id === prodottoId);
        if (elemento) {
          if (qta > elemento.prodotto.quantita) {
            this.mostraNotifica(`Non puoi selezionare ${qta} pezzi. Disponibili: ${elemento.prodotto.quantita} per ${elemento.prodotto.nome}`);
          } else {
            this.store.dispatch(aggiornaQuantitaCarrello({ prodottoId, quantitaAssoluta: qta }));
          }
        }
      });
    }
  }

  private mostraNotifica(messaggio: string): void {
    const notifica = document.createElement('div');
    notifica.className = 'notifica-carrello';
    notifica.textContent = messaggio;
    notifica.style.cssText = `
      position: fixed;
      bottom: 20px;
      right: 20px;
      background: #dc3545;
      color: white;
      padding: 12px 20px;
      border-radius: 8px;
      box-shadow: 0 4px 6px rgba(0,0,0,0.1);
      z-index: 9999;
      font-weight: 500;
      transform: translateX(400px);
      transition: transform 0.3s ease-in-out;
      max-width: 350px;
    `;
    
    document.body.appendChild(notifica);
    
    setTimeout(() => {
      notifica.style.transform = 'translateX(0)';
    }, 50);
    
    setTimeout(() => {
      notifica.style.transform = 'translateX(400px)';
      setTimeout(() => {
        if (document.body.contains(notifica)) {
          document.body.removeChild(notifica);
        }
      }, 300);
    }, 4000); // Mostro più a lungo per messaggi di errore
  }

  calcolaSottototale(prezzo: number, quantita: number): number {
    return prezzo * quantita;
  }

  continuaAcquisti(): void {
    this.router.navigate(['/']);
  }

  avviaCheckout(): void {
    if (!this.utenteCorrente) {
      alert('Devi effettuare il login per procedere con l\'acquisto!');
      this.router.navigate(['/login']);
      return;
    }
    this.mostraCheckout = true;
  }

  confermaOrdine(): void {
    if (!this.indirizzoSpedizione.trim()) {
      alert('Per favore, inserisci un indirizzo di spedizione.');
      return;
    }

    if (!this.telefono.trim()) {
      alert('Per favore, inserisci un numero di telefono.');
      return;
    }

    if (!this.utenteCorrente) {
      alert('Devi effettuare il login per completare l\'acquisto.');
      this.router.navigate(['/login']);
      return;
    }

    this.carrello$.pipe(take(1)).subscribe(elementi => {
      const ordine = {
        utente: { id: this.utenteCorrente!.id },
        indirizzoSpedizione: this.indirizzoSpedizione,
        telefono: this.telefono,
        metodoPagamento: this.metodoPagamento,
        elementiOrdine: elementi.map(el => ({
          prodotto: { id: el.prodotto.id },
          quantita: el.quantita
        }))
      };

      this.ordineService.creaOrdine(ordine).subscribe({
        next: () => {
          alert('Grazie per il tuo acquisto! Il tuo ordine è stato confermato. Verrai reindirizzato al tuo profilo per vedere i dettagli.');
          this.store.dispatch(svuotaCarrello());
          this.mostraCheckout = false;

          // aggiorna l'utente e poi naviga al profilo
          this.authService.refreshUtenteCorrente().subscribe(() => {
            setTimeout(() => {
              this.router.navigate(['/profilo']);
            }, 200);
          });
        },
        error: (err: any) => {
          if (typeof err.error === 'string') {
            this.messaggioErrore = err.error;
          } else if (err.message) {
            this.messaggioErrore = err.message;
          } else {
            this.messaggioErrore = "Si è verificato un errore imprevisto durante la creazione dell'ordine.";
          }
          alert(this.messaggioErrore);
        }
      });
    });
  }

  trackByProdotto(_: number, elemento: ElementoCarrello): number {
    return elemento.prodotto.id;
  }
}