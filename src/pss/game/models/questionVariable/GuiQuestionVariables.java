package pss.game.models.questionVariable;

import pss.core.win.JWin;
import pss.core.winUI.lists.JWinList;
import pss.core.win.JWins;

public class GuiQuestionVariables extends JWins {



  /**
   * Constructor de la Clase
   */
  public GuiQuestionVariables() throws Exception {
  }


  public int     GetNroIcono() throws Exception  { return 5000; } 
  public String  GetTitle()    throws Exception  { return "Preguntas Variables"; }
  public Class<? extends JWin>  GetClassWin()                   { return GuiQuestionVariable.class; }
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
//    	zLista.AddColumnaLista("question_id");
//    	zLista.AddColumnaLista("variable_id");
    	zLista.AddColumnaLista("company");
  }

}
