package  pss.game.gameBusiness;

import pss.core.win.JWin;
import pss.core.winUI.forms.JBaseForm;
import pss.game.gameBusiness.company.GuiCompanyInfo;

public class FormGameCompany extends JBaseForm {

	// -------------------------------------------------------------------------//
	// Propiedades de la Clase
	// -------------------------------------------------------------------------//

	// -------------------------------------------------------------------------//
	// Constructor de la Clase
	// -------------------------------------------------------------------------//
	public FormGameCompany() throws Exception {

	}

	public GuiGameCompany GetWin() {
		return (GuiGameCompany) getBaseWin();
	}

	// -------------------------------------------------------------------------//
	// Inicializacion Grafica
	// -------------------------------------------------------------------------//

	// -------------------------------------------------------------------------//
	// Linkeo los campos con la variables del form
	// -------------------------------------------------------------------------//
	@Override
	public void InicializarPanel(JWin zWin) throws Exception {
		AddItemEdit(null, CHAR, OPT, "company").setHide(true);
		AddItemEdit(null, CHAR, OPT, "business").setHide(true).SetValorDefault(GetWin().GetcDato().getBusiness());
		getInternalPanel().AddCardPanel("company_info",GuiCompanyInfo.class,JWin.ACTION_QUERY);
		AddItemTabPanel().AddItemList( 10).setFilterBar(false);
	}


} // @jve:decl-index=0:visual-constraint="10,10"
