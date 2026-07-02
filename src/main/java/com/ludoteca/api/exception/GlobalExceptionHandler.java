package com.ludoteca.api.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private String traceIdOrNull() {
        return null;
    }

    private ApiErrorResponse base(HttpStatus status, String code, String message, HttpServletRequest req) {
        return ApiErrorResponse.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .code(code)
                .message(message)
                .path(req.getRequestURI())
                .traceId(traceIdOrNull())
                .build();
    }

    // excepción de negocio
    @ExceptionHandler(ReservaNoEncontradaException.class)
    public ResponseEntity<ApiErrorResponse> handleReservaNoEncontradaException(
            ReservaNoEncontradaException ex, HttpServletRequest req) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiErrorResponse body = base(status, ex.getCode(), ex.getMessage(), req);
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(JuegoNoEncontradoException.class)
    public ResponseEntity<ApiErrorResponse> handleJuegoNoEncontradoException(
            JuegoNoEncontradoException ex, HttpServletRequest req) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiErrorResponse body = base(status, ex.getCode(), ex.getMessage(), req);
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(EventoNoEncontradoException.class)
    public ResponseEntity<ApiErrorResponse> handleEventoNoEncontradoException(
            EventoNoEncontradoException ex, HttpServletRequest req) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiErrorResponse body = base(status, ex.getCode(), ex.getMessage(), req);
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(QueEstaPasandoNoEncontradoException.class)
    public ResponseEntity<ApiErrorResponse> handleQueEstaPasandoNoEncontradoException(
            QueEstaPasandoNoEncontradoException ex, HttpServletRequest req) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiErrorResponse body = base(status, ex.getCode(), ex.getMessage(), req);
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(JuegoDestacadoNoEncontradoException.class)
    public ResponseEntity<ApiErrorResponse> handleJuegoDestacadoNoEncontradoException(
            JuegoDestacadoNoEncontradoException ex, HttpServletRequest req) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiErrorResponse body = base(status, ex.getCode(), ex.getMessage(), req);
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(MesaNoEncontradaException.class)
    public ResponseEntity<ApiErrorResponse> handleMesaNoEncontradaException(
            MesaNoEncontradaException ex, HttpServletRequest req) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiErrorResponse body = base(status, ex.getCode(), ex.getMessage(), req);
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(TurnoNoEncontradoException.class)
    public ResponseEntity<ApiErrorResponse> handleTurnoNoEncontradoException(
            TurnoNoEncontradoException ex, HttpServletRequest req) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiErrorResponse body = base(status, ex.getCode(), ex.getMessage(), req);
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<ApiErrorResponse> handleUsuarioNoEncontradoExeption(
            UsuarioNoEncontradoException ex, HttpServletRequest req) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiErrorResponse body = base(status, ex.getCode(), ex.getMessage(), req);
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(MesaReservadaException.class)
    public ResponseEntity<ApiErrorResponse> handleMesaReservadaException(MesaReservadaException ex,
                                                                         HttpServletRequest req) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiErrorResponse body = base(status, ex.getCode(), ex.getMessage(), req);
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(FechaInvalidaException.class)
    public ResponseEntity<ApiErrorResponse> handleFechaInvalidaException(
            FechaInvalidaException ex, HttpServletRequest req) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiErrorResponse body = base(status, ex.getCode(), ex.getMessage(), req);
        return ResponseEntity.status(status).body(body);
    }

    // ✅ Validación @Valid en request body (DTO)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(
            MethodArgumentNotValidException ex, HttpServletRequest req) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        List<ApiErrorResponse.FieldErrorItem> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::toFieldErrorItem)
                .toList();

        ApiErrorResponse body = ApiErrorResponse.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .code("VALIDATION_ERROR")
                .message("Hay campos inválidos en la solicitud")
                .path(req.getRequestURI())
                .traceId(traceIdOrNull())
                .fieldErrors(fieldErrors)
                .build();

        return ResponseEntity.status(status).body(body);
    }

    private ApiErrorResponse.FieldErrorItem toFieldErrorItem(FieldError fe) {
        return ApiErrorResponse.FieldErrorItem.builder()
                .field(fe.getField())
                .message(fe.getDefaultMessage())
                .build();
    }

    // ✅ Tipos inválidos en params/path (ej: ?numeroMesa=abc)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex, HttpServletRequest req) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String msg = "Parámetro inválido: " + ex.getName();

        ApiErrorResponse body = base(status, "INVALID_PARAMETER", msg, req);
        return ResponseEntity.status(status).body(body);
    }

    // ✅ JSON mal formado / enum inválido en body / etc.
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleNotReadable(
            HttpMessageNotReadableException ex, HttpServletRequest req) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiErrorResponse body = base(status, "MALFORMED_JSON", "El cuerpo de la solicitud es inválido", req);
        return ResponseEntity.status(status).body(body);
    }

    // ✅ Fallback genérico (último recurso)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneric(
            Exception ex, HttpServletRequest req) {

        log.error("Error no controlado en {} {}",
                req.getMethod(), req.getRequestURI(), ex);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiErrorResponse body = base(status, "INTERNAL_ERROR", "Ocurrió un error inesperado", req);
        return ResponseEntity.status(status).body(body);
    }
}
