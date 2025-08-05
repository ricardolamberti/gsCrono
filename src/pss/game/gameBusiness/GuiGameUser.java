package pss.game.gameBusiness;

import pss.common.security.BizUsuario;
import pss.common.security.FormUsuario;
import pss.common.security.GuiUsuario;
import pss.core.services.records.JRecord;
import pss.core.winUI.forms.JBaseForm;

public class GuiGameUser extends GuiUsuario {

	public GuiGameUser() throws Exception {
		super();
	}
	@Override
	public String GetTitle() throws Exception {
		return "Usuario";
	}
	@Override
	public JRecord ObtenerDato() throws Exception {
		return new BizGameUser();
	}

	@Override
	public Class<? extends JBaseForm> getFormBase() throws Exception {
		return FormGameUser.class;
	}

	public void createActionMap() throws Exception {

		this.addActionQuery(1, "Consultar");
		this.addActionUpdate(2, "Modificar");
		this.addActionDelete(3, "Eliminar");
		this.addAction(8, "Activar Usuario", null, 5511, true, true);
		this.addAction(9, "Desactivar Usuario", null, 5510, true, true);
		this.addAction(25, "Blanqueo de Clave", null, 5513, true, true).setConfirmMessage(true);
//  this.addAction(73, "Configuracion Correo", null, 5514, true, true);

	}
}
