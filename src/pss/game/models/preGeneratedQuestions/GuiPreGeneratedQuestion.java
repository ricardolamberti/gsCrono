package pss.game.models.preGeneratedQuestions;

import pss.core.services.records.JRecord;
import pss.core.win.JWin;
import pss.core.win.actions.BizAction;
import pss.core.win.submits.JAct;
import pss.core.win.submits.JActWins;
import pss.core.winUI.forms.JBaseForm;
import pss.game.models.preGeneratedQuestionOption.GuiPreGeneratedQuestionOptions;
import pss.game.models.questionOption.GuiQuestionOptions;

public class GuiPreGeneratedQuestion extends JWin {



  /**
   * Constructor de la Clase
   */
  public GuiPreGeneratedQuestion() throws Exception {
  }


  public JRecord ObtenerDato()   throws Exception { return new BizPreGeneratedQuestion(); }
  public int GetNroIcono()   throws Exception { return 979; }
  public String GetTitle()   throws Exception { return "Pre generacion de pregunta"; }
  public Class<? extends JBaseForm> getFormBase() throws Exception { return FormPreGeneratedQuestion.class; }
  public String  getKeyField() throws Exception { return "id"; }
  public String  getDescripField() { return "rendered_question"; }
  public BizPreGeneratedQuestion GetcDato() throws Exception { return (BizPreGeneratedQuestion) this.getRecord(); }


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
  	if (a.getId() == 10) return new JActWins(getQuestionOptions());
    return super.getSubmitFor(a);
  }	
	public GuiPreGeneratedQuestionOptions getQuestionOptions() throws Exception {
		GuiPreGeneratedQuestionOptions segs= new GuiPreGeneratedQuestionOptions();
		segs.getRecords().addFilter("company", GetcDato().getCompany());
		segs.getRecords().addFilter("pre_question_id", GetcDato().getId());
		segs.getRecords().addOrderBy("id","ASC");
		return segs;
		
	}
 }
