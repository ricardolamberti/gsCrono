package pss.game;

import pss.common.personalData.GuiPersonas;
import pss.common.security.BizUsuario;
import pss.common.security.GuiUsuarios;
import pss.core.win.JBaseWin;
import pss.core.win.JWin;
import pss.core.win.actions.BizAction;
import pss.core.win.modules.GuiModulo;
import pss.core.win.submits.JAct;
import pss.core.win.submits.JActQuery;
import pss.core.win.submits.JActUpdate;
import pss.core.win.submits.JActWins;
import pss.game.consola.GuiGameConsola;
import pss.game.gameBusiness.BizGameUser;
import pss.game.gameBusiness.GuiGameCompany;


public class GuiModuloGame extends GuiModulo {

	public GuiModuloGame() throws Exception {
		super();
		SetModuleName("Game");
		SetNroIcono(10055);
	}

	@Override
	public BizAction createDynamicAction() throws Exception {
		return this.addAction(1, "Game", null, 10055, true, true, true, "Group");
	}

	@Override
	public void createActionMap() throws Exception {
//		this.addAction(20, "Configuración Game", null, 10055, true, true).setBackAction(false);
		BizAction a = this.addAction(10, "Consola operacion", null, 10055, true, true);
		a.setBackAction(false);
		this.addAction(30, "Editar empresa", null, 10055, true, true);
		this.addAction(40, "Usuarios", null, 10055, true, true);

		this.addAction(100, "Personas", null, 10055, true, true);
	}

	@Override
	public JAct getSubmitFor(BizAction a) throws Exception {
		if (a.getId() == 10) return new JActQuery(getConsolaUsuario());
		if (a.getId() == 30) return new JActUpdate(getCompany(),JWin.ACTION_UPDATE);
		if (a.getId() == 40) return new JActWins(getUsuarios());
	
//		if (a.getId() == 80) return new JActWins(getGame());
		if (a.getId() == 100) return new JActWins(this.getPersonas());
//			
		return null;
	}
	public GuiGameCompany getCompany() throws Exception {
		GuiGameCompany c = new GuiGameCompany();
		c.setRecord(BizGameUser.getUsrGame().getObjCompany());
		return c;
	}
	

//	public GuiPlayers getPlayers() throws Exception {
//		GuiPlayers c = new GuiPlayers();
//		if (!BizUsuario.getUsr().IsAdminUser())
//			c.getRecords().addFilter("company", BizUsuario.getUsr().getCompany());
//		return c;
//	}
	public GuiUsuarios getUsuarios() throws Exception {
		GuiUsuarios c = new GuiUsuarios();
		if (!BizUsuario.getUsr().IsAdminUser())
			c.getRecords().addFilter("company", BizUsuario.getUsr().getCompany());
		return c;
	}
	
	public GuiPersonas getPersonas() throws Exception {
		GuiPersonas c = new GuiPersonas();
		if (!BizUsuario.getUsr().IsAdminUser())
			c.getRecords().addFilter("company", BizUsuario.getUsr().getCompany());
		return c;
	}
	

	public GuiGameConsola getConsolaUsuario() throws Exception {
		GuiGameConsola c = new GuiGameConsola();
		c.setToolbarForced(JBaseWin.TOOLBAR_LEFT);

		if (BizGameUser.getUsrGame().getGameConsola()!=null) {
			c.setRecord(BizGameUser.getUsrGame().getGameConsola());
			return c;
		}
		return c;
	}
	

}
