package pss.game.models.playerAnswers;

import pss.core.services.records.JRecord;
import pss.core.win.JWin;
import pss.core.winUI.forms.JBaseForm;

public class GuiPlayerAnswer extends JWin {



  /**
   * Constructor de la Clase
   */
  public GuiPlayerAnswer() throws Exception {
  }


  public JRecord ObtenerDato()   throws Exception { return new BizPlayerAnswer(); }
  public int GetNroIcono()   throws Exception { return 912; }
  public String GetTitle()   throws Exception { return "Respuesta de Jugador"; }
  public Class<? extends JBaseForm> getFormBase() throws Exception { return FormPlayerAnswer.class; }
  public String  getKeyField() throws Exception { return "id"; }
  public String  getDescripField() { return "points_awarded"; }
  public BizPlayerAnswer GetcDato() throws Exception { return (BizPlayerAnswer) this.getRecord(); }

 }
