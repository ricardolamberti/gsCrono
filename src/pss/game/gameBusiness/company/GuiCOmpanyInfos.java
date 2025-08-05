package  pss.game.gameBusiness.company;

import pss.core.win.JWin;
import pss.core.win.JWins;
import pss.core.winUI.lists.JWinList;

public class GuiCOmpanyInfos extends JWins {



  /**
   * Constructor de la Clase
   */
  public GuiCOmpanyInfos() throws Exception {
  }


  public int     GetNroIcono() throws Exception  { return 10051; } 
  public String  GetTitle()    throws Exception  { return "Empresa Info"; }
  public Class<? extends JWin>  GetClassWin()                   { return GuiCompanyInfo.class; }
  /**
   * Mapeo las acciones con las operaciones
   */
  public void createActionMap() throws Exception {
//    addActionNew( 1, "Nueva tarea" );
  }
  
  



  /**
   * Configuro las columnas que quiero mostrar en la grilla
   */
  public void ConfigurarColumnasLista(JWinList zLista) throws Exception {
  	zLista.AddColumnaLista("description");
   }
  
  @Override
  public int getWebPageSize() {
  	return -1;
  }

  

}
