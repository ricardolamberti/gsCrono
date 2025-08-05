package pss.game.apis;

import java.util.Date;

public class SubscribirRequest {

    private String nombre;
    private String clave;
    private Date fecha;
    private Long idGame;
    private Long avatar;
    
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getIdGame() {
        return idGame;
    }

    public void setIdGame(Long idGame) {
        this.idGame = idGame;
    }

		public Long getAvatar() {
			return avatar;
		}

		public void setAvatar(Long avatar) {
			this.avatar = avatar;
		}
    
    
}

