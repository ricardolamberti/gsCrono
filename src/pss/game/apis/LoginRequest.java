package pss.game.apis;

import java.util.Date;

public class LoginRequest {

  private String nombre;
  private String clave;
  private long idGame;

  // Getters y Setters
  public String getNombre() {
      return nombre;
  }

  public void setNombre(String nombre) {
      this.nombre = nombre;
  }

  public String getClave() {
      return clave;
  }

  public void setClave(String clave) {
      this.clave = clave;
  }

	public long getIdGame() {
		return idGame;
	}

	public void setIdGame(long idGame) {
		this.idGame = idGame;
	}
  
}
