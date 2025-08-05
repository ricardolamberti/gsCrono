package pss.game.models.questionOption;

import pss.core.services.fields.JBoolean;
import pss.core.services.fields.JLong;
import pss.core.services.fields.JString;
import pss.core.services.records.JRecord;
import pss.core.tools.JPair;
import pss.game.models.question.GuiQuestions;

public class BizQuestionOptionBase extends JRecord {

	protected JLong pId = new JLong();
	protected JLong pQuestionId = new JLong();
	protected JString pAnswerFormula = new JString();
	protected JBoolean pIscorrect = new JBoolean();
	protected JString pExtraData = new JString();
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
	public void setAnswerFormula(String zValue) throws Exception {    pAnswerFormula.setValue(zValue);  }
	public String getAnswerFormula()	throws Exception {     return pAnswerFormula.getValue();  }
	public boolean isNullAnswerFormula() throws Exception { return  pAnswerFormula.isNull(); } 
	public void setNullToAnswerFormula() throws Exception {  pAnswerFormula.setNull(); } 
	public void setIscorrect(boolean zValue) throws Exception {    pIscorrect.setValue(zValue);  }
	public boolean getIscorrect()	throws Exception {     return pIscorrect.getValue();  }
	public boolean isNullIscorrect() throws Exception { return  pIscorrect.isNull(); } 
	public void setNullToIscorrect() throws Exception {  pIscorrect.setNull(); } 
	public void setExtraData(String zValue) throws Exception {    pExtraData.setValue(zValue);  }
	public String getExtraDatos()	throws Exception {     return pExtraData.getValue();  }
	public boolean isNullExtraDatos() throws Exception { return  pExtraData.isNull(); } 
	public void setNullToExtraData() throws Exception {  pExtraData.setNull(); } 
	public void setCompany(String zValue) throws Exception {    pCompany.setValue(zValue);  }
	public String getCompany()	throws Exception {     return pCompany.getValue();  }
	public boolean isNullCompany() throws Exception { return  pCompany.isNull(); } 
	public void setNullToCompany() throws Exception {  pCompany.setNull(); } 


  /**
   * Constructor de la Clase
   */
  public BizQuestionOptionBase() throws Exception {
  }


	public void createProperties() throws Exception {
		addItem( "id", pId );
		addItem( "question_id", pQuestionId );
		addItem( "answer_formula", pAnswerFormula );
		addItem( "is_correct", pIscorrect );
		addItem( "extra_data", pExtraData );
		addItem( "company", pCompany );
  }
  /**
   * Adds the fixed object properties
   */
	public void createFixedProperties() throws Exception {
		addFixedItem( KEY, "id", "id", false, false, 64 );
		addFixedItem( FIELD, "question_id", "question_id", true,false, 64 );
		addFixedItem( FIELD, "answer_formula", "answer_formula", true, true, 255 );
		addFixedItem( FIELD, "is_correct", "is_correct", true, true, 1 );
		addFixedItem( FIELD, "extra_data", "extra_data", true,false, 1000 );
		addFixedItem( FIELD, "company", "company", true, true, 15 );
  }
	
  @Override
  public void createControlProperties() throws Exception {
  	this.addControlsProperty("question_id", createControlCombo(GuiQuestions.class,"id", new JPair<String, String>("company","company") ));
  	super.createControlProperties();
  }
  /**
   * Returns the table name
   */
	public String GetTable() { return "gms_question_option"; }


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
