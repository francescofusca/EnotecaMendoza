package com.negozio.servizi;

import com.negozio.entita.*;
import com.negozio.repository.OrdineRepository;
import com.negozio.repository.ProdottoRepository;
import com.negozio.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

// servizio per gestire tutta la logica degli ordini
// qui c e il cuore del sistema di acquisto
@Service
public class OrdineService {

    // iniezione delle dipendenze per accedere al database
    @Autowired private OrdineRepository ordineRepository;
    @Autowired private UtenteRepository utenteRepository;
    @Autowired private ProdottoRepository prodottoRepository;

    // metodo principale per processare un acquisto
    // uso transactional per sicurezza se qualcosa va male tutto viene annullato
    @Transactional
    public Ordine processaAcquisto(Ordine ordine) throws Exception {
        
        // controllo che l utente esista davvero
        Utente utente = utenteRepository.findById(ordine.getUtente().getId())
            .orElseThrow(() -> new Exception("Utente non valido per l'acquisto."));

        // calcolo il totale dell ordine
        double totaleCalcolato = 0.0;

        // controllo ogni vino nell ordine
        for (ElementoOrdine elemento : ordine.getElementiOrdine()) {
            // prendo il prodotto dal database per avere dati aggiornati
            Prodotto prodottoDB = prodottoRepository.findById(elemento.getProdotto().getId())
                .orElseThrow(() -> new Exception("Un prodotto nel carrello non è più disponibile."));
            
            // controllo se e disponibile e se ce ne abbastanza
            if (!prodottoDB.getAttivo() || prodottoDB.getQuantita() < elemento.getQuantita()) {
                throw new Exception("Quantità non disponibile per: " + prodottoDB.getNome());
            }
            
            // salvo il prezzo attuale per lo storico
            elemento.setPrezzo(prodottoDB.getPrezzo());
            // calcolo il prezzo per questa quantita
            double subtotale = prodottoDB.getPrezzo() * elemento.getQuantita();
            totaleCalcolato += subtotale;
            elemento.setOrdine(ordine); 
        }

        // imposto il totale calcolato
        ordine.setTotale(totaleCalcolato);

        // controllo se ha abbastanza soldi
        if (utente.getSaldo() < totaleCalcolato) {
            double mancante = totaleCalcolato - utente.getSaldo();
            throw new Exception("Saldo insufficiente. Ti mancano " + String.format("%.2f", mancante) + "€ per completare l'acquisto.");
        }

        // scala i soldi dal saldo
        utente.setSaldo(utente.getSaldo() - totaleCalcolato);
        utenteRepository.save(utente);

        // aggiorna le quantita in magazzino
        for (ElementoOrdine elemento : ordine.getElementiOrdine()) {
            Prodotto prodottoDB = prodottoRepository.findById(elemento.getProdotto().getId()).get();
            prodottoDB.setQuantita(prodottoDB.getQuantita() - elemento.getQuantita());
            prodottoRepository.save(prodottoDB);
        }

        // cambia lo stato e salva tutto
        ordine.setStato(Ordine.StatoOrdine.CONFERMATO);
        ordine.setUtente(utente);
        return ordineRepository.save(ordine);
    }

    // trova tutti gli ordini di un utente
    public List<Ordine> trovaOrdiniUtente(Long utenteId) {
        try {
            List<Ordine> ordini = ordineRepository.findByUtenteIdOrderByDataCreazioneDesc(utenteId);
            return ordini;
        } catch (Exception e) {
            System.err.println("Errore nella query ordini: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // trova tutti gli ordini per l admin
    public List<Ordine> trovaTuttiOrdini() {
        return ordineRepository.findAllByOrderByDataCreazioneDesc();
    }

    // cambia lo stato di un ordine admin only
    @Transactional
    public Ordine cambiaStatoOrdine(Long ordineId, Ordine.StatoOrdine nuovoStato) throws Exception {
        Ordine ordine = ordineRepository.findById(ordineId)
            .orElseThrow(() -> new Exception("Ordine non trovato"));

        // controllo speciale per ARRIVATO - può essere cambiato solo da SPEDITO
        if (nuovoStato == Ordine.StatoOrdine.ARRIVATO && ordine.getStato() != Ordine.StatoOrdine.SPEDITO) {
            throw new Exception("L'ordine può essere confermato come arrivato solo se è stato spedito");
        }

        ordine.setStato(nuovoStato);
        return ordineRepository.save(ordine);
    }
}