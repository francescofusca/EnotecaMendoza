package com.negozio.repository;

import com.negozio.entita.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// interfaccia per accedere ai dati dei vini nel database
// spring data jpa genera automaticamente tutte le query
@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
    
    // trova tutti i vini attivi
    List<Prodotto> findByAttivoTrue();
    
    // filtra per categoria e solo quelli attivi
    List<Prodotto> findByCategoriaIdAndAttivoTrue(Long categoriaId);
    
    // cerca nel nome senza distinguere maiuscole
    List<Prodotto> findByNomeContainingIgnoreCaseAndAttivoTrue(String nome);
    
    // trova vini con poche scorte
    List<Prodotto> findByQuantitaLessThanAndAttivoTrue(Integer qty);
    
    // cerca vini in un range di prezzo
    @Query("SELECT p FROM Prodotto p WHERE p.prezzo BETWEEN :min AND :max AND p.attivo = true ORDER BY p.prezzo")
    List<Prodotto> trovaPerRangePrezzo(@Param("min") double prezzoMin, @Param("max") double prezzoMax);
    
    // vini ordinati dal piu recente
    @Query("SELECT p FROM Prodotto p WHERE p.attivo = true ORDER BY p.id DESC")
    List<Prodotto> trovaProdottiRecenti();
    
    // prende 6 vini a caso per la homepage
    @Query(value = "SELECT * FROM prodotti WHERE attivo = 1 AND quantita > 0 ORDER BY RAND() LIMIT 6", nativeQuery = true)
    List<Prodotto> trovaProdottiCasuali();
}