import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject, of } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Utente } from '../modelli/utente.model';
import { Router } from '@angular/router';
import { UtenteService } from './utente.service'; //  IMPORTA IL NUOVO SERVIZIO

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';
  
  private utenteCorrenteSubject = new BehaviorSubject<Utente | null>(null);
  public utenteCorrente$ = this.utenteCorrenteSubject.asObservable();

  constructor(
    private http: HttpClient, 
    private router: Router,
    private utenteService: UtenteService //  INIETTA IL NUOVO SERVIZIO
  ) {
    this.caricaUtenteDaStorage();
  }
  
  private caricaUtenteDaStorage(): void {
    const utenteJson = localStorage.getItem('utenteCorrente');
    if (utenteJson) {
      this.utenteCorrenteSubject.next(JSON.parse(utenteJson));
    }
  }

  login(credenziali: any): Observable<Utente> {
    return this.http.post<Utente>(`${this.apiUrl}/login`, credenziali).pipe(
      tap(utente => {
        localStorage.setItem('utenteCorrente', JSON.stringify(utente));
        this.utenteCorrenteSubject.next(utente);
      })
    );
  }

  registrazione(utente: Utente): Observable<Utente> {
    return this.http.post<Utente>(`${this.apiUrl}/registrazione`, utente);
  }

  logout(): void {
    localStorage.removeItem('utenteCorrente');
    this.utenteCorrenteSubject.next(null);
    this.router.navigate(['/']);
  }

  getUtenteCorrente(): Utente | null {
    return this.utenteCorrenteSubject.value;
  }

  //  METODO AGGIUNTO PER AGGIORNARE IL SALDO 
  public refreshUtenteCorrente(): Observable<Utente | null> {
    const utente = this.getUtenteCorrente();
    if (!utente) {
      // Se per qualche motivo non c'è un utente, non fare nulla
      return of(null);
    }
    
    // Chiama il backend per ottenere i dati aggiornati dell'utente
    return this.utenteService.getUtenteById(utente.id).pipe(
      tap(utenteAggiornato => {
        // Aggiorna sia il localStorage che lo stream di dati (bheaviorsubject)
        localStorage.setItem('utenteCorrente', JSON.stringify(utenteAggiornato));
        this.utenteCorrenteSubject.next(utenteAggiornato);
      })
    );
  }
}