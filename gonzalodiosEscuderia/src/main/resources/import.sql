-- ------------------------------------------------------------------
-- SCRIPT SQL: 2 COCHES (Alonso y Verstappen), 3 CARRERAS Y COMPONENTES
-- ------------------------------------------------------------------

-- 1. INSERTS DE COCHES (Coche: 2 Entidades)
-- ------------------------------------------------------------------

INSERT INTO coche (id, modelo, imagen, piloto, potencia) VALUES 
    (1, 'Red Bull RB20', 'https://upload.wikimedia.org/wikipedia/commons/d/d6/2022_Miami_GP_-_Red_Bull_RB18_of_Sergio_P%C3%A9rez.jpg', 'Max Verstappen', 750); 

INSERT INTO coche (id, modelo, imagen, piloto, potencia) VALUES 
    (2, 'Alpine A522', 'https://img.genial.ly/57c53c241f0adb11c0b9f35c/dcfc28bd-3504-45d6-9606-955750bb96db.jpeg', 'Fernando Alonso', 740);

-- ------------------------------------------------------------------
-- 2. INSERTS DE COMPONENTES
-- ------------------------------------------------------------------

INSERT INTO componente (nombre, tipo, limite_usos, veces_usado, estado, caballos, coche_id) VALUES 
    
    -- Componentes instalados en Coche 1 (Red Bull RB20 / Verstappen)
    ('Motor de Combustión Interna', 'Motor', 8, 1, 0.87, 150.0, 1), 
    ('Turbo Compresor', 'Turbo', 4, 0, 1.0, 25.5, 1), 
    ('Batería ESS KERS v2', 'Bateria', 4, 2, 0.5, 10.0, 1), 
    ('Caja de Cambios Xtrac Gp', 'Caja de Cambios', 5, 0, 1.0, 0.0, 1),
    ('Neumáticos Pirelli Dura', 'Neumaticos', 1, 0, 1.0, 2.0, 1),
    ('Alerón Frontal Específico', 'Aleron', 20, 5, 0.75, 4.5, 1),
    ('Difusor Trasero RB', 'Paragolpes', 50, 0, 1.0, 0.0, 1),
    ('Suspensión Multilink Activo', 'Suspension', 10, 3, 0.7, 0.0, 1),
    ('Power Steering Pro', 'Direccion', 50, 0, 1.0, 0.0, 1),
    
    -- Componentes instalados en Coche 2 (Alpine A522 / Alonso)
    ('Motor Renault E-Tech 24', 'Motor', 8, 1, 0.87, 145.0, 2), 
    ('Turbo Compresor VT02', 'Turbo', 4, 0, 1.0, 26.0, 2), 
    ('Batería ESS KERS v3', 'Bateria', 4, 2, 0.5, 10.5, 2),
    ('Caja de Cambios Xtrac Gp Pro', 'Caja de Cambios', 5, 0, 1.0, 0.0, 2),
    ('Neumáticos Pirelli Blando', 'Neumaticos', 1, 0, 1.0, 2.5, 2),
    ('Alerón Frontal Alpine', 'Aleron', 20, 5, 0.75, 4.8, 2),
    ('Difusor Trasero Alpine', 'Paragolpes', 50, 0, 1.0, 0.0, 2),
    ('Suspensión Doble Horquilla', 'Suspension', 10, 3, 0.7, 0.0, 2),
    ('Dirección Hidráulica Espec.', 'Direccion', 50, 0, 1.0, 0.0, 2),
    
    -- Componentes en el Almacén (Existentes)
    ('Motor Ferrari PU24', 'Motor', 8, 0, 1.0, 155.0, NULL), 
    ('Turbo Compresor Spare', 'Turbo', 4, 0, 1.0, 25.0, NULL),
    ('Batería Auxiliar', 'Bateria', 4, 0, 1.0, 8.0, NULL),
    ('Caja Secuencial Almacen', 'Caja de Cambios', 10, 0, 1.0, 0.0, NULL), 
    ('Neumáticos Pirelli Intermedios', 'Neumaticos', 1, 0, 1.0, 2.2, NULL),
    ('Alerón Trasero Estándar', 'Aleron', 20, 0, 1.0, 3.0, NULL),
    ('Paragolpes Delantero Base', 'Paragolpes', 50, 0, 1.0, 0.0, NULL), 
    ('Suspensión de Recambio', 'Suspension', 10, 0, 1.0, 0.0, NULL),
    ('Dirección Asistida Estandar', 'Direccion', 50, 0, 1.0, 0.0, NULL),
    
    -- Componentes en el Almacén (NUEVOS: 20 más)
    ('Motor Mercedes-AMG PU24', 'Motor', 8, 0, 1.0, 148.0, NULL), 
    ('MGU-H Recuperador de Calor', 'MGU-H', 4, 0, 1.0, 5.0, NULL),
    ('MGU-K Recuperador Cinético', 'MGU-K', 4, 0, 1.0, 20.0, NULL),
    ('Unidad de Control Electrónico (ECU)', 'Unidad de Control', 4, 0, 1.0, 0.0, NULL),
    ('Turbo Compresor de Alto Flujo', 'Turbo', 4, 0, 1.0, 27.0, NULL),
    ('Batería ESS de Larga Duración', 'Bateria', 4, 0, 1.0, 9.5, NULL),
    ('Caja de Cambios Secuencial GT', 'Caja de Cambios', 10, 0, 1.0, 0.0, NULL),
    ('Embrague de Carbono Multidisco', 'Embrague', 50, 0, 1.0, 0.0, NULL),
    ('Eje de Transmisión Reforzado', 'Eje Transmisión', 50, 0, 1.0, 0.0, NULL),
    ('Neumáticos Pirelli Lluvia', 'Neumaticos', 1, 0, 1.0, 1.5, NULL),
    ('Alerón Delantero de Bajo Drag', 'Aleron', 20, 0, 1.0, 3.5, NULL),
    ('Alerón Trasero DRS Activo', 'Aleron', 20, 0, 1.0, 4.0, NULL),
    ('Difusor Venturi Extendido', 'Paragolpes', 50, 0, 1.0, 0.0, NULL),
    ('Frenos de Carbono Cerámico', 'Frenos', 50, 0, 1.0, 0.0, NULL),
    ('Conductos de Freno Ventilados', 'Frenos', 50, 0, 1.0, 0.0, NULL),
    ('Suspensión Push-Rod de Competición', 'Suspension', 10, 0, 1.0, 0.0, NULL),
    ('Triángulos de Suspensión (Wishbones)', 'Suspension', 50, 0, 1.0, 0.0, NULL),
    ('Power Steering Avanzada (Torque Sensor)', 'Direccion', 50, 0, 1.0, 0.0, NULL),
    ('Chasis Monocasco de Fibra', 'Chasis', 1, 0, 1.0, 0.0, NULL),
    ('Extintor Automático (Sistema de Seguridad)', 'Seguridad', 50, 0, 1.0, 0.0, NULL);

-- ------------------------------------------------------------------
-- 3. INSERTS DE CARRERAS (Carrera: 3 Entidades)
-- ------------------------------------------------------------------

INSERT INTO carrera (id, nombre, fecha, imagen) VALUES 
    (1, 'Gran Premio de Bahréin', '2025-03-02', 'https://de1.sportal365images.com/process/smp-bet365-images/news.bet365.com-br/01032023/aac55af1-a5ea-4db3-8e38-18c78ace58d8.jpg'),
    (2, 'Gran Premio de España', '2025-05-11', 'https://hips.hearstapps.com/hmg-prod/images/espan-a-mesa-de-trabajo-1-67b86e0891f96.png?resize=980:*'),
    (3, 'Gran Premio de Mónaco', '2025-05-25', 'https://www.f1cfa.com/images/post/C06-MONACO.JPG');

-- ------------------------------------------------------------------
-- 4. INSERTS DE RELACIÓN MANY-TO-MANY (carrera_coche)
-- TODOS LOS VALORES DEBEN ESTAR SEPARADOS SOLO POR COMAS
-- ------------------------------------------------------------------

INSERT INTO carrera_coche (carreras_id, coche_id) VALUES 
    (1, 1), -- GP Bahréin: Coche 1
    (1, 2), -- GP Bahréin: Coche 2
    (2, 1), -- GP España: Coche 1
    (2, 2), -- GP España: Coche 2
    (3, 1), -- GP Mónaco: Coche 1
    (3, 2); -- GP Mónaco: Coche 2



ALTER TABLE coche ALTER COLUMN id RESTART WITH 3;