import { createReducer, on } from '@ngrx/store';
import { Prodotto } from '../../modelli/prodotto.model';
import { ElementoCarrello } from '../../modelli/elemento-carrello.model';
import * as AzioniProdotti from '../actions/prodotto.actions';

// stato dell app con ngrx store per gestione stato centralizzato
// uso il pattern redux per avere uno stato immutabile
export interface StatoProdotti {
  prodotti: Prodotto[];
  carrello: ElementoCarrello[];
  caricamento: boolean;
  errore: any;
  queryRicerca: string;
  totaleCarrello: number;
}

// stato iniziale del reducer
export const statoIniziale: StatoProdotti = {
  prodotti: [],
  carrello: [],
  caricamento: false,
  errore: null,
  queryRicerca: '',
  totaleCarrello: 0
};

// reducer che gestisce le azioni e modifica lo stato in modo immutabile
// uso createReducer di ngrx per definire come lo stato cambia
export const riduttoreProdotti = createReducer(
  statoIniziale,
  
  // quando inizio a caricare prodotti metto loading a true
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
  
  // aggiorna quantita nel carrello
  on(AzioniProdotti.aggiornaQuantitaCarrello, (stato, { prodottoId, quantita, quantitaAssoluta }) => {
    const nuovoCarrello = stato.carrello.map(elem => {
      if (elem.prodotto.id === prodottoId) {
        let nuovaQuantita;
        
        // Se è specificata una quantità assoluta, usiamo quella
        if (quantitaAssoluta !== undefined) {
          nuovaQuantita = quantitaAssoluta;
        } else {
          // Altrimenti, facciamo un'addizione oppur sottrazione
          nuovaQuantita = elem.quantita + (quantita || 0);
        }
        
        // La quantità non può scendere sotto 1
        return { ...elem, quantita: Math.max(1, nuovaQuantita) };
      }
      return elem;
    });
    
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