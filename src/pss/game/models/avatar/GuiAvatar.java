package pss.game.models.avatar;

import pss.core.services.records.JRecord;
import pss.core.win.JWin;
import pss.core.winUI.forms.JBaseForm;

public class GuiAvatar extends JWin {



  /**
   * Constructor de la Clase
   */
  public GuiAvatar() throws Exception {
  }


  public JRecord ObtenerDato()   throws Exception { return new BizAvatar(); }
  public int GetNroIcono()   throws Exception { return 10027; }
  public String GetTitle()   throws Exception { return "Avatar"; }
  public Class<? extends JBaseForm> getFormBase() throws Exception { return FormAvatar.class; }
  public String  getKeyField() throws Exception { return "id_avatar"; }
  public String  getDescripField() { return "avatar"; }
  public BizAvatar GetcDato() throws Exception { return (BizAvatar) this.getRecord(); }

 }
