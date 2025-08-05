package pss.game.models.question;

import pss.core.win.JWin;
import pss.core.winUI.lists.JWinList;
import pss.core.win.JWins;

public class GuiQuestions extends JWins {



  /**
   * Constructor de la Clase
   */
  public GuiQuestions() throws Exception {
  }


  public int     GetNroIcono() throws Exception  { return 12; } 
  public String  GetTitle()    throws Exception  { return "Preguntas"; }
  public Class<? extends JWin>  GetClassWin()                   { return GuiQuestion.class; }
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
//  	zLista.AddColumnaLista("game_id");
//  	zLista.AddColumnaLista("company");
    	zLista.AddColumnaLista("family_name");
    	zLista.AddColumnaLista("question_type_name");
    	zLista.AddColumnaLista("statement");
    	zLista.AddColumnaLista("difficulty_name");
  }

}
