package pss.game.models.level;

import pss.core.winUI.forms.JBaseForm;
import pss.core.ui.components.*;
import pss.core.win.JWin;

import pss.core.winUI.responsiveControls.JFormEditResponsive;

public class FormLevel extends JBaseForm {


private static final long serialVersionUID = 1726795379834L;



  /**
   * Constructor de la Clase
   */
  public FormLevel() throws Exception {
  }

  public GuiLevel getWin() { return (GuiLevel) getBaseWin(); }

  /**
   * Inicializacion Grafica
   */
  /**
   * Linkeo los campos con la variables del form
   */
  public void InicializarPanel( JWin zWin ) throws Exception {
  	super.InicializarPanel(zWin);
  } 
} 
