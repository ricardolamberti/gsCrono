package pss.game.models.preGeneratedQuestionOption;

import java.util.Date;
import pss.core.services.records.JRecord;
import pss.core.services.fields.*;

public class BizpreGeneratedQuestionOptionBase extends JRecord {

	protected JLong pId = new JLong();
	protected JLong pPreQuestionid = new JLong();
	protected JString pAnswerFormula = new JString();
	protected  JBoolean  pIscorrect = new  JBoolean ();
	protected JString pExtraData = new JString();
	protected JString pCompany = new JString();


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Getter & Setters methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	public void setId(long zValue) throws Exception {    pId.setValue(zValue);  }
	public long getId()	throws Exception {     return pId.getValue();  }
	public boolean isNullId() throws Exception { return  pId.isNull(); } 
	public void setNullToId() throws Exception {  pId.setNull(); } 
	public void setPreQuestionid(long zValue) throws Exception {    pPreQuestionid.setValue(zValue);  }
	public long getPreQuestionid()	throws Exception {     return pPreQuestionid.getValue();  }
	public boolean isNullPreQuestionid() throws Exception { return  pPreQuestionid.isNull(); } 
	public void setNullToPreQuestionid() throws Exception {  pPreQuestionid.setNull(); } 
	public void setAnswerFormula(String zValue) throws Exception {    pAnswerFormula.setValue(zValue);  }
	public String getAnswerFormula()	throws Exception {     return pAnswerFormula.getValue();  }
	public boolean isNullAnswerFormula() throws Exception { return  pAnswerFormula.isNull(); } 
	public void setNullToAnswerFormula() throws Exception {  pAnswerFormula.setNull(); } 
	public void setIscorrect( boolean  zValue) throws Exception {    pIscorrect.setValue(zValue);  }
	public  boolean  getIscorrect()	throws Exception {     return pIscorrect.getValue();  }
	public boolean isNullIscorrect() throws Exception { return  pIscorrect.isNull(); } 
	public void setNullToIscorrect() throws Exception {  pIscorrect.setNull(); } 
	public void setExtraDatos(String zValue) throws Exception {    pExtraData.setValue(zValue);  }
	public String getExtraDatos()	throws Exception {     return pExtraData.getValue();  }
	public boolean isNullExtraData() throws Exception { return  pExtraData.isNull(); } 
	public void setNullToExtraData() throws Exception {  pExtraData.setNull(); } 
	public void setCompany(String zValue) throws Exception {    pCompany.setValue(zValue);  }
	public String getCompany()	throws Exception {     return pCompany.getValue();  }
	public boolean isNullCompany() throws Exception { return  pCompany.isNull(); } 
	public void setNullToCompany() throws Exception {  pCompany.setNull(); } 


  /**
   * Constructor de la Clase
   */
  public BizpreGeneratedQuestionOptionBase() throws Exception {
  }


	public void createProperties() throws Exception {
		addItem( "id", pId );
		addItem( "pre_question_id", pPreQuestionid );
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
		addFixedItem( FIELD, "pre_question_id", "pre_question_id", true,false, 64 );
		addFixedItem( FIELD, "answer_formula", "answer_formula", true, true, 255 );
		addFixedItem( FIELD, "is_correct", "is_correct", true, true, 1 );
		addFixedItem( FIELD, "extra_data", "extra_data", true,false, 0 );
		addFixedItem( FIELD, "company", "company", true, true, 15 );
  }
  /**
   * Returns the table name
   */
	public String GetTable() { return "gms_pre_generated_question_option"; }


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
