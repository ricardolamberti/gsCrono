package pss.game.models.questionVariable;

import pss.core.services.records.JRecord;
import pss.core.win.JWin;
import pss.core.winUI.forms.JBaseForm;

public class GuiQuestionVariable extends JWin {



  /**
   * Constructor de la Clase
   */
  public GuiQuestionVariable() throws Exception {
  }


  public JRecord ObtenerDato()   throws Exception { return new BizQuestionVariable(); }
  public int GetNroIcono()   throws Exception { return 5000; }
  public String GetTitle()   throws Exception { return "Pregunta Variable"; }
  public Class<? extends JBaseForm> getFormBase() throws Exception { return FormQuestionVariable.class; }
  public String  getKeyField() throws Exception { return "id"; }
  public String  getDescripField() { return "variable_id"; }
  public BizQuestionVariable GetcDato() throws Exception { return (BizQuestionVariable) this.getRecord(); }

 }
