package pss.game.gameBusiness;

import pss.common.regions.company.BizCompanies;
import pss.common.regions.company.BizCompany;
import pss.common.regions.company.GuiCompanies;
import pss.common.regions.divitions.GuiPaises;
import pss.core.services.records.JRecords;
import pss.core.win.JWin;
import pss.core.win.actions.BizAction;
import pss.core.win.submits.JAct;
import pss.core.win.submits.JActNew;
import pss.core.win.submits.JActWins;
import pss.core.winUI.lists.JWinList;

public class GuiGameCompanies extends GuiCompanies {

	@Override
	public Class<? extends JWin> GetClassWin() {
		return GuiGameCompany.class;
	}

	public GuiGameCompanies() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public void createActionMap() throws Exception {
				super.createActionMap();
	}
	

	@Override
	public JAct getSubmit(BizAction a) throws Exception {
		if (a.getId()==1) return new JActNew(this.getNewGameCompany(), 0);
	
		return this.getSubmitFor(a);
	}

	public GuiNewGameCompany getNewGameCompany() throws Exception {
		GuiNewGameCompany newCompany=new GuiNewGameCompany();
		this.joinData(newCompany);
		return newCompany;
	}
	@Override
	public JRecords<BizCompany> ObtenerDatos() throws Exception {
		return new BizCompanies();
	}
	
	@Override
	public void ConfigurarColumnasLista(JWinList zLista) throws Exception {
		zLista.AddIcono("");
		zLista.AddColumnaLista("company");
		zLista.AddColumnaLista("description");


  }
	


}
