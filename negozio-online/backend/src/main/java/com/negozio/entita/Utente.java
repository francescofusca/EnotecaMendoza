package com.negozio.entita;

import javax.persistence.*;
import java.time.LocalDateTime;

// classe per gli utenti che si registrano all enoteca
// sia clienti normali che amministratori
@Entity
@Table(name = "utenti")
public class Utente {
    
    // id univoco per ogni utente generato automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // email per fare login deve essere unica
    @Column(unique = true, nullable = false)
    private String email;
    
    // password criptata per sicurezza
    @Column(nullable = false)
    private String password;
    
    // dati personali del cliente
    private String nome;
    private String cognome;
    private String telefono;
    private String indirizzo;
    
    // ruolo dell utente normale o admin
    @Enumerated(EnumType.STRING)
    private RuoloUtente ruolo = RuoloUtente.UTENTE;
    
    // per disabilitare account senza cancellarli
    private Boolean attivo = true;
    
    // quando si e registrato
    @Column(name = "data_creazione")
    private LocalDateTime dataCreazione;
    
    // ultimo login fatto
    @Column(name = "ultimo_accesso")
    private LocalDateTime ultimoAccesso;

    // soldi virtuali per comprare vini
    @Column(name = "saldo", nullable = false)
    private double saldo = 1000.0;

    // tipi di utente possibili
    public enum RuoloUtente {
        UTENTE, ADMIN
    }

    // costruttore vuoto per JPA
    public Utente() {
        this.dataCreazione = LocalDateTime.now();
    }
    
    // costruttore per creare nuovo utente
    public Utente(String email, String password, String nome, String cognome) {
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.dataCreazione = LocalDateTime.now();
        this.attivo = true;
    }

    // metodi per accedere ai campi privati
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getIndirizzo() { return indirizzo; }
    public void setIndirizzo(String indirizzo) { this.indirizzo = indirizzo; }
    public RuoloUtente getRuolo() { return ruolo; }
    public void setRuolo(RuoloUtente ruolo) { this.ruolo = ruolo; }
    public Boolean getAttivo() { return attivo; }
    public void setAttivo(Boolean attivo) { this.attivo = attivo; }
    public LocalDateTime getDataCreazione() { return dataCreazione; }
    public void setDataCreazione(LocalDateTime dataCreazione) { this.dataCreazione = dataCreazione; }
    public LocalDateTime getUltimoAccesso() { return ultimoAccesso; }
    public void setUltimoAccesso(LocalDateTime ultimoAccesso) { this.ultimoAccesso = ultimoAccesso; }
    public String getNomeCompleto() { return nome + " " + cognome; }
    
    // metodi per il saldo
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}