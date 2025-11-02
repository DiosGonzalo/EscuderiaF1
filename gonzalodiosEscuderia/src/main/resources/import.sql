-- ------------------------------------------------------------------
-- SCRIPT SQL: 2 COCHES (Alonso y Verstappen) Y 3 CARRERAS
-- ------------------------------------------------------------------

-- 1. INSERTS DE COCHES (Coche: 2 Entidades)
-- ------------------------------------------------------------------

-- Coche de Max Verstappen (ID 1)
INSERT INTO coche (id, modelo, imagen, piloto) VALUES 
    (1, 'Red Bull RB20', 'https://upload.wikimedia.org/wikipedia/commons/d/d6/2022_Miami_GP_-_Red_Bull_RB18_of_Sergio_P%C3%A9rez.jpg', 'Max Verstappen'); 

-- Coche de Fernando Alonso (ID 2)
INSERT INTO coche (id, modelo, imagen, piloto) VALUES 
    (2, 'Alpine A522', 'https://img.genial.ly/57c53c241f0adb11c0b9f35c/dcfc28bd-3504-45d6-9606-955750bb96db.jpeg', 'Fernando Alonso');


-- ------------------------------------------------------------------
-- 2. INSERTS DE COMPONENTES (Componente: 4 Entidades)
-- Componentes asociados a los Coches 1 y 2
-- ------------------------------------------------------------------

INSERT INTO componente (tipo, limite_usos, veces_usado, estado, coche_id) VALUES 
    -- Componentes para Coche 1 (Verstappen)
    ('Motor', 8, 1, 0.87, 1), 
    ('Turbo', 4, 0, 1.0, 1), 
    -- Componentes para Coche 2 (Alonso)
    ('Batería', 4, 2, 0.5, 2),
    ('Caja de Cambios', 5, 0, 1.0, 2);

-- ------------------------------------------------------------------
-- 3. INSERTS DE CARRERAS (Carrera: 3 Entidades)
-- ------------------------------------------------------------------

INSERT INTO carrera (id, nombre, fecha, imagen) VALUES 
    (1, 'Gran Premio de Bahréin', '2025-03-02', 'https://de1.sportal365images.com/process/smp-bet365-images/news.bet365.com-br/01032023/aac55af1-a5ea-4db3-8e38-18c78ace58d8.jpg'),
    (2, 'Gran Premio de España', '2025-05-11', 'https://hips.hearstapps.com/hmg-prod/images/espan-a-mesa-de-trabajo-1-67b86e0891f96.png?resize=980:*'),
    (3, 'Gran Premio de Mónaco', '2025-05-25', 'https://www.f1cfa.com/images/post/C06-MONACO.JPG');

-- ------------------------------------------------------------------
-- 4. INSERTS DE RELACIÓN MANY-TO-MANY (carrera_coche)
-- Todos los coches (ID 1 y 2) participan en todas las carreras (ID 1, 2 y 3).
-- ------------------------------------------------------------------

INSERT INTO carrera_coche (carreras_id, coche_id) VALUES 
    -- GP Bahréin (ID 1): Coche 1 y 2
    (1, 1), 
    (1, 2), 
    
    -- GP España (ID 2): Coche 1 y 2
    (2, 1), 
    (2, 2), 
    
    -- GP Mónaco (ID 3): Coche 1 y 2
    (3, 1), 
    (3, 2);