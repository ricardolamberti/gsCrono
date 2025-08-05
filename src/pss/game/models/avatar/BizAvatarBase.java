package pss.game.models.avatar;

import java.util.Date;
import pss.core.services.records.JRecord;
import pss.core.services.fields.*;

public class BizAvatarBase extends JRecord {

	protected JLong pIdavatar = new JLong();
	protected JString pAvatar = new JString();
	protected JString pCompany = new JString();


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Getter & Setters methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	public void setIdavatar(long zValue) throws Exception {    pIdavatar.setValue(zValue);  }
	public long getIdavatar()	throws Exception {     return pIdavatar.getValue();  }
	public boolean isNullIdavatar() throws Exception { return  pIdavatar.isNull(); } 
	public void setNullToIdavatar() throws Exception {  pIdavatar.setNull(); } 
	public void setAvatar(String zValue) throws Exception {    pAvatar.setValue(zValue);  }
	public String getAvatar()	throws Exception {     return pAvatar.getValue();  }
	public boolean isNullAvatar() throws Exception { return  pAvatar.isNull(); } 
	public void setNullToAvatar() throws Exception {  pAvatar.setNull(); } 
	public void setCompany(String zValue) throws Exception {    pCompany.setValue(zValue);  }
	public String getCompany()	throws Exception {     return pCompany.getValue();  }
	public boolean isNullCompany() throws Exception { return  pCompany.isNull(); } 
	public void setNullToCompany() throws Exception {  pCompany.setNull(); } 


  /**
   * Constructor de la Clase
   */
  public BizAvatarBase() throws Exception {
  }


	public void createProperties() throws Exception {
		addItem( "id_avatar", pIdavatar );
		addItem( "avatar", pAvatar );
		addItem( "company", pCompany );
  }
  /**
   * Adds the fixed object properties
   */
	public void createFixedProperties() throws Exception {
		addFixedItem( KEY, "id_avatar", "id", false, false, 64 );
		addFixedItem( FIELD, "avatar", "Avatar", true,false, 250 );
		addFixedItem( FIELD, "company", "Compañia", true,false, 15 );
  }
  /**
   * Returns the table name
   */
	public String GetTable() { return "gms_avatar"; }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Functionality methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


  /**
   * Default read() method
   */
	public boolean read( long zIdavatar ) throws Exception { 
		addFilter( "id_avatar",  zIdavatar ); 
		return read(); 
  } 
}
