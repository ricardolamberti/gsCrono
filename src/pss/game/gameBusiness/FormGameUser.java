package pss.game.gameBusiness;

import pss.common.regions.divitions.GuiPaises;
import pss.common.regions.multilanguage.JLanguage;
import pss.common.security.type.GuiUsuarioTipos;
import pss.core.win.JControlWin;
import pss.core.win.JWin;
import pss.core.win.JWins;
import pss.core.winUI.controls.JControlCombo;
import pss.core.winUI.controls.JFormControl;
import pss.core.winUI.forms.JBaseForm;

public class FormGameUser extends JBaseForm {
	@Override
	public void InicializarPanel(JWin zWin) throws Exception {
		AddItemEdit(null,CHAR, OPT, "company").setHide(true);
		AddItemEdit(null,CHAR, OPT, "pais").setHide(true);
		AddItemEdit(null, CHAR, OPT, "usuario").setHide(true);
		AddItemEdit("Apellido y nombre", CHAR, REQ, "descripcion").setSizeColumns(10);
		AddItemCheck("Activo", OPT, "activo").setSizeColumns(2).SetReadOnly(true).SetValorDefault(true);
		AddItemEdit("email", CHAR, REQ, "user_mail").setSizeColumns(10);
		AddItemEdit(null, CHAR, OPT, "tipo_usuario").SetValorDefault("usuario").setHide(true);
		AddItemEdit(null, CHAR, OPT, "is_system_user").setHide(true);
		AddItemEdit(null, CHAR,OPT,  "LONGITUD_CLAVE").setHide(true);
		AddItemEdit(null, CHAR,OPT,  "retries_clave").setHide(true);
		AddItemEdit(null, CHAR,OPT,  "INTERVALO_CADUCIDAD").setHide(true);
		AddItemCombo("País", CHAR, REQ, "birth_country", new GuiPaises());
		AddItemCombo("Idioma", CHAR, REQ, "lenguaje", JWins.CreateVirtualWins(JLanguage.ObtenerIdiomas()));
		AddItemEdit(null, CHAR, "custom_menu").SetValorDefault("vacio").setHide(true);
		
	
	}

}
