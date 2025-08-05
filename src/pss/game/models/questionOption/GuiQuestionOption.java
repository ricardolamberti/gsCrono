package pss.game.models.questionOption;

import pss.core.services.records.JRecord;
import pss.core.win.JWin;
import pss.core.winUI.forms.JBaseForm;

public class GuiQuestionOption extends JWin {



  /**
   * Constructor de la Clase
   */
  public GuiQuestionOption() throws Exception {
  }


  public JRecord ObtenerDato()   throws Exception { return new BizQuestionOption(); }
  public int GetNroIcono()   throws Exception { return 12; }
  public String GetTitle()   throws Exception { return "Opcion"; }
  public Class<? extends JBaseForm> getFormBase() throws Exception { return FormQuestionOption.class; }
  public String  getKeyField() throws Exception { return "id"; }
  public String  getDescripField() { return "answer_formula"; }
  public BizQuestionOption GetcDato() throws Exception { return (BizQuestionOption) this.getRecord(); }

 }
