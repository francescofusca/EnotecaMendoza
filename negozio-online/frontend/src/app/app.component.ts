import { Component, OnInit } from '@angular/core';
import { AuthService } from './servizi/auth.service';
import { Utente } from './modelli/utente.model';
import { Observable } from 'rxjs';
import { Store } from '@ngrx/store';
import { AppState } from './store/app.state';
import { selezionaConteggioProdottiCarrello } from './store/selectors/prodotto.selectors'; // CORRETTO

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  titoloApp = 'Enoteca Mendoza';
  utenteCorrente$: Observable<Utente | null>;
  conteggioCarrello$: Observable<number>;
  menuMobileAperto = false;

  constructor(
    private authService: AuthService,
    private store: Store<AppState>
  ) {
    this.utenteCorrente$ = this.authService.utenteCorrente$;
    this.conteggioCarrello$ = this.store.select(selezionaConteggioProdottiCarrello); // CORRETTO
  }

  ngOnInit(): void {}

  logout(): void {
    if (confirm('Sei sicuro di voler effettuare il logout?')) {
      this.authService.logout();
      this.chiudiMenuMobile();
    }
  }
  
  toggleMenuMobile(): void {
    this.menuMobileAperto = !this.menuMobileAperto;
  }

  chiudiMenuMobile(): void {
    this.menuMobileAperto = false;
  }
}