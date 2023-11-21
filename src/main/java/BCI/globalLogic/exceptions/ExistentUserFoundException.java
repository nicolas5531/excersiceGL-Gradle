package BCI.globalLogic.exceptions;

public class ExistentUserFoundException extends RuntimeException{
  public ExistentUserFoundException() {
    super("Usuario ya existente.");
  }
}
