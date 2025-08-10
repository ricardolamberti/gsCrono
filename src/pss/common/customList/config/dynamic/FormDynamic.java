package  pss.common.customList.config.dynamic;

import pss.core.tools.collections.JIterator;
import pss.core.win.JWin;
import pss.core.win.actions.BizAction;
import pss.core.winUI.forms.JBaseForm;
import pss.core.winUI.responsiveControls.JFormTabPanelResponsive;

public class FormDynamic extends JBaseForm {


private static final long serialVersionUID = 1226426905817L;

  /**
   * Constructor de la Clase
   */
  public FormDynamic() throws Exception {
  }

  public GuiDynamic getWin() { return (GuiDynamic) getBaseWin(); }

  /**
   * Inicializacion Grafica
   */
  protected void jbInit() throws Exception {

  }
  /**
   * Linkeo los campos con la variables del form
   */
  public void InicializarPanel( JWin zWin ) throws Exception {
  	JFormTabPanelResponsive tabs= this.AddItemTabPanel();
  	JIterator<BizAction> iter = this.getWin().getActionMap().getStaticIterator();
  	while (iter.hasMoreElements()) {
  		BizAction a = iter.nextElement();
  		if (!a.isListType()) continue;
  		tabs.AddItemTab( a.getId());
  	}
  	tabs.AddItemTab( 10);
  	
  }
  


}  //  @jve:decl-index=0:visual-constraint="10,10" 
