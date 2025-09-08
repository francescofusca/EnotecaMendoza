import { createReducer, on } from '@ngrx/store';
import { Prodotto, ElementoCarrello } from '../../modelli/prodotto.model';
import * as AzioniProdotti from '../actions/prodotto.actions';

export interface StatoProdotti {
  prodotti: Prodotto[];
  carrello: ElementoCarrello[];
  caricamento: boolean;
  errore: any;
  queryRicerca: string;
  totaleCarrello: number;
}

export const statoIniziale: StatoProdotti = {
  prodotti: [],
  carrello: [],
  caricamento: false,
  errore: null,
  queryRicerca: '',
  totaleCarrello: 0
};

export const riduttoreProdotti = createReducer(
  statoIniziale,
  
  on(AzioniProdotti.caricaProdotti, stato => ({
    ...stato,
    caricamento: true,
    errore: null
  })),
  
  on(AzioniProdotti.caricaProdottiSuccesso, (stato, { prodotti }) => ({
    ...stato,
    prodotti,
    caricamento: false,
    errore: null
  })),
  
  on(AzioniProdotti.caricaProdottiErrore, (stato, { errore }) => ({
    ...stato,
    caricamento: false,
    errore
  })),
  
  on(AzioniProdotti.aggiungiAlCarrello, (stato, { prodotto, quantita }) => {
    const elementoEsistente = stato.carrello.find(elem => elem.prodotto.id === prodotto.id);
    
    let nuovoCarrello: ElementoCarrello[];
    
    if (elementoEsistente) {
      nuovoCarrello = stato.carrello.map(elem =>
        elem.prodotto.id === prodotto.id
          ? { ...elem, quantita: elem.quantita + quantita }
          : elem
      );
    } else {
      nuovoCarrello = [...stato.carrello, { prodotto, quantita }];
    }
    
    const nuovoTotale = nuovoCarrello.reduce((tot, elem) => 
      tot + (elem.prodotto.prezzo * elem.quantita), 0);
    
    return {
      ...stato,
      carrello: nuovoCarrello,
      totaleCarrello: nuovoTotale
    };
  }),
  
  on(AzioniProdotti.rimuoviDalCarrello, (stato, { prodottoId }) => {
    const nuovoCarrello = stato.carrello.filter(elem => elem.prodotto.id !== prodottoId);
    const nuovoTotale = nuovoCarrello.reduce((tot, elem) => 
      tot + (elem.prodotto.prezzo * elem.quantita), 0);
    
    return {
      ...stato,
      carrello: nuovoCarrello,
      totaleCarrello: nuovoTotale
    };
  }),
  
  on(AzioniProdotti.aggiornaQuantitaCarrello, (stato, { prodottoId, quantita }) => {
    const nuovoCarrello = stato.carrello.map(elem =>
      elem.prodotto.id === prodottoId
        ? { ...elem, quantita: quantita }
        : elem
    );
    
    const nuovoTotale = nuovoCarrello.reduce((tot, elem) => 
      tot + (elem.prodotto.prezzo * elem.quantita), 0);
    
    return {
      ...stato,
      carrello: nuovoCarrello,
      totaleCarrello: nuovoTotale
    };
  }),
  
  on(AzioniProdotti.svuotaCarrello, stato => ({
    ...stato,
    carrello: [],
    totaleCarrello: 0
  })),
  
  on(AzioniProdotti.impostaQueryRicerca, (stato, { query }) => ({
    ...stato,
    queryRicerca: query
  })),
  
  on(AzioniProdotti.cercaProdottiSuccesso, (stato, { prodotti }) => ({
    ...stato,
    prodotti,
    caricamento: false
  }))
);