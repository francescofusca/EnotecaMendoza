package com.negozio.servizi;

import com.negozio.entita.Prodotto;
import com.negozio.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdottoService {
    
    @Autowired
    private ProdottoRepository repo;
    
    public List<Prodotto> trovaTutti() {
        List<Prodotto> listaProdotti = repo.findByAttivoTrue();
        System.out.println("Trovati " + listaProdotti.size() + " prodotti");
        return listaProdotti;
    }
    
    public Prodotto trovaPerID(Long id) {
        Optional<Prodotto> prodotto = repo.findById(id);
        if(prodotto.isPresent()) {
            return prodotto.get();
        } else {
            return null;
        }
    }
    
    public Prodotto salva(Prodotto prodotto) {
        if(prodotto.getNome() == null || prodotto.getNome().trim().isEmpty()) {
            throw new RuntimeException("Nome prodotto obbligatorio");
        }
        if(prodotto.getPrezzo() == null) {
            throw new RuntimeException("Prezzo obbligatorio");
        }
        
        Prodotto prodottoSalvato = repo.save(prodotto);
        System.out.println("Prodotto salvato con ID: " + prodottoSalvato.getId());
        return prodottoSalvato;
    }
    
    public void elimina(Long id) {
        Prodotto prod = trovaPerID(id);
        if(prod != null) {
            prod.setAttivo(false);
            repo.save(prod);
        }
    }
    
    public List<Prodotto> trovaPerCategoria(Long categoriaId) {
        return repo.findByCategoriaIdAndAttivoTrue(categoriaId);
    }
    
    public List<Prodotto> cercaPerNome(String nome) {
        return repo.findByNomeContainingIgnoreCaseAndAttivoTrue(nome);
    }
    
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
    
    public List<Prodotto> trovaConScorteScarse() {
        return repo.findByQuantitaLessThanAndAttivoTrue(5);
    }
}