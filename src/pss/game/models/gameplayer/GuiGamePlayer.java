package pss.game.models.gameplayer;

import pss.core.services.JExec;
import pss.core.services.records.JRecord;
import pss.core.win.JWin;
import pss.core.win.actions.BizAction;
import pss.core.win.submits.JAct;
import pss.core.win.submits.JActSubmit;
import pss.core.win.submits.JActWins;
import pss.core.winUI.forms.JBaseForm;
import pss.game.models.preGeneratedQuestions.GuiPreGeneratedQuestions;

public class GuiGamePlayer extends JWin {



  /**
   * Constructor de la Clase
   */
  public GuiGamePlayer() throws Exception {
  }


  public JRecord ObtenerDato()   throws Exception { return new BizGamePlayer(); }
  public int GetNroIcono()   throws Exception { return 15000; }
  public String GetTitle()   throws Exception { return "Jugador en Juego"; }
  public Class<? extends JBaseForm> getFormBase() throws Exception { return FormGamePlayer.class; }
  public String  getKeyField() throws Exception { return "id_gameplayer"; }
  public String  getDescripField() { return "id_player"; }
  public BizGamePlayer GetcDato() throws Exception { return (BizGamePlayer) this.getRecord(); }



  public void createActionMap() throws Exception {
		this.addAction(10, "Preguntas", null, 10027, false, false ).setHelp("Información de las preguntas generadas.");
		this.addAction(20, "Generar preguntas", null, 10027, true, true ).setHelp("Genera las preguntas del siguiente nivel.");
		super.createActionMap();
	} 
  @Override
  public boolean OkAction(BizAction a) throws Exception {
  	return super.OkAction(a);
  }
  
  @Override
  public JAct getSubmitFor(BizAction a) throws Exception {
  	if (a.getId() == 10) return new JActWins(getPreGeneratedQuestions());
  	if (a.getId() == 20) return new JActSubmit(this) {
  		@Override
  		public void submit() throws Exception {
  			GetcDato().execProcessGenerateQuestions(null);
  			super.submit();
  		}
  	};
    return super.getSubmitFor(a);
  }	
	public GuiPreGeneratedQuestions getPreGeneratedQuestions() throws Exception {
		GuiPreGeneratedQuestions segs= new GuiPreGeneratedQuestions();
		segs.getRecords().addFilter("company", GetcDato().getCompany());
		segs.getRecords().addFilter("gameplayer_id", GetcDato().getIdgameplayer());
		segs.getRecords().addOrderBy("level_id","DESC");
		return segs;
		
	}
 }
