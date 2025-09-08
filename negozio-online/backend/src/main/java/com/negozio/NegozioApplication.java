package com.negozio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import com.negozio.servizi.ProdottoService;

@SpringBootApplication
public class NegozioApplication implements CommandLineRunner {
	
	@Autowired
	private ProdottoService prodottoService;

	public static void main(String[] args) {
		System.out.println("Avviando applicazione negozio online...");
		SpringApplication.run(NegozioApplication.class, args);
		System.out.println("Applicazione avviata con successo!");
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Inizializzazione completata");
		
		try {
			int numeroProdotti = prodottoService.trovaTutti().size();
			System.out.println("Prodotti disponibili nel database: " + numeroProdotti);
		} catch (Exception e) {
			System.err.println("Errore durante il caricamento iniziale: " + e.getMessage());
		}
	}

}