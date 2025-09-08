package com.negozio.controller;

import com.negozio.entita.Prodotto;
import com.negozio.servizi.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/prodotti")
@CrossOrigin(origins = "http://localhost:4200")
public class ProdottoController {
    
    @Autowired
    private ProdottoService prodottoService;
    
    @GetMapping
    public List<Prodotto> getTuttiProdotti() {
        System.out.println("Richiesta per tutti i prodotti...");
        return prodottoService.trovaTutti();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Prodotto> getProdotto(@PathVariable Long id) {
        Prodotto prod = prodottoService.trovaPerID(id);
        if (prod != null) {
            return ResponseEntity.ok(prod);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public Prodotto creaProdotto(@RequestBody Prodotto prodotto) {
        System.out.println("Creando nuovo prodotto: " + prodotto.getNome());
        return prodottoService.salva(prodotto);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Prodotto> aggiornaProdotto(@PathVariable Long id, @RequestBody Prodotto prodotto) {
        Prodotto prodottoEsistente = prodottoService.trovaPerID(id);
        if(prodottoEsistente != null) {
            prodotto.setId(id);
            Prodotto prodottoAggiornato = prodottoService.salva(prodotto);
            return ResponseEntity.ok(prodottoAggiornato);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaProdotto(@PathVariable Long id) {
        Prodotto prodotto = prodottoService.trovaPerID(id);
        if(prodotto != null) {
            prodottoService.elimina(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/categoria/{categoriaId}")
    public List<Prodotto> getProdottiPerCategoria(@PathVariable Long categoriaId) {
        return prodottoService.trovaPerCategoria(categoriaId);
    }
    
    @GetMapping("/cerca")
    public List<Prodotto> cercaProdotti(@RequestParam String ricerca) {
        System.out.println("Ricerca per: " + ricerca);
        return prodottoService.cercaPerNome(ricerca);
    }
}