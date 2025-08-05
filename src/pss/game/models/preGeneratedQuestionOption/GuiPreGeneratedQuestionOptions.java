package pss.game.models.preGeneratedQuestionOption;

import pss.core.win.JWin;
import pss.core.win.JWins;
import pss.core.winUI.lists.JWinList;

public class GuiPreGeneratedQuestionOptions extends JWins {



  /**
   * Constructor de la Clase
   */
  public GuiPreGeneratedQuestionOptions() throws Exception {
  }


  public int     GetNroIcono() throws Exception  { return 12; } 
  public String  GetTitle()    throws Exception  { return "Opciones"; }
  public Class<? extends JWin>  GetClassWin()                   { return GuiPreGeneratedQuestionOption.class; }
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
//    	zLista.AddColumnaLista("pre_question_id");
    	zLista.AddColumnaLista("answer_formula");
    	zLista.AddColumnaLista("is_correct");
    	zLista.AddColumnaLista("extra_data");
//    	zLista.AddColumnaLista("company");
  }

}
