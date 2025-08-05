package pss.game.models.gameplayer;

import pss.core.winUI.forms.JBaseForm;
import pss.core.ui.components.*;
import pss.core.win.JWin;

import pss.core.winUI.responsiveControls.JFormEditResponsive;

public class FormGamePlayer extends JBaseForm {


private static final long serialVersionUID = 1725462039626L;



  /**
   * Constructor de la Clase
   */
  public FormGamePlayer() throws Exception {
  }

  public GuiGamePlayer getWin() { return (GuiGamePlayer) getBaseWin(); }

 @Override
	public void InicializarPanel(JWin zBase) throws Exception {
	 AddItemEdit(null, CHAR, OPT, "id_gameplayer").setHide(true);
	 AddItemEdit(null, CHAR, OPT,"company").setHide(true);
	 AddItemCombo("Juego", CHAR, REQ,"id_game");
	 AddItemCombo("Jugador", CHAR, REQ,"id_player");
	 AddItemEdit("Nivel", INT, OPT,"level",4);
	 AddItemEdit("Puntos", INT, OPT,"points",4);
	 AddItemEdit("Gemas", INT, OPT,"gems",4);
		if (isAlta())
			return;
		autoBuildTabs(getInternalPanel(), zBase);

	}
} 
