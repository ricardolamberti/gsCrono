package pss.game.apis;

public class ResponseGetinfo {
  private String resultado;
  private String errorMessage;
  private String user;
  private long point;
  private long gems;
  private long level;
  private long gameplayerid;

  // Constructor
  public ResponseGetinfo(String resultado, String errorMessage,long gameplayerid, String user, long point, long gems, long level) {
      this.resultado = resultado;
      this.errorMessage = errorMessage;
      this.user = user;
      this.point=point;
      this.gems=gems;;
      this.level=level;
      this.gameplayerid=gameplayerid;
  }

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public long getGameplayerid() {
		return gameplayerid;
	}

	public void setGameplayerid(long gameplayerid) {
		this.gameplayerid = gameplayerid;
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
