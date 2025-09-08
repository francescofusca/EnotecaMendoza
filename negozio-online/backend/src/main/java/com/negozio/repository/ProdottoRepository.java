package com.negozio.repository;

import com.negozio.entita.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
    
    List<Prodotto> findByAttivoTrue();
    
    List<Prodotto> findByCategoriaIdAndAttivoTrue(Long categoriaId);
    
    List<Prodotto> findByNomeContainingIgnoreCaseAndAttivoTrue(String nome);
    
    List<Prodotto> findByQuantitaLessThanAndAttivoTrue(Integer qty);
    
    @Query("SELECT p FROM Prodotto p WHERE p.prezzo BETWEEN :min AND :max AND p.attivo = true ORDER BY p.prezzo")
    List<Prodotto> trovaPerRangePrezzo(@Param("min") BigDecimal prezzoMin, @Param("max") BigDecimal prezzoMax);
    
    @Query("SELECT p FROM Prodotto p WHERE p.attivo = true ORDER BY p.id DESC")
    List<Prodotto> trovaProdottiRecenti();
    
    @Query(value = "SELECT * FROM prodotti WHERE attivo = 1 AND quantita > 0 ORDER BY RAND() LIMIT 6", nativeQuery = true)
    List<Prodotto> trovaProdottiCasuali();
}