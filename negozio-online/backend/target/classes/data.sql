
DELETE FROM elementi_ordine;
DELETE FROM ordini;
DELETE FROM prodotti;
DELETE FROM categorie;
ALTER TABLE elementi_ordine ALTER COLUMN id RESTART WITH 1;
ALTER TABLE ordini ALTER COLUMN id RESTART WITH 1;
ALTER TABLE prodotti ALTER COLUMN id RESTART WITH 1;
ALTER TABLE categorie ALTER COLUMN id RESTART WITH 1;


INSERT INTO categorie (id, nome, descrizione) VALUES
(1, 'Vini Rossi', 'Una selezione dei migliori vini rossi, dalla Calabria e dal mondo.'),
(2, 'Vini Bianchi', 'Vini bianchi freschi e aromatici, perfetti per ogni occasione.'),
(3, 'Vini Rosati', 'La freschezza dei bianchi con il carattere dei rossi.'),
(4, 'Vini da 375 ml', 'Le nostre migliori etichette in formato mezza bottiglia.'),
(5, 'Vini Spumanti', 'Bollicine per celebrare i momenti speciali.'),
(6, 'Vini Dolci', 'Vini da dessert e da meditazione per concludere il pasto.');


INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('I Monaci', 'Giraldi & Giraldi - Uvaggio: Magliocco', 16.00, 12, 1, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Don Onofrio', 'Giraldi & Giraldi - Uvaggio: Magliocco', 25.00, 12, 1, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Calabrise', 'Ippolito - Uvaggio: Nerello', 15.00, 12, 1, '/Foto_Vini_FINALE/Calabrise - Ippolito_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('160 Anni', 'Ippolito - Uvaggio: Gaglioppo', 32.00, 12, 1, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Ripe del Falco', 'Ippolito - Uvaggio: Gaglioppo', 37.00, 12, 1, '/Foto_Vini_FINALE/Ripe del Falco - Ippolito_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Virgani', 'Russo e Longo - Uvaggio: Gaglioppo, Magliocco', 15.00, 12, 1, '/Foto_Vini_FINALE/Virgani - Russo e Longo_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Jachello', 'Russo e Longo - Uvaggio: Gaglioppo, Greco nero,Sangiovese', 35.00, 12, 1, '/Foto_Vini_FINALE/Jachello - Russo e Longo_2.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Melissa Jacca Ventu', 'Pizzuta del Principe - Uvaggio: Gaglioppo, Greco nero', 17.00, 12, 1, '/Foto_Vini_FINALE/Melissa Jacca Ventu - Pizzuta del Principe_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Zingamaro', 'Pizzuta del Principe - Uvaggio: Greco nero', 29.00, 12, 1, '/Foto_Vini_FINALE/Zingamaro - Pizzuta del Principe_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Duca S. Felice', 'Librandi - Uvaggio: Gaglioppo', 20.00, 12, 1, '/Foto_Vini_FINALE/Duca S. Felice - Librandi_2.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Magno Megonio', 'Librandi - Uvaggio: Magliocco', 29.00, 12, 1, '/Foto_Vini_FINALE/Magno Megonio - Librandi_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Gravello', 'Librandi - Uvaggio: Gaglioppo, Cabernet S.', 40.00, 12, 1, '/Foto_Vini_FINALE/Gravello - Librandi_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Sette Chiese', 'Serracavallo - Uvaggio: Magliocco, Cabernet S.', 15.00, 12, 1, '/Foto_Vini_FINALE/Sette Chiese - Serracavallo_1.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Quattro Lustri', 'Serracavallo - Uvaggio: Magliocco', 21.00, 12, 1, '/Foto_Vini_FINALE/Quattro Lustri - Serracavallo_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Terraccia', 'Serracavallo - Uvaggio: Magliocco, Cabernet S.', 24.00, 12, 1, '/Foto_Vini_FINALE/Terraccia - Serracavallo_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Vigna Savuco', 'Serracavallo - Uvaggio: Magliocco', 60.00, 12, 1, '/Foto_Vini_FINALE/Vigna Savuco - Serracavallo_1.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Magliocco', 'Ferrocinto - Uvaggio: Magliocco', 15.00, 12, 1, '/Foto_Vini_FINALE/Magliocco - Ferrocinto_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Pollino Rosso', 'Ferrocinto - Uvaggio: Magliocco', 18.00, 12, 1, '/Foto_Vini_FINALE/Pollino Rosso - Ferrocinto_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Pollino Riserva', 'Ferrocinto - Uvaggio: Magliocco', 29.00, 12, 1, '/Foto_Vini_FINALE/Pollino Riserva - Ferrocinto_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Serra delle Ciavole', 'Ferrocinto - Uvaggio: Aglianico', 25.00, 12, 1, '/Foto_Vini_FINALE/Serra delle Ciavole - Ferrocinto_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Petraro', 'Ceraudo - Uvaggio: Gaglioppo, Greco N.,Cabernet S.', 40.00, 12, 1, '/Foto_Vini_FINALE/Petraro - Ceraudo_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Dattilo', 'Ceraudo - Uvaggio: Gaglioppo', 35.00, 12, 1, '/Foto_Vini_FINALE/Dattilo - Ceraudo_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Nanà', 'Ceraudo - Uvaggio: Gaglioppo, Magliocco', 26.00, 12, 1, '/Foto_Vini_FINALE/Nanà - Ceraudo_2.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Gaudio Rosso', 'Magna Greacia - Uvaggio: Magliocco, Merlot', 18.00, 12, 1, '/Foto_Vini_FINALE/Gaudio Rosso - Magna Greacia_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Baronè', 'Magna Greacia - Uvaggio: Guarnaccia', 33.00, 12, 1, '/Foto_Vini_FINALE/Baronè - Magna Greacia_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('VI Secolo', 'Magna Greacia - Uvaggio: Magliocco', 70.00, 12, 1, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Cariglio', 'Terre Nobili - Uvaggio: Magliocco', 16.00, 12, 1, '/Foto_Vini_FINALE/Cariglio - Terre Nobili_3.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Alarico', 'Terre Nobili - Uvaggio: Nerello', 24.00, 12, 1, '/Foto_Vini_FINALE/Alarico - Terre Nobili_3.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Teodora', 'Terre Nobili - Uvaggio: Nerello M.,Nerello C.', 42.00, 12, 1, '/Foto_Vini_FINALE/Teodora - Terre Nobili_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Principe Spinelli', 'Iuzzolini - Uvaggio: Gaglioppo', 17.00, 12, 1, '/Foto_Vini_FINALE/Principe Spinelli - Iuzzolini_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Artino', 'Iuzzolini - Uvaggio: Gaglioppo, Magliocco', 18.00, 12, 1, '/Foto_Vini_FINALE/Artino - Iuzzolini_3.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Maradea', 'Iuzzolini - Uvaggio: Gaglioppo', 32.00, 12, 1, '/Foto_Vini_FINALE/Maradea - Iuzzolini_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Paternum', 'Iuzzolini - Uvaggio: Magliocco', 70.00, 12, 1, '/Foto_Vini_FINALE/Paternum - Iuzzolini_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Muranera', 'Iuzzolini - Uvaggio: Magliocco, Gaglioppo, Cabernet, Merlot', 28.00, 12, 1, '/Foto_Vini_FINALE/Muranera - Iuzzolini_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Britto', 'Colacino - Uvaggio: Arvino, Greco, Magliocco, Nerello', 31.00, 12, 1, '/Foto_Vini_FINALE/Britto - Colacino_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Kaulos', 'Terre del Gufo - Uvaggio: Calabrese, Magliocco,Greco, Cabernet', 27.00, 12, 1, '/Foto_Vini_FINALE/Kaulos - Terre del Gufo_2.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Portapiana', 'Terre del Gufo - Uvaggio: Magliocco', 26.00, 12, 1, '/Foto_Vini_FINALE/Portapiana - Terre del Gufo_1.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Timpamara', 'Terre del Gufo - Uvaggio: Syrah', 50.00, 12, 1, '/Foto_Vini_FINALE/Timpamara - Terre del Gufo_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Estremo', 'Terre del Gufo - Uvaggio: Magliocco', 29.00, 12, 1, '/Foto_Vini_FINALE/Estremo - Terre del Gufo_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('1480 L'' Inizio', 'Odoardi - Uvaggio: Aglianico, Magliocco', 21.00, 12, 1, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Terra da Mia', 'Odoardi - Uvaggio: Magliocco, Gaglioppo,Nerello, Greco', 24.00, 12, 1, '/Foto_Vini_FINALE/Terra da Mia - Odoardi_1.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('GB', 'Odoardi - Uvaggio: Magliocco, Gaglioppo, Nerello, Greco', 50.00, 12, 1, '/Foto_Vini_FINALE/GB - Odoardi_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Zero 1l.', 'Brigante - Uvaggio: Gaglioppo', 30.00, 12, 1, '/Foto_Vini_FINALE/Zero 1l. - Brigante_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Cirò Etafe', 'Brigante - Uvaggio: Gaglioppo', 14.00, 12, 1, '/Foto_Vini_FINALE/Cirò Etafe - Brigante_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Cervinago', 'Agostino Cerchiara - Uvaggio: Magliocco', 17.00, 12, 1, '/Foto_Vini_FINALE/Cervinago - Agostino Cerchiara_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Lamantino', 'Agostino Cerchiara - Uvaggio: Magliocco', 29.00, 12, 1, '/Foto_Vini_FINALE/Lamantino - Agostino Cerchiara_2.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Passariello', 'Cantine dei Briganti - Uvaggio: Aglianico', 16.00, 12, 1, '/Foto_Vini_FINALE/Passariello - Cantine dei Briganti_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Eleuteria', 'Tenute del Travalle - Uvaggio: Nerello Mascalese', 60.00, 12, 1, '/Foto_Vini_FINALE/Eleuteria - Tenute del Travalle_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Fra Antonio', 'Nesci - Uvaggio: Nerello, Nocera, Merlot,', 30.00, 12, 1, '/Foto_Vini_FINALE/Fra Antonio - Nesci_1.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Chapeaux', 'Nesci - Uvaggio: Merlot', 16.00, 12, 1, '/Foto_Vini_FINALE/Chapeaux - Nesci_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Catà', 'I Greco - Uvaggio: Gaglioppo', 17.00, 12, 1, '/Foto_Vini_FINALE/Catà - I Greco_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Masino', 'I Greco - Uvaggio: Nerello', 30.00, 12, 1, '/Foto_Vini_FINALE/Masino - I Greco_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Tumàsino', 'I Greco - Uvaggio: Gaglioppo', 30.00, 12, 1, '/Foto_Vini_FINALE/Tumàsino - I Greco_3.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Cires', 'Masseria Falvo - Uvaggio: Magliocco', 16.00, 12, 1, '/Foto_Vini_FINALE/Cires - Masseria Falvo_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Graneta', 'Masseria Falvo - Uvaggio: Magliocco', 20.00, 12, 1, '/Foto_Vini_FINALE/Graneta - Masseria Falvo_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Don Rosario', 'Masseria Falvo - Uvaggio: Magliocco', 35.00, 12, 1, '/Foto_Vini_FINALE/Don Rosario - Masseria Falvo_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Fervore', 'Terre di Balbia - Uvaggio: Magliocco', 28.00, 12, 1, '/Foto_Vini_FINALE/Fervore - Terre di Balbia_1.webp');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Blandus', 'Terre di Balbia - Uvaggio: Merlot', 32.00, 12, 1, '/Foto_Vini_FINALE/Blandus - Terre di Balbia_1.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Rosso Viola', 'Cantine Viola - Uvaggio: Magliocco', 18.00, 12, 1, '/Foto_Vini_FINALE/Rosso Viola - Cantine Viola_1.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Rinni', 'Cantine Viola - Uvaggio: Magliocco', 17.00, 12, 1, '/Foto_Vini_FINALE/Rinni - Cantine Viola_1.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Il Cabernet', 'Serragiumenta - Uvaggio: Cabernet S.', 14.00, 12, 1, '/Foto_Vini_FINALE/Il Cabernet - Serragiumenta_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Maglianico', 'Serragiumenta - Uvaggio: Magliocco, Aglianico', 14.00, 12, 1, '/Foto_Vini_FINALE/Maglianico - Serragiumenta_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Soprano dello Ionio', 'Tenute del Castello - Uvaggio: Aglianico', 32.00, 12, 1, '/Foto_Vini_FINALE/Soprano dello Ionio - Tenute del Castello_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Narobio', 'Tenute del Castello - Uvaggio: Aglianico', 22.00, 12, 1, '/Foto_Vini_FINALE/Narobio - Tenute del Castello_1.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Neostos', 'Spiriti Ebbri - Uvaggio: Guarnaccia, Greco, Merlot', 31.00, 12, 1, '/Foto_Vini_FINALE/Neostos - Spiriti Ebbri_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Appianum', 'Spiriti Ebbri - Uvaggio: Magliocco, Gaglioppo, Greco, Nerello', 44.00, 12, 1, '/Foto_Vini_FINALE/Appianum - Spiriti Ebbri_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Amarone Campo Dei Gigli', 'Tenute Sant''Antonio - Uvaggio: Corvina,Rondinella,Croatina,Oseleta', 90.00, 12, 1, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Telos Amarone Bio', 'Tenute Sant''Antonio - Uvaggio: Corvina,Rondinella,Croatina,Oseleta', 72.00, 12, 1, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Ripasso Monte Garbi', 'Tenute Sant''Antonio - Uvaggio: Corvina,Rondinella,Croatina,Oseleta', 25.00, 12, 1, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Amarone (Roccolo Grassi)', 'Roccolo Grassi - Uvaggio: Corvina, Corvinone,Rondinella,Croat.', 80.00, 12, 1, '/Foto_Vini_FINALE/Amarone - Roccolo Grassi_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Ripasso (Rocca Sveva)', 'Rocca Sveva - Uvaggio: Corvina, Molinara, Rondinella', 28.00, 12, 1, '/Foto_Vini_FINALE/Ripasso - Rocca Sveva_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Amarone (Rocca Sveva)', 'Rocca Sveva - Uvaggio: Corvina, Molinara, Rondinella', 65.00, 12, 1, '/Foto_Vini_FINALE/Amarone - Rocca Sveva_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Ripasso MariaBella', 'Massimago - Uvaggio: Corvina, Molinara, Rondinella', 31.00, 12, 1, '/Foto_Vini_FINALE/Ripasso MariaBella - Massimago_3.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Amarone (Massimago)', 'Massimago - Uvaggio: Corvina, Corvinone, Rondinella', 70.00, 12, 1, '/Foto_Vini_FINALE/Amarone - Massimago_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Amarone Gastone', 'Massimago - Uvaggio: Corvina, Corvinone, Rondinella', 65.00, 12, 1, '/Foto_Vini_FINALE/Amarone Gastone - Massimago_3.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Cinque Autoctoni', 'Fantini - Uvaggio: Montepu., Prim.,Sangi.,Negroa.,Malva.', 60.00, 12, 1, '/Foto_Vini_FINALE/Cinque Autoctoni - Fantini_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Three Dreamers', 'Fantini - Uvaggio: Montepulciano', 75.00, 12, 1, '/Foto_Vini_FINALE/Three Dreamers - Fantini_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Tancredi Dolce&Gabbana', 'Donna Fugata - Uvaggio: Nero D''Avola, Cabernet S., Tannat', 45.00, 12, 1, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Etna Cuor Di Lava D&G', 'Donna Fugata - Uvaggio: Nerello Mascalese', 80.00, 12, 1, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Etna Rosso', 'Donna Fugata - Uvaggio: Nerello Mascalese', 30.00, 12, 1, '/Foto_Vini_FINALE/Etna Rosso - Donna Fugata_1.webp');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Floramundi', 'Donna Fugata - Uvaggio: Cerasuolo', 24.00, 12, 1, '/Foto_Vini_FINALE/Floramundi - Donna Fugata_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Bell''Assai', 'Donna Fugata - Uvaggio: Frappato', 24.00, 12, 1, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Sedarà', 'Donna Fugata - Uvaggio: Nero D''Avola', 15.00, 12, 1, '/Foto_Vini_FINALE/Sedarà - Donna Fugata_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Barolo (Pio Cesare)', 'Pio Cesare - Uvaggio: Nebbiolo', 90.00, 12, 1, '/Foto_Vini_FINALE/Barolo - Pio Cesare_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Barbera D''alba (Pio Cesare)', 'Pio Cesare - Uvaggio: Barbera', 25.00, 12, 1, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Dolcetto D''alba (Pio Cesare)', 'Pio Cesare - Uvaggio: Dolcetto', 25.00, 12, 1, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Barolo (Prunotto)', 'Prunotto - Uvaggio: Nebbiolo', 60.00, 12, 1, '/Foto_Vini_FINALE/Barolo - Prunotto_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Barbaresco (Prunotto)', 'Prunotto - Uvaggio: Nebbiolo', 50.00, 12, 1, '/Foto_Vini_FINALE/Barbaresco - Prunotto_1.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Barbera D''Alba (Prunotto)', 'Prunotto - Uvaggio: Barbera', 22.00, 12, 1, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Dolcetto D''Alba (Prunotto)', 'Prunotto - Uvaggio: Dolcetto', 20.00, 12, 1, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Nebbiolo Occhietti', 'Prunotto - Uvaggio: Nebbiolo', 27.00, 12, 1, '/Foto_Vini_FINALE/Nebbiolo Occhietti - Prunotto_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Nizza Bansella', 'Prunotto - Uvaggio: Barbera', 24.00, 12, 1, '/Foto_Vini_FINALE/Nizza Bansella - Prunotto_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Fralù', 'Bruno Rocca - Uvaggio: Nebbiolo', 35.00, 12, 1, '/Foto_Vini_FINALE/Fralù - Bruno Rocca_3.webp');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Barbaresco (Bruno Rocca)', 'Bruno Rocca - Uvaggio: Nebbiolo', 70.00, 12, 1, '/Foto_Vini_FINALE/Barbaresco - Bruno Rocca_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Bonarda', 'Bisi - Uvaggio: Bonarda', 16.00, 12, 1, '/Foto_Vini_FINALE/Bonarda - Bisi_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Corte del Lupo', 'Ca'' del Bosco - Uvaggio: Merlot, Cabernet F.eS., Carmenérè', 35.00, 12, 1, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Chianti Gallo Nero', 'Panaretta - Uvaggio: Sangiovese', 24.00, 12, 1, '/Foto_Vini_FINALE/Chianti Gallo Nero - Panaretta_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Noisy', 'Petrognano - Uvaggio: Syrah', 31.00, 12, 1, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Chianti Meme', 'Petrognano - Uvaggio: Sangiovese', 18.00, 12, 1, '/Foto_Vini_FINALE/Chianti Meme - Petrognano_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Petrognano Riserva', 'Petrognano - Uvaggio: Sangiovese', 30.00, 12, 1, '/Foto_Vini_FINALE/Petrognano Riserva - Petrognano_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Chianti (Banfi)', 'Banfi - Uvaggio: Sangiovese', 14.00, 12, 1, '/Foto_Vini_FINALE/Chianti - Banfi_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Stil Novo', 'Banfi - Uvaggio: Sangiovese', 15.00, 12, 1, '/Foto_Vini_FINALE/Stil Novo - Banfi_3.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Rosso Montalcino (Banfi)', 'Banfi - Uvaggio: Sangiovese', 25.00, 12, 1, '/Foto_Vini_FINALE/Rosso Montalcino - Banfi_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Rosso Montalcino (Cencioni)', 'Cencioni - Uvaggio: Sangiovese', 28.00, 12, 1, '/Foto_Vini_FINALE/Rosso Montalcino - Cencioni_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Brunello Poggio alle M.', 'Banfi - Uvaggio: Sangiovese', 75.00, 12, 1, '/Foto_Vini_FINALE/Brunello Poggio alle M. - Banfi_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Brunello (Cencioni)', 'Cencioni - Uvaggio: Sangiovese', 65.00, 12, 1, '/Foto_Vini_FINALE/Brunello - Cencioni_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Solaia', 'Antinori - Uvaggio: Sangiovese, Cabernet F. e S.', 480.00, 12, 1, '/Foto_Vini_FINALE/Solaia - Antinori_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Brunello Pian delle V.', 'Antinori - Uvaggio: Sangiovese', 72.00, 12, 1, '/Foto_Vini_FINALE/Brunello Pian delle V. - Antinori_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Santa Cristina', 'Antinori - Uvaggio: Sangiovese, Merlot', 15.00, 12, 1, '/Foto_Vini_FINALE/Santa Cristina - Antinori_3.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Tignanello', 'Antinori - Uvaggio: Sangiovese, Cabernet S.', 150.00, 12, 1, '/Foto_Vini_FINALE/Tignanello - Antinori_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Conte Ugo', 'Antinori - Uvaggio: Cabernet, S. Merlot, Syrah', 60.00, 12, 1, '/Foto_Vini_FINALE/Conte Ugo - Antinori_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Guado al Tasso', 'Antinori - Uvaggio: Cabernet F, Cabernet S,Merlot', 180.00, 12, 1, '/Foto_Vini_FINALE/Guado al Tasso - Antinori_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Bolgheri Linda', 'Rossetti - Uvaggio: Sangiovese, Cabernet S, Merlot', 48.00, 12, 1, '/Foto_Vini_FINALE/Bolgheri Linda - Rossetti_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Morellino di Scanzano', 'Poliziano - Uvaggio: Sangiovese', 19.00, 12, 1, '/Foto_Vini_FINALE/Morellino di Scanzano - Poliziano_2.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Rosso Montepulciano', 'Poliziano - Uvaggio: Sangiovese, Merlot', 21.00, 12, 1, '/Foto_Vini_FINALE/Rosso Montepulciano - Poliziano_1.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Asinone', 'Poliziano - Uvaggio: Sangiovese', 85.00, 12, 1, '/Foto_Vini_FINALE/Asinone - Poliziano_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Le Stanze', 'Poliziano - Uvaggio: Cabernet S., Merlot', 80.00, 12, 1, '/Foto_Vini_FINALE/Le Stanze - Poliziano_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Pinot Nero (Tramin)', 'Tramin - Uvaggio: Pinot Nero', 34.00, 12, 1, '/Foto_Vini_FINALE/Pinot Nero - Tramin_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Vigna Cantaghel', 'Maso Cantaghel - Uvaggio: Pinot Nero', 36.00, 12, 1, '/Foto_Vini_FINALE/Vigna Cantaghel - Maso Cantaghel_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Pinot Nero (Falkestein)', 'Falkestein - Uvaggio: Pinot Nero', 38.00, 12, 1, '/Foto_Vini_FINALE/Pinot Nero - Falkestein_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Taurasi', 'Vesevo - Uvaggio: Aglianico', 50.00, 12, 1, '/Foto_Vini_FINALE/Taurasi - Vesevo_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('L''Atto', 'Cantine Del Notaio - Uvaggio: Aglianico', 17.00, 12, 1, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Il Repertorio', 'Cantine Del Notaio - Uvaggio: Aglianico', 30.00, 12, 1, '/Foto_Vini_FINALE/Il Repertorio - Cantine Del Notaio_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('La Firma', 'Cantine Del Notaio - Uvaggio: Aglianico', 45.00, 12, 1, '/Foto_Vini_FINALE/La Firma - Cantine Del Notaio_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('La Procura', 'Cantina Del Notaio - Uvaggio: Primitivo', 45.00, 12, 1, '/Foto_Vini_FINALE/La Procura - Cantina Del Notaio_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Terre D''Eclano', 'Quintodecimo - Uvaggio: Aglianico', 75.00, 12, 1, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Zero (solfiti)', 'Pipoli - Uvaggio: Aglianico', 18.00, 12, 1, '/Foto_Vini_FINALE/Zero (solfiti) - Pipoli_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Talò', 'S. Marzano - Uvaggio: Negroamaro', 15.00, 12, 1, '/Foto_Vini_FINALE/Talò - S. Marzano_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Sessantanni', 'S.Marzano - Uvaggio: Primitivo', 40.00, 12, 1, '/Foto_Vini_FINALE/Sessantanni - S.Marzano_1.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Primitivo di Manduria', 'Zolla - Uvaggio: Primitivo', 24.00, 12, 1, '/Foto_Vini_FINALE/Primitivo di Manduria - Zolla_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Susumaniello', 'Zolla - Uvaggio: Susumaniello', 24.00, 12, 1, '/Foto_Vini_FINALE/Susumaniello - Zolla_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Il Bacca', 'Lucarelli - Uvaggio: Primitivo', 30.00, 12, 1, '/Foto_Vini_FINALE/Il Bacca - Lucarelli_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Casale Vecchio', 'Fantini - Uvaggio: Montepulciano', 24.00, 12, 1, '/Foto_Vini_FINALE/Casale Vecchio - Fantini_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Opi', 'Fantini - Uvaggio: Montepulciano', 47.00, 12, 1, '/Foto_Vini_FINALE/Opi - Fantini_3.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Sagrantino RC2', 'Falesco - Uvaggio: Sagrantino', 65.00, 12, 1, '/Foto_Vini_FINALE/Sagrantino RC2 - Falesco_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Naniha', 'Tenute Perdarubia - Uvaggio: Cannonau', 23.00, 12, 1, '/Foto_Vini_FINALE/Naniha - Tenute Perdarubia_3.jpg');

INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Madre Goccia', 'Iuzzolini - Uvaggio: Greco, Chardonnay', 18.00, 12, 2, '/Foto_Vini_FINALE/Madre Goccia - Iuzzolini_3.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Donna Giovanna', 'Iuzzolini - Uvaggio: Greco', 28.00, 12, 2, '/Foto_Vini_FINALE/Donna Giovanna - Iuzzolini_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Arintha', 'Giraldi & Giraldi - Uvaggio: Greco', 14.00, 12, 2, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Prima Fila', 'Iuzzolini - Uvaggio: Pecorello', 17.00, 12, 2, '/Foto_Vini_FINALE/Prima Fila - Iuzzolini_2.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Pecorello', 'Ferrocinto - Uvaggio: Pecorello', 20.00, 12, 2, '/Foto_Vini_FINALE/Pecorello - Ferrocinto_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Neostos', 'Spiriti Ebbri - Uvaggio: Pecorello', 27.00, 12, 2, '/Foto_Vini_FINALE/Neostos - Spiriti Ebbri_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Petelia', 'Ceraudo - Uvaggio: Mantonico, Greco', 22.00, 12, 2, '/Foto_Vini_FINALE/Petelia - Ceraudo_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Baronè (bianco)', 'Magna Graecia - Uvaggio: Pecorello', 24.00, 12, 2, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Lepanio', 'Tenuta del Castello - Uvaggio: Greco, Traminer', 20.00, 12, 2, '/Foto_Vini_FINALE/Lepanio - Tenuta del Castello_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Pircoca', 'Masseria Falvo - Uvaggio: Guarnaccia, Traminer', 16.00, 12, 2, '/Foto_Vini_FINALE/Pircoca - Masseria Falvo_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Donna Filomena', 'Masseria Falvo - Uvaggio: Guarnaccia, Traminer', 24.00, 12, 2, '/Foto_Vini_FINALE/Donna Filomena - Masseria Falvo_2.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Critone', 'Librandi - Uvaggio: Chardonnay, Sauvignon B.', 15.00, 12, 2, '/Foto_Vini_FINALE/Critone - Librandi_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Santa chiara', 'Terre Nobili - Uvaggio: Greco', 15.00, 12, 2, '/Foto_Vini_FINALE/Santa chiara - Terre Nobili_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Gewurztraminer', 'Tramin - Uvaggio: Traminer', 24.00, 12, 2, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Muller Thurgau', 'Tramin - Uvaggio: Muller Thurgau', 17.00, 12, 2, '/Foto_Vini_FINALE/Muller Thurgau - Tramin_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Pinot Bianco', 'Tramin - Uvaggio: Pinot Bianco', 19.00, 12, 2, '/Foto_Vini_FINALE/Pinot Bianco - Tramin_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Ribolla Gialla', 'Di Lenardo - Uvaggio: Ribolla', 14.00, 12, 2, '/Foto_Vini_FINALE/Ribolla Gialla - Di Lenardo_2.jpg');

INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Ligrezza', 'Terre Di Balbia - Uvaggio: Gaglioppo', 22.00, 12, 3, '/Foto_Vini_FINALE/Ligrezza - Terre Di Balbia_1.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Savu', 'I Greco - Uvaggio: Gaglioppo', 14.00, 12, 3, '/Foto_Vini_FINALE/Savu - I Greco_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Terre Lontane', 'Librandi - Uvaggio: Gaglioppo', 16.00, 12, 3, '/Foto_Vini_FINALE/Terre Lontane - Librandi_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Grayasusi E. A.', 'Ceraudo - Uvaggio: Gaglioppo', 0.00, 12, 3, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Grayasusi E. R.', 'Ceraudo - Uvaggio: Gaglioppo', 26.00, 12, 3, '/Foto_Vini_FINALE/Grayasusi E. R. - Ceraudo_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Lumare', 'Iuzzolini - Uvaggio: Gaglioppo', 18.00, 12, 3, '/Foto_Vini_FINALE/Lumare - Iuzzolini_1.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Fermentino', 'Iuzzolini - Uvaggio: Gaglioppo', 16.00, 12, 3, '/Foto_Vini_FINALE/Fermentino - Iuzzolini_1.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Donna Giuliana', 'Giraldi & Giraldi - Uvaggio: Magliocco', 16.00, 12, 3, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Pian delle Rose', 'Tenute del Castello - Uvaggio: Aglianico', 22.00, 12, 3, '/Foto_Vini_FINALE/Pian delle Rose - Tenute del Castello_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Pesca Nera', 'Ippolito - Uvaggio: Greco Nero', 18.00, 12, 3, '/Foto_Vini_FINALE/Pesca Nera - Ippolito_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Zero 1l.', 'Brigante - Uvaggio: Gaglioppo', 30.00, 12, 3, '/Foto_Vini_FINALE/Zero 1l. - Brigante_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Cirò Manyari''', 'Brigante - Uvaggio: Gaglioppo', 15.00, 12, 3, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Greco Nero', 'Statti - Uvaggio: Greco Nero', 19.00, 12, 3, '/Foto_Vini_FINALE/Greco Nero - Statti_2.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Pollino Rosato', 'Ferrocinto - Uvaggio: Aglianico', 17.00, 12, 3, '/Foto_Vini_FINALE/Pollino Rosato - Ferrocinto_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Dolcedorme', 'Ferrocinto - Uvaggio: Aglianico', 18.00, 12, 3, '/Foto_Vini_FINALE/Dolcedorme - Ferrocinto_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Fili', 'Serracavallo - Uvaggio: Magliocco', 14.00, 12, 3, '/Foto_Vini_FINALE/Fili - Serracavallo_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Don Fili', 'Serracavallo - Uvaggio: Magliocco', 21.00, 12, 3, '/Foto_Vini_FINALE/Don Fili - Serracavallo_2.webp');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Gaudio Rosato', 'Magna Graecia - Uvaggio: Gaglioppo', 19.00, 12, 3, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Colli di Ginestra', 'Russo & Longo - Uvaggio: Gaglioppo, Calabrese', 14.00, 12, 3, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Il Pumo', 'San Marzano - Uvaggio: Negroamaro', 14.00, 12, 3, '/Foto_Vini_FINALE/Il Pumo - San Marzano_2.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Tramari', 'San Marzano - Uvaggio: Primitivo', 15.00, 12, 3, '/Foto_Vini_FINALE/Tramari - San Marzano_2.webp');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Scalabrone', 'Antinori - Uvaggio: Cabernet S, Merlot, Syrah', 24.00, 12, 3, '/Foto_Vini_FINALE/Scalabrone - Antinori_2.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Il Rogito', 'Cantine Del Notaio - Uvaggio: Aglianico', 26.00, 12, 3, '/Foto_Vini_FINALE/Il Rogito - Cantine Del Notaio_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Lagrein', 'Tramin - Uvaggio: Lagrein', 17.00, 12, 3, '/Foto_Vini_FINALE/Lagrein - Tramin_2.jpg');

INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('I Monaci 375ml', 'Giraldi & Giraldi - Uvaggio: Magliocco', 8.00, 12, 4, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Magliocco 375ml', 'Ferrocinto - Uvaggio: Magliocco', 8.00, 12, 4, '/Foto_Vini_FINALE/Magliocco 375ml - Ferrocinto_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Dolcedorme 375ml', 'Ferrocinto - Uvaggio: Aglianico', 8.00, 12, 4, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Terre Lontane 375ml', 'Librandi - Uvaggio: Gaglioppo', 15.00, 12, 4, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Gaglioppo 375ml', 'Statti - Uvaggio: Gaglioppo', 8.00, 12, 4, '/Foto_Vini_FINALE/Gaglioppo 375ml - Statti_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Armacia 375ml', 'Criserà - Uvaggio: Nocera', 9.00, 12, 4, '/Foto_Vini_FINALE/Armacia 375ml - Criserà_2.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Sedarà 375ml', 'Donna Fugata - Uvaggio: Nero D''avola', 7.00, 12, 4, '/Foto_Vini_FINALE/Sedarà 375ml - Donna Fugata_3.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Chianti 375ml', 'Banfi - Uvaggio: Sangiovese', 9.00, 12, 4, '/Foto_Vini_FINALE/Chianti 375ml - Banfi_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Rosso di Montepulciano 375ml', 'Poliziano - Uvaggio: Sangiovese', 9.00, 12, 4, '/Foto_Vini_FINALE/Rosso di Montepulciano 375ml - Poliziano_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('S. Cristina 375ml', 'Antinori - Uvaggio: Sangiovese, Merlot', 14.00, 12, 4, '/Foto_Vini_FINALE/S. Cristina 375ml - Antinori_1.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Morellino 375ml', 'Rocca Pesta - Uvaggio: Sangiovese', 9.00, 12, 4, '/Foto_Vini_FINALE/Morellino 375ml - Rocca Pesta_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Dolcetto D'' Alba 375ml', 'Prunotto - Uvaggio: Dolcetto D'' Alba', 10.00, 12, 4, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Pinot Nero 375ml', 'Tramin - Uvaggio: Pinot Nero', 17.00, 12, 4, '/Foto_Vini_FINALE/Pinot Nero 375ml - Tramin_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Lagrein 375ml', 'Tramin - Uvaggio: Lagrein', 12.00, 12, 4, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Timpa del Principe 375ml', 'Ferrocinto - Uvaggio: Greco, Mantonico', 8.00, 12, 4, '/Foto_Vini_FINALE/Timpa del Principe 375ml - Ferrocinto_3.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Critone 375ml', 'Librandi - Uvaggio: Chardonnay, Sauvignon', 9.00, 12, 4, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Gewurztraminer 375ml', 'Tramin - Uvaggio: Traminer', 14.00, 12, 4, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Prosecco Bandarossa 375ml', 'Bortolomiol - Uvaggio: Glera', 13.00, 12, 4, '/Foto_Vini_FINALE/Prosecco Bandarossa 375ml - Bortolomiol_1.webp');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Franciacorta Cuvèe Prestige 375ml', 'Ca'' del Bosco - Uvaggio: Pinot B., Pinot N., Chardonnay', 22.00, 12, 4, '/Foto_Vini_FINALE/placeholder.jpg');

INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Franciacorta Extra Bru', 'Ca'' del Bosco - Uvaggio: Pinot Bianco, Pinot Nero, Chardonnay', 50.00, 12, 5, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Franciacorta Saten', 'Ca'' del Bosco - Uvaggio: Pinot Bianco, Pinot Nero, Chardonnay', 80.00, 12, 5, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Asti', 'Banfi - Uvaggio: Moscato', 15.00, 12, 5, '/Foto_Vini_FINALE/Asti - Banfi_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Prosecco Prior Brut', 'Bortolomiol - Uvaggio: Glera', 22.00, 12, 5, '/Foto_Vini_FINALE/Prosecco Prior Brut - Bortolomiol_2.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Prosecco Miol', 'Bortolomiol - Uvaggio: Glera', 16.00, 12, 5, '/Foto_Vini_FINALE/Prosecco Miol - Bortolomiol_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Prosecco Bandarossa', 'Bortolomiol - Uvaggio: Glera', 22.00, 12, 5, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Prosecco Cartize', 'Bortolomiol - Uvaggio: Glera', 30.00, 12, 5, '/Foto_Vini_FINALE/Prosecco Cartize - Bortolomiol_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Grand Brut', 'Perrier-Jouët - Uvaggio: Pinot Nero, P. Meunier, Chardonnay', 70.00, 12, 5, '/Foto_Vini_FINALE/Grand Brut - Perrier-Jouët_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Blason Rosè', 'Perrier-Jouët - Uvaggio: Pinot Nero, P. Meunier, Chardonnay', 150.00, 12, 5, '/Foto_Vini_FINALE/Blason Rosè - Perrier-Jouët_3.jpg');

INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Moscato di Saracena', 'Viola - Uvaggio: Pinot Bianco, Pinot Nero, Chardonnay', 6.00, 12, 6, '/Foto_Vini_FINALE/Moscato di Saracena - Viola_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Pass the cookies', 'Di Lenardo - Uvaggio: Verduzzo, Resling', 6.00, 12, 6, '/Foto_Vini_FINALE/Pass the cookies - Di Lenardo_3.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Greco di Bianco', 'Baccellieri - Uvaggio: Greco', 5.00, 12, 6, '/Foto_Vini_FINALE/Greco di Bianco - Baccellieri_3.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Florus', 'Banfi - Uvaggio: Moscato', 4.00, 12, 6, '/Foto_Vini_FINALE/Florus - Banfi_1.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Recioto di Soave', 'Rocca Sveva - Uvaggio: Recioto', 4.00, 12, 6, '/Foto_Vini_FINALE/Recioto di Soave - Rocca Sveva_1.png');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Ben Rye', 'Donna Fugata - Uvaggio: Zibbibo', 5.00, 12, 6, '/Foto_Vini_FINALE/Ben Rye - Donna Fugata_1.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Muffato della Sala', 'Antinori - Uvaggio: Sauvignon, Grechetto,Resling,Traminer', 7.00, 12, 6, '/Foto_Vini_FINALE/Muffato della Sala - Antinori_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Marsala', 'Pellegrino - Uvaggio: Grillo, Insolia,Cataratto', 3.00, 12, 6, '/Foto_Vini_FINALE/placeholder.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Primitivo Dolce', 'Vignaioli del Salento - Uvaggio: Primitivo', 4.00, 12, 6, '/Foto_Vini_FINALE/Primitivo Dolce - Vignaioli del Salento_3.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Vin Santo (Poliziano)', 'Poliziano - Uvaggio: Trebiano', 6.00, 12, 6, '/Foto_Vini_FINALE/Vin Santo - Poliziano_2.jpg');
INSERT INTO prodotti (nome, descrizione, prezzo, quantita, categoria_id, url_immagine) VALUES ('Vin Santo (Antinori)', 'Antinori - Uvaggio: Trebiano, Malvasia', 4.00, 12, 6, '/Foto_Vini_FINALE/Vin Santo - Antinori_1.jpg');


INSERT INTO utenti (email, password, nome, cognome, ruolo) VALUES
('admin@negozio.com', '$2a$10$demopasswordhash', 'Mario', 'Rossi', 'ADMIN'),
('utente@test.com', '$2a$10$demopasswordhash', 'Giulia', 'Verdi', 'UTENTE');

INSERT INTO ordini (utente_id, totale, stato, indirizzo_spedizione, metodo_pagamento) VALUES
(2, 47.00, 'CONFERMATO', 'Via Roma 123, 80100 Napoli', 'Carta di Credito');

INSERT INTO elementi_ordine (ordine_id, prodotto_id, quantita, prezzo) VALUES
(1, 3, 1, 15.00), 
(1, 4, 1, 32.00); 