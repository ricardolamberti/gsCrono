package pss.game.models.variable;

import pss.core.winUI.forms.JBaseForm;
import pss.core.ui.components.*;
import pss.core.win.JWin;

import pss.core.winUI.responsiveControls.JFormEditResponsive;
import pss.core.winUI.responsiveControls.JFormTabPanelResponsive;

public class FormVariable extends JBaseForm {


private static final long serialVersionUID = 1726795724613L;



  /**
   * Constructor de la Clase
   */
  public FormVariable() throws Exception {
  }

  public GuiVariable getWin() { return (GuiVariable) getBaseWin(); }

  /**
   * Inicializacion Grafica
   */
  /**
   * Linkeo los campos con la variables del form
   */
  public void InicializarPanel( JWin zWin ) throws Exception {
  	AddItemEdit(null, CHAR, OPT, "id").setHide(true);
  	AddItemEdit(null, CHAR, OPT, "company").setHide(true);
  	AddItemEdit("Denominación", CHAR, OPT, "name");
  	AddItemCombo("Tipo", CHAR, OPT, "type").setRefreshForm(true);
  	AddItemEdit("Rango mínimo", CHAR, OPT, "range_min").setVisible(getWin().GetcDato().isRangeVisible());
  	AddItemEdit("Rango máximo", CHAR, OPT, "range_max").setVisible(getWin().GetcDato().isRangeVisible());
  	
  	if (getWin().GetcDato().hasListValueVisible()) {
    	JFormTabPanelResponsive tabs = AddItemTabPanel();
    	tabs.AddItemTab(10);
  	}
  } 
} 
