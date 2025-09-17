import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../servizi/auth.service';

@Component({
  selector: 'app-registrazione',
  templateUrl: './registrazione.component.html',
  styleUrls: ['./registrazione.component.css']
})
export class RegistrazioneComponent {
  formRegistrazione: FormGroup;
  messaggioErrore: string = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.formRegistrazione = this.fb.group({
      nome: ['', Validators.required],
      cognome: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit(): void {
    if (this.formRegistrazione.invalid) {
      this.messaggioErrore = "Per favore, compila tutti i campi correttamente.";
      return;
    }
    this.messaggioErrore = '';
    
    this.authService.registrazione(this.formRegistrazione.value).subscribe({
      next: () => {
        alert('Registrazione completata con successo! Ora puoi effettuare il login.');
        this.router.navigate(['/login']);
      },
      error: (err) => {
        this.messaggioErrore = err.error || 'Si è verificato un errore imprevisto.';
      }
    });
  }
}