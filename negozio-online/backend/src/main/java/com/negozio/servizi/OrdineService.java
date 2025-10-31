package com.negozio.servizi;

import com.negozio.entita.*;
import com.negozio.repository.OrdineRepository;
import com.negozio.repository.ProdottoRepository;
import com.negozio.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class OrdineService {

    @Autowired private OrdineRepository ordineRepository;
    @Autowired private UtenteRepository utenteRepository;
    @Autowired private ProdottoRepository prodottoRepository;

    @Transactional
    public Ordine processaAcquisto(Ordine ordine) throws Exception {

        Utente utente = utenteRepository.findById(ordine.getUtente().getId())
            .orElseThrow(() -> new Exception("Utente non valido per l'acquisto."));

        double totaleCalcolato = 0.0;

        for (ElementoOrdine elemento : ordine.getElementiOrdine()) {
            Prodotto prodottoDB = prodottoRepository.findById(elemento.getProdotto().getId())
                .orElseThrow(() -> new Exception("Un prodotto nel carrello non è più disponibile."));

            if (!prodottoDB.getAttivo() || prodottoDB.getQuantita() < elemento.getQuantita()) {
                throw new Exception("Quantità non disponibile per: " + prodottoDB.getNome());
            }

            elemento.setPrezzo(prodottoDB.getPrezzo());
            double subtotale = prodottoDB.getPrezzo() * elemento.getQuantita();
            totaleCalcolato += subtotale;
            elemento.setOrdine(ordine);
        }

        ordine.setTotale(totaleCalcolato);

        if (utente.getSaldo() < totaleCalcolato) {
            double mancante = totaleCalcolato - utente.getSaldo();
            throw new Exception("Saldo insufficiente. Ti mancano " + String.format("%.2f", mancante) + "€ per completare l'acquisto.");
        }

        utente.setSaldo(utente.getSaldo() - totaleCalcolato);
        utenteRepository.save(utente);

        for (ElementoOrdine elemento : ordine.getElementiOrdine()) {
            Prodotto prodottoDB = prodottoRepository.findById(elemento.getProdotto().getId()).get();
            prodottoDB.setQuantita(prodottoDB.getQuantita() - elemento.getQuantita());
            prodottoRepository.save(prodottoDB);
        }

        ordine.setStato(Ordine.StatoOrdine.CONFERMATO);
        ordine.setUtente(utente);
        return ordineRepository.save(ordine);
    }

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

    public List<Ordine> trovaTuttiOrdini() {
        return ordineRepository.findAllByOrderByDataCreazioneDesc();
    }

    @Transactional
    public Ordine cambiaStatoOrdine(Long ordineId, Ordine.StatoOrdine nuovoStato) throws Exception {
        Ordine ordine = ordineRepository.findById(ordineId)
            .orElseThrow(() -> new Exception("Ordine non trovato"));

        if (nuovoStato == Ordine.StatoOrdine.ARRIVATO && ordine.getStato() != Ordine.StatoOrdine.SPEDITO) {
            throw new Exception("L'ordine può essere confermato come arrivato solo se è stato spedito");
        }

        ordine.setStato(nuovoStato);
        return ordineRepository.save(ordine);
    }
}