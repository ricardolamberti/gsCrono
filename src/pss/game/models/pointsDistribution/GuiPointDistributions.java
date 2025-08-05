package pss.game.models.pointsDistribution;

import pss.core.win.JWin;
import pss.core.winUI.lists.JWinList;
import pss.core.win.JWins;

public class GuiPointDistributions extends JWins {



  /**
   * Constructor de la Clase
   */
  public GuiPointDistributions() throws Exception {
  }


  public int     GetNroIcono() throws Exception  { return 862; } 
  public String  GetTitle()    throws Exception  { return "Distribuciones de puntos"; }
  public Class<? extends JWin>  GetClassWin()                   { return GuiPointDistribution.class; }
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
    	zLista.AddColumnaLista("difficulty_id");
    	zLista.AddColumnaLista("correct_percentage");
    	zLista.AddColumnaLista("points_awarded");
//    	zLista.AddColumnaLista("company");
  }

}
