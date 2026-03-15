package com.daniel.desafio_itau_vaga_99_junior.exeption;

import com.daniel.desafio_itau_vaga_99_junior.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleMensagemIlegivel(HttpMessageNotReadableException ex) {
        logger.warn("Requisição com corpo inválido: {}", ex.getMessage());
        return buildResponse(HttpStatus.BAD_REQUEST, "Requisição inválida", "O corpo da requisição está mal formatado ou contém tipos inválidos.");
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointer(NullPointerException ex) {
        logger.warn("Campo nulo encontrado: {}", ex.getMessage());
        return buildResponse(HttpStatus.BAD_REQUEST, "Campo obrigatório ausente", "Um ou mais campos obrigatórios estão nulos.");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        logger.warn("Argumento inválido: {}", ex.getMessage());
        return buildResponse(HttpStatus.UNPROCESSABLE_ENTITY, "Argumento inválido", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenerico(Exception ex) {
        logger.error("Erro inesperado: {}", ex.getMessage(), ex);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
    }

    private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String erro, String mensagem) {
        ErrorResponse body = new ErrorResponse(status.value(), erro, mensagem, OffsetDateTime.now());
        return ResponseEntity.status(status).body(body);
    }

}
