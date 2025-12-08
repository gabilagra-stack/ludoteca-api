UPDATE juego_para_jugar SET dificultad = 'FACIL'   WHERE lower(dificultad) IN ('facil','fácil');
UPDATE juego_para_jugar SET dificultad = 'MEDIA'   WHERE lower(dificultad) IN ('medio','media');
UPDATE juego_para_jugar SET dificultad = 'DIFICIL' WHERE lower(dificultad) IN ('dificil','difícil');
UPDATE juego_para_jugar SET dificultad = 'EXPERTO' WHERE lower(dificultad) IN ('experto');

-- asegurá integridad
ALTER TABLE juego_para_jugar DROP CONSTRAINT IF EXISTS chk_dificultad;
ALTER TABLE juego_para_jugar
    ADD CONSTRAINT chk_dificultad
        CHECK (dificultad IN ('FACIL','MEDIA','DIFICIL','EXPERTO'));