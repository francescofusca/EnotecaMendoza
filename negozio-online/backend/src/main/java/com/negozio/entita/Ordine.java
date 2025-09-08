package com.negozio.entita;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ordini")
public class Ordine {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "utente_id")
    private Long utenteId;
    
    @Column(nullable = false)
    private BigDecimal totale;
    
    @Enumerated(EnumType.STRING)
    private StatoOrdine stato = StatoOrdine.IN_ATTESA;
    
    @Column(name = "data_creazione")
    private LocalDateTime dataCreazione;
    
    @Column(name = "indirizzo_spedizione")
    private String indirizzoSpedizione;
    
    @Column(name = "metodo_pagamento")
    private String metodoPagamento;
    
    private String note;
    
    @Column(name = "data_aggiornamento")
    private LocalDateTime dataAggiornamento;
    
    @OneToMany(mappedBy = "ordineId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ElementoOrdine> elementi;

    public enum StatoOrdine {
        IN_ATTESA, CONFERMATO, SPEDITO, CONSEGNATO, ANNULLATO
    }

    public Ordine() {
        this.dataCreazione = LocalDateTime.now();
        this.dataAggiornamento = LocalDateTime.now();
    }
    
    public Ordine(Long utenteId, BigDecimal totale, String indirizzoSpedizione) {
        this.utenteId = utenteId;
        this.totale = totale;
        this.indirizzoSpedizione = indirizzoSpedizione;
        this.dataCreazione = LocalDateTime.now();
        this.dataAggiornamento = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(Long utenteId) {
        this.utenteId = utenteId;
    }

    public BigDecimal getTotale() {
        return totale;
    }

    public void setTotale(BigDecimal totale) {
        this.totale = totale;
    }

    public StatoOrdine getStato() {
        return stato;
    }

    public void setStato(StatoOrdine stato) {
        this.stato = stato;
        this.dataAggiornamento = LocalDateTime.now();
    }

    public LocalDateTime getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDateTime dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public String getIndirizzoSpedizione() {
        return indirizzoSpedizione;
    }

    public void setIndirizzoSpedizione(String indirizzoSpedizione) {
        this.indirizzoSpedizione = indirizzoSpedizione;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getDataAggiornamento() {
        return dataAggiornamento;
    }

    public void setDataAggiornamento(LocalDateTime dataAggiornamento) {
        this.dataAggiornamento = dataAggiornamento;
    }

    public List<ElementoOrdine> getElementi() {
        return elementi;
    }

    public void setElementi(List<ElementoOrdine> elementi) {
        this.elementi = elementi;
    }
}