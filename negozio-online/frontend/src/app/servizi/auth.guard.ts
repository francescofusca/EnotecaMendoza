import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): boolean {
    if (this.authService.getUtenteCorrente()) {
      return true; // Se l'utente è loggato, permette la navigazione
    } else {
      // Se non è loggato, lo reindirizza al login e blocca la navigazione
      this.router.navigate(['/login']);
      return false;
    }
  }
}