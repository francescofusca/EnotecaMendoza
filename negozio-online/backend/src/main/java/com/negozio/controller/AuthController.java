package com.negozio.controller;

import com.negozio.entita.Utente;
import com.negozio.servizi.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// classe per i dati del login
class LoginRequest {
    private String email;
    private String password;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

// controller per login e registrazione
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UtenteService utenteService;

    // login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        Utente utente = utenteService.autenticaUtente(loginRequest.getEmail(), loginRequest.getPassword());

        if (utente != null) {
            // tolgo la password
            utente.setPassword(null);
            return ResponseEntity.ok(utente);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email o password non corretti.");
        }
    }

    // registrazione nuovi utenti
    @PostMapping("/registrazione")
    public ResponseEntity<?> registraUtente(@RequestBody Utente nuovoUtente) {

        try {
            Utente utenteSalvato = utenteService.registraNuovoUtente(nuovoUtente);
            utenteSalvato.setPassword(null);
            return ResponseEntity.status(HttpStatus.CREATED).body(utenteSalvato);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}