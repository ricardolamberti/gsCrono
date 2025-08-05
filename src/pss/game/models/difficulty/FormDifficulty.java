package pss.game.models.difficulty;

import pss.core.winUI.forms.JBaseForm;
import pss.core.ui.components.*;
import pss.core.win.JWin;

import pss.core.winUI.responsiveControls.JFormEditResponsive;

public class FormDifficulty extends JBaseForm {


private static final long serialVersionUID = 1726795468455L;



  /**
   * Constructor de la Clase
   */
  public FormDifficulty() throws Exception {
  }

  public GuiDifficulty getWin() { return (GuiDifficulty) getBaseWin(); }

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
