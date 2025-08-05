package pss.game.models.questionOption;

import pss.core.winUI.forms.JBaseForm;
import pss.core.ui.components.*;
import pss.core.win.JWin;

import pss.core.winUI.responsiveControls.JFormEditResponsive;

public class FormQuestionOption extends JBaseForm {


private static final long serialVersionUID = 1726833868286L;



  /**
   * Constructor de la Clase
   */
  public FormQuestionOption() throws Exception {
  }

  public GuiQuestionOption getWin() { return (GuiQuestionOption) getBaseWin(); }

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
