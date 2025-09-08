import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Prodotto } from '../modelli/prodotto.model';

@Injectable({
  providedIn: 'root'
})
export class ProdottoService {
  
  private urlBase = 'http://localhost:8080/api/prodotti';

  constructor(private http: HttpClient) { 
    console.log('ProdottoService inizializzato');
  }

  ottieniProdotti(): Observable<Prodotto[]> {
    console.log('Richiesta prodotti...');
    return this.http.get<Prodotto[]>(this.urlBase)
      .pipe(
        retry(2),
        catchError(this.gestisciErrore)
      );
  }

  ottieniProdotto(id: number): Observable<Prodotto> {
    const url = `${this.urlBase}/${id}`;
    return this.http.get<Prodotto>(url)
      .pipe(catchError(this.gestisciErrore));
  }

  creaProdotto(prodotto: Prodotto): Observable<Prodotto> {
    console.log('Creando prodotto:', prodotto);
    return this.http.post<Prodotto>(this.urlBase, prodotto)
      .pipe(catchError(this.gestisciErrore));
  }

  aggiornaProdotto(id: number, prodotto: Prodotto): Observable<Prodotto> {
    const url = `${this.urlBase}/${id}`;
    return this.http.put<Prodotto>(url, prodotto)
      .pipe(catchError(this.gestisciErrore));
  }

  eliminaProdotto(id: number): Observable<void> {
    const url = `${this.urlBase}/${id}`;
    return this.http.delete<void>(url)
      .pipe(catchError(this.gestisciErrore));
  }

  cercaProdotti(ricerca: string): Observable<Prodotto[]> {
    console.log('Ricerca per:', ricerca);
    const url = `${this.urlBase}/cerca?ricerca=${ricerca}`;
    return this.http.get<Prodotto[]>(url)
      .pipe(catchError(this.gestisciErrore));
  }

  ottieniProdottiPerCategoria(categoriaId: number): Observable<Prodotto[]> {
    const url = `${this.urlBase}/categoria/${categoriaId}`;
    return this.http.get<Prodotto[]>(url)
      .pipe(catchError(this.gestisciErrore));
  }
  
  uploadImmagine(prodottoId: number, file: File): Observable<Prodotto> {
    const formData = new FormData();
    
    formData.append('file', file, file.name);

    const url = `${this.urlBase}/${prodottoId}/upload-immagine`;
    return this.http.post<Prodotto>(url, formData)
      .pipe(catchError(this.gestisciErrore));
  }

  private gestisciErrore(errore: HttpErrorResponse) {
    console.error('Errore HTTP:', errore);
    
    let messaggioErrore = 'Qualcosa è andato storto!';
    
    if (errore.error instanceof ErrorEvent) {
      messaggioErrore = `Errore: ${errore.error.message}`;
    } else {
      messaggioErrore = `Codice errore: ${errore.status}, Messaggio: ${errore.message}`;
    }
    
    return throwError(() => new Error(messaggioErrore)); 
  }
}