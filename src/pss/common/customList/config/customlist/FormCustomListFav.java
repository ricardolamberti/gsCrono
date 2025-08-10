package  pss.common.customList.config.customlist;

import pss.common.security.GuiUsuarios;
import pss.core.win.JWin;
import pss.core.winUI.forms.JBaseForm;

public class FormCustomListFav extends JBaseForm {


private static final long serialVersionUID = 1226426806993L;



/**
   * Constructor de la Clase
   */
  public FormCustomListFav() throws Exception {
    try { jbInit(); }
    catch (Exception e) { e.printStackTrace(); } 
  }

  public GuiCustomListFav getWin() { return (GuiCustomListFav) getBaseWin(); }

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
  	AddItemEdit( null, UINT, OPT, "list_id" ).setHide(true);
  	AddItemCombo( "Usuario", CHAR, REQ, "usuario" , new GuiUsuarios());
 
  }
  



}  //  @jve:decl-index=0:visual-constraint="10,10" 
