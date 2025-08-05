package pss.game.apis;

public class SetInfoRequest {
  private String nombre;
  private long idGame;
  private long point;
  private long gems;
  private long level;
  
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public long getIdGame() {
		return idGame;
	}
	public void setIdGame(long idGame) {
		this.idGame = idGame;
	}
	public long getPoint() {
		return point;
	}
	public void setPoint(long point) {
		this.point = point;
	}
	public long getGems() {
		return gems;
	}
	public void setGems(long gems) {
		this.gems = gems;
	}
	public long getLevel() {
		return level;
	}
	public void setLevel(long level) {
		this.level = level;
	}

}
