package pss.game.models.playerAnswers;

import pss.core.win.JWin;
import pss.core.winUI.lists.JWinList;
import pss.core.win.JWins;

public class GuiPlayerAnswers extends JWins {



  /**
   * Constructor de la Clase
   */
  public GuiPlayerAnswers() throws Exception {
  }


  public int     GetNroIcono() throws Exception  { return 912; } 
  public String  GetTitle()    throws Exception  { return "Respuestas de jugadores"; }
  public Class<? extends JWin>  GetClassWin()                   { return GuiPlayerAnswer.class; }
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
//    	zLista.AddColumnaLista("gameplayer_id");
//    	zLista.AddColumnaLista("level_id");
//    	zLista.AddColumnaLista("question_id");
    	zLista.AddColumnaLista("given_answer");
    	zLista.AddColumnaLista("points_awarded");
    	zLista.AddColumnaLista("date");
//    	zLista.AddColumnaLista("company");
  }

}
