package  pss.game.gameBusiness.company;

import pss.common.personalData.types.GuiPersonaJuridica;
import pss.common.security.BizUsuario;
import pss.core.win.JWin;
import pss.core.winUI.forms.JBaseForm;

public class FormCompanyInfo extends JBaseForm {


private static final long serialVersionUID = 1352315031772L;

  /**
   * Propiedades de la Clase
   */



  /**
   * Constructor de la Clase
   */
  public FormCompanyInfo() throws Exception {
  }

  public GuiCompanyInfo getWin() { return (GuiCompanyInfo) getBaseWin(); }


  /**
   * Linkeo los campos con la variables del form
   */
  public void InicializarPanel( JWin zWin ) throws Exception {
  	AddItemEdit(null, CHAR, OPT, "company").setHide(true).SetValorDefault(BizUsuario.getUsr().getCompany());
	AddItemFieldsetExpanded("Info Empresa").setOnStartCollapse(false).AddCardPanel("persona",GuiPersonaJuridica.class,JWin.ACTION_QUERY);
  
  } 
}  //  @jve:decl-index=0:visual-constraint="10,10" 
