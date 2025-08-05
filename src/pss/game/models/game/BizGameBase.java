package pss.game.models.game;

import java.util.Date;
import pss.core.services.records.JRecord;
import pss.core.services.fields.*;

public class BizGameBase extends JRecord {

	protected JLong pIdgame = new JLong();
	protected JString pGame = new JString();
	protected JString pCompany = new JString();


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Getter & Setters methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	public void setIdgame(long zValue) throws Exception {    pIdgame.setValue(zValue);  }
	public long getIdgame()	throws Exception {     return pIdgame.getValue();  }
	public boolean isNullIdgame() throws Exception { return  pIdgame.isNull(); } 
	public void setNullToIdgame() throws Exception {  pIdgame.setNull(); } 
	public void setGame(String zValue) throws Exception {    pGame.setValue(zValue);  }
	public String getGame()	throws Exception {     return pGame.getValue();  }
	public boolean isNullGame() throws Exception { return  pGame.isNull(); } 
	public void setNullToGame() throws Exception {  pGame.setNull(); } 
	public void setCompany(String zValue) throws Exception {    pCompany.setValue(zValue);  }
	public String getCompany()	throws Exception {     return pCompany.getValue();  }
	public boolean isNullCompany() throws Exception { return  pCompany.isNull(); } 
	public void setNullToCompany() throws Exception {  pCompany.setNull(); } 


  /**
   * Constructor de la Clase
   */
  public BizGameBase() throws Exception {
  }


	public void createProperties() throws Exception {
		addItem( "id_game", pIdgame );
		addItem( "game", pGame );
		addItem( "company", pCompany );
  }
  /**
   * Adds the fixed object properties
   */
	public void createFixedProperties() throws Exception {
		addFixedItem( KEY, "id_game", "id", false, false, 64 );
		addFixedItem( FIELD, "game", "Nombre", true,false, 250 );
		addFixedItem( FIELD, "company", "Compañia", true,false, 15 );
  }
  /**
   * Returns the table name
   */
	public String GetTable() { return "gms_game"; }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Functionality methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


  /**
   * Default read() method
   */
	public boolean read( long zIdgame ) throws Exception { 
		addFilter( "id_game",  zIdgame ); 
		return read(); 
  } 
}
