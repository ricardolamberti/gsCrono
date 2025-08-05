package pss.game.models.avatar;

import pss.core.winUI.forms.JBaseForm;
import pss.core.ui.components.*;
import pss.core.win.JWin;

import pss.core.winUI.responsiveControls.JFormEditResponsive;

public class FormAvatar extends JBaseForm {


private static final long serialVersionUID = 1725461962887L;



  /**
   * Constructor de la Clase
   */
  public FormAvatar() throws Exception {
  }

  public GuiAvatar getWin() { return (GuiAvatar) getBaseWin(); }


} 
