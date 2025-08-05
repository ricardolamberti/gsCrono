package pss.game.models.avatar;

import pss.core.win.JWin;
import pss.core.winUI.lists.JWinList;
import pss.core.win.JWins;

public class GuiAvatars extends JWins {



  /**
   * Constructor de la Clase
   */
  public GuiAvatars() throws Exception {
  }


  public int     GetNroIcono() throws Exception  { return 10027; } 
  public String  GetTitle()    throws Exception  { return "Avatars"; }
  public Class<? extends JWin>  GetClassWin()                   { return GuiAvatar.class; }
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
   // 	zLista.AddColumnaLista("id_avatar");
    	zLista.AddColumnaLista("avatar");
   // 	zLista.AddColumnaLista("company");
  }

}
