package pss.game.models.pointsDistribution;

import pss.core.winUI.forms.JBaseForm;
import pss.core.ui.components.*;
import pss.core.win.JWin;

import pss.core.winUI.responsiveControls.JFormEditResponsive;

public class FormPointDistribution extends JBaseForm {


private static final long serialVersionUID = 1726795552344L;



  /**
   * Constructor de la Clase
   */
  public FormPointDistribution() throws Exception {
  }

  public GuiPointDistribution getWin() { return (GuiPointDistribution) getBaseWin(); }

  /**
   * Inicializacion Grafica
   */
  /**
   * Linkeo los campos con la variables del form
   */
  public void InicializarPanel( JWin zWin ) throws Exception {
  	super.InicializarPanel(zWin);
  } 
} 
