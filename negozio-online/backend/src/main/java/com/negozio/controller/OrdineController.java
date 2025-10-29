package com.negozio.controller;

import com.negozio.entita.Ordine;
import com.negozio.servizi.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ordini")
@CrossOrigin(origins = "http://localhost:4200")
public class OrdineController {

    @Autowired
    private OrdineService ordineService;

    @PostMapping("/crea")
    public ResponseEntity<?> creaOrdine(@RequestBody Ordine ordine) {
        try {
            // Deleghiamo tutta la logica complessa al servizio.
            // Il controller rimane pulito e si occupa solo di gestire la richiesta HTTP.
            Ordine nuovoOrdine = ordineService.processaAcquisto(ordine);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuovoOrdine);
        } catch (Exception e) {
            // Se il servizio lancia un errore (es. saldo insufficiente),
            // lo catturiamo e lo restituiamo come un errore 400 (Bad Request) al frontend.
            System.err.println("Errore durante la creazione dell'ordine: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // get ordini di un utente specifico
    @GetMapping("/utente/{utenteId}")
    public ResponseEntity<?> getOrdiniUtente(@PathVariable Long utenteId) {
        try {
            List<Ordine> ordini = ordineService.trovaOrdiniUtente(utenteId);
            return ResponseEntity.ok(ordini);
        } catch (Exception e) {
            System.err.println("Errore nel recupero ordini per utente " + utenteId + ": " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body("Errore interno: " + e.getMessage());
        }
    }

    // endpoint di test per verificare connessione
    @GetMapping("/test/{utenteId}")
    public ResponseEntity<?> testOrdiniUtente(@PathVariable Long utenteId) {
        try {
            List<Ordine> ordini = ordineService.trovaOrdiniUtente(utenteId);
            int count = ordini.size();

            // ritorna solo il conteggio per evitare problemi di serializzazione
            return ResponseEntity.ok("Utente " + utenteId + " ha " + count + " ordini");

        } catch (Exception e) {
            System.err.println("Errore nel test ordini: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body("Errore: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    // get tutti gli ordini per admin
    @GetMapping("/tutti")
    public ResponseEntity<List<Ordine>> getTuttiOrdini() {
        try {
            List<Ordine> ordini = ordineService.trovaTuttiOrdini();
            return ResponseEntity.ok(ordini);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // classe per il request body del cambio stato
    public static class CambioStatoRequest {
        private String stato;

        public String getStato() { return stato; }
        public void setStato(String stato) { this.stato = stato; }
    }

    // cambia stato ordine solo per admin
    @PutMapping("/{ordineId}/stato")
    public ResponseEntity<?> cambiaStatoOrdine(@PathVariable Long ordineId, @RequestBody CambioStatoRequest request) {
        try {
            Ordine.StatoOrdine nuovoStato = Ordine.StatoOrdine.valueOf(request.getStato().toUpperCase());
            Ordine ordine = ordineService.cambiaStatoOrdine(ordineId, nuovoStato);
            return ResponseEntity.ok(ordine);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Stato non valido");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // endpoint per utenti per confermare arrivo ordine
    @PutMapping("/{ordineId}/conferma-arrivo")
    public ResponseEntity<?> confermaArrivoOrdine(@PathVariable Long ordineId) {
        try {
            Ordine ordine = ordineService.cambiaStatoOrdine(ordineId, Ordine.StatoOrdine.ARRIVATO);
            return ResponseEntity.ok(ordine);
        } catch (Exception e) {
            System.err.println("Errore conferma arrivo ordine " + ordineId + ": " + e.getMessage());
            return ResponseEntity.badRequest().body("Errore: " + e.getMessage());
        }
    }
}