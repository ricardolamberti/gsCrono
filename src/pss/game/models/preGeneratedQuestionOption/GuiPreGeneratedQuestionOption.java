package pss.game.models.preGeneratedQuestionOption;

import pss.core.services.records.JRecord;
import pss.core.win.JWin;
import pss.core.winUI.forms.JBaseForm;

public class GuiPreGeneratedQuestionOption extends JWin {



  /**
   * Constructor de la Clase
   */
  public GuiPreGeneratedQuestionOption() throws Exception {
  }


  public JRecord ObtenerDato()   throws Exception { return new BizPreGeneratedQuestionOption(); }
  public int GetNroIcono()   throws Exception { return 12; }
  public String GetTitle()   throws Exception { return "Opcion"; }
  public Class<? extends JBaseForm> getFormBase() throws Exception { return FormPreGeneratedQuestionOption.class; }
  public String  getKeyField() throws Exception { return "id"; }
  public String  getDescripField() { return "answer_formula"; }
  public BizPreGeneratedQuestionOption GetcDato() throws Exception { return (BizPreGeneratedQuestionOption) this.getRecord(); }

 }
