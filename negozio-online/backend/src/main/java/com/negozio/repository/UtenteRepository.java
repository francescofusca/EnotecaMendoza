package com.negozio.repository;

import com.negozio.entita.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// repository per accedere ai dati degli utenti
@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
    // trova un utente usando la sua email per il login
    Optional<Utente> findByEmail(String email);
}