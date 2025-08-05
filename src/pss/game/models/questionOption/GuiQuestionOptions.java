package pss.game.models.questionOption;

import pss.core.win.JWin;
import pss.core.winUI.lists.JWinList;
import pss.core.win.JWins;

public class GuiQuestionOptions extends JWins {



  /**
   * Constructor de la Clase
   */
  public GuiQuestionOptions() throws Exception {
  }


  public int     GetNroIcono() throws Exception  { return 12; } 
  public String  GetTitle()    throws Exception  { return "Opciones"; }
  public Class<? extends JWin>  GetClassWin()                   { return GuiQuestionOption.class; }
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
    //	zLista.AddColumnaLista("id");
    //	zLista.AddColumnaLista("question_id");
    	zLista.AddColumnaLista("answer_formula");
    	zLista.AddColumnaLista("is_correct");
    	zLista.AddColumnaLista("extra_data");
 //   	zLista.AddColumnaLista("company");
  }

}
