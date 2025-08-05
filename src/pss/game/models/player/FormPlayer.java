package pss.game.models.player;

import pss.core.winUI.forms.JBaseForm;
import pss.core.ui.components.*;
import pss.core.win.JWin;

import pss.core.winUI.responsiveControls.JFormEditResponsive;

public class FormPlayer extends JBaseForm {


private static final long serialVersionUID = 1725461840688L;



  /**
   * Constructor de la Clase
   */
  public FormPlayer() throws Exception {
  }

  public GuiPlayer getWin() { return (GuiPlayer) getBaseWin(); }


} 
