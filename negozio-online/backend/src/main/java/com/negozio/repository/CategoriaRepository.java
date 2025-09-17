package com.negozio.repository;

import com.negozio.entita.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("SELECT c FROM Categoria c ORDER BY c.id")
    List<Categoria> findAllOrderById();

    @Query("SELECT c FROM Categoria c WHERE c.nome = ?1")
    Categoria findByNome(String nome);
}