package com.negozio.controller;

import com.negozio.entita.Categoria;
import com.negozio.servizi.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorie")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> ottieniTutteLeCategorie() {
        try {
            List<Categoria> categorie = categoriaService.ottieniTutteLeCategorie();
            return ResponseEntity.ok(categorie);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> ottieniCategoriaPerId(@PathVariable Long id) {
        try {
            Optional<Categoria> categoria = categoriaService.ottieniCategoriaPerId(id);
            if (categoria.isPresent()) {
                return ResponseEntity.ok(categoria.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/cerca")
    public ResponseEntity<Categoria> cercaCategoriaPerNome(@RequestParam String nome) {
        try {
            Categoria categoria = categoriaService.ottieniCategoriaPerNome(nome);
            if (categoria != null) {
                return ResponseEntity.ok(categoria);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/conta")
    public ResponseEntity<Long> contaCategorie() {
        try {
            long numero = categoriaService.contaCategorie();
            return ResponseEntity.ok(numero);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Categoria> creaCategoria(@RequestBody Categoria nuovaCategoria) {
        try {
            Categoria categoriaSalvata = categoriaService.salvaCategoria(nuovaCategoria);
            return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalvata);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> aggiornaCategoria(@PathVariable Long id, @RequestBody Categoria categoriaAggiornata) {
        try {
            if (categoriaService.esisteCategoria(id)) {
                categoriaAggiornata.setId(id);
                Categoria categoriaSalvata = categoriaService.salvaCategoria(categoriaAggiornata);
                return ResponseEntity.ok(categoriaSalvata);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaCategoria(@PathVariable Long id) {
        try {
            if (categoriaService.esisteCategoria(id)) {
                categoriaService.eliminaCategoria(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}