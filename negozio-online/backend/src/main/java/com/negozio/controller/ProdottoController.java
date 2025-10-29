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

// controller che gestisce tutte le richieste HTTP per i vini
// fa da ponte tra il frontend angular e il backend spring
@RestController  // dice a spring che questa classe gestisce api rest
@RequestMapping("/api/prodotti")  // tutti i metodi iniziano con questo path
@CrossOrigin(origins = "http://localhost:4200")  // permette al frontend di chiamare le api
public class ProdottoController {

    // spring mi inietta automaticamente il service
    @Autowired
    private ProdottoService prodottoService;

    // repository per operazioni dirette sul database
    @Autowired
    private ProdottoRepository prodottoRepository; 

    // cartella dove salvo le foto dei vini
    private static final String UPLOAD_DIRECTORY = "src/main/resources/static/Foto_Vini_FINALE";

    // API per prendere tutti i vini GET /api/prodotti
    @GetMapping
    public List<Prodotto> getTuttiProdotti() {
        return prodottoService.trovaTutti();
    }

    // API per un singolo vino GET /api/prodotti/1
    @GetMapping("/{id}")
    public ResponseEntity<Prodotto> getProdotto(@PathVariable Long id) {
        Prodotto prod = prodottoService.trovaPerID(id);
        if (prod != null) {
            return ResponseEntity.ok(prod);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // API per creare un nuovo vino POST /api/prodotti
    @PostMapping
    public Prodotto creaProdotto(@RequestBody Prodotto prodotto) {
        return prodottoService.salva(prodotto);
    }

    // API per modificare un vino esistente PUT /api/prodotti/1
    @PutMapping("/{id}")
    public ResponseEntity<Prodotto> aggiornaProdotto(@PathVariable Long id, @RequestBody Prodotto prodotto) {
        Prodotto prodottoEsistente = prodottoService.trovaPerID(id);
        if(prodottoEsistente != null) {
            prodotto.setId(id);
            // mantengo la foto vecchia se non ne carico una nuova
            if(prodotto.getUrlImmagine() == null || prodotto.getUrlImmagine().isEmpty()) {
                prodotto.setUrlImmagine(prodottoEsistente.getUrlImmagine());
            }
            Prodotto prodottoAggiornato = prodottoService.salva(prodotto);
            return ResponseEntity.ok(prodottoAggiornato);
        }
        return ResponseEntity.notFound().build();
    }

    // API per eliminare un vino DELETE /api/prodotti/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaProdotto(@PathVariable Long id) {
        Prodotto prodotto = prodottoService.trovaPerID(id);
        if(prodotto != null) {
            prodottoService.elimina(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // API per filtrare vini per categoria
    @GetMapping("/categoria/{categoriaId}")
    public List<Prodotto> getProdottiPerCategoria(@PathVariable Long categoriaId) {
        return prodottoService.trovaPerCategoria(categoriaId);
    }

    // API per cercare vini per nome
    @GetMapping("/cerca")
    public List<Prodotto> cercaProdotti(@RequestParam String ricerca) {
        return prodottoService.cercaPerNome(ricerca);
    }

    // API per caricare la foto di un vino
    @PostMapping("/{id}/upload-immagine")
    public ResponseEntity<Prodotto> uploadImmagineProdotto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        
        // controllo che il prodotto esista
        Optional<Prodotto> prodottoData = prodottoRepository.findById(id);

        if (!prodottoData.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Prodotto prodotto = prodottoData.get();

        // controllo che il file non sia vuoto
        if (file.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            // creo la cartella se non esiste
            Path uploadPath = Paths.get(UPLOAD_DIRECTORY);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            
            // genero un nome file unico
            String originalFilename = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String nuovoNomeFile = id + "_" + System.currentTimeMillis() + fileExtension;

            // salvo il file sul server
            Path filePath = uploadPath.resolve(nuovoNomeFile);
            Files.copy(file.getInputStream(), filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            // aggiorno il prodotto con il nuovo URL
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