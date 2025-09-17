package com.negozio.repository;

import com.negozio.entita.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// repository per gestire gli ordini nel database
@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long> {
    // trova ordini di un utente ordinati per data
    List<Ordine> findByUtenteIdOrderByDataCreazioneDesc(Long utenteId);

    // trova tutti gli ordini ordinati per data
    List<Ordine> findAllByOrderByDataCreazioneDesc();
}