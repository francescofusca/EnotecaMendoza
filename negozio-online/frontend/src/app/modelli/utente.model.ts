export interface Utente {
  id: number;
  email: string;
  password?: string; // Il '?' lo rende opzionale, non ci serve sempre
  nome: string;
  cognome: string;
  telefono?: string;
  indirizzo?: string;
  ruolo: 'UTENTE' | 'ADMIN';
  attivo: boolean;
  dataCreazione: string; // Le date arrivano come stringhe JSON
  ultimoAccesso?: string;
  saldo: number;
}