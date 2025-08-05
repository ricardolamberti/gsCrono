package pss.game.models.question;

import pss.core.winUI.forms.JBaseForm;
import pss.core.ui.components.*;
import pss.core.win.JWin;
import pss.core.winUI.responsiveControls.JFormColumnResponsive;
import pss.core.winUI.responsiveControls.JFormEditResponsive;

public class FormQuestion extends JBaseForm {


private static final long serialVersionUID = 1726805105822L;



  /**
   * Constructor de la Clase
   */
  public FormQuestion() throws Exception {
  }

  public GuiQuestion getWin() { return (GuiQuestion) getBaseWin(); }

  /**
   * Inicializacion Grafica
   */
  /**
   * Linkeo los campos con la variables del form
   */
  public void InicializarPanel( JWin zWin ) throws Exception {
  	JFormColumnResponsive zone1 = AddItemColumn(6);
  	JFormColumnResponsive zone2 = AddItemColumn(6);
			getWin().GetcDato().processGenerateQuestion();
			 
  	zone1.AddItemEdit(null, CHAR, OPT,"id").setHide(true);
  	zone1.AddItemEdit(null, CHAR, OPT,"game_id").setHide(true);
  	zone1.AddItemEdit(null, CHAR, OPT,"company").setHide(true);
  	zone1.AddItemCombo("Familia Preguntas", CHAR, REQ,"family_id").setSizeColumns(4 ).setRefreshForm(true);
    zone1.AddItemCombo("Tipo Pregunta", CHAR, REQ,"question_type_id").setSizeColumns(4 );
    zone1.AddItemCombo("Dificultad", CHAR, REQ,"difficulty_id").setSizeColumns(4 );
  	zone1.AddItemEdit("Sentencia", CHAR, REQ,"statement").setRefreshForm(true);
  	zone1.AddItemLabelData(CHAR,"error").setForeground("FF0000");
  	zone1.AddItemCombo("Estilo estético pregunta", CHAR, OPT,"style_image");
  	zone1.AddItemEdit("#Imagen 1", CHAR, OPT,"num_image",2);
  	zone1.AddItemEdit("Imagen 1", CHAR, OPT,"image",4);
  	zone1.AddItemEdit("#Imagen 2", CHAR, OPT,"num_image2",2);
  	zone1.AddItemEdit("Imagen 2", CHAR, OPT,"image2",4);
    
  	
  	zone2.AddItemHtml("Ejemplos",CHAR, OPT, "example").setVisible(getWin().GetcDato().isRendered()).SetReadOnly(true);

  	if (isAlta())
			return;
		autoBuildTabs(getInternalPanel(), zWin);
  } 
 
  @Override
  	public void checkControls(String sControlId) throws Exception {
  		if (sControlId.equals("statement") ) {
  			getWin().GetcDato().processGenerateQuestion();
  		}
  		super.checkControls(sControlId);
  	}
  
} 
