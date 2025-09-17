import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Utente } from '../modelli/utente.model';

@Injectable({
  providedIn: 'root'
})
export class UtenteService {
  // Nota: questo endpoint non esiste ancora nel backend, dovrai crearlo!
  private apiUrl = 'http://localhost:8080/api/utenti';

  constructor(private http: HttpClient) { }

  // Metodo per ottenere i dati aggiornati di un utente tramite il suo ID
  getUtenteById(id: number): Observable<Utente> {
    return this.http.get<Utente>(`${this.apiUrl}/${id}`);
  }

  // Metodo per ricaricare il saldo di un utente
  ricaricaSaldo(id: number, importo: number): Observable<Utente> {
    return this.http.post<Utente>(`${this.apiUrl}/${id}/ricarica`, { importo });
  }
}