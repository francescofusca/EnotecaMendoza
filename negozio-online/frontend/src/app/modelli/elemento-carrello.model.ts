import { Prodotto } from './prodotto.model';

export interface ElementoCarrello {
  prodotto: Prodotto;
  quantita: number;
}