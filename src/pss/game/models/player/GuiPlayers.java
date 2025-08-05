package pss.game.models.player;

import pss.core.win.JWin;
import pss.core.winUI.lists.JWinList;
import pss.core.win.JWins;

public class GuiPlayers extends JWins {



  /**
   * Constructor de la Clase
   */
  public GuiPlayers() throws Exception {
  }


  public int     GetNroIcono() throws Exception  { return 10013; } 
  public String  GetTitle()    throws Exception  { return "Jugadores"; }
  public Class<? extends JWin>  GetClassWin()                   { return GuiPlayer.class; }
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
    //	zLista.AddColumnaLista("id_player");
    	zLista.AddColumnaLista("name");
    	zLista.AddColumnaLista("password");
    	zLista.AddColumnaLista("id_avatar");
    	zLista.AddColumnaLista("birthday");
  //  	zLista.AddColumnaLista("company");
  }

}
