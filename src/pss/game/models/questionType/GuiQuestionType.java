package pss.game.models.questionType;

import pss.core.services.records.JRecord;
import pss.core.win.JWin;
import pss.core.win.actions.BizAction;
import pss.core.win.submits.JAct;
import pss.core.win.submits.JActWins;
import pss.core.winUI.forms.JBaseForm;
import pss.game.models.gameplayer.GuiGamePlayers;
import pss.game.models.question.GuiQuestion;
import pss.game.models.question.GuiQuestions;

public class GuiQuestionType extends JWin {



  /**
   * Constructor de la Clase
   */
  public GuiQuestionType() throws Exception {
  }


  public JRecord ObtenerDato()   throws Exception { return new BizQuestionType(); }
  public int GetNroIcono()   throws Exception { return 988; }
  public String GetTitle()   throws Exception { return "Tipo de pregunta"; }
  public Class<? extends JBaseForm> getFormBase() throws Exception { return FormQuestionType.class; }
  public String  getKeyField() throws Exception { return "id"; }
  public String  getDescripField() { return "name"; }
  public BizQuestionType GetcDato() throws Exception { return (BizQuestionType) this.getRecord(); }

  
 }
