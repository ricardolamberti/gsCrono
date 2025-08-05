package pss.game.models.preGeneratedQuestions;

import pss.core.winUI.forms.JBaseForm;
import pss.core.ui.components.*;
import pss.core.win.JWin;

import pss.core.winUI.responsiveControls.JFormEditResponsive;

public class FormPreGeneratedQuestion extends JBaseForm {


private static final long serialVersionUID = 1726795931242L;



  /**
   * Constructor de la Clase
   */
  public FormPreGeneratedQuestion() throws Exception {
  }

  public GuiPreGeneratedQuestion getWin() { return (GuiPreGeneratedQuestion) getBaseWin(); }

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
