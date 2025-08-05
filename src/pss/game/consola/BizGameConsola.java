package pss.game.consola;


import pss.common.security.BizUsuario;
import pss.core.services.fields.JLong;
import pss.core.services.records.JRecord;
import pss.game.models.avatar.BizAvatar;
import pss.game.models.game.BizGame;
import pss.game.models.player.BizPlayer;

public class BizGameConsola extends JRecord {
	JLong pPlayers = new JLong() {
		public void preset() throws Exception {
			pPlayers.setValue(getPlayers());
		};

	};


	public long getPlayers() throws Exception {
		BizPlayer x = new BizPlayer();
		x.addFilter("company", BizUsuario.getUsr().getCompany());
		return (long) x.selectCount();
	}
	
	JLong pGames = new JLong() {
		public void preset() throws Exception {
			pGames.setValue(getGames());
		};

	};


	public long getAvatar() throws Exception {
		BizAvatar x = new BizAvatar();
		x.addFilter("company", BizUsuario.getUsr().getCompany());
		return (long) x.selectCount();
	}

	JLong pAvatars = new JLong() {
		public void preset() throws Exception {
			pAvatars.setValue(getAvatars());
		};

	};


	public long getGames() throws Exception {
		BizGame x = new BizGame();
		x.addFilter("company", BizUsuario.getUsr().getCompany());
		return (long) x.selectCount();
	}


	public long getAvatars() throws Exception {
		BizAvatar x = new BizAvatar();
		x.addFilter("company", BizUsuario.getUsr().getCompany());
		return (long) x.selectCount();
	}

  /**
   * Constructor de la Clase
   */
  public BizGameConsola() throws Exception {
  }
  
 
	public void createProperties() throws Exception {
		this.addItem("player", pPlayers);
		this.addItem("game", pGames);
		this.addItem("avatar", pAvatars);
	}

	public void createFixedProperties() throws Exception {
		this.addFixedItem(FIELD, "player", "player", true, false, 15);
		this.addFixedItem(FIELD, "game", "game", true, false, 15);
		this.addFixedItem(FIELD, "avatar", "avatar", true, false, 15);
	}
 
}

