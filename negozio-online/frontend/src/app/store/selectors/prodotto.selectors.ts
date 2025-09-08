import { createSelector, createFeatureSelector } from '@ngrx/store';
import { StatoProdotti } from '../reducers/prodotto.reducer';

export const selezionaStatoProdotti = createFeatureSelector<StatoProdotti>('prodotti');

export const selezionaTuttiProdotti = createSelector(
  selezionaStatoProdotti,
  (stato: StatoProdotti) => stato.prodotti
);

export const selezionaCaricamentoProdotti = createSelector(
  selezionaStatoProdotti,
  (stato: StatoProdotti) => stato.caricamento
);

export const selezionaErroreProdotti = createSelector(
  selezionaStatoProdotti,
  (stato: StatoProdotti) => stato.errore
);

export const selezionaCarrello = createSelector(
  selezionaStatoProdotti,
  (stato: StatoProdotti) => stato.carrello
);

export const selezionaTotaleCarrello = createSelector(
  selezionaStatoProdotti,
  (stato: StatoProdotti) => stato.totaleCarrello
);

export const selezionaConteggioProdottiCarrello = createSelector(
  selezionaCarrello,
  (carrello) => carrello.reduce((conteggio, elem) => conteggio + elem.quantita, 0)
);

export const selezionaQueryRicerca = createSelector(
  selezionaStatoProdotti,
  (stato: StatoProdotti) => stato.queryRicerca
);

export const selezionaProdottoPerId = (id: number) => createSelector(
  selezionaTuttiProdotti,
  (prodotti) => prodotti.find(prodotto => prodotto.id === id)
);

export const carrelloVuoto = createSelector(
  selezionaCarrello,
  (carrello) => carrello.length === 0
);