package pss.game.models.variable;

import pss.core.win.JWin;
import pss.core.winUI.lists.JWinList;
import pss.core.win.JWins;

public class GuiVariables extends JWins {



  /**
   * Constructor de la Clase
   */
  public GuiVariables() throws Exception {
  }


  public int     GetNroIcono() throws Exception  { return 5011; } 
  public String  GetTitle()    throws Exception  { return "Variables"; }
  public Class<? extends JWin>  GetClassWin()                   { return GuiVariable.class; }
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
    	zLista.AddColumnaLista("type");
    	zLista.AddColumnaLista("range_min");
    	zLista.AddColumnaLista("range_max");
  //  	zLista.AddColumnaLista("company");
  }

}
