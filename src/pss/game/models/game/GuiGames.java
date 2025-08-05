package pss.game.models.game;

import pss.core.win.JWin;
import pss.core.winUI.lists.JWinList;
import pss.core.win.JWins;

public class GuiGames extends JWins {



  /**
   * Constructor de la Clase
   */
  public GuiGames() throws Exception {
  }


  public int     GetNroIcono() throws Exception  { return 10023; } 
  public String  GetTitle()    throws Exception  { return "Juegos"; }
  public Class<? extends JWin>  GetClassWin()                   { return GuiGame.class; }
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
    //	zLista.AddColumnaLista("id_game");
    	zLista.AddColumnaLista("game");
   // 	zLista.AddColumnaLista("company");
  }

}
