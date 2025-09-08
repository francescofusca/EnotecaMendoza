-- Dati iniziali per il popolamento del database

INSERT INTO categorie (nome, descrizione) VALUES
('Elettronica', 'Smartphone, computer, accessori tecnologici'),
('Abbigliamento', 'Vestiti, scarpe, accessori moda'),
('Casa e Giardino', 'Mobili, decorazioni, attrezzi per la casa'),
('Sport e Tempo Libero', 'Attrezzature sportive, giochi, hobby'),
('Libri e Media', 'Libri, DVD, musica, videogames');

INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES
('iPhone 14 Pro', 'Ultimo smartphone Apple con fotocamera professionale da 48MP', 1199.99, 15, 1, 'https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-14-pro-deep-purple-select?wid=320&hei=420'),
('Samsung Galaxy S23', 'Smartphone Android top di gamma con display AMOLED', 899.99, 22, 1, 'https://images.samsung.com/is/image/samsung/p6pim/it/galaxy-s23/gallery/it-galaxy-s23-s911-sm-s911bzadeub-534851764'),
('MacBook Air M2', 'Laptop ultra-sottile con chip Apple M2 e 16GB RAM', 1499.99, 8, 1, 'https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/macbook-air-midnight-select-20220606'),
('Dell XPS 13', 'Ultrabook Windows con processore Intel i7 di ultima generazione', 1299.99, 12, 1, null),
('T-shirt Nike Dri-FIT', 'Maglietta sportiva traspirante in tessuto tecnico', 29.99, 45, 2, null),
('Jeans Levi''s 501', 'Jeans classici in denim 100% cotone, vestibilità regular', 89.99, 38, 2, null),
('Sneakers Adidas Ultraboost', 'Scarpe da running con tecnologia Boost per il massimo comfort', 179.99, 28, 2, null),
('Giacca North Face', 'Giacca impermeabile e antivento per trekking e outdoor', 249.99, 15, 2, null),
('Aspirapolvere Robot Roomba', 'Robot aspirapolvere intelligente con mappatura avanzata', 399.99, 18, 3, null),
('Macchina del Caffè Nespresso', 'Macchina automatica per caffè espresso con 15 bar di pressione', 149.99, 25, 3, null),
('Set Pentole Acciaio Inox', 'Set completo di 12 pentole in acciaio inossidabile', 199.99, 12, 3, null),
('Lampada LED Smart', 'Lampada intelligente controllabile da smartphone con 16 milioni di colori', 79.99, 35, 3, null),
('Bicicletta Mountain Bike', 'MTB con telaio in alluminio e sospensioni anteriori', 649.99, 8, 4, null),
('Racchetta Tennis Wilson', 'Racchetta professionale usata dai tennisti professionisti', 189.99, 14, 4, null),
('Tappetino Yoga Premium', 'Tappetino antiscivolo in materiale ecologico per yoga e pilates', 39.99, 42, 4, null),
('Pallone Calcio Nike', 'Pallone ufficiale Serie A con tecnologia Aerowsculpt', 49.99, 33, 4, null),
('Harry Potter Collezione Completa', 'Cofanetto con tutti i 7 libri della saga di J.K. Rowling', 79.99, 28, 5, null),
('PlayStation 5', 'Console gaming di ultima generazione con SSD ultra-veloce', 499.99, 3, 5, null),
('The Last of Us Part II', 'Videogame d''avventura esclusiva PlayStation 4 e 5 - Edizione Deluxe', 39.99, 22, 5, null),
('Cuffie Sony WH-1000XM5', 'Cuffie wireless premium con noise cancelling', 349.99, 18, 5, null);

INSERT INTO utenti (email, password, nome, cognome, ruolo) VALUES
('admin@negozio.com', '$2a$10$demopasswordhash', 'Mario', 'Rossi', 'ADMIN'),
('utente@test.com', '$2a$10$demopasswordhash', 'Giulia', 'Verdi', 'UTENTE'),
('cliente@email.com', '$2a$10$demopasswordhash', 'Luca', 'Bianchi', 'UTENTE');

INSERT INTO ordini (utente_id, totale, stato, indirizzo_spedizione, metodo_pagamento) VALUES
(2, 1199.99, 'CONFERMATO', 'Via Roma 123, 80100 Napoli', 'Carta di Credito'),
(3, 259.98, 'SPEDITO', 'Corso Umberto 45, 20100 Milano', 'PayPal'),
(2, 89.99, 'CONSEGNATO', 'Via Roma 123, 80100 Napoli', 'Contrassegno');

INSERT INTO elementi_ordine (ordine_id, prodotto_id, quantita, prezzo) VALUES
(1, 1, 1, 1199.99),
(2, 5, 2, 29.99),
(2, 7, 1, 179.99),
(3, 6, 1, 89.99);