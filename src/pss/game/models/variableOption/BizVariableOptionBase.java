package pss.game.models.variableOption;

import java.util.Date;
import pss.core.services.records.JRecord;
import pss.game.models.variable.BizVariableBase;
import pss.core.services.fields.*;

public class BizVariableOptionBase extends JRecord {

	protected JLong pId = new JLong();
	protected JLong pOrden = new JLong();
	protected JLong pVariableId = new JLong();
	protected JString pValueanswer = new JString();
	protected JString pValuequestion = new JString();
	protected JString pValueanswers = new JString();
	protected JString pValuequestions = new JString();
	protected JString pExtradata = new JString();
	protected  JBoolean  pCorrect = new  JBoolean ();
	protected JString pCompany = new JString();
	protected JString pImagen = new JString();


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Getter & Setters methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	public void setId(long zValue) throws Exception {    pId.setValue(zValue);  }
	public long getId()	throws Exception {     return pId.getValue();  }
	public boolean isNullId() throws Exception { return  pId.isNull(); } 
	public void setNullToId() throws Exception {  pId.setNull(); } 
	public void setOrden(long zValue) throws Exception {    pOrden.setValue(zValue);  }
	public long getOrden()	throws Exception {     return pOrden.getValue();  }
	public void setVariableId(long zValue) throws Exception {    pVariableId.setValue(zValue);  }
	public long getVariableId()	throws Exception {     return pVariableId.getValue();  }
	public boolean isNullVariableId() throws Exception { return  pVariableId.isNull(); } 
	public void setNullToVariableId() throws Exception {  pVariableId.setNull(); } 
	public void setValueanswer(String zValue) throws Exception {    pValueanswer.setValue(zValue);  }
	public String getValueanswer()	throws Exception {     return pValueanswer.getValue();  }
	public boolean isNullValueanswer() throws Exception { return  pValueanswer.isNull(); } 
	public void setNullToValueanswer() throws Exception {  pValueanswer.setNull(); } 
	public void setValuequestion(String zValue) throws Exception {    pValuequestion.setValue(zValue);  }
	public String getValuequestion()	throws Exception {     return pValuequestion.getValue();  }
	public boolean isNullValuequestion() throws Exception { return  pValuequestion.isNull(); } 
	public void setNullToValuequestion() throws Exception {  pValuequestion.setNull(); } 
	public String getValueanswers()	throws Exception {     return pValueanswers.getValue();  }
	public void setValueanswers(String zValue) throws Exception {    pValueanswers.setValue(zValue);  }
	public boolean isNullValueanswers() throws Exception { return  pValueanswers.isNull(); } 
	public void setNullToValueanswers() throws Exception {  pValueanswers.setNull(); } 
	public void setValuequestions(String zValue) throws Exception {    pValuequestions.setValue(zValue);  }
	public String getValuequestions()	throws Exception {     return pValuequestions.getValue();  }
	public boolean isNullValuequestions() throws Exception { return  pValuequestions.isNull(); } 
	public void setNullToValuequestions() throws Exception {  pValuequestions.setNull(); } 
	public void setExtradata(String zValue) throws Exception {    pExtradata.setValue(zValue);  }
	public String getExtradata()	throws Exception {     return pExtradata.getValue();  }
	public boolean isNullExtradata() throws Exception { return  pExtradata.isNull(); } 
	public void setNullToExtradata() throws Exception {  pExtradata.setNull(); } 
	public void setCorrect( boolean  zValue) throws Exception {    pCorrect.setValue(zValue);  }
	public  boolean  getCorrect()	throws Exception {     return pCorrect.getValue();  }
	public boolean isNullCorrect() throws Exception { return  pCorrect.isNull(); } 
	public void setNullToCorrect() throws Exception {  pCorrect.setNull(); } 
	public void setCompany(String zValue) throws Exception {    pCompany.setValue(zValue);  }
	public String getCompany()	throws Exception {     return pCompany.getValue();  }
	public boolean isNullCompany() throws Exception { return  pCompany.isNull(); } 
	public void setNullToCompany() throws Exception {  pCompany.setNull(); } 
	public void setImagen(String zValue) throws Exception {    pImagen.setValue(zValue);  }
	public String getImagen()	throws Exception {     return pImagen.getValue();  }
	

  /**
   * Constructor de la Clase
   */
  public BizVariableOptionBase() throws Exception {
  }
  public BizVariableOptionBase(String value) throws Exception {
  	setValuequestion(value);
  	setValuequestions(value);
  	setValueanswer(value);
  	setValueanswers(value);
  }


	public void createProperties() throws Exception {
		addItem( "id", pId );
		addItem( "orden", pOrden );
		addItem( "variable_id", pVariableId );
		addItem( "valueanswer", pValueanswer );
		addItem( "valuequestion", pValuequestion );
		addItem( "valueanswers", pValueanswers );
		addItem( "valuequestions", pValuequestions );
		addItem( "company", pCompany );
		addItem( "imagen", pImagen );
  }
  /**
   * Adds the fixed object properties
   */
	public void createFixedProperties() throws Exception {
		addFixedItem( KEY, "id", "id", false, false, 64 );
		addFixedItem( FIELD, "orden", "orden", true,false, 18 );
		addFixedItem( FIELD, "variable_id", "variable_id", true,false, 64 );
		addFixedItem( FIELD, "valueanswer", "valueanswer", true,false, 255 );
		addFixedItem( FIELD, "valuequestion", "valuequestion", true, true, 255 );
		addFixedItem( FIELD, "valueanswers", "valueanswer", true,false, 255 );
		addFixedItem( FIELD, "valuequestions", "valuequestion", true, false, 255 );
		addFixedItem( FIELD, "company", "company", true, true, 15 );
		addFixedItem( FIELD, "imagen", "imagen", true, true, 255 );
		  }
  /**
   * Returns the table name
   */
	public String GetTable() { return "gms_variable_option"; }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Functionality methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	transient BizVariableBase objVariable;
	
	public BizVariableBase getObjVariable()  throws Exception {
		if (isNullVariableId()) return null;
		if (objVariable!=null) return objVariable;
		BizVariableBase var = new BizVariableBase();
		var.dontThrowException(true);
		if (!var.read(getVariableId())) return null;
		return objVariable=var;
	}

	public String getValue(boolean question,boolean plural)	throws Exception {
		if (!question && plural && !isNullValueanswers()) return getValueanswers();
		if (!question && !plural && !isNullValueanswer()) return getValueanswer();
		if (plural&& !isNullValuequestions()) return getValuequestions();
		return getValuequestion();
		
	}
  /**
   * Default read() method
   */
	public boolean read( long zId ) throws Exception { 
		addFilter( "id",  zId ); 
		return read(); 
  } 
	public boolean readByOrden( long zIdVar ,long zorden) throws Exception { 
		addFilter( "variable_id",  zIdVar ); 
		addFilter( "orden",  zorden ); 
		return read(); 
  } 
	
	@Override
	public void processInsert() throws Exception {
		super.processInsert();
		getObjVariable() .processReordenar();
	}
	
	@Override
	public void processDelete() throws Exception {
		super.processDelete();
		getObjVariable() .processReordenar();
	}
	
	@Override
	public void processUpdate() throws Exception {
		super.processUpdate();
		getObjVariable() .processReordenar();
	}
}
