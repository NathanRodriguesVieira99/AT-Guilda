package com.edu.infnet.tp1.shared.errors.exceptions;

public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException() {
    super("Parametros inválidos!");
  }

  public ResourceNotFoundException(String message) {
    super(message);
  }
}
