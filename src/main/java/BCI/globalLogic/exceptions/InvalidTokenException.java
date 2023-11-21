package BCI.globalLogic.exceptions;

public class InvalidTokenException extends RuntimeException {
  public InvalidTokenException(Exception exception) {
    super("Token invalido.", exception);
  }
}
