import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { ElementoCarrello } from '../modelli/prodotto.model';
import { AppState } from '../store/app.state';
import { selezionaCarrello, selezionaTotaleCarrello } from '../store/selectors/prodotto.selectors';
import { aggiornaQuantitaCarrello, rimuoviDalCarrello, svuotaCarrello } from '../store/actions/prodotto.actions';
import { Router } from '@angular/router';

@Component({
  selector: 'app-carrello',
  templateUrl: './carrello.component.html',
  styleUrls: ['./carrello.component.css']
})
export class CarrelloComponent implements OnInit {

  carrello$: Observable<ElementoCarrello[]>;
  totale$: Observable<number>;
  mostraCheckout = false;
  indirizzoSpedizione = '';
  metodoPagamento = 'carta';

  constructor(
    private store: Store<AppState>,
    private router: Router
  ) {
    this.carrello$ = this.store.select(selezionaCarrello);
    this.totale$ = this.store.select(selezionaTotaleCarrello);
  }

  ngOnInit(): void {
    console.log('Componente carrello inizializzato');
  }

  aggiornaQuantita(prodottoId: number, nuovaQuantita: number): void {
    console.log('Aggiornando quantità prodotto', prodottoId, 'a', nuovaQuantita);
    
    if (nuovaQuantita > 0) {
      this.store.dispatch(aggiornaQuantitaCarrello({ 
        prodottoId: prodottoId, 
        quantita: nuovaQuantita 
      }));
    } else {
      this.rimuoviElemento(prodottoId);
    }
  }

  rimuoviElemento(prodottoId: number): void {
    const conferma = confirm('Sei sicuro di voler rimuovere questo prodotto dal carrello?');
    if (conferma) {
      console.log('Rimuovendo prodotto dal carrello:', prodottoId);
      this.store.dispatch(rimuoviDalCarrello({ prodottoId }));
    }
  }

  svuotaTutto(): void {
    const conferma = confirm('⚠️ Vuoi davvero svuotare tutto il carrello? Tutti i prodotti verranno rimossi!');
    if (conferma) {
      console.log('Svuotando tutto il carrello...');
      this.store.dispatch(svuotaCarrello());
    }
  }

  aumentaQuantita(prodottoId: number, quantitaAttuale: number): void {
    this.aggiornaQuantita(prodottoId, quantitaAttuale + 1);
  }

  diminuisciQuantita(prodottoId: number, quantitaAttuale: number): void {
    if (quantitaAttuale > 1) {
      this.aggiornaQuantita(prodottoId, quantitaAttuale - 1);
    } else {
      this.rimuoviElemento(prodottoId);
    }
  }

  continuaAcquisti(): void {
    console.log('Tornando alla lista prodotti...');
    this.router.navigate(['/']);
  }

  avviaCheckout(): void {
    this.mostraCheckout = true;
    console.log('Avviando processo di checkout');
  }

  confermaOrdine(): void {
    if (!this.indirizzoSpedizione.trim()) {
      alert('⚠️ Inserisci un indirizzo di spedizione valido!');
      return;
    }

    console.log('Confermando ordine...');
    console.log('Indirizzo:', this.indirizzoSpedizione);
    console.log('Metodo pagamento:', this.metodoPagamento);
    
    alert('🎉 Ordine confermato con successo! Riceverai un\'email di conferma a breve.');
    
    this.store.dispatch(svuotaCarrello());
    this.mostraCheckout = false;
    this.router.navigate(['/']);
  }

  calcolaSottototale(prezzo: number, quantita: number): number {
    return prezzo * quantita;
  }
  
  trackByProdotto(index: number, elemento: ElementoCarrello): number {
    return elemento.prodotto.id;
  }
}