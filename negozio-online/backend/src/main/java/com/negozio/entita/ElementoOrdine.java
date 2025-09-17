package com.negozio.entita;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

// questa classe rappresenta ogni singolo vino dentro un ordine
// per esempio se compro 2 barolo e 3 chianti avro 2 ElementoOrdine
@Entity
@Table(name = "elementi_ordine")
public class ElementoOrdine {

    // id univoco per ogni elemento di ordine
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // collegamento all ordine di appartenenza molti elementi per un ordine
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ordine_id", nullable = false)
    @JsonBackReference // evita problemi quando converto in JSON
    private Ordine ordine;

    // quale vino e stato ordinato
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prodotto_id", nullable = false)
    private Prodotto prodotto;

    // quante bottiglie di questo vino
    @Column(nullable = false)
    private int quantita;

    // prezzo del vino al momento dell acquisto per storico
    @Column(nullable = false)
    private double prezzo;

    // metodi per accedere ai dati privati
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Ordine getOrdine() { return ordine; }
    public void setOrdine(Ordine ordine) { this.ordine = ordine; }
    public Prodotto getProdotto() { return prodotto; }
    public void setProdotto(Prodotto prodotto) { this.prodotto = prodotto; }
    public int getQuantita() { return quantita; }
    public void setQuantita(int quantita) { this.quantita = quantita; }
    public double getPrezzo() { return prezzo; }
    public void setPrezzo(double prezzo) { this.prezzo = prezzo; }
}