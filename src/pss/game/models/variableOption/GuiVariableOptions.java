package pss.game.models.variableOption;

import pss.core.win.JWin;
import pss.core.winUI.lists.JWinList;
import pss.core.win.JWins;

public class GuiVariableOptions extends JWins {



  /**
   * Constructor de la Clase
   */
  public GuiVariableOptions() throws Exception {
  }


  public int     GetNroIcono() throws Exception  { return 12; } 
  public String  GetTitle()    throws Exception  { return "Opciones"; }
  public Class<? extends JWin>  GetClassWin()                   { return GuiVariableOption.class; }
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
//    	zLista.AddColumnaLista("variable_id");
    	zLista.AddColumnaLista("orden");
    	zLista.AddColumnaLista("valuequestion");
    	zLista.AddColumnaLista("valuequestions");
    	zLista.AddColumnaLista("valueanswer");
    	zLista.AddColumnaLista("valueanswers");
//    	zLista.AddColumnaLista("company");
  }

}
