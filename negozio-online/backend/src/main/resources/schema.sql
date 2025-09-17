-- Schema per H2 Database (ambiente di sviluppo)

-- Pulisce le tabelle se esistono già, per partire puliti ad ogni riavvio
DROP TABLE IF EXISTS elementi_ordine CASCADE;
DROP TABLE IF EXISTS ordini CASCADE;
DROP TABLE IF EXISTS prodotti CASCADE;
DROP TABLE IF EXISTS categorie CASCADE;
DROP TABLE IF EXISTS utenti CASCADE;

-- Creazione delle tabelle (versione semplificata per H2)

CREATE TABLE utenti (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    nome VARCHAR(100),
    cognome VARCHAR(100),
    telefono VARCHAR(20),
    indirizzo TEXT,
    ruolo VARCHAR(10) DEFAULT 'UTENTE', -- Nota: VARCHAR è più compatibile con H2 di ENUM
    attivo BOOLEAN DEFAULT TRUE,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ultimo_accesso TIMESTAMP NULL,
    saldo DECIMAL(10,2) DEFAULT 1000.00 -- <-- LA RIGA DA AGGIUNGERE/MODIFICARE
);

CREATE TABLE categorie (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descrizione TEXT,
    attiva BOOLEAN DEFAULT TRUE
);

CREATE TABLE prodotti (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descrizione TEXT,
    prezzo DECIMAL(10,2) NOT NULL,
    quantita INT DEFAULT 0,
    url_immagine VARCHAR(500),
    categoria_id BIGINT,
    attivo BOOLEAN DEFAULT TRUE,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_modifica TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    FOREIGN KEY (categoria_id) REFERENCES categorie(id)
);

CREATE TABLE ordini (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    utente_id BIGINT,
    totale DECIMAL(10,2) NOT NULL,
    stato VARCHAR(20) DEFAULT 'IN_ATTESA', 
    indirizzo_spedizione TEXT,
    metodo_pagamento VARCHAR(50),
    note TEXT,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    FOREIGN KEY (utente_id) REFERENCES utenti(id)
);

CREATE TABLE elementi_ordine (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ordine_id BIGINT,
    prodotto_id BIGINT,
    quantita INT NOT NULL,
    prezzo DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (ordine_id) REFERENCES ordini(id) ON DELETE CASCADE,
    FOREIGN KEY (prodotto_id) REFERENCES prodotti(id)
);