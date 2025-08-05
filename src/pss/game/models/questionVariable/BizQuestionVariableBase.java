package pss.game.models.questionVariable;

import pss.common.help.GuiQuestions;
import pss.core.services.fields.JLong;
import pss.core.services.fields.JString;
import pss.core.services.records.JRecord;
import pss.core.tools.JPair;
import pss.game.models.variable.GuiVariables;

public class BizQuestionVariableBase extends JRecord {

	protected JLong pId = new JLong();
	protected JLong pQuestionId = new JLong();
	protected JLong pVariableId = new JLong();
	protected JString pCompany = new JString();


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Getter & Setters methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	public void setId(long zValue) throws Exception {    pId.setValue(zValue);  }
	public long getId()	throws Exception {     return pId.getValue();  }
	public boolean isNullId() throws Exception { return  pId.isNull(); } 
	public void setNullToId() throws Exception {  pId.setNull(); } 
	public void setQuestionId(long zValue) throws Exception {    pQuestionId.setValue(zValue);  }
	public long getQuestionId()	throws Exception {     return pQuestionId.getValue();  }
	public boolean isNullQuestionId() throws Exception { return  pQuestionId.isNull(); } 
	public void setNullToQuestionId() throws Exception {  pQuestionId.setNull(); } 
	public void setVariableId(long zValue) throws Exception {    pVariableId.setValue(zValue);  }
	public long getVariableId()	throws Exception {     return pVariableId.getValue();  }
	public boolean isNullVariableId() throws Exception { return  pVariableId.isNull(); } 
	public void setNullToVariableId() throws Exception {  pVariableId.setNull(); } 
	public void setCompany(String zValue) throws Exception {    pCompany.setValue(zValue);  }
	public String getCompany()	throws Exception {     return pCompany.getValue();  }
	public boolean isNullCompany() throws Exception { return  pCompany.isNull(); } 
	public void setNullToCompany() throws Exception {  pCompany.setNull(); } 


  /**
   * Constructor de la Clase
   */
  public BizQuestionVariableBase() throws Exception {
  }


	public void createProperties() throws Exception {
		addItem( "id", pId );
		addItem( "question_id", pQuestionId );
		addItem( "variable_id", pVariableId );
		addItem( "company", pCompany );
  }
  /**
   * Adds the fixed object properties
   */
	public void createFixedProperties() throws Exception {
		addFixedItem( KEY, "id", "id", false, false, 64 );
		addFixedItem( FIELD, "question_id", "question_id", true,false, 64 );
		addFixedItem( FIELD, "variable_id", "variable_id", true,false, 64 );
		addFixedItem( FIELD, "company", "company", true, true, 15 );
  }
	
  @Override
  public void createControlProperties() throws Exception {
  	this.addControlsProperty("question_id", createControlCombo(GuiQuestions.class,"id", new JPair<String, String>("company","company") ));
  	this.addControlsProperty("variable_id", createControlCombo(GuiVariables.class,"id", new JPair<String, String>("company","company") ));
  	super.createControlProperties();
  }
  /**
   * Returns the table name
   */
	public String GetTable() { return "gms_question_variable"; }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Functionality methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


  /**
   * Default read() method
   */
	public boolean read( long zId ) throws Exception { 
		addFilter( "id",  zId ); 
		return read(); 
  } 
}
