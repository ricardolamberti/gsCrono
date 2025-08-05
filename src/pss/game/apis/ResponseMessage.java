package pss.game.apis;

public class ResponseMessage {
  private String resultado;
  private String errorMessage;
  private String user;

  // Constructor
  public ResponseMessage(String resultado, String errorMessage, String user) {
      this.resultado = resultado;
      this.errorMessage = errorMessage;
      this.user = user;
  }

  // Getters y Setters
  public String getResultado() {
      return resultado;
  }

  public void setResultado(String resultado) {
      this.resultado = resultado;
  }

  public String getErrorMessage() {
      return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
      this.errorMessage = errorMessage;
  }

  public String getUser() {
      return user;
  }

  public void setUser(String user) {
      this.user = user;
  }
}
