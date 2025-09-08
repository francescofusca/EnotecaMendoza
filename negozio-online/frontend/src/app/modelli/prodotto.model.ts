export interface Prodotto {
  id: number;
  nome: string;
  descrizione: string;
  prezzo: number;
  quantita: number;
  urlImmagine: string;
  categoriaId: number;
  attivo: boolean;
  dataCreazione?: Date;
}

export interface ElementoCarrello {
  prodotto: Prodotto;
  quantita: number;
}

export interface Carrello {
  elementi: ElementoCarrello[];
  totale: number;
  numeroElementi: number;
}

export interface CriteriaRicerca {
  testo: string;
  categoriaId?: number;
  prezzoMin?: number;
  prezzoMax?: number;
}

export interface Categoria {
  id: number;
  nome: string;
  descrizione: string;
}