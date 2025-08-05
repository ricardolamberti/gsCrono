package pss.game.models.questionType;

import pss.core.winUI.forms.JBaseForm;
import pss.core.ui.components.*;
import pss.core.win.JWin;

import pss.core.winUI.responsiveControls.JFormEditResponsive;

public class FormQuestionType extends JBaseForm {


private static final long serialVersionUID = 1726795658262L;



  /**
   * Constructor de la Clase
   */
  public FormQuestionType() throws Exception {
  }

  public GuiQuestionType getWin() { return (GuiQuestionType) getBaseWin(); }

  /**
   * Inicializacion Grafica
   */
  /**
   * Linkeo los campos con la variables del form
   */
  public void InicializarPanel( JWin zWin ) throws Exception {
  	AddItemEdit(null, CHAR,OPT,"id").setHide(true);
  	AddItemEdit(null, CHAR,OPT,"company").setHide(true);
  	AddItemCombo("Juego", CHAR,OPT,"game_id").setHide(true);
  	AddItemEdit("Descripción", CHAR,REQ,"name");
  	AddItemCombo("Tipo", CHAR,REQ,"type");
  	
  } 
} 
