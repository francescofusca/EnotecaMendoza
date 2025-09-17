package com.negozio.servizi;

import com.negozio.entita.Prodotto;
import com.negozio.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// servizio per gestire tutti i vini del catalogo
// qui c e la logica per cercare salvare e modificare i prodotti
@Service
public class ProdottoService {
    
    // repository per accedere al database dei prodotti
    @Autowired
    private ProdottoRepository repo;
    
    // prende tutti i vini attivi dal database
    public List<Prodotto> trovaTutti() {
        return repo.findByAttivoTrue();
    }
    
    // cerca un vino specifico per il suo id
    public Prodotto trovaPerID(Long id) {
        Optional<Prodotto> prodotto = repo.findById(id);
        if(prodotto.isPresent()) {
            return prodotto.get();
        } else {
            return null;
        }
    }
    
    // salva un nuovo vino o aggiorna uno esistente
    public Prodotto salva(Prodotto prodotto) {
        // controlli di validazione
        if(prodotto.getNome() == null || prodotto.getNome().trim().isEmpty()) {
            throw new RuntimeException("Nome prodotto obbligatorio");
        }
        if(prodotto.getPrezzo() <= 0) {
            throw new RuntimeException("Prezzo deve essere maggiore di zero");
        }
        
        return repo.save(prodotto);
    }
    
    // non cancello davvero ma disattivo il prodotto
    public void elimina(Long id) {
        Prodotto prod = trovaPerID(id);
        if(prod != null) {
            prod.setAttivo(false);
            repo.save(prod);
        }
    }
    
    // filtra i vini per categoria rosso bianco ecc
    public List<Prodotto> trovaPerCategoria(Long categoriaId) {
        return repo.findByCategoriaIdAndAttivoTrue(categoriaId);
    }
    
    // cerca vini che contengono una parola nel nome
    public List<Prodotto> cercaPerNome(String nome) {
        return repo.findByNomeContainingIgnoreCaseAndAttivoTrue(nome);
    }
    
    // aggiorna la quantita in magazzino dopo una vendita
    public boolean aggiornaQuantita(Long prodottoId, Integer qty) {
        Prodotto prod = trovaPerID(prodottoId);
        if(prod != null && prod.getQuantita() >= qty) {
            int nuovaQty = prod.getQuantita() - qty;
            prod.setQuantita(nuovaQty);
            repo.save(prod);
            return true;
        }
        return false;
    }
    
    // trova vini che stanno finendo per riordinarli
    public List<Prodotto> trovaConScorteScarse() {
        return repo.findByQuantitaLessThanAndAttivoTrue(5);
    }
}