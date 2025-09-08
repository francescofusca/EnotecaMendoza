import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Prodotto } from '../modelli/prodotto.model';
import { AppState } from '../store/app.state';
import { caricaProdotti, aggiungiAlCarrello } from '../store/actions/prodotto.actions';
import { selezionaTuttiProdotti, selezionaCaricamentoProdotti } from '../store/selectors/prodotto.selectors';

@Component({
  selector: 'app-prodotti',
  templateUrl: './prodotti.component.html',
  styleUrls: ['./prodotti.component.css']
})
export class ProdottiComponent implements OnInit {
  
  prodotti$: Observable<Prodotto[]>;
  loading$: Observable<boolean>;
  testoRicerca: string = '';
  
  constructor(private store: Store<AppState>) {
    this.prodotti$ = this.store.select(selezionaTuttiProdotti);
    this.loading$ = this.store.select(selezionaCaricamentoProdotti);
  }

  ngOnInit(): void {
    console.log('Caricamento componente prodotti...');
    this.store.dispatch(caricaProdotti());
  }
  
  aggiungiProdottoAlCarrello(prodotto: Prodotto): void {
    console.log('Aggiungendo al carrello:', prodotto.nome);
    this.store.dispatch(aggiungiAlCarrello({ prodotto: prodotto, quantita: 1 }));
  }
  
  getUrlImmagine(prodotto: Prodotto): string {
    if(prodotto.urlImmagine && prodotto.urlImmagine.length > 0) {
      return prodotto.urlImmagine;
    } else {
      return 'assets/immagini/no-image.png';
    }
  }
  
  cerca() {
    console.log('Cercando:', this.testoRicerca);
  }
  
  trackByProdotto(index: number, prodotto: Prodotto): number {
    return prodotto.id;
  }
}