import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrdineService {
  private apiUrl = 'http://localhost:8080/api/ordini';

  constructor(private http: HttpClient) { }

  creaOrdine(ordine: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/crea`, ordine);
  }

  // Ottenere ordini di un utente specifico
  getOrdiniUtente(utenteId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/utente/${utenteId}`);
  }

  // Ottenere tutti gli ordini per admin
  getTuttiOrdini(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/tutti`);
  }

  // Cambiare stato di un ordine admin only
  cambiaStatoOrdine(ordineId: number, stato: string): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${ordineId}/stato`, { stato });
  }

  // Confermare arrivo ordine per utenti
  confermaArrivoOrdine(ordineId: number): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${ordineId}/conferma-arrivo`, {});
  }
}