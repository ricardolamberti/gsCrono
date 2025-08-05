package pss.game.models.familyQuestion;

import pss.core.win.JWin;
import pss.core.winUI.lists.JWinList;
import pss.core.win.JWins;

public class GuiFamilyQuestions extends JWins {



  /**
   * Constructor de la Clase
   */
  public GuiFamilyQuestions() throws Exception {
  }


  public int     GetNroIcono() throws Exception  { return 10023; } 
  public String  GetTitle()    throws Exception  { return "Familias de preguntas"; }
  public Class<? extends JWin>  GetClassWin()                   { return GuiFamilyQuestion.class; }
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
//    	zLista.AddColumnaLista("game_id");
//    	zLista.AddColumnaLista("company");
  }

}
