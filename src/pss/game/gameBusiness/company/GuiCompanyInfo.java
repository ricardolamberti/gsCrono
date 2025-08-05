package  pss.game.gameBusiness.company;

import pss.common.security.BizUsuario;
import pss.core.services.records.JRecord;
import pss.core.win.JBaseWin;
import pss.core.win.JWin;
import pss.core.win.actions.BizAction;
import pss.core.win.submits.JAct;
import pss.core.winUI.forms.JBaseForm;

public class GuiCompanyInfo extends JWin {



  /**
   * Constructor de la Clase
   */
  public GuiCompanyInfo() throws Exception {
  }


  public JRecord ObtenerDato()   throws Exception { return new BizCompanyInfo(); }
  public int GetNroIcono()   throws Exception { return 10051; }
  public String GetTitle()   throws Exception { return "Empresa Info"; }
  public Class<? extends JBaseForm> getFormBase() throws Exception { return FormCompanyInfo.class; }
  public String  getKeyField() throws Exception { return "company"; }
  public String  getDescripField() { return "description"; }
  public BizCompanyInfo GetcDato() throws Exception { return (BizCompanyInfo) this.getRecord(); }

  public void createActionMap() throws Exception {
  	super.createActionMap();
	}  

	
  @Override
  public boolean OkAction(BizAction a) throws Exception {
  	return super.OkAction(a);
  }
	
	public JAct getSubmitFor(BizAction a) throws Exception {
		return super.getSubmitFor(a);
	}
	


 }
