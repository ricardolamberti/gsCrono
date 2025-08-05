package pss.game.models.question;

import pss.core.services.records.JRecord;
import pss.core.win.JWin;
import pss.core.win.actions.BizAction;
import pss.core.win.submits.JAct;
import pss.core.win.submits.JActWins;
import pss.core.winUI.forms.JBaseForm;
import pss.game.models.pointsDistribution.GuiPointDistributions;
import pss.game.models.questionOption.GuiQuestionOptions;

public class GuiQuestion extends JWin {



  /**
   * Constructor de la Clase
   */
  public GuiQuestion() throws Exception {
  }


  public JRecord ObtenerDato()   throws Exception { return new BizQuestion(); }
  public int GetNroIcono()   throws Exception { return 12; }
  public String GetTitle()   throws Exception { return "Pregunta"; }
  public Class<? extends JBaseForm> getFormBase() throws Exception { return FormQuestion.class; }
  public String  getKeyField() throws Exception { return "id"; }
  public String  getDescripField() { return "statement"; }
  public BizQuestion GetcDato() throws Exception { return (BizQuestion) this.getRecord(); }

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
	public GuiQuestionOptions getQuestionOptions() throws Exception {
		GuiQuestionOptions segs= new GuiQuestionOptions();
		segs.getRecords().addFilter("company", GetcDato().getCompany());
		segs.getRecords().addFilter("question_id", GetcDato().getId());
		segs.getRecords().addOrderBy("id","ASC");
		return segs;
		
	}
 }
