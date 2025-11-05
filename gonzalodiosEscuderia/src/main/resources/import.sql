-- ------------------------------------------------------------------
-- SCRIPT SQL: 2 COCHES (Alonso y Verstappen), 3 CARRERAS Y COMPONENTES
-- (Valores de TIPO corregidos a MAYÚSCULAS para cumplir con el ENUM de H2)
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
    ('Motor de Combustión Interna', 'MOTOR', 8, 1, 0.87, 150.0, 1), 
    ('Turbo Compresor', 'TURBO', 4, 0, 1.0, 25.5, 1), 
    ('Batería ESS KERS v2', 'BATERIA', 4, 2, 0.5, 10.0, 1), 
    ('Caja de Cambios Xtrac Gp', 'CAJA_DE_CAMBIOS', 5, 0, 1.0, 0.0, 1),
    ('Neumáticos Pirelli Dura', 'NEUMATICOS', 1, 0, 1.0, 2.0, 1),
    ('Alerón Frontal Específico', 'ALERON', 20, 5, 0.75, 4.5, 1),
    ('Difusor Trasero RB', 'PARAGOLPES', 50, 0, 1.0, 0.0, 1),
    ('Suspensión Multilink Activo', 'SUSPENSION', 10, 3, 0.7, 0.0, 1),
    ('Power Steering Pro', 'DIRECCION', 50, 0, 1.0, 0.0, 1),
    
    -- Componentes instalados en Coche 2 (Alpine A522 / Alonso)
    ('Motor Renault E-Tech 24', 'MOTOR', 8, 1, 0.87, 145.0, 2), 
    ('Turbo Compresor VT02', 'TURBO', 4, 0, 1.0, 26.0, 2), 
    ('Batería ESS KERS v3', 'BATERIA', 4, 2, 0.5, 10.5, 2),
    ('Caja de Cambios Xtrac Gp Pro', 'CAJA_DE_CAMBIOS', 5, 0, 1.0, 0.0, 2),
    ('Neumáticos Pirelli Blando', 'NEUMATICOS', 1, 0, 1.0, 2.5, 2),
    ('Alerón Frontal Alpine', 'ALERON', 20, 5, 0.75, 4.8, 2),
    ('Difusor Trasero Alpine', 'PARAGOLPES', 50, 0, 1.0, 0.0, 2),
    ('Suspensión Doble Horquilla', 'SUSPENSION', 10, 3, 0.7, 0.0, 2),
    ('Dirección Hidráulica Espec.', 'DIRECCION', 50, 0, 1.0, 0.0, 2),
    
    -- Componentes en el Almacén (Solo se mantienen los 9 tipos válidos)
    ('Motor Ferrari PU24', 'MOTOR', 8, 0, 1.0, 155.0, NULL), 
    ('Turbo Compresor Spare', 'TURBO', 4, 0, 1.0, 25.0, NULL),
    ('Batería Auxiliar', 'BATERIA', 4, 0, 1.0, 8.0, NULL),
    ('Caja Secuencial Almacen', 'CAJA_DE_CAMBIOS', 10, 0, 1.0, 0.0, NULL), 
    ('Neumáticos Pirelli Intermedios', 'NEUMATICOS', 1, 0, 1.0, 2.2, NULL),
    ('Alerón Trasero Estándar', 'ALERON', 20, 0, 1.0, 3.0, NULL),
    ('Paragolpes Delantero Base', 'PARAGOLPES', 50, 0, 1.0, 0.0, NULL), 
    ('Suspensión de Recambio', 'SUSPENSION', 10, 0, 1.0, 0.0, NULL),
    ('Dirección Asistida Estandar', 'DIRECCION', 50, 0, 1.0, 0.0, NULL),
    
    -- Los siguientes componentes con tipos nuevos (MGU-H, Frenos, Chasis, etc.)
    -- deben eliminarse o su tipo debe cambiarse a uno de los 9 permitidos
    -- hasta que modifiques la definición de la columna `tipo` en Java/Hibernate.
    -- Mantengo algunos que son de tipos permitidos, pero con el formato corregido.
    ('Motor Mercedes-AMG PU24', 'MOTOR', 8, 0, 1.0, 148.0, NULL), 
    ('Turbo Compresor de Alto Flujo', 'TURBO', 4, 0, 1.0, 27.0, NULL),
    ('Batería ESS de Larga Duración', 'BATERIA', 4, 0, 1.0, 9.5, NULL),
    ('Caja de Cambios Secuencial GT', 'CAJA_DE_CAMBIOS', 10, 0, 1.0, 0.0, NULL),
    ('Neumáticos Pirelli Lluvia', 'NEUMATICOS', 1, 0, 1.0, 1.5, NULL),
    ('Alerón Delantero de Bajo Drag', 'ALERON', 20, 0, 1.0, 3.5, NULL),
    ('Alerón Trasero DRS Activo', 'ALERON', 20, 0, 1.0, 4.0, NULL),
    ('Difusor Venturi Extendido', 'PARAGOLPES', 50, 0, 1.0, 0.0, NULL),
    ('Suspensión Push-Rod de Competición', 'SUSPENSION', 10, 0, 1.0, 0.0, NULL),
    ('Power Steering Avanzada (Torque Sensor)', 'DIRECCION', 50, 0, 1.0, 0.0, NULL);
    -- Eliminados: MGU-H, MGU-K, Unidad de Control, Embrague, Eje Transmisión, Frenos, Chasis, Seguridad.

-- ------------------------------------------------------------------
-- 3. INSERTS DE CARRERAS (Carrera: 3 Entidades)
-- ------------------------------------------------------------------

INSERT INTO carrera (id, nombre, fecha, imagen) VALUES 
    (1, 'Gran Premio de Bahréin', '2025-03-02', 'https://de1.sportal365images.com/process/smp-bet365-images/news.bet365.com-br/01032023/aac55af1-a5ea-4db3-8e38-18c78ace58d8.jpg'),
    (2, 'Gran Premio de España', '2025-05-11', 'https://hips.hearstapps.com/hmg-prod/images/espan-a-mesa-de-trabajo-1-67b86e0891f96.png?resize=980:*'),
    (3, 'Gran Premio de Mónaco', '2025-05-25', 'https://www.f1cfa.com/images/post/C06-MONACO.JPG');

-- ------------------------------------------------------------------
-- 4. INSERTS DE RELACIÓN MANY-TO-MANY (carrera_coche)
-- ------------------------------------------------------------------

INSERT INTO carrera_coche (carreras_id, coche_id) VALUES 
    (1, 1),
    (1, 2), 
    (2, 1), 
    (2, 2), 
    (3, 1), 
    (3, 2); 

-- 5. REINICIAMOS EL CONTADOR DE ID PARA COCHE
ALTER TABLE coche ALTER COLUMN id RESTART WITH 3;