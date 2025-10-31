package com.negozio.controller;

import com.negozio.entita.Prodotto;
import com.negozio.repository.ProdottoRepository; 
import com.negozio.servizi.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile; 
import java.io.IOException; 
import java.nio.file.Files; 
import java.nio.file.Path; 
import java.nio.file.Paths; 
import java.util.List;
import java.util.Optional; 

@RestController
@RequestMapping("/api/prodotti")
@CrossOrigin(origins = "http://localhost:4200")
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;

    @Autowired
    private ProdottoRepository prodottoRepository;

    private static final String UPLOAD_DIRECTORY = "src/main/resources/static/Foto_Vini_FINALE";

    @GetMapping
    public List<Prodotto> getTuttiProdotti() {
        return prodottoService.trovaTutti();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prodotto> getProdotto(@PathVariable Long id) {
        Prodotto prod = prodottoService.trovaPerID(id);
        if (prod != null) {
            return ResponseEntity.ok(prod);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Prodotto creaProdotto(@RequestBody Prodotto prodotto) {
        return prodottoService.salva(prodotto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prodotto> aggiornaProdotto(@PathVariable Long id, @RequestBody Prodotto prodotto) {
        Prodotto prodottoEsistente = prodottoService.trovaPerID(id);
        if(prodottoEsistente != null) {
            prodotto.setId(id);
            if(prodotto.getUrlImmagine() == null || prodotto.getUrlImmagine().isEmpty()) {
                prodotto.setUrlImmagine(prodottoEsistente.getUrlImmagine());
            }
            Prodotto prodottoAggiornato = prodottoService.salva(prodotto);
            return ResponseEntity.ok(prodottoAggiornato);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaProdotto(@PathVariable Long id) {
        Prodotto prodotto = prodottoService.trovaPerID(id);
        if(prodotto != null) {
            prodottoService.elimina(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/categoria/{categoriaId}")
    public List<Prodotto> getProdottiPerCategoria(@PathVariable Long categoriaId) {
        return prodottoService.trovaPerCategoria(categoriaId);
    }

    @GetMapping("/cerca")
    public List<Prodotto> cercaProdotti(@RequestParam String ricerca) {
        return prodottoService.cercaPerNome(ricerca);
    }

    @PostMapping("/{id}/upload-immagine")
    public ResponseEntity<Prodotto> uploadImmagineProdotto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {

        Optional<Prodotto> prodottoData = prodottoRepository.findById(id);

        if (!prodottoData.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Prodotto prodotto = prodottoData.get();

        if (file.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Path uploadPath = Paths.get(UPLOAD_DIRECTORY);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String originalFilename = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String nuovoNomeFile = id + "_" + System.currentTimeMillis() + fileExtension;

            Path filePath = uploadPath.resolve(nuovoNomeFile);
            Files.copy(file.getInputStream(), filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            String urlImmagineWeb = "/Foto_Vini_FINALE/" + nuovoNomeFile;
            prodotto.setUrlImmagine(urlImmagineWeb);
            prodottoRepository.save(prodotto);

            return new ResponseEntity<>(prodotto, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}