package pss.game.models.gameplayer;

import pss.core.win.JWin;
import pss.core.winUI.lists.JWinList;
import pss.core.win.JWins;

public class GuiGamePlayers extends JWins {



  /**
   * Constructor de la Clase
   */
  public GuiGamePlayers() throws Exception {
  }


  public int     GetNroIcono() throws Exception  { return 15000; } 
  public String  GetTitle()    throws Exception  { return "Jugadores en Juego"; }
  public Class<? extends JWin>  GetClassWin()                   { return GuiGamePlayer.class; }
  /**
   * Mapeo las acciones con las operaciones
   */
  public void createActionMap() throws Exception {
    addActionNew( 1, "Nuevo Registro" );
  }



  /**
   * Configuro las columnas que quiero mostrar en la grilla
   */
  public void ConfigurarColumnasLista(JWinList zLista) throws Exception {
    	zLista.AddIcono("");
//    	zLista.AddColumnaLista("id_gameplayer");
//    	zLista.AddColumnaLista("id_game");
//    	zLista.AddColumnaLista("id_player");
    	if (GetVision().equals("PLAYER"))
      	zLista.AddColumnaLista("game_name");
    	else
    		zLista.AddColumnaLista("player_name");
    	zLista.AddColumnaLista("date");
    	zLista.AddColumnaLista("points");
    	zLista.AddColumnaLista("level");
    	zLista.AddColumnaLista("gems");
    //	zLista.AddColumnaLista("company");
  }

}
