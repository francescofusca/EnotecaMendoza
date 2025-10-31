package com.negozio.entita;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// classe che rappresenta un ordine completo fatto da un cliente
// contiene tutti i vini ordinati e le info di pagamento
@Entity
@Table(name = "ordini")
public class Ordine {

    // id univoco dell ordine
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // quale cliente ha fatto questo ordine
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "utente_id")
    private Utente utente;

    // prezzo totale dell ordine calcolato automaticamente
    @Column(nullable = false)
    private double totale;

    // stato dell ordine che cambia durante il processo
    @Enumerated(EnumType.STRING)
    private StatoOrdine stato = StatoOrdine.IN_ATTESA;

    // indirizzo di spedizione fornito al momento dell ordine
    @Column(name = "indirizzo_spedizione", nullable = false, columnDefinition = "TEXT")
    private String indirizzoSpedizione;

    // numero di telefono per contattare il cliente
    @Column(name = "telefono", nullable = false)
    private String telefono;

    // quando e stato fatto l ordine
    @Column(name = "data_creazione")
    private LocalDateTime dataCreazione;

    // lista di tutti i vini in questo ordine
    // se cancello un ordine cancello anche tutti i suoi elementi
    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference // evita problemi nella conversione JSON
    private List<ElementoOrdine> elementiOrdine = new ArrayList<>();

    // stati possibili di un ordine
    public enum StatoOrdine {
        IN_ATTESA, CONFERMATO, SPEDITO, ARRIVATO
    }

    // imposta automaticamente la data quando salvo nel db
    @PrePersist
    protected void onCreate() {
        dataCreazione = LocalDateTime.now();
    }

    // metodi per accedere ai campi privati
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Utente getUtente() { return utente; }
    public void setUtente(Utente utente) { this.utente = utente; }
    public double getTotale() { return totale; }
    public void setTotale(double totale) { this.totale = totale; }
    public StatoOrdine getStato() { return stato; }
    public void setStato(StatoOrdine stato) { this.stato = stato; }
    public LocalDateTime getDataCreazione() { return dataCreazione; }
    public void setDataCreazione(LocalDateTime dataCreazione) { this.dataCreazione = dataCreazione; }
    public List<ElementoOrdine> getElementiOrdine() { return elementiOrdine; }
    public void setElementiOrdine(List<ElementoOrdine> elementiOrdine) { this.elementiOrdine = elementiOrdine; }
    public String getIndirizzoSpedizione() { return indirizzoSpedizione; }
    public void setIndirizzoSpedizione(String indirizzoSpedizione) { this.indirizzoSpedizione = indirizzoSpedizione; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}