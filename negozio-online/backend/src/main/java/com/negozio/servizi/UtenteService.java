package com.negozio.servizi;

import com.negozio.entita.Utente;
import com.negozio.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// servizio per gestire login registrazione e operazioni utenti
@Service
public class UtenteService {

    // repository per accedere ai dati utenti
    @Autowired
    private UtenteRepository utenteRepository;
    
    // per criptare le password
    @Autowired
    private PasswordEncoder passwordEncoder;

    // metodo per fare il login
    public Utente autenticaUtente(String email, String passwordFornita) {
        // cerco l utente per email
        Utente utente = utenteRepository.findByEmail(email).orElse(null);
        
        // controllo se esiste e se la password e giusta
        if (utente != null && passwordEncoder.matches(passwordFornita, utente.getPassword())) {
            return utente;
        }
        
        return null;
    }

    // metodo per registrare un nuovo cliente
    public Utente registraNuovoUtente(Utente utente) throws Exception {
        // controllo che l email non sia gia usata
        if (utenteRepository.findByEmail(utente.getEmail()).isPresent()) {
            throw new Exception("Questa email è già stata registrata. Prova a fare il login.");
        }
        
        // cripto la password per sicurezza
        String passwordCriptata = passwordEncoder.encode(utente.getPassword());
        utente.setPassword(passwordCriptata);
        
        // do 1000 euro di saldo iniziale a tutti
        utente.setSaldo(1000.0);
        
        return utenteRepository.save(utente);
    }

    // metodo per ricaricare il saldo dell utente
    public Utente ricaricaSaldo(Long utenteId, double importo) throws Exception {
        if (importo <= 0) {
            throw new Exception("L'importo della ricarica deve essere positivo");
        }

        Utente utente = utenteRepository.findById(utenteId)
            .orElseThrow(() -> new Exception("Utente non trovato"));

        // aggiungo l importo al saldo esistente
        utente.setSaldo(utente.getSaldo() + importo);

        return utenteRepository.save(utente);
    }
}