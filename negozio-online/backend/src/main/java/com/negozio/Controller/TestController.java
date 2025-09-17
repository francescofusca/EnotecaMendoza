package com.negozio.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// controller per testare che il server funzioni
// utile durante lo sviluppo per verificare la connessione
@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {
    
    // prendo i valori dalle properties del progetto
    @Value("${app.nome}")
    private String nomeApp;
    
    @Value("${app.versione}")
    private String versione;
    
    // endpoint per controllare se il server e attivo
    @GetMapping("/health")
    public Map<String, Object> healthCheck() {
        System.out.println("Health check richiesto...");
        
        Map<String, Object> risposta = new HashMap<>();
        risposta.put("status", "OK");
        risposta.put("timestamp", LocalDateTime.now());
        risposta.put("applicazione", nomeApp);
        risposta.put("versione", versione);
        risposta.put("messaggio", "Il server funziona correttamente!");
        
        return risposta;
    }
    
    // informazioni sul progetto e lo studente
    @GetMapping("/info")
    public Map<String, String> getInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("nome", nomeApp);
        info.put("versione", versione);
        info.put("autore", "Mario Rossi");
        info.put("corso", "Ingegneria del Software");
        info.put("universita", "Università degli Studi di Napoli Federico II");
        
        return info;
    }
    
    // endpoint che rimanda indietro quello che riceve
    @PostMapping("/echo")
    public Map<String, Object> echo(@RequestBody Map<String, Object> payload) {
        System.out.println("Echo richiesto con payload: " + payload);
        
        Map<String, Object> risposta = new HashMap<>();
        risposta.put("ricevuto", payload);
        risposta.put("timestamp", LocalDateTime.now());
        risposta.put("messaggio", "Echo completato con successo");
        
        return risposta;
    }
}