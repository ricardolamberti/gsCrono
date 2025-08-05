package pss.game.models.familyQuestion;

import pss.core.services.fields.JLong;
import pss.core.services.fields.JString;
import pss.core.services.records.JRecord;
import pss.core.tools.JPair;
import pss.core.tools.collections.JCollectionFactory;
import pss.core.tools.collections.JMap;
import pss.core.win.JWins;
import pss.game.models.familyQuestion.logica.FamiliaLogicaBase;
import pss.game.models.familyQuestion.logica.IFamiliaLogica;
import pss.game.models.familyQuestion.logica.IFamiliaLogicaMultiple;
import pss.game.models.game.GuiGames;

public class BizFamilyQuestionBase extends JRecord {

	protected JLong pId = new JLong();
	protected JString pName = new JString();
	protected JLong pGameId = new JLong();
	protected JString pCompany = new JString();
	protected JString pType = new JString();


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
	public void setGameId(long zValue) throws Exception {    pGameId.setValue(zValue);  }
	public long getGameId()	throws Exception {     return pGameId.getValue();  }
	public boolean isNullGameId() throws Exception { return  pGameId.isNull(); } 
	public void setNullToGameId() throws Exception {  pGameId.setNull(); } 
	public void setCompany(String zValue) throws Exception {    pCompany.setValue(zValue);  }
	public String getCompany()	throws Exception {     return pCompany.getValue();  }
	public boolean isNullCompany() throws Exception { return  pCompany.isNull(); } 
	public void setNullToCompany() throws Exception {  pCompany.setNull(); } 
	public void setType(String zValue) throws Exception {    pType.setValue(zValue);  }
	public String getType()	throws Exception {     return pType.getValue();  }
	public boolean isNullType() throws Exception { return  pType.isNull(); } 
	public void setNullToType() throws Exception {  pType.setNull(); } 

  public static final String SIMPLE = "SIMPLE";
  public static final String MULTIPLE = "MULTIPLE";

	public static Class variableLogicasClasesConocidas(String id) {
		if (id.equals(BizFamilyQuestionBase.SIMPLE)) return FamiliaLogicaBase.class;
		if (id.equals(BizFamilyQuestionBase.MULTIPLE)) return IFamiliaLogicaMultiple.class;
		return null;
	}

	public static JMap<String, String> variableLogicasClasesConocidasMap(JMap<String, String> cc) {
		cc.addElement(BizFamilyQuestionBase.SIMPLE, "Simple");
		cc.addElement(BizFamilyQuestionBase.MULTIPLE, "Multiple");
		return cc;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Functionality methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	static JMap<String, String> logicas;

	public static JMap<String, String> getLogicas() throws Exception {
		if (logicas != null)
			return logicas;
		JMap<String, String> maps = JCollectionFactory.createMap();
		return logicas = variableLogicasClasesConocidasMap(maps);
	}

	public static Class getLogicasClass(String id) throws Exception {
		return variableLogicasClasesConocidas(id);
	}
	
	public IFamiliaLogica getObjLogica() throws Exception {
		if (isNullType()) return null;
		return (IFamiliaLogica)getLogicasClass(getType()).newInstance();
	}

  /**
   * Constructor de la Clase
   */
  public BizFamilyQuestionBase() throws Exception {
  }


	public void createProperties() throws Exception {
		addItem( "id", pId );
		addItem( "type", pType );
		addItem( "name", pName );
		addItem( "game_id", pGameId );
		addItem( "company", pCompany );
  }
  /**
   * Adds the fixed object properties
   */
	public void createFixedProperties() throws Exception {
		addFixedItem( KEY, "id", "id", false, false, 64 );
		addFixedItem( FIELD, "type", "type", true, true, 0 );
		addFixedItem( FIELD, "name", "Nombre", true, true, 255 );
		addFixedItem( FIELD, "game_id", "Juego", true,false, 64 );
		addFixedItem( FIELD, "company", "company", true, true, 15 );
  }
  /**
   * Returns the table name
   */
	public String GetTable() { return "gms_family"; }

  @Override
  public void createControlProperties() throws Exception {
		this.addControlsProperty("game_id", createControlCombo(GuiGames.class, "id_game", new JPair<String, String>("company", "company")));
		this.addControlsProperty("type", createControlCombo(JWins.createVirtualWinsFromMap(getLogicas()), null, null));
		super.createControlProperties();
	}
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
