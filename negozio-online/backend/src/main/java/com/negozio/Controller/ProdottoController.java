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
        System.out.println("Richiesta per tutti i prodotti...");
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
        System.out.println("Creando nuovo prodotto: " + prodotto.getNome());
        return prodottoService.salva(prodotto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prodotto> aggiornaProdotto(@PathVariable Long id, @RequestBody Prodotto prodotto) {
        Prodotto prodottoEsistente = prodottoService.trovaPerID(id);
        if(prodottoEsistente != null) {
            prodotto.setId(id);
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
        System.out.println("Ricerca per: " + ricerca);
        return prodottoService.cercaPerNome(ricerca);
    }

    // NUOVO METODO PER L'UPLOAD DELL'IMMAGINE
    @PostMapping("/{id}/upload-immagine")
    public ResponseEntity<Prodotto> uploadImmagineProdotto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        
        //  Troviamo il prodotto nel database usando il repository
        Optional<Prodotto> prodottoData = prodottoRepository.findById(id);

        if (!prodottoData.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Prodotto prodotto = prodottoData.get();

        //  Controlliamo se il file è vuoto
        if (file.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            //  Creiamo la cartella se non esiste
            Path uploadPath = Paths.get(UPLOAD_DIRECTORY);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            
            // Generiamo un nome file univoco per evitare problemi e sovrascritture
            String originalFilename = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String nuovoNomeFile = id + "_" + System.currentTimeMillis() + fileExtension;

            //  Salviamo il file nel nostro filesystem
            Path filePath = uploadPath.resolve(nuovoNomeFile);
            Files.copy(file.getInputStream(), filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            //  Aggiorniamo il database con il percorso WEB corretto
            String urlImmagineWeb = "/Foto_Vini_FINALE/" + nuovoNomeFile;
            prodotto.setUrlImmagine(urlImmagineWeb);
            prodottoRepository.save(prodotto);

            return new ResponseEntity<>(prodotto, HttpStatus.OK);

        } catch (IOException e) {
            // Gestiamo eventuali errori durante il salvataggio
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}