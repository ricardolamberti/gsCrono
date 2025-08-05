package pss.game.gameBusiness;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import pss.common.regions.company.GuiCompanies;
import pss.common.regions.divitions.GuiPaisesLista;
import pss.common.security.license.typeLicense.GuiTypeLicenses;
import pss.core.ui.components.JPssCalendarEdit;
import pss.core.ui.components.JPssEdit;
import pss.core.ui.components.JPssLabel;
import pss.core.win.JWin;
import pss.core.win.JWins;
import pss.core.winUI.controls.JControlCombo;
import pss.core.winUI.forms.JBaseForm;

public class FormNewGameCompany extends JBaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -824914234250493196L;
	// -------------------------------------------------------------------------//
	// Propiedades de la Clase
	// -------------------------------------------------------------------------//



	// -------------------------------------------------------------------------//
	// Constructor de la Clase
	// -------------------------------------------------------------------------//
	public FormNewGameCompany() throws Exception {

	}

	public GuiNewGameCompany GetWin() {
		return (GuiNewGameCompany) getBaseWin();
	}

	// -------------------------------------------------------------------------//
	// Linkeo los campos con la variables del form
	// -------------------------------------------------------------------------//
	@Override
	public void InicializarPanel(JWin zWin) throws Exception {
		AddItemEdit("Compania", CHAR, REQ, "company");
		AddItemEdit("descripcion", CHAR, REQ, "description");
	}
	
	

	

}  //  @jve:decl-index=0:visual-constraint="40,6"
