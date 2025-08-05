package  pss.common.customList.config.customlist;

import java.awt.Dimension;
import java.awt.Rectangle;

import pss.common.customList.config.field.campo.GuiCampo;
import pss.core.ui.components.JPssEdit;
import pss.core.ui.components.JPssLabel;
import pss.core.ui.components.JScrollableTextArea;
import pss.core.win.JWin;
import pss.core.winUI.forms.JBaseForm;

public class FormSQL extends JBaseForm {


private static final long serialVersionUID = 1226426905817L;

	/**
   * Constructor de la Clase
   */
  public FormSQL() throws Exception {
    try { jbInit(); }
    catch (Exception e) { e.printStackTrace(); } 
  }

  public GuiCampo getWin() { return (GuiCampo) getBaseWin(); }

  /**
   * Inicializacion Grafica
   */
  protected void jbInit() throws Exception {

  }
  /**
   * Linkeo los campos con la variables del form
   */
  public void InicializarPanel( JWin zWin ) throws Exception {
    AddItemEdit( null, CHAR, REQ, "company" ).setHide(true);
    AddItemEdit( null, UINT, REQ, "list_id" ).setHide(true);
    AddItemArea( "SQL", CHAR, OPT, "sql");
  }
  

	/**
	 * This method initializes jSQL	
	 * 	
	 * @return javax.swing.JTextArea	
	 */

}  //  @jve:decl-index=0:visual-constraint="10,10" 
