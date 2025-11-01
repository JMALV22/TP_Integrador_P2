
CREATE SCHEMA IF NOT EXISTS pacienteHistoriaClinica;
USE pacienteHistoriaClinica;

DROP TABLE IF EXISTS historia_clinica;
DROP TABLE IF EXISTS paciente;
DROP TABLE IF EXISTS generador;

CREATE TABLE paciente (
  id_paciente INT PRIMARY KEY AUTO_INCREMENT,
  eliminado BOOLEAN NOT NULL DEFAULT FALSE,        
  nombre VARCHAR(80) NOT NULL,
  apellido VARCHAR(80) NOT NULL,
  dni VARCHAR(15) NOT NULL UNIQUE,
  fecha_nacimiento DATE
);


CREATE TABLE historia_clinica (
  id_historia INT PRIMARY KEY AUTO_INCREMENT,
  eliminado BOOLEAN NOT NULL DEFAULT FALSE,        
  nro_historia VARCHAR(20) NOT NULL UNIQUE,
  grupo_sanguineo ENUM('A+','A-','B+','B-','AB+','AB-','O+','O-') NOT NULL,
  antecedentes TEXT,
  medicacion_actual TEXT,
  observaciones TEXT,
  id_paciente INT UNIQUE,
  FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE generador (
  id INT PRIMARY KEY AUTO_INCREMENT
);

-- 10 filas base
INSERT INTO generador () VALUES (),(),(),(),(),(),(),(),(),();

-- Duplicaciones para alcanzar ~10k registros
INSERT INTO generador (id) SELECT NULL FROM generador;
INSERT INTO generador (id) SELECT NULL FROM generador;
INSERT INTO generador (id) SELECT NULL FROM generador;
INSERT INTO generador (id) SELECT NULL FROM generador;
INSERT INTO generador (id) SELECT NULL FROM generador;
INSERT INTO generador (id) SELECT NULL FROM generador;
INSERT INTO generador (id) SELECT NULL FROM generador;
INSERT INTO generador (id) SELECT NULL FROM generador;
INSERT INTO generador (id) SELECT NULL FROM generador;
INSERT INTO generador (id) SELECT NULL FROM generador;

INSERT INTO paciente (eliminado, nombre, apellido, dni, fecha_nacimiento)
SELECT
  FALSE AS eliminado,
  CONCAT('PacNombre', id),
  CONCAT('PacApellido', id),
  LPAD(20000000 + id, 8, '0'),  -- DNIs de 8 dígitos
  DATE_ADD('1950-01-01', INTERVAL FLOOR(RAND()*24000) DAY)
FROM generador
LIMIT 10000;

INSERT INTO historia_clinica (
  eliminado,
  nro_historia,
  grupo_sanguineo,
  antecedentes,
  medicacion_actual,
  observaciones,
  id_paciente
)
SELECT
  FALSE AS eliminado,
  CONCAT('HC-', LPAD(p.id_paciente, 5, '0')),  
  ELT(FLOOR(RAND()*8)+1, 'A+','A-','B+','B-','AB+','AB-','O+','O-'),
  CONCAT('Antecedentes del paciente ', p.id_paciente),
  CONCAT('Medicacion actual del paciente ', p.id_paciente),
  CONCAT('Observaciones del paciente ', p.id_paciente),
  p.id_paciente
FROM paciente p;

DROP TABLE generador;

