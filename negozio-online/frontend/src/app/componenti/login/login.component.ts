import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../servizi/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['../registrazione/registrazione.component.css'] // Riusa lo stile
})
export class LoginComponent {
  formLogin: FormGroup;
  messaggioErrore: string = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.formLogin = this.fb.group({
      email: ['admin@negozio.com', [Validators.required, Validators.email]],
      password: ['password', [Validators.required]]
    });
  }

  onSubmit(): void {
    if (this.formLogin.invalid) {
      this.messaggioErrore = "Email o password non validi.";
      return;
    }
    this.messaggioErrore = '';

    this.authService.login(this.formLogin.value).subscribe({
      next: () => {
        this.router.navigate(['/']);
      },
      error: (err) => {
        this.messaggioErrore = err.error || 'Credenziali non valide.';
      }
    });
  }
}