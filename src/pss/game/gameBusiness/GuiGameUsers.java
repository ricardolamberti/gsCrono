package pss.game.gameBusiness;

import pss.common.security.GuiUsuario;
import pss.common.security.GuiUsuarios;
import pss.common.security.type.GuiUsuarioTipos;
import pss.core.win.JWin;
import pss.core.win.JWins;
import pss.core.winUI.controls.JControlCombo;
import pss.core.winUI.lists.JFormFiltro;
import pss.core.winUI.lists.JWinList;

public class GuiGameUsers extends GuiUsuarios {
	public GuiGameUsers() throws Exception {
		super();
	}

	@Override
	public Class<? extends JWin> GetClassWin() {
		return GuiGameUser.class;
	}
	@Override
	public void ConfigurarColumnasLista(JWinList zLista) throws Exception {
		zLista.AddIcono("");
		zLista.AddColumnaLista("user_mail");
		zLista.AddColumnaLista("descripcion");
		zLista.AddColumnaLista("activo");
		zLista.AddColumnaLista("fecha_ultimo_ingreso");
	}
	
  public void ConfigurarFiltros(JFormFiltro filter) throws Exception {
	 	filter.addEditResponsive("Descripción", "descripcion").setFilterNeverHide(true).setOperator("ilike");
	 	filter.addCheckResponsive("Ver Inactivos", "activo").SetValorDefault(false);
  }
  
	
}
