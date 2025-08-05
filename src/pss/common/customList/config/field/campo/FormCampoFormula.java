package  pss.common.customList.config.field.campo;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import pss.common.customList.config.relation.GuiCamposGallery;
import pss.common.layout.JFieldSetWins;
import pss.core.ui.components.JPssColorPicker;
import pss.core.ui.components.JPssEdit;
import pss.core.ui.components.JPssHtmlTextArea;
import pss.core.ui.components.JPssLabel;
import pss.core.win.JWin;
import pss.core.win.JWins;
import pss.core.win.actions.BizAction;
import pss.core.winUI.controls.JControlCombo;
import pss.core.winUI.controls.JFormControl;
import pss.core.winUI.forms.JBaseForm;
import pss.www.platform.actions.resolvers.JDoLayoutActionResolver;

public class FormCampoFormula extends JBaseForm {


private static final long serialVersionUID = 1226426905817L;

/**
   * Constructor de la Clase
   */
  public FormCampoFormula() throws Exception {
    try { jbInit(); }
    catch (Exception e) { e.printStackTrace(); } 
  }

  public GuiCampo getWin() { return (GuiCampo) getBaseWin(); }

  /**
   * Inicializacion Grafica
   */
  protected void jbInit() throws Exception {
   
  }
  /**
   * Linkeo los campos con la variables del form
   */
  public void InicializarPanel( JWin zWin ) throws Exception {
    AddItemEdit( null, CHAR, REQ, "company" ).setHide(true);
    AddItemEdit( null, UINT, REQ, "list_id" ).setHide(true);
    AddItemEdit( null, UINT, OPT, "secuencia" ).setHide(true);
//    AddItem( jOrden, UINT, OPT, "orden" );
		AddItemEdit(null, CHAR, OPT, "serial_deep").setHide(true);
		AddItemEdit( null, CHAR, REQ, "record_owner" ).setHide(true);
		AddItemEdit( null, CHAR, REQ, "record_source" ).setHide(true);
		AddItemEdit( null, CHAR, REQ, "rel_id").setHide(true);
//    , new JControlCombo() {
//    	public JWins getRecords(boolean one) throws Exception {
//    		return getRecordSets(one);
//    	}
//    }).setRefreshForm(true);
    
		AddItemEdit( "Campo", CHAR, OPT, "campo");
    JFormControl c = AddItemArea( "Formula", CHAR, OPT, "formato_param" , false, JDoLayoutActionResolver.getDatosPlantilla(getWin().GetcDato().getObjCustomList().getObjRelation().getClassTarget().getName(), JFieldSetWins.SECTOR_MAIN),
    		getWin().GetcDato().getObjCustomList().getObjRelation().getClassTarget().getName(),400,
    		0,0,0,0,null, 
    		false);

//    JFormControl c = AddItem( jDescrCampo, CHAR, OPT, "descr_campo" );
//    c.SetValorDefault(this.getWin().GetcDato().getDescrCampo());
//    c.SetReadOnly(true);

//    AddItem( jFormat, CHAR, OPT, "formato", new JControlCombo() {
//    	public JWins getRecords(boolean one) throws Exception {
//    		return JWins.createVirtualWinsFromMap(BizCampo.getFormatMap());
//    	}
//    }).setVisible(!BizUsuario.getUsr().getObjBusiness().isCustomListSimplify());
    AddItemEdit( "Nombre Col.", CHAR, OPT, "nombre_columna");
    //AddItem( jFormatParam, CHAR, OPT, "formato_param").setVisible(!BizUsuario.getUsr().getObjBusiness().isCustomListSimplify());
    AddItemCheck( "Visible", CHAR, OPT, "visible", "S","N").SetValorDefault(true);
    AddItemCheck( "Expresar en porcentaje del total", CHAR, OPT, "porcentaje", "S","N").SetValorDefault(false);
    c = AddItemCombo( "Función", CHAR, OPT, "funcion", new JControlCombo() {
    	public JWins getRecords(boolean one) throws Exception {
    		return getFunciones(one);
    	}
    });
    c.SetValorDefault(BizCampo.FUNTION_FORMULA);
    
    if (this.getWin().GetcDato().getObjCustomList().isLista()) { 
    	c.setVisible(false);
    }
    c=AddItemCheck( "Configuración especial colores",  OPT, "colores");
    c.setRefreshForm(true);
    c.SetValorDefault(false);
    c=AddItemColor( "Color Fondo", CHAR, OPT, "color_background");
    c=AddItemColor( "Color Letra", CHAR, OPT, "color_foreground");
    c=AddItemCheck( "Marcar valores superiores",  OPT, "marca_mayores");
    c.setRefreshForm(true);
    c.SetValorDefault(false);
    c=AddItemColor( null, CHAR, OPT, "color_topbackground");
    c.SetValorDefault("FF0000");
    c=AddItemCheck( "Top 10",  OPT, "marca_top");
    c.setRefreshForm(true);
    c.SetValorDefault(false);
    c.setVisible(getWin().GetcDato().getObjCustomList().isMatriz()?true:false);
    c=AddItemEdit( "Mayores a", CHAR, OPT, "mayores_a");
    c=AddItemCheck( "Marcar valores inferiores",  OPT, "marca_menores");
    c.setRefreshForm(true);
    c.SetValorDefault(false);
    c=AddItemColor( null, CHAR, OPT, "color_bottombackground");
    c.SetValorDefault("00AA00");
    c=AddItemCheck( "Top 10",  OPT, "marca_bottom");
    c.setRefreshForm(true);
    c.SetValorDefault(false);
    c.setVisible(getWin().GetcDato().getObjCustomList().isMatriz()?true:false);
    c=AddItemEdit( "Menores a", CHAR, OPT, "menores_a");

  }
public JWins getFunciones(boolean one) throws Exception {
	if (one) {
		return JWins.createVirtualWinsFromMap(BizCampo.getFunctionAllMap());
	}
	return JWins.createVirtualWinsFromMap(BizCampo.getFunctionAllMap());
	
}
  @Override
  	public void OnPostShow() throws Exception {
  		checkControls(null);
  		super.OnPostShow();
  	}
  @Override
  	public void checkControls(String sControlId) throws Exception {
  		if (this.getControles().findControl("colores").getValue().equals("S")) {
  			this.getControles().findControl("color_background").edit(this.GetModo());
  			this.getControles().findControl("color_foreground").edit(this.GetModo());
  		} else {
  			this.getControles().findControl("color_background").disable();
  			this.getControles().findControl("color_foreground").disable();
  		}
  		if (this.getControles().findControl("marca_mayores").getValue().equals("S")) {
  			this.getControles().findControl("color_topbackground").edit(this.GetModo());
  			this.getControles().findControl("marca_top").edit(this.GetModo());
    		if (this.getControles().findControl("marca_top").getValue().equals("S")) 
    			this.getControles().findControl("mayores_a").disable();
    		else
    			this.getControles().findControl("mayores_a").edit(this.GetModo());
  		} else {
  			this.getControles().findControl("color_topbackground").disable();
  			this.getControles().findControl("marca_top").disable();
  			this.getControles().findControl("mayores_a").disable();
  		}
  		if (this.getControles().findControl("marca_menores").getValue().equals("S")) {
  			this.getControles().findControl("color_bottombackground").edit(this.GetModo());
  			this.getControles().findControl("marca_bottom").edit(this.GetModo());
    		if (this.getControles().findControl("marca_bottom").getValue().equals("S")) 
    			this.getControles().findControl("menores_a").disable();
    		else
    			this.getControles().findControl("menores_a").edit(this.GetModo());
  		} else {
  			this.getControles().findControl("color_bottombackground").disable();
  			this.getControles().findControl("marca_bottom").disable();
  			this.getControles().findControl("menores_a").disable();
  		}
  		super.checkControls(sControlId);
  	}
  
//  private JWins getCampos(boolean one) throws Exception {
//  	GuiCamposGallery g = new GuiCamposGallery();
//  	if (one) {
//  		g.setRecords(this.getWin().GetcDato().getCamposGallery());
//  	} else {
//  		if (!this.findControl("rel_id").hasValue()) return null;
//  		this.getWin().GetcDato().setRecordOwner(this.findControl("record_owner").getValue());
//  		this.getWin().GetcDato().setRelId(this.findControl("rel_id").getValue());
//  		g.setRecords(this.getWin().GetcDato().getCamposGallery());
//  	}
//  	return g;
//  }
  
  private JWins getCamposAll(boolean one) throws Exception {
  	GuiCamposGallery g = new GuiCamposGallery();
 		g.setRecords(this.getWin().GetcDato().getObjCustomList().getAllCampos((BizAction)null, true));
  	return g;
  }

//  private JWins getGeoCampos(boolean one) throws Exception {
//  	GuiCamposGallery g = new GuiCamposGallery();
//  	if (one) {
//  		return JWins.createVirtualWinsFromMap(this.getWin().GetcDato().getCamposGallery());
//  	} else {
//  		if (!this.findControl("rel_id").hasValue()) return null;
//  		this.getWin().GetcDato().setRecordOwner(this.findControl("record_owner").getValue());
//  		this.getWin().GetcDato().setRelId(this.findControl("rel_id").getValue());
//  		return JWins.createVirtualWinsFromMap(this.getWin().GetcDato().getCamposGallery());
//  	}
//  }

}  //  @jve:decl-index=0:visual-constraint="-1,16"
