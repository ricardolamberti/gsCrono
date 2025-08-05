package pss.game.models.pointsDistribution;

import pss.core.services.records.JRecord;
import pss.core.win.JWin;
import pss.core.winUI.forms.JBaseForm;

public class GuiPointDistribution extends JWin {



  /**
   * Constructor de la Clase
   */
  public GuiPointDistribution() throws Exception {
  }


  public JRecord ObtenerDato()   throws Exception { return new BizPointDistribution(); }
  public int GetNroIcono()   throws Exception { return 862; }
  public String GetTitle()   throws Exception { return "Distribucion de puntos"; }
  public Class<? extends JBaseForm> getFormBase() throws Exception { return FormPointDistribution.class; }
  public String  getKeyField() throws Exception { return "correct_percentage"; }
  public String  getDescripField() { return "correct_percentage"; }
  public BizPointDistribution GetcDato() throws Exception { return (BizPointDistribution) this.getRecord(); }

 }
