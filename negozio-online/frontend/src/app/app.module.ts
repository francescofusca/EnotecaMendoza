import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';

import { AppComponent } from './app.component';
import { ProdottiComponent } from './componenti/prodotti.component';
import { AdminComponent } from './componenti/admin.component';
import { CarrelloComponent } from './componenti/carrello.component';

import { riduttoreProdotti } from './store/reducers/prodotto.reducer';
import { EffettiProdotti } from './store/effects/prodotto.effects';
import { environment } from '../environments/environment';

const percorsi: Routes = [
  { path: '', component: ProdottiComponent },
  { path: 'prodotti', component: ProdottiComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'carrello', component: CarrelloComponent },
  { path: 'gestione', redirectTo: '/admin' },
  { path: '**', redirectTo: '' }
];

@NgModule({
  declarations: [
    AppComponent,
    ProdottiComponent,
    AdminComponent,
    CarrelloComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule.forRoot(percorsi),
    StoreModule.forRoot({
      prodotti: riduttoreProdotti
    }),
    EffectsModule.forRoot([EffettiProdotti]),
    StoreDevtoolsModule.instrument({
      maxAge: 25,
      logOnly: environment.production,
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { 
  constructor() {
    console.log('AppModule caricato con successo!');
  }
}