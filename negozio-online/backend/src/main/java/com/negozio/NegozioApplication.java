package com.negozio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

// classe principale dell enoteca mendoza
// fa partire tutto il sistema backend quando lancio il progetto
// spring boot crea automaticamente il server web sulla porta 8080
@SpringBootApplication
public class NegozioApplication implements CommandLineRunner {


	// metodo main che avvia l applicazione spring boot
	public static void main(String[] args) {
		SpringApplication.run(NegozioApplication.class, args);
	}

	// questo metodo viene eseguito automaticamente dopo l avvio
	// serve per inizializzazioni o messaggi di conferma
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Enoteca Mendoza avviata correttamente!");
		System.out.println("Server backend attivo su porta 8080");
		System.out.println("API REST disponibili per il frontend Angular");
	}

}