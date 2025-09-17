import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';

// interceptor per aggiungere token JWT automaticamente alle richieste HTTP
// intercetta tutte le chiamate HTTP in uscita e modifica gli headers
@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor() {}

  // metodo che intercetta ogni richiesta HTTP
  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    
    // prendo il token dal localStorage se c'è
    const token = localStorage.getItem('jwt_token');
    
    if (token) {
      // clono la richiesta e aggiungo l header Authorization
      // le richieste HTTP sono immutabili quindi devo clonare
      const authReq = request.clone({
        headers: request.headers.set('Authorization', `Bearer ${token}`)
      });
      
      return next.handle(authReq);
    }
    
    // se non c'è token mando la richiesta normale
    return next.handle(request);
  }
}