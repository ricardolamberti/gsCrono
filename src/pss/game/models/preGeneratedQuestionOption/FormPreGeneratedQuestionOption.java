package pss.game.models.preGeneratedQuestionOption;

import pss.core.winUI.forms.JBaseForm;
import pss.core.ui.components.*;
import pss.core.win.JWin;

import pss.core.winUI.responsiveControls.JFormEditResponsive;

public class FormPreGeneratedQuestionOption extends JBaseForm {


private static final long serialVersionUID = 1726929235025L;



  /**
   * Constructor de la Clase
   */
  public FormPreGeneratedQuestionOption() throws Exception {
  }

  public GuiPreGeneratedQuestionOption getWin() { return (GuiPreGeneratedQuestionOption) getBaseWin(); }

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
