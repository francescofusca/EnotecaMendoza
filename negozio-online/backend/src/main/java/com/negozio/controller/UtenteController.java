package com.negozio.controller;

import com.negozio.entita.Utente;
import com.negozio.repository.UtenteRepository;
import com.negozio.servizi.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/utenti")
@CrossOrigin(origins = "http://localhost:4200")
public class UtenteController {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/{id}")
    public ResponseEntity<Utente> getUtenteById(@PathVariable Long id) {
        Optional<Utente> utenteData = utenteRepository.findById(id);

        if (utenteData.isPresent()) {
            Utente utente = utenteData.get();
            utente.setPassword(null); // Non inviare mai la password al frontend
            return ResponseEntity.ok(utente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // classe per il request body della ricarica
    public static class RicaricaRequest {
        private double importo;

        public double getImporto() { return importo; }
        public void setImporto(double importo) { this.importo = importo; }
    }

    // endpoint per ricaricare il saldo
    @PostMapping("/{id}/ricarica")
    public ResponseEntity<?> ricaricaSaldo(@PathVariable Long id, @RequestBody RicaricaRequest ricarica) {
        try {
            Utente utente = utenteService.ricaricaSaldo(id, ricarica.getImporto());
            utente.setPassword(null); // non inviare mai la password
            return ResponseEntity.ok(utente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}