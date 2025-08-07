
INSERT INTO turno_horario (hora_inicio, hora_fin) VALUES
                                                      ('17:00', '19:00'),
                                                      ('19:15', '21:15'),
                                                      ('21:30', '23:30');

INSERT INTO mesa (numero, capacidad) VALUES
                                         (1, 6),
                                         (2, 6),
                                         (3, 6),
                                         (4, 4);

INSERT INTO juego_para_jugar (nombre, numero_maximo, dificultad, categoria, duracion_aproximada, cantidad_disponible)
VALUES
    ('¡Arre Unicornio!', 8, 'Fácil', 'Roles ocultos', '30 minutos', 1),
    ('¡Extinción!', 6, 'Fácil', 'Familiar, set collection', '30 minutos', 1),
    ('1812 Argentina', 7, 'Fácil', 'Cooperativo, Familiar', '30 minutos', 1),
    ('3 Ring Circus', 4, 'Medio', 'Euro game', '2 hs', 1),
    ('7 Wonders Duel', 2, 'Medio', 'Euro game', '45 minutos', 1),
    ('7 Wonders: Architects', 7, 'Fácil', 'Familiar', '30 minutos', 1),
    ('Aberration', 4, 'Difícil', 'Cooperativo', '2 hs + seteo', 1),
    ('After the Virus', 3, 'Medio', 'Cooperativo, cartas', '1 h', 1),
    ('Aliados: por ahora...', 10, 'Fácil', 'Trivia', '1 h 40 minutos', 1),
    ('Alice is Missing', 5, 'Medio', 'Rol', '2 a 3 hs', 1),
    ('Alubari: A Nice Cup of Tea', 5, 'Medio', 'Euro game', '1 a 2 hs', 1),
    ('Amazonia', 2, 'Medio', 'Set collection, cartas', '30 minutos', 1),
    ('Amigos de mierda', 22, 'Fácil', 'Party game, humor', '30 minutos', 1),
    ('Anachrony', 4, 'Experto', 'Euro game', '2 a 4 hs', 1),
    ('Ankh: Gods of Egypt', 5, 'Difícil', 'Control de territorio', '2 a 4 hs', 1),
    ('ArcheOlogic', 4, 'Medio', 'Deducción', '45 minutos', 1),
    ('Ark Nova', 4, 'Difícil', 'Euro game', '2 a 3 horas', 1),
    ('Arkham Horror: El juego de cartas', 2, 'Difícil', 'Cooperativo, cartas', '2 hs', 1),
    ('Autumn', 2, 'Fácil', 'Formación de patrones', '20 minutos', 1),
    ('Aventureros y el Medallón de Ra', 6, 'Fácil', 'Familiar, cartas', '20 minutos', 1),
    ('Awkward Guests 2: The Berwick Cases', 4, 'Medio', 'Deducción', '40 minutos', 1),
    ('Azul', 4, 'Medio', 'Formación de patrones', '1 hora', 1);