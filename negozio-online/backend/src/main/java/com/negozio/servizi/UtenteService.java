package com.negozio.servizi;

import com.negozio.entita.Utente;
import com.negozio.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Utente autenticaUtente(String email, String passwordFornita) {
        Utente utente = utenteRepository.findByEmail(email).orElse(null);

        if (utente != null && passwordEncoder.matches(passwordFornita, utente.getPassword())) {
            return utente;
        }
        
        return null;
    }

    public Utente registraNuovoUtente(Utente utente) throws Exception {
        if (utenteRepository.findByEmail(utente.getEmail()).isPresent()) {
            throw new Exception("Questa email è già stata registrata. Prova a fare il login.");
        }

        String passwordCriptata = passwordEncoder.encode(utente.getPassword());
        utente.setPassword(passwordCriptata);

        utente.setSaldo(1000.0);
        
        return utenteRepository.save(utente);
    }

    public Utente ricaricaSaldo(Long utenteId, double importo) throws Exception {
        if (importo <= 0) {
            throw new Exception("L'importo della ricarica deve essere positivo");
        }

        Utente utente = utenteRepository.findById(utenteId)
            .orElseThrow(() -> new Exception("Utente non trovato"));

        utente.setSaldo(utente.getSaldo() + importo);

        return utenteRepository.save(utente);
    }
}