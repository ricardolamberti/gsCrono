package pss.game.models.player;

import pss.core.services.records.JRecord;
import pss.core.win.JWin;
import pss.core.win.actions.BizAction;
import pss.core.win.submits.JAct;
import pss.core.win.submits.JActWins;
import pss.core.winUI.forms.JBaseForm;
import pss.game.models.gameplayer.GuiGamePlayers;

public class GuiPlayer extends JWin {



  /**
   * Constructor de la Clase
   */
  public GuiPlayer() throws Exception {
  }


  public JRecord ObtenerDato()   throws Exception { return new BizPlayer(); }
  public int GetNroIcono()   throws Exception { return 10013; }
  public String GetTitle()   throws Exception { return "Jugador"; }
  public Class<? extends JBaseForm> getFormBase() throws Exception { return FormPlayer.class; }
  public String  getKeyField() throws Exception { return "id_player"; }
  public String  getDescripField() { return "name"; }
  public BizPlayer GetcDato() throws Exception { return (BizPlayer) this.getRecord(); }

  public void createActionMap() throws Exception {
		this.addAction(10, "Suscripciones", null, 10027, false, false ).setHelp("Información de los juegos a los que se esta subscripto.");
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
	public GuiGamePlayers getSuscriptores() throws Exception {
		GuiGamePlayers segs= new GuiGamePlayers();
		segs.getRecords().addFilter("company", GetcDato().getCompany());
		segs.getRecords().addFilter("id_player", GetcDato().getIdplayer());
		segs.getRecords().addOrderBy("date","DESC");
		segs.SetVision("PLAYER");
		return segs;
		
	}
 }
