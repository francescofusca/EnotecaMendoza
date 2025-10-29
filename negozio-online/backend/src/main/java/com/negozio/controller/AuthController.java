package com.negozio.controller;

import com.negozio.entita.Utente;
import com.negozio.servizi.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// classe per ricevere i dati di login dal frontend angular
// contiene email e password che l utente inserisce nel form
class LoginRequest {
    private String email;
    private String password;

    // metodi per accedere ai campi privati necessari per spring
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

// controller per gestire login e registrazione nell enoteca mendoza
// riceve richieste dal frontend angular e risponde con dati json
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    // spring inietta automaticamente il service degli utenti
    @Autowired
    private UtenteService utenteService;

    // endpoint per il login dei clienti e amministratori
    // riceve email e password e verifica se sono corrette
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        Utente utente = utenteService.autenticaUtente(loginRequest.getEmail(), loginRequest.getPassword());

        if (utente != null) {
            // login riuscito rimando i dati utente al frontend
            // tolgo la password per sicurezza
            utente.setPassword(null);
            return ResponseEntity.ok(utente);
        } else {
            // login fallito email o password sbagliate
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email o password non corretti.");
        }
    }

    // endpoint per registrare nuovi clienti nell enoteca
    // controlla che l email non sia gia usata
    @PostMapping("/registrazione")
    public ResponseEntity<?> registraUtente(@RequestBody Utente nuovoUtente) {

        try {
            Utente utenteSalvato = utenteService.registraNuovoUtente(nuovoUtente);
            utenteSalvato.setPassword(null); // non rimando mai la password
            return ResponseEntity.status(HttpStatus.CREATED).body(utenteSalvato);
        } catch (Exception e) {
            // errore se email gia esistente o altri problemi
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}