import { createAction, props } from '@ngrx/store';
import { Prodotto } from '../../modelli/prodotto.model';

export const caricaProdotti = createAction('[Prodotto] Carica Prodotti');

export const caricaProdottiSuccesso = createAction(
  '[Prodotto] Carica Prodotti Successo',
  props<{ prodotti: Prodotto[] }>()
);

export const caricaProdottiErrore = createAction(
  '[Prodotto] Carica Prodotti Errore',
  props<{ errore: any }>()
);

export const aggiungiAlCarrello = createAction(
  '[Carrello] Aggiungi Al Carrello',
  props<{ prodotto: Prodotto; quantita: number }>()
);

export const rimuoviDalCarrello = createAction(
  '[Carrello] Rimuovi Dal Carrello',
  props<{ prodottoId: number }>()
);

export const aggiornaQuantitaCarrello = createAction(
  '[Carrello] Aggiorna Quantità Prodotto',
  props<{ 
    prodottoId: number; 
    quantita?: number;          //  quantita per incrementare/decrementare (opzionale)
    quantitaAssoluta?: number; //  quantita per impostare un valore fisso (opzionale)
  }>()
);

export const svuotaCarrello = createAction('[Carrello] Svuota Carrello');

export const aggiungiAlCarrelloFallito = createAction(
  '[Carrello] Aggiungi Al Carrello Fallito',
  props<{ 
    prodotto: Prodotto; 
    quantitaRichiesta: number; 
    quantitaDisponibile: number;
    quantitaGiaCarrello?: number;
  }>()
);

export const impostaQueryRicerca = createAction(
  '[Prodotto] Imposta Query Ricerca',
  props<{ query: string }>()
);

export const cercaProdotti = createAction(
  '[Prodotto] Cerca Prodotti',
  props<{ query: string }>()
);

export const cercaProdottiSuccesso = createAction(
  '[Prodotto] Cerca Prodotti Successo',
  props<{ prodotti: Prodotto[] }>()
);