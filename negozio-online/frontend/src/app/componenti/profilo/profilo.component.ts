import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';
import { Subscription } from 'rxjs';
import { AuthService } from '../../servizi/auth.service';
import { UtenteService } from '../../servizi/utente.service';
import { OrdineService } from '../../servizi/ordine.service';
import { Utente } from '../../modelli/utente.model';

@Component({
  selector: 'app-profilo',
  templateUrl: './profilo.component.html',
  styleUrls: ['./profilo.component.css']
})
export class ProfiloComponent implements OnInit, OnDestroy {
  utenteCorrente: Utente | null = null;
  ordiniUtente: any[] = [];
  importoRicarica: number = 0;
  messaggioRicarica: string = '';
  caricandoOrdini: boolean = false;
  caricandoRicarica: boolean = false;
  private routerSubscription: Subscription | null = null;

  constructor(
    private authService: AuthService,
    private utenteService: UtenteService,
    private ordineService: OrdineService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.authService.utenteCorrente$.subscribe(utente => {
      this.utenteCorrente = utente;
      if (utente) {
        // piccolo delay per permettere al backend di salvare l'ordine se arriviamo dal carrello
        setTimeout(() => {
          this.caricaOrdini();
        }, 800);
      }
    });

    // ascolta i cambiamenti di rotta per ricaricare gli ordini quando si torna al profilo
    this.routerSubscription = this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe((event) => {
        const navEvent = event as NavigationEnd;
        if (navEvent.url === '/profilo' && this.utenteCorrente) {
          setTimeout(() => {
            this.caricaOrdini();
          }, 500);
        }
      });
  }

  ngOnDestroy(): void {
    if (this.routerSubscription) {
      this.routerSubscription.unsubscribe();
    }
  }

  caricaOrdini(): void {
    if (!this.utenteCorrente) {
      console.log('Nessun utente corrente per caricare ordini');
      return;
    }

    console.log('Caricando ordini per utente ID:', this.utenteCorrente.id);
    this.caricandoOrdini = true;
    this.ordineService.getOrdiniUtente(this.utenteCorrente.id).subscribe({
      next: (ordini) => {
        console.log('Ordini ricevuti:', ordini);
        this.ordiniUtente = ordini || [];
        this.caricandoOrdini = false;
      },
      error: (error) => {
        console.error('Errore caricamento ordini:', error);
        this.ordiniUtente = [];
        this.caricandoOrdini = false;
      }
    });
  }

  ricaricaSaldo(): void {
    if (!this.utenteCorrente || this.importoRicarica <= 0) {
      this.messaggioRicarica = 'Inserisci un importo valido';
      return;
    }

    this.caricandoRicarica = true;
    this.utenteService.ricaricaSaldo(this.utenteCorrente.id, this.importoRicarica).subscribe({
      next: (utenteAggiornato) => {
        this.messaggioRicarica = `Ricarica di €${this.importoRicarica.toFixed(2)} completata!`;
        this.importoRicarica = 0;
        this.caricandoRicarica = false;

        // aggiorna i dati utente nel servizio auth
        this.authService.refreshUtenteCorrente().subscribe(() => {
          // dopo aver aggiornato l'utente, aggiorna anche gli ordini se necessario
          setTimeout(() => {
            this.caricaOrdini();
          }, 300);
        });
      },
      error: (error) => {
        console.error('Errore ricarica:', error);
        if (error.error && typeof error.error === 'string') {
          this.messaggioRicarica = 'Errore: ' + error.error;
        } else if (error.message) {
          this.messaggioRicarica = 'Errore: ' + error.message;
        } else {
          this.messaggioRicarica = 'Errore durante la ricarica. Riprova più tardi.';
        }
        this.caricandoRicarica = false;
      }
    });
  }

  getStatoOrdineColore(stato: string): string {
    switch (stato) {
      case 'IN_ATTESA': return 'warning';
      case 'CONFERMATO': return 'primary';
      case 'SPEDITO': return 'info';
      case 'ARRIVATO': return 'success';
      default: return 'secondary';
    }
  }

  getStatoOrdineDescrizione(stato: string): string {
    switch (stato) {
      case 'IN_ATTESA': return 'In Attesa';
      case 'CONFERMATO': return 'Confermato';
      case 'SPEDITO': return 'Spedito';
      case 'ARRIVATO': return 'Arrivato';
      default: return stato;
    }
  }

  // conferma arrivo ordine
  confermaArrivo(ordineId: number): void {
    const conferma = confirm('Sei sicuro che l\'ordine sia arrivato?\n\nConfermando, lo stato dell\'ordine cambierà in "Arrivato" e non potrai più modificarlo.');

    if (conferma) {
      this.ordineService.confermaArrivoOrdine(ordineId).subscribe({
        next: (ordineAggiornato) => {
          console.log('Arrivo confermato per ordine:', ordineId);
          alert('Perfetto! Ordine confermato come arrivato. Grazie per l\'acquisto!');
          // ricarica gli ordini per aggiornare la lista
          this.caricaOrdini();
        },
        error: (error) => {
          console.error('Errore conferma arrivo:', error);
          let messaggio = 'Errore durante la conferma dell\'arrivo.';
          if (error.error && typeof error.error === 'string') {
            messaggio = error.error;
          }
          alert('Attenzione: ' + messaggio);
        }
      });
    }
  }

}