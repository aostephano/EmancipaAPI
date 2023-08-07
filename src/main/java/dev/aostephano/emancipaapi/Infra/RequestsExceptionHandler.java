package dev.aostephano.emancipaapi.Infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RequestsExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ExceptionDTO> threat404() {
    ExceptionDTO response = new ExceptionDTO("Data not found with provided UUID", 404);
    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(InternalError.class)
  public ResponseEntity<ExceptionDTO> threat500() {
    ExceptionDTO response = new ExceptionDTO("Internal Server Error", 404);
    return ResponseEntity.badRequest().body(response);
  }
}
