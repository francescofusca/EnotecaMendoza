package com.negozio.entita;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "utenti")
public class Utente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    private String nome;
    private String cognome;
    private String telefono;
    private String indirizzo;
    
    @Enumerated(EnumType.STRING)
    private RuoloUtente ruolo = RuoloUtente.UTENTE;
    
    private Boolean attivo = true;
    
    @Column(name = "data_creazione")
    private LocalDateTime dataCreazione;
    
    @Column(name = "ultimo_accesso")
    private LocalDateTime ultimoAccesso;

    public enum RuoloUtente {
        UTENTE, ADMIN
    }

    public Utente() {
        this.dataCreazione = LocalDateTime.now();
    }
    
    public Utente(String email, String password, String nome, String cognome) {
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.dataCreazione = LocalDateTime.now();
        this.attivo = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public RuoloUtente getRuolo() {
        return ruolo;
    }

    public void setRuolo(RuoloUtente ruolo) {
        this.ruolo = ruolo;
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

    public LocalDateTime getUltimoAccesso() {
        return ultimoAccesso;
    }

    public void setUltimoAccesso(LocalDateTime ultimoAccesso) {
        this.ultimoAccesso = ultimoAccesso;
    }
    
    public String getNomeCompleto() {
        return nome + " " + cognome;
    }
}