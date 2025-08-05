package pss.game.models.variable;

import pss.core.services.JExec;
import pss.core.services.records.JRecord;
import pss.core.services.records.JRecords;
import pss.core.tools.collections.JIterator;
import pss.core.win.JWin;
import pss.core.win.actions.BizAction;
import pss.core.win.submits.JAct;
import pss.core.win.submits.JActWins;
import pss.core.winUI.forms.JBaseForm;
import pss.game.models.variableOption.BizVariableOption;
import pss.game.models.variableOption.GuiVariableOptions;

public class GuiVariable extends JWin {



  /**
   * Constructor de la Clase
   */
  public GuiVariable() throws Exception {
  }


  public JRecord ObtenerDato()   throws Exception { return new BizVariable(); }
  public int GetNroIcono()   throws Exception { return 5011; }
  public String GetTitle()   throws Exception { return "Variable"; }
  public Class<? extends JBaseForm> getFormBase() throws Exception { return FormVariable.class; }
  public String  getKeyField() throws Exception { return "id"; }
  public String  getDescripField() { return "name"; }
  public BizVariable GetcDato() throws Exception { return (BizVariable) this.getRecord(); }

  public void createActionMap() throws Exception {
		this.addAction(10, "Opciones", null, 10027, false, false ).setHelp("Información de las opciones.");
		super.createActionMap();
	} 
  @Override
  public boolean OkAction(BizAction a) throws Exception {
  	return super.OkAction(a);
  }
  
  @Override
  public JAct getSubmitFor(BizAction a) throws Exception {
  	if (a.getId() == 10) return new JActWins(getVariableOptions());
  	return super.getSubmitFor(a);
  }	
	public GuiVariableOptions getVariableOptions() throws Exception {
		GuiVariableOptions segs= new GuiVariableOptions();
		segs.getRecords().addFilter("company", GetcDato().getCompany());
		segs.getRecords().addFilter("variable_id", GetcDato().getId());
  	segs.getRecords().addOrderBy("id","DESC");
		return segs;
		
	}
	

 }
