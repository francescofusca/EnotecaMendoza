package com.negozio.entita;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "elementi_ordine")
public class ElementoOrdine {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ordine_id")
    private Long ordineId;
    
    @Column(name = "prodotto_id")
    private Long prodottoId;
    
    private Integer quantita;
    
    private BigDecimal prezzo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prodotto_id", insertable = false, updatable = false)
    private Prodotto prodotto;

    public ElementoOrdine() {}
    
    public ElementoOrdine(Long ordineId, Long prodottoId, Integer quantita, BigDecimal prezzo) {
        this.ordineId = ordineId;
        this.prodottoId = prodottoId;
        this.quantita = quantita;
        this.prezzo = prezzo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrdineId() {
        return ordineId;
    }

    public void setOrdineId(Long ordineId) {
        this.ordineId = ordineId;
    }

    public Long getProdottoId() {
        return prodottoId;
    }

    public void setProdottoId(Long prodottoId) {
        this.prodottoId = prodottoId;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }
    
    public BigDecimal calcolaSottototale() {
        if (prezzo != null && quantita != null) {
            return prezzo.multiply(BigDecimal.valueOf(quantita));
        }
        return BigDecimal.ZERO;
    }
}