-- =========================
-- PRODOTTI DI DEFAULT
-- =========================

-- Uomo (sub_category_id = 6)
INSERT INTO product (id, description, manufacturer, name, price, quantity, seller, sub_category_id) VALUES
(1, 'Camicia slim fit elegante in cotone', 'Armani', 'Camicia Uomo Slim Fit', 79.90, 50, 1, 6),
(2, 'Pantaloni classici in lana', 'Gucci', 'Pantaloni Uomo Classic', 129.00, 40, 1, 6),
(3, 'Giacca sportiva in pelle', 'Dolce&Gabbana', 'Giacca Uomo Pelle', 299.00, 20, 1, 6);

-- Donna (sub_category_id = 7)
INSERT INTO product (id, description, manufacturer, name, price, quantity, seller, sub_category_id) VALUES
(4, 'Vestito estivo floreale', 'Zara', 'Vestito Donna Floreale', 59.90, 30, 1, 7),
(5, 'Borsa a tracolla in pelle', 'Prada', 'Borsa Donna Tracolla', 450.00, 15, 1, 7),
(6, 'Tailleur elegante in lino', 'Max Mara', 'Tailleur Donna Lino', 399.00, 10, 1, 7);

-- Bambini (sub_category_id = 8)
INSERT INTO product (id, description, manufacturer, name, price, quantity, seller, sub_category_id) VALUES
(7, 'T-shirt 100% cotone', 'H&M', 'T-shirt Bambino Blu', 14.99, 100, 1, 8),
(8, 'Pantaloncini sportivi', 'Adidas', 'Shorts Bambino Sport', 19.99, 80, 1, 8),
(9, 'Felpa con cappuccio', 'Nike', 'Felpa Bambino Hoodie', 34.99, 60, 1, 8);

-- Scarpe (sub_category_id = 9)
INSERT INTO product (id, description, manufacturer, name, price, quantity, seller, sub_category_id) VALUES
(10, 'Sneakers running leggere', 'Nike', 'Air Zoom Pegasus', 129.90, 40, 1, 9),
(11, 'Stivaletti in pelle', 'Timberland', 'Stivaletti Classici', 159.00, 35, 1, 9),
(12, 'Sandali estivi comodi', 'Birkenstock', 'Sandali Estivi', 69.90, 50, 1, 9);

-- Accessori (sub_category_id = 10)
INSERT INTO product (id, description, manufacturer, name, price, quantity, seller, sub_category_id) VALUES
(13, 'Orologio analogico in pelle', 'Fossil', 'Orologio Classico', 149.00, 25, 1, 10),
(14, 'Cintura in vera pelle', 'Calvin Klein', 'Cintura Uomo Nero', 59.00, 40, 1, 10),
(15, 'Occhiali da sole UV400', 'Ray-Ban', 'Occhiali Aviator', 179.00, 30, 1, 10);

-- =========================
-- IMMAGINI (2 per prodotto)
-- =========================

INSERT INTO image (id, content, product_id, review_id) VALUES
-- Uomo
(1,  x'89504E470D0A1A0A01', 1, NULL), (2,  x'89504E470D0A1A0A02', 1, NULL),
(3,  x'89504E470D0A1A0A03', 2, NULL), (4,  x'89504E470D0A1A0A04', 2, NULL),
(5,  x'89504E470D0A1A0A05', 3, NULL), (6,  x'89504E470D0A1A0A06', 3, NULL),

-- Donna
(7,  x'89504E470D0A1A0A07', 4, NULL), (8,  x'89504E470D0A1A0A08', 4, NULL),
(9,  x'89504E470D0A1A0A09', 5, NULL), (10, x'89504E470D0A1A0A0A', 5, NULL),
(11, x'89504E470D0A1A0A0B', 6, NULL), (12, x'89504E470D0A1A0A0C', 6, NULL),

-- Bambini
(13, x'89504E470D0A1A0A0D', 7, NULL), (14, x'89504E470D0A1A0A0E', 7, NULL),
(15, x'89504E470D0A1A0A0F', 8, NULL), (16, x'89504E470D0A1A0A10', 8, NULL),
(17, x'89504E470D0A1A0A11', 9, NULL), (18, x'89504E470D0A1A0A12', 9, NULL),

-- Scarpe
(19, x'89504E470D0A1A0A13', 10, NULL), (20, x'89504E470D0A1A0A14', 10, NULL),
(21, x'89504E470D0A1A0A15', 11, NULL), (22, x'89504E470D0A1A0A16', 11, NULL),
(23, x'89504E470D0A1A0A17', 12, NULL), (24, x'89504E470D0A1A0A18', 12, NULL),

-- Accessori
(25, x'89504E470D0A1A0A19', 13, NULL), (26, x'89504E470D0A1A0A1A', 13, NULL),
(27, x'89504E470D0A1A0A1B', 14, NULL), (28, x'89504E470D0A1A0A1C', 14, NULL),
(29, x'89504E470D0A1A0A1D', 15, NULL), (30, x'89504E470D0A1A0A1E', 15, NULL);
