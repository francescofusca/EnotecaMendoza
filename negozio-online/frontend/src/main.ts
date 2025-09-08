import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app/app.module';
import { environment } from './environments/environment';
import { enableProdMode } from '@angular/core';

if (environment.production) {
  enableProdMode();
}

console.log('Avvio applicazione Angular...');
console.log('Ambiente:', environment.production ? 'produzione' : 'sviluppo');

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => {
    console.error('Errore durante l\'avvio dell\'applicazione:', err);
  });