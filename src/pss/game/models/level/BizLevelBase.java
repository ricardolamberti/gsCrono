package pss.game.models.level;

import pss.core.services.fields.JLong;
import pss.core.services.fields.JString;
import pss.core.services.records.JRecord;
import pss.core.tools.JPair;
import pss.game.models.difficulty.GuiDifficulties;
import pss.game.models.game.GuiGames;

public class BizLevelBase extends JRecord {

	protected JLong pId = new JLong();
	protected JLong pLevelNumber = new JLong();
	protected JLong pGameId = new JLong();
	protected JLong pNumQuestions = new JLong();
	protected JLong pDifficultyId = new JLong();
	protected JString pDifficultyName = new JString();
	protected JString pCompany = new JString();


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Getter & Setters methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	public void setId(long zValue) throws Exception {    pId.setValue(zValue);  }
	public long getId()	throws Exception {     return pId.getValue();  }
	public boolean isNullId() throws Exception { return  pId.isNull(); } 
	public void setNullToId() throws Exception {  pId.setNull(); } 
	public void setLevelNumber(long zValue) throws Exception {    pLevelNumber.setValue(zValue);  }
	public long getLevelNumber()	throws Exception {     return pLevelNumber.getValue();  }
	public boolean isNullLevelNumber() throws Exception { return  pLevelNumber.isNull(); } 
	public void setNullToLevelNumber() throws Exception {  pLevelNumber.setNull(); } 
	public void setGameId(long zValue) throws Exception {    pGameId.setValue(zValue);  }
	public long getGameId()	throws Exception {     return pGameId.getValue();  }
	public boolean isNullGameId() throws Exception { return  pGameId.isNull(); } 
	public void setNullToGameId() throws Exception {  pGameId.setNull(); } 
	public void setNumQuestions(long zValue) throws Exception {    pNumQuestions.setValue(zValue);  }
	public long getNumQuestions()	throws Exception {     return pNumQuestions.getValue();  }
	public boolean isNullNumQuestions() throws Exception { return  pNumQuestions.isNull(); } 
	public void setNullToNumQuestions() throws Exception {  pNumQuestions.setNull(); } 
	public void setDifficultyId(long zValue) throws Exception {    pDifficultyId.setValue(zValue);  }
	public long getDifficultyId()	throws Exception {     return pDifficultyId.getValue();  }
	public boolean isNullDifficultyId() throws Exception { return  pDifficultyId.isNull(); } 
	public void setNullToDifficultyId() throws Exception {  pDifficultyId.setNull(); } 
	public void setCompany(String zValue) throws Exception {    pCompany.setValue(zValue);  }
	public String getCompany()	throws Exception {     return pCompany.getValue();  }
	public boolean isNullCompany() throws Exception { return  pCompany.isNull(); } 
	public void setNullToCompany() throws Exception {  pCompany.setNull(); } 


  /**
   * Constructor de la Clase
   */
  public BizLevelBase() throws Exception {
  }


	public void createProperties() throws Exception {
		addItem( "id", pId );
		addItem( "level_number", pLevelNumber );
		addItem( "game_id", pGameId );
		addItem( "num_questions", pNumQuestions );
		addItem( "difficulty_id", pDifficultyId );
		addItem( "difficulty_name", pDifficultyName );
		addItem( "company", pCompany );
  }
  /**
   * Adds the fixed object properties
   */
	public void createFixedProperties() throws Exception {
		addFixedItem( KEY, "id", "id", false, false, 64 );
		addFixedItem( FIELD, "level_number", "level_number", true, true, 32 );
		addFixedItem( FIELD, "game_id", "game_id", true,false, 64 );
		addFixedItem( FIELD, "num_questions", "num_questions", true, true, 32 );
		addFixedItem( FIELD, "difficulty_id", "difficulty_id", true,false, 64 );
		addFixedItem( FIELD, "company", "company", true, true, 15 );
		addFixedItem( FOREIGN, "difficulty_name", "difficulty_name", true,false, 250 );
  }
	
  @Override
  public void createControlProperties() throws Exception {
  	this.addControlsProperty("game_id", createControlCombo(GuiGames.class,"id_game", new JPair<String, String>("company","company") ));
  	this.addControlsProperty("difficulty_id", createControlCombo(GuiDifficulties.class,"id", new JPair<String, String>("company","company") ));
  	super.createControlProperties();
  }
  /**
   * Returns the table name
   */
	public String GetTable() { return "gms_level"; }

	@Override
	public String GetTableTemporal() throws Exception {
		String sql = "";
		sql += "select gms_level.*, gms_difficulty.name as difficulty_name \n" + 
				"from gms_level \n" +
				"join gms_difficulty on gms_difficulty.id=gms_level.difficulty_id\n";
			
		return "(" + sql + ") as " + GetTable();
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
	
	@Override
	protected boolean loadForeignFields() throws Exception {
		return true;
	}
}
