package pss.game.consola;

import java.awt.Dimension;

import pss.common.customList.config.carpetas.GuiCarpeta;
import pss.core.ui.components.JPssEdit;
import pss.core.ui.components.JPssLabel;
import pss.core.win.JWin;
import pss.core.winUI.forms.JBaseForm;
import pss.www.ui.JWebIcon;

public class FormGameConsola extends JBaseForm {


	private static final long serialVersionUID = 1245260233503L;

	  /**
	   * Propiedades de la Clase
	   */
	JPssLabel lusuario = new JPssLabel();
	JPssEdit usuario = new JPssEdit  ();
	//JPssLabel lusos = new JPssLabel();

	@Override
	public boolean isWithHeader() {
		return false;
	}
	  /**
	   * Constructor de la Clase
	   */
	  public FormGameConsola() throws Exception {
	    try { jbInit(); }
	    catch (Exception e) { e.printStackTrace(); } 
	  }

	  public GuiGameConsola getWin() { return (GuiGameConsola) getBaseWin(); }

	  /**
	   * Inicializacion Grafica
	   */
	  protected void jbInit() throws Exception {

	    GuiCarpeta.setNav("");//trucho

	  }

			@Override
			public Dimension getSize() {
				return new Dimension(1070, 1665);
			}

	  /**
	   * Linkeo los campos con la variables del form
	   */
	  public void InicializarPanel( JWin zWin ) throws Exception {
	  	if( getWin().hasGame())
	  		AddInfoCard("Juegos", CHAR, "game", JWebIcon.getResponsiveIcon("fa fa-gamepad fa-5x"),"Juegos", 20).setResponsiveClass("border-left-primary shadow ").setComplexColumnClass("clearfix  col-xl-3 col-lg-6 col-md-6 col-sm-12 col-xs-12");
	  	else
	  		AddInfoCard("Agregar Juego", CHAR, "game", JWebIcon.getResponsiveIcon("fa fa-gamepad fa-5x"),"Juego ", 21,false).setResponsiveClass("border-left-primary shadow ").setComplexColumnClass("clearfix col-xl-3 col-lg-6 col-md-6 col-sm-12 col-xs-12");
	  

	  	if( getWin().hasPlayers())
	  		AddInfoCard("Jugadores", CHAR, "player", JWebIcon.getResponsiveIcon("fa fa-user fa-5x"),"Jugadores", 10).setResponsiveClass("border-left-primary shadow ").setComplexColumnClass("clearfix  col-xl-3 col-lg-6 col-md-6 col-sm-12 col-xs-12");
	  	else
	  		AddInfoCard("Agregar Jugador", CHAR, "player", JWebIcon.getResponsiveIcon("fa fa-user fa-5x"),"Jugador ", 11,false).setResponsiveClass("border-left-primary shadow ").setComplexColumnClass("clearfix col-xl-3 col-lg-6 col-md-6 col-sm-12 col-xs-12");

	 


      AddItemRow();
  	  }
	  
	  
		
	@Override
	public boolean isFullSize() throws Exception {
		return true;
	}
	
	public boolean isFixHight() throws Exception {
		return false;
	}

}