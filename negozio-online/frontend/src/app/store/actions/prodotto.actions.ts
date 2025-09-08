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
  '[Carrello] Aggiorna Quantita',
  props<{ prodottoId: number; quantita: number }>()
);

export const svuotaCarrello = createAction('[Carrello] Svuota Carrello');

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