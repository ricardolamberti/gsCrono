package pss.game.models.difficulty;

import pss.core.win.JWin;
import pss.core.winUI.lists.JWinList;
import pss.core.win.JWins;

public class GuiDifficulties extends JWins {



  /**
   * Constructor de la Clase
   */
  public GuiDifficulties() throws Exception {
  }


  public int     GetNroIcono() throws Exception  { return 26; } 
  public String  GetTitle()    throws Exception  { return "Dificultades"; }
  public Class<? extends JWin>  GetClassWin()                   { return GuiDifficulty.class; }
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
//    	zLista.AddColumnaLista("id");
    	zLista.AddColumnaLista("name");
//    	zLista.AddColumnaLista("company");
  }

}
