package pss.game.models.difficulty;

import pss.core.services.records.JRecord;
import pss.core.win.JWin;
import pss.core.win.actions.BizAction;
import pss.core.win.submits.JAct;
import pss.core.win.submits.JActWins;
import pss.core.winUI.forms.JBaseForm;
import pss.game.models.gameplayer.GuiGamePlayers;
import pss.game.models.pointsDistribution.GuiPointDistributions;

public class GuiDifficulty extends JWin {



  /**
   * Constructor de la Clase
   */
  public GuiDifficulty() throws Exception {
  }


  public JRecord ObtenerDato()   throws Exception { return new BizDifficulty(); }
  public int GetNroIcono()   throws Exception { return 26; }
  public String GetTitle()   throws Exception { return "Dificultad"; }
  public Class<? extends JBaseForm> getFormBase() throws Exception { return FormDifficulty.class; }
  public String  getKeyField() throws Exception { return "id"; }
  public String  getDescripField() { return "name"; }
  public BizDifficulty GetcDato() throws Exception { return (BizDifficulty) this.getRecord(); }



  public void createActionMap() throws Exception {
		this.addAction(10, "Distribución puntos", null, 10027, false, false ).setHelp("Información de la distribucion de puntos segun nivel.");
		super.createActionMap();
	} 
  @Override
  public boolean OkAction(BizAction a) throws Exception {
  	return super.OkAction(a);
  }
  
  @Override
  public JAct getSubmitFor(BizAction a) throws Exception {
  	if (a.getId() == 10) return new JActWins(getSuscriptores());
    return super.getSubmitFor(a);
  }	
	public GuiPointDistributions getSuscriptores() throws Exception {
		GuiPointDistributions segs= new GuiPointDistributions();
		segs.getRecords().addFilter("company", GetcDato().getCompany());
		segs.getRecords().addFilter("difficulty_id", GetcDato().getId());
		segs.getRecords().addOrderBy("points_awarded","DESC");
		return segs;
		
	}
 }
