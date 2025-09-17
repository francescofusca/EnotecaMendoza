import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { CommonModule } from '@angular/common'; 
import { RouterModule, Routes } from '@angular/router';

// Componenti
import { AppComponent } from './app.component';
import { ProdottiComponent } from './componenti/prodotti.component'; 
import { AdminComponent } from './componenti/admin.component';
import { CarrelloComponent } from './componenti/carrello.component';
import { CategoriaProdottiComponent } from './componenti/categoria-prodotti/categoria-prodotti.component';
import { DettaglioProdottoComponent } from './componenti/dettaglio-prodotto/dettaglio-prodotto.component';
import { LoginComponent } from './componenti/login/login.component';
import { RegistrazioneComponent } from './componenti/registrazione/registrazione.component';
import { ContattiComponent } from './componenti/contatti/contatti.component';
import { ProfiloComponent } from './componenti/profilo/profilo.component';

// NGRX
import { riduttoreProdotti } from './store/reducers/prodotto.reducer';
import { EffettiProdotti } from './store/effects/prodotto.effects';

// Auth Guard
import { AuthGuard } from './servizi/auth.guard';

// Interceptor per gestire automaticamente i token JWT
import { AuthInterceptor } from './servizi/auth.interceptor';

// Mappa completa e corretta delle rotte
const appRoutes: Routes = [
  { path: '', component: ProdottiComponent, pathMatch: 'full' },
  { path: 'admin', component: AdminComponent, canActivate: [AuthGuard] },
  { path: 'carrello', component: CarrelloComponent },
  { path: 'profilo', component: ProfiloComponent, canActivate: [AuthGuard] },
  { path: 'contatti', component: ContattiComponent },
  { path: 'categoria/:id', component: CategoriaProdottiComponent },
  { path: 'prodotto/:id', component: DettaglioProdottoComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registrazione', component: RegistrazioneComponent },
  { path: '**', redirectTo: '' }
];

// modulo principale dell applicazione angular
// configuro tutti i componenti servizi e dipendenze qui
@NgModule({
  declarations: [
    // tutti i componenti che ho creato per l app
    AppComponent,
    ProdottiComponent,
    AdminComponent,
    CarrelloComponent,
    CategoriaProdottiComponent,
    DettaglioProdottoComponent,
    LoginComponent,
    RegistrazioneComponent,
    ContattiComponent,
    ProfiloComponent
  ],
  imports: [
    BrowserModule,  // per far funzionare angular nel browser
    CommonModule,   // direttive base di angular
    HttpClientModule,  // per fare chiamate HTTP alle API
    FormsModule,    // per i form template driven
    ReactiveFormsModule,  // per i form reactive
    RouterModule.forRoot(appRoutes),  // configurazione routing SPA
    StoreModule.forRoot({ prodotti: riduttoreProdotti }),  // stato globale con ngrx
    EffectsModule.forRoot([EffettiProdotti]),  // side effects per async operations
    StoreDevtoolsModule.instrument({ maxAge: 25 })  // devtools per debuggare lo stato
  ],
  providers: [
    // configuro l interceptor per aggiungere JWT token a tutte le richieste HTTP
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true  // multi: true perche posso avere piu interceptor
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }