package pss.game.models.questionVariable;

import pss.core.winUI.forms.JBaseForm;
import pss.core.ui.components.*;
import pss.core.win.JWin;

import pss.core.winUI.responsiveControls.JFormEditResponsive;

public class FormQuestionVariable extends JBaseForm {


private static final long serialVersionUID = 1726795836370L;



  /**
   * Constructor de la Clase
   */
  public FormQuestionVariable() throws Exception {
  }

  public GuiQuestionVariable getWin() { return (GuiQuestionVariable) getBaseWin(); }

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
