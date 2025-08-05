package pss.game.models.difficulty;

import pss.core.services.fields.JLong;
import pss.core.services.fields.JString;
import pss.core.services.records.JRecord;
import pss.core.tools.JPair;
import pss.game.models.game.BizGameBase;
import pss.game.models.game.GuiGames;

public class BizDifficultyBase extends JRecord {

	protected JLong pId = new JLong();
	protected JLong pGameId = new JLong();
	protected JString pName = new JString();
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
	

  /**
   * Constructor de la Clase
   */
  public BizDifficultyBase() throws Exception {
  }


	public void createProperties() throws Exception {
		addItem( "id", pId );
		addItem( "id_game", pGameId );
	  addItem( "name", pName );
		addItem( "company", pCompany );
  }
  /**
   * Adds the fixed object properties
   */
	public void createFixedProperties() throws Exception {
		addFixedItem( KEY, "id", "id", false, false, 64 );
		addFixedItem( KEY, "id_game", "Juego", true, true, 64 );
		addFixedItem( FIELD, "name", "Nombre", true, true, 50 );
		addFixedItem( FIELD, "company", "company", true, true, 15 );
  }
	
  @Override
  public void createControlProperties() throws Exception {
  	this.addControlsProperty("game_id", createControlCombo(GuiGames.class,"id_game", new JPair<String, String>("company","company") ));
  	super.createControlProperties();
  }
  /**
   * Returns the table name
   */
	public String GetTable() { return "gms_difficulty"; }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Functionality methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	transient BizGameBase objGame;

	public BizGameBase getObjGame() throws Exception {
		if (isNullGameId())
			return null;
		if (objGame != null)
			return objGame;
		BizGameBase fam = new BizGameBase();
		fam.dontThrowException(true);
		if (!fam.read(getGameId()))
			return null;
		return objGame = fam;
	}


  /**
   * Default read() method
   */
	public boolean read( long zId ) throws Exception { 
		addFilter( "id",  zId ); 
		return read(); 
  } 
}
