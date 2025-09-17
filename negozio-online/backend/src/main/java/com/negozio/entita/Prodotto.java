package com.negozio.entita;

import javax.persistence.*;
import java.time.LocalDateTime;

// questa e la classe che rappresenta i vini nel database
// uso JPA per mappare automaticamente questa classe sulla tabella prodotti
@Entity
@Table(name = "prodotti")
public class Prodotto {
    
    // id del prodotto generato automaticamente dal database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // nome del vino obbligatorio
    @Column(nullable = false)
    private String nome;
    
    // descrizione del vino per i dettagli
    private String descrizione;
    
    @Column(nullable = false)
    private double prezzo;
    
    // quante bottiglie abbiamo in magazzino
    private Integer quantita;
    
    // link alla foto del vino
    private String urlImmagine;
    
    // collegamento alla categoria rosso bianco rosato ecc
    @Column(name = "categoria_id")
    private Long categoriaId;
    
    // per nascondere prodotti senza cancellarli dal db
    private Boolean attivo = true;
    
    // quando ho aggiunto questo vino al catalogo
    private LocalDateTime dataCreazione;

    // costruttore vuoto richiesto da JPA
    public Prodotto() {
        this.dataCreazione = LocalDateTime.now();
    }
    
    // costruttore per creare un nuovo prodotto
    public Prodotto(String nome, String descrizione, double prezzo) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.dataCreazione = LocalDateTime.now();
        this.attivo = true;
    }

    // metodi getter e setter per accedere ai campi privati
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public String getUrlImmagine() {
        return urlImmagine;
    }

    public void setUrlImmagine(String urlImmagine) {
        this.urlImmagine = urlImmagine;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Boolean getAttivo() {
        return attivo;
    }

    public void setAttivo(Boolean attivo) {
        this.attivo = attivo;
    }
    
    public LocalDateTime getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDateTime dataCreazione) {
        this.dataCreazione = dataCreazione;
    }
}