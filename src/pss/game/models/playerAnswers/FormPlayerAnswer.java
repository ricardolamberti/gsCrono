package pss.game.models.playerAnswers;

import pss.core.winUI.forms.JBaseForm;
import pss.core.ui.components.*;
import pss.core.win.JWin;

import pss.core.winUI.responsiveControls.JFormEditResponsive;

public class FormPlayerAnswer extends JBaseForm {


private static final long serialVersionUID = 1726796014509L;



  /**
   * Constructor de la Clase
   */
  public FormPlayerAnswer() throws Exception {
  }

  public GuiPlayerAnswer getWin() { return (GuiPlayerAnswer) getBaseWin(); }

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
