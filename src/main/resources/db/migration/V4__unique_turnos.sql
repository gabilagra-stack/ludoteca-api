ALTER TABLE turno_dia
ADD CONSTRAINT uk_turno_dia_fecha_horario
UNIQUE (fecha, turno_horario_id);