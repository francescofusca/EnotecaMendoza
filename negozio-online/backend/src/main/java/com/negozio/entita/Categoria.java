package com.negozio.entita;

import javax.persistence.*;

// entita per le categorie dei vini rosso bianco rosato spumante ecc
// permette di organizzare i vini per tipologia nell enoteca mendoza
@Entity
@Table(name = "CATEGORIE")
public class Categoria {

    // id univoco per ogni categoria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    // nome della categoria come vino rosso vino bianco
    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    // descrizione opzionale della categoria
    @Column(name = "DESCRIZIONE", length = 500)
    private String descrizione;

    // costruttore vuoto richiesto da jpa
    public Categoria() {}

    // costruttore per creare categoria con solo il nome
    public Categoria(String nome) {
        this.nome = nome;
    }

    // costruttore completo con nome e descrizione
    public Categoria(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
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

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }
}