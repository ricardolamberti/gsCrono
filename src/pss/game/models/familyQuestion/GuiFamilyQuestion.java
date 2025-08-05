package pss.game.models.familyQuestion;

import pss.core.services.records.JRecord;
import pss.core.win.JWin;
import pss.core.win.actions.BizAction;
import pss.core.win.submits.JAct;
import pss.core.win.submits.JActWins;
import pss.core.winUI.forms.JBaseForm;
import pss.game.models.preGeneratedQuestions.GuiPreGeneratedQuestions;
import pss.game.models.questionType.GuiQuestionTypes;

public class GuiFamilyQuestion extends JWin {



  /**
   * Constructor de la Clase
   */
  public GuiFamilyQuestion() throws Exception {
  }


  public JRecord ObtenerDato()   throws Exception { return new BizFamilyQuestion(); }
  public int GetNroIcono()   throws Exception { return 10023; }
  public String GetTitle()   throws Exception { return "Familia de preguntas"; }
  public Class<? extends JBaseForm> getFormBase() throws Exception { return FormFamilyQuestion.class; }
  public String  getKeyField() throws Exception { return "id"; }
  public String  getDescripField() { return "name"; }
  public BizFamilyQuestion GetcDato() throws Exception { return (BizFamilyQuestion) this.getRecord(); }

  }
