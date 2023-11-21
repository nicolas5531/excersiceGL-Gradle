package BCI.globalLogic.exceptions;

public class ExistentUserFoundException extends RuntimeException{
  public ExistentUserFoundException() {
    super("The user entered is currently in use.");
  }
}
