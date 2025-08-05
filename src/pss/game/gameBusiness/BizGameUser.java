package pss.game.gameBusiness;

import pss.common.security.BizUsuario;
import pss.game.consola.BizGameConsola;

public class BizGameUser extends BizUsuario {
	
	BizGameConsola turnosConsola = null;

	public BizGameConsola getGameConsola() {
		return turnosConsola;
	}

	public void setGameConsola(BizGameConsola turnosConsola) {
		this.turnosConsola = turnosConsola;
	}
	
	

	public BizGameUser() throws Exception {
		super();
	}
	
	public synchronized static BizGameUser getUsrGame() {
		return (BizGameUser) BizUsuario.getUsr();
	}
	
	public static JCompanyBusinessGame getBusinessGame() throws Exception {
		return (JCompanyBusinessGame) BizUsuario.getUsr().getObjBusiness();
	}

	
	@Override
	public void processInsert() throws Exception {
		if (GetUsuario()==null || GetUsuario().equals("")) {
	  	BizUsuario usuarioCount = new BizUsuario();
	  	long cantUser = usuarioCount.selectCount();
	  	String newUserName = "RU_"+cantUser;
	  	SetUsuario(newUserName);
		}
	  
		if (!getMailUser().equals(""))
			BizUsuario.checkEmail(GetUsuario(),getMailUser(),true);
 		super.processInsert();
	}

	@Override
	public void processUpdate() throws Exception {
		if (!getMailUser().equals(""))
			BizUsuario.checkEmail(GetUsuario(),getMailUser(),true);
		super.processUpdate();
	}
	

}
