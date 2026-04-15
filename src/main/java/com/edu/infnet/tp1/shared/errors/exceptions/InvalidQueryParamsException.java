package com.edu.infnet.tp1.shared.errors.exceptions;

public class InvalidQueryParamsException extends RuntimeException {

  public InvalidQueryParamsException() {
    super("Parametros de busca inválidos!");
  }

  public InvalidQueryParamsException(String message) {
    super(message);
  }
}
