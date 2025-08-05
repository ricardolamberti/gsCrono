package pss.game.models.questionType;

import pss.core.services.fields.JLong;
import pss.core.services.fields.JString;
import pss.core.services.records.JRecord;
import pss.core.tools.JPair;
import pss.core.tools.collections.JCollectionFactory;
import pss.core.tools.collections.JMap;
import pss.core.win.JWins;
import pss.game.models.familyQuestion.GuiFamilyQuestions;
import pss.game.models.variable.BizVariableBase;
import pss.game.models.variable.logica.VariableLogicaListValue;
import pss.game.models.variable.logica.VariableLogicaRange;
import pss.game.models.variable.logica.VariableLogicaRangeFloat;

public class BizQuestionTypeBase extends JRecord {

	protected JLong pId = new JLong();
	protected JLong pGameId = new JLong();
	protected JLong pFamilyId = new JLong();
	protected JString pName = new JString();
	protected JString pType = new JString();
	protected JString pCompany = new JString();


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Getter & Setters methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	public void setId(long zValue) throws Exception {    pId.setValue(zValue);  }
	public long getId()	throws Exception {     return pId.getValue();  }
	public boolean isNullId() throws Exception { return  pId.isNull(); } 
	public void setNullToId() throws Exception {  pId.setNull(); } 
	public void setName(String zValue) throws Exception {    pName.setValue(zValue);  }
	public String getName()	throws Exception {     return pName.getValue();  }
	public boolean isNullName() throws Exception { return  pName.isNull(); } 
	public void setNullToName() throws Exception {  pName.setNull(); } 
	public void setCompany(String zValue) throws Exception {    pCompany.setValue(zValue);  }
	public String getCompany()	throws Exception {     return pCompany.getValue();  }
	public boolean isNullCompany() throws Exception { return  pCompany.isNull(); } 
	public void setNullToCompany() throws Exception {  pCompany.setNull(); } 
	public void setGameId(long zValue) throws Exception {    pGameId.setValue(zValue);  }
	public long getGameId()	throws Exception {     return pGameId.getValue();  }
	public boolean isNullGameId() throws Exception { return  pGameId.isNull(); } 
	public void setNullToGameId() throws Exception {  pGameId.setNull(); } 
	public void setTypeId(String zValue) throws Exception {    pType.setValue(zValue);  }
	public String getTypeId()	throws Exception {     return pType.getValue();  }
	public boolean isNullTypeId() throws Exception { return  pType.isNull(); } 
	public void setNullToTypeId() throws Exception {  pType.setNull(); } 
	
  public static final String SUMA = "SUMA";
  public static final String RESTA = "RESTA";
  public static final String MULTI = "MULTI";
  public static final String DIVISION = "DIVISION";
  public static final String MIX = "MIX";


	public static JMap<String, String> getTiposConocidos(JMap<String, String> cc) {
		cc.addElement(BizQuestionType.SUMA, "Suma");
		cc.addElement(BizQuestionType.RESTA, "Resta");
		cc.addElement(BizQuestionType.MULTI, "Multiplicación");
		cc.addElement(BizQuestionType.DIVISION, "División");
		cc.addElement(BizQuestionType.MIX, "Mix");
		return cc;
	}

	static JMap<String, String> types;

	public static JMap<String, String> getTypes() throws Exception {
		if (types != null)
			return types;
		JMap<String, String> maps = JCollectionFactory.createMap();
		return types = getTiposConocidos(maps);
	}

  /**
   * Constructor de la Clase
   */
  public BizQuestionTypeBase() throws Exception {
  }


	public void createProperties() throws Exception {
		addItem( "id", pId );
		addItem( "game_id", pGameId );
		addItem( "name", pName );
		addItem( "type", pType );
		addItem( "company", pCompany );
  }
  /**
   * Adds the fixed object properties
   */
	public void createFixedProperties() throws Exception {
		addFixedItem( KEY, "id", "id", false, false, 64 );
		addFixedItem( FIELD, "game_id", "game_id", true,false, 64 );
		addFixedItem( FIELD, "name", "name", true, true, 255 );
		addFixedItem( FIELD, "type", "type", true, true, 255 );
		addFixedItem( FIELD, "company", "company", true, true, 15 );
  }
  @Override
  public void createControlProperties() throws Exception {
   	this.addControlsProperty("type", createControlCombo(JWins.createVirtualWinsFromMap(getTypes()),null, null) );
  	super.createControlProperties();
  }
  /**
   * Returns the table name
   */
	public String GetTable() { return "gms_question_type"; }


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
