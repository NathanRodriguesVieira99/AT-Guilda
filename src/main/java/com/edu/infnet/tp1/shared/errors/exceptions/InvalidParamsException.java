package com.edu.infnet.tp1.shared.errors.exceptions;

public class InvalidParamsException extends RuntimeException {

  public InvalidParamsException() {
    super("Parametros inválidos!");
  }

  public InvalidParamsException(String message) {
    super(message);
  }
}
