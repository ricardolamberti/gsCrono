package pss.game.models.player;

import java.util.Date;

import pss.core.services.JExec;
import pss.game.models.gameplayer.BizGamePlayer;

public class BizPlayer extends BizPlayerBase {



  /**
   * Constructor de la Clase
   */
  public BizPlayer() throws Exception {

  } 
  

  public void execProcessSubscribir(long game) throws Exception {
		JExec oExec = new JExec(this, "processSubscribir") {

			@Override
			public void Do() throws Exception {
				processSubscribir(game);
			}
		};
		oExec.execute();
	}
  public void processSubscribir(long game) throws Exception {
  	this.processInsert();
  	BizGamePlayer gamePlayer = new BizGamePlayer();
  	gamePlayer.setIdgame(game);
  	gamePlayer.setCompany(this.getCompany());
  	gamePlayer.setIdplayer(this.getIdplayer());
  	gamePlayer.setDate(new Date());
  	gamePlayer.setPoints(0);
  	gamePlayer.setGems(0);
  	gamePlayer.setLevel(0);
  	gamePlayer.processInsert();
  }
  

} 
