package  pss.common.customList.config.field.filtro;

import pss.common.security.BizUsuario;
import pss.core.win.JWin;
import pss.core.win.JWins;
import pss.core.winUI.controls.JControlCombo;
import pss.core.winUI.forms.JBaseForm;

public class FormFiltroSQL extends JBaseForm {



/**
   * Constructor de la Clase
   */
  public FormFiltroSQL() throws Exception {
    try { jbInit(); }
    catch (Exception e) { e.printStackTrace(); } 
  }

  public GuiFiltroSQL getWin() { return (GuiFiltroSQL) getBaseWin(); }

  /**
   * Inicializacion Grafica
   */
  protected void jbInit() throws Exception {

  }
  /**
   * Linkeo los campos con la variables del form
   */
  public void InicializarPanel( JWin zWin ) throws Exception {
    AddItemEdit( null, CHAR, REQ, "company" );
    AddItemEdit( null, UINT, REQ, "list_id" );
    AddItemEdit( null, UINT, OPT, "secuencia" );
    AddItemEdit( "Orden", UINT, OPT, "orden" );

    AddItemEdit( "Filtro", CHAR, REQ, "filtro");
    AddItemEdit( "Nombre", CHAR, OPT, "nombre_filtro" );
    AddItemCombo( "Tipo", CHAR, OPT, "tipo_filtro", new JControlCombo() {
    	public JWins getRecords(boolean one) throws Exception {
    		return JWins.createVirtualWinsFromMap(BizFiltroSQL.getTipos());
    	}
    });
    AddItemCombo( "Valor Default", CHAR, OPT, "campo_key", new JControlCombo() {
    	public JWins getRecords(boolean one) throws Exception {
    		return JWins.createVirtualWinsFromMap(BizUsuario.getUsr().getObjBusiness().getCamposClavesParaCustomList());
    	}
    });
    }


}  //  @jve:decl-index=0:visual-constraint="20,9" 
