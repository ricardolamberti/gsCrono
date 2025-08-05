package pss.game.models.level;

import pss.core.services.records.JRecord;
import pss.core.win.JWin;
import pss.core.winUI.forms.JBaseForm;

public class GuiLevel extends JWin {



  /**
   * Constructor de la Clase
   */
  public GuiLevel() throws Exception {
  }


  public JRecord ObtenerDato()   throws Exception { return new BizLevel(); }
  public int GetNroIcono()   throws Exception { return 906; }
  public String GetTitle()   throws Exception { return "Nivel"; }
  public Class<? extends JBaseForm> getFormBase() throws Exception { return FormLevel.class; }
  public String  getKeyField() throws Exception { return "id"; }
  public String  getDescripField() { return "level_number"; }
  public BizLevel GetcDato() throws Exception { return (BizLevel) this.getRecord(); }

 }
