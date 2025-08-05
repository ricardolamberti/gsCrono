package pss.game.models.variableOption;

import pss.core.winUI.forms.JBaseForm;
import pss.core.ui.components.*;
import pss.core.win.JWin;

import pss.core.winUI.responsiveControls.JFormEditResponsive;
import pss.core.winUI.responsiveControls.JFormPanelResponsive;

public class FormVariableOption extends JBaseForm {


private static final long serialVersionUID = 1726841537738L;



  /**
   * Constructor de la Clase
   */
  public FormVariableOption() throws Exception {
  }

  public GuiVariableOption getWin() { return (GuiVariableOption) getBaseWin(); }

  /**
   * Inicializacion Grafica
   */
  /**
   * Linkeo los campos con la variables del form
   */
  public void InicializarPanel( JWin zWin ) throws Exception {
  	AddItemEdit(null, CHAR, OPT,"id").setHide(true);
  	AddItemEdit(null, CHAR, OPT,"company").setHide(true);
  	AddItemEdit(null, CHAR, OPT,"variable_id").setHide(true);
  	JFormPanelResponsive row = AddItemRow();
  	row.AddItemEdit("Valor preg.singular", CHAR, OPT,"valuequestion",6);
  	row.AddItemEdit("Valor preg.plural", CHAR, OPT,"valuequestions",6);
  	row = AddItemRow();
  	row.AddItemEdit("Valor resp.singular", CHAR, OPT,"valueanswer",6);
  	row.AddItemEdit("Valor resp.plural", CHAR, OPT,"valueanswers",6);
  	row = AddItemRow();
  	row.AddItemEdit("Imagen", CHAR, OPT,"imagen",6);
  	row = AddItemRow();
  	row.AddItemEdit("orden", INT, OPT,"variable_id",4);
  } 
  

} 
