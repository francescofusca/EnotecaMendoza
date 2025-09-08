import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { AppState } from './store/app.state';
import { selezionaConteggioProdottiCarrello } from './store/selectors/prodotto.selectors';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  
  titoloApp = 'Il Mio Negozio Online';
  conteggioCarrello$: Observable<number>;
  menuMobileAperto = false;
  
  constructor(private store: Store<AppState>) {
    this.conteggioCarrello$ = this.store.select(selezionaConteggioProdottiCarrello);
    console.log('App component inizializzato');
  }
  
  ngOnInit(): void {
    console.log('Applicazione avviata!');
    
    this.conteggioCarrello$.subscribe(conteggio => {
      console.log('Elementi nel carrello:', conteggio);
    });
  }
  
  toggleMenuMobile(): void {
    this.menuMobileAperto = !this.menuMobileAperto;
    console.log('Menu mobile:', this.menuMobileAperto ? 'aperto' : 'chiuso');
  }
  
  chiudiMenuMobile(): void {
    this.menuMobileAperto = false;
  }
}