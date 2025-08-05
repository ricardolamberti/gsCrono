package  pss.game.gameBusiness;

import pss.common.regions.company.GuiCompany;
import pss.common.security.BizUsuario;
import pss.common.security.BizUsuarioRol;
import pss.common.security.GuiUsuario;
import pss.common.security.GuiUsuarios;
import pss.core.services.records.JRecord;
import pss.core.win.JBaseWin;
import pss.core.win.JWin;
import pss.core.win.JWins;
import pss.core.win.actions.BizAction;
import pss.core.win.submits.JAct;
import pss.core.win.submits.JActNew;
import pss.core.win.submits.JActSubmit;
import pss.core.win.submits.JActUpdate;
import pss.core.win.submits.JActWins;
import pss.core.winUI.forms.JBaseForm;

public class GuiGameCompany extends GuiCompany {

	// public static final String VISION_IN_COUNTRY_FORM = "VISION_IN_COUNTRY_FORM";

	// -------------------------------------------------------------------------//
	// Constructor de la Clase
	// -------------------------------------------------------------------------//
	public GuiGameCompany() throws Exception {
	}

	@Override
	public JRecord ObtenerDato() throws Exception {
		return new BizGameCompany();
	}

	@Override
	public Class<? extends JBaseForm> getFormBase() {
		return FormGameCompany.class;
	}

	@Override
	public String GetTitle() {
			return "Empresa";
	}

	@Override
	public void createActionMap() throws Exception {
		this.createActionQuery();
		this.createActionUpdate();
		this.createActionDelete();
		this.addAction(10, "Usuarios", null, 10074, true, false, true, "Group");
		this.addAction(15, "Nuevo Usuario", null, 10074, true, true).setOnlyInForm(true);
   	this.addAction(500, "Modelos Licencia", null, 912, true, true, true, "Group" );
//		this.addAction(50, "Renovar Licencia", null, 5133, true, true, true, "Group").setMulti(true);;
//		this.addAction(18, "Cambiar Logo", null, 5060, true, true, true, "Group" );
	}

	@Override
	public boolean OkAction(BizAction zAct) throws Exception {
		if (zAct.getId()==JWin.ACTION_DELETE) return BizUsuario.getUsr().isAdminUser();
		if (zAct.getId()==500) return BizUsuario.getUsr().isAdminUser();
			return super.OkAction(zAct);
	}
	
	@Override
	public JAct getSubmitFor(BizAction a) throws Exception {
		if (a.getId()==10) return new JActWins(this.getCompanyUsers());
		if (a.getId()==15) return new JActNew(this.getNewUser(), 4);

	return super.getSubmitFor(a);
	}

	@Override
	public String getFieldForeground(String zColName) throws Exception {

		return super.getFieldForeground(zColName);
	}
  

	public BizGameCompany GetccDato() throws Exception {
		return (BizGameCompany) this.getRecord();
	}
	public GuiUsuario getNewUser() throws Exception {
		GuiGameUser newUser = new GuiGameUser();
		newUser.getRecord().addFilter("company", this.GetcDato().getCompany());
		newUser.setDropListener(this);
		return newUser;
	}


	public JWins getCompanyUsers() throws Exception {
		GuiGameUsers wins = new GuiGameUsers();
		wins.getRecords().addFilter("company", this.GetcDato().getCompany());
		return wins;
	}
	
	@Override
	public JAct Drop(JBaseWin zBaseWin) throws Exception {
		if (zBaseWin instanceof GuiUsuario) {
			GuiUsuario usr = (GuiUsuario)zBaseWin;
			usr.GetcDato().processInsert();
			BizUsuarioRol rol = new BizUsuarioRol();
			rol.setCompany(usr.GetcDato().getCompany());
			rol.SetUsuario(usr.GetcDato().GetUsuario());
			rol.setRol(3);
			rol.execProcessInsert();
			
		}
		return super.Drop(zBaseWin);
	}
	
}
