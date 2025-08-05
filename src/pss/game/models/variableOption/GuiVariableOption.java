package pss.game.models.variableOption;

import pss.core.services.records.JRecord;
import pss.core.win.JWin;
import pss.core.winUI.forms.JBaseForm;

public class GuiVariableOption extends JWin {



  /**
   * Constructor de la Clase
   */
  public GuiVariableOption() throws Exception {
  }


  public JRecord ObtenerDato()   throws Exception { return new BizVariableOption(); }
  public int GetNroIcono()   throws Exception { return 12; }
  public String GetTitle()   throws Exception { return "Opción"; }
  public Class<? extends JBaseForm> getFormBase() throws Exception { return FormVariableOption.class; }
  public String  getKeyField() throws Exception { return "id"; }
  public String  getDescripField() { return "valuequestion"; }
  public BizVariableOption GetcDato() throws Exception { return (BizVariableOption) this.getRecord(); }

 }
