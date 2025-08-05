package  pss.game.gameBusiness;

import pss.common.regions.company.GuiNewCompany;
import pss.core.services.records.JRecord;
import pss.core.winUI.forms.JBaseForm;

public class GuiNewGameCompany extends GuiNewCompany {

  public GuiNewGameCompany() throws Exception {
  }
  
  public JRecord ObtenerDato() throws Exception {
  	return new BizNewGameCompany();
  }
  public BizNewGameCompany GetcDato() throws Exception { return (BizNewGameCompany) this.getRecord(); }

  public Class<? extends JBaseForm> getFormBase() throws Exception { 
  	return FormNewGameCompany.class; 
  }

}

