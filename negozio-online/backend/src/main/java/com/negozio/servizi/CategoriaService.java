package com.negozio.servizi;

import com.negozio.entita.Categoria;
import com.negozio.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> ottieniTutteLeCategorie() {
        return categoriaRepository.findAllOrderById();
    }

    public Optional<Categoria> ottieniCategoriaPerId(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria ottieniCategoriaPerNome(String nome) {
        return categoriaRepository.findByNome(nome);
    }

    public Categoria salvaCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void eliminaCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    public long contaCategorie() {
        return categoriaRepository.count();
    }

    public boolean esisteCategoria(Long id) {
        return categoriaRepository.existsById(id);
    }
}