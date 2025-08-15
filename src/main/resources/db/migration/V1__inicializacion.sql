-- Tabla de usuarios
CREATE TABLE usuario (
                         id SERIAL PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL,
                         email VARCHAR(100) UNIQUE NOT NULL,
                         contraseña VARCHAR(100) NOT NULL,
                         rol VARCHAR(20) NOT NULL -- CLIENTE, ADMIN
);

-- Tabla de mesas
CREATE TABLE mesa (
                      id SERIAL PRIMARY KEY,
                      numero INT NOT NULL UNIQUE,
                      capacidad INT NOT NULL,
                      disponible BOOLEAN DEFAULT TRUE
);

-- Horarios fijos de turnos (ej: 17–19, 19–21, 21–23)
CREATE TABLE turno_horario (
                               id SERIAL PRIMARY KEY,
                               hora_inicio TIME NOT NULL,
                               hora_fin TIME NOT NULL
);

-- Turnos concretos por día
CREATE TABLE turno_dia (
                           id SERIAL PRIMARY KEY,
                           fecha DATE NOT NULL,
                           dia_semana VARCHAR(10),
                           turno_horario_id INT NOT NULL,
                           FOREIGN KEY (turno_horario_id) REFERENCES turno_horario(id)
);

-- Reservas concretas hechas por usuarios
CREATE TABLE reserva (
                         id SERIAL PRIMARY KEY,
                         usuario_id INT NOT NULL,
                         mesa_id INT NOT NULL,
                         turno_dia_id INT NOT NULL,
                         estado VARCHAR(20) NOT NULL, -- RESERVADO, CANCELADO
                         FOREIGN KEY (usuario_id) REFERENCES usuario(id),
                         FOREIGN KEY (mesa_id) REFERENCES mesa(id),
                         FOREIGN KEY (turno_dia_id) REFERENCES turno_dia(id),
                         UNIQUE (mesa_id, turno_dia_id) -- No se puede reservar 2 veces la misma mesa en el mismo turno
);

-- Juegos disponibles para jugar en la ludoteca
CREATE TABLE juego_para_jugar (
                                  id SERIAL PRIMARY KEY,
                                  nombre VARCHAR(100) NOT NULL,
                                  descripcion TEXT,
                                  cantidad_disponible INT NOT NULL,
                                  imagen_url TEXT,
                                  numero_maximo INT,
                                  dificultad VARCHAR(20),
                                  categoria VARCHAR(100),
                                  duracion_aproximada VARCHAR(50)
);
-- Juegos disponibles para la venta
CREATE TABLE juego_para_vender (
                                   id SERIAL PRIMARY KEY,
                                   nombre VARCHAR(100) NOT NULL,
                                   descripcion TEXT,
                                   precio DECIMAL(10, 2) NOT NULL,
                                   stock INT NOT NULL,
                                   imagen_url TEXT,
                                   numero_maximo INT,
                                   dificultad VARCHAR(20),
                                   categoria VARCHAR(100),
                                   duracion_aproximada VARCHAR(50)
);