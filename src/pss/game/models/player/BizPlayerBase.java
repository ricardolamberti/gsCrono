package pss.game.models.player;

import java.util.Date;

import pss.core.services.fields.JDate;
import pss.core.services.fields.JLong;
import pss.core.services.fields.JString;
import pss.core.services.records.JRecord;
import pss.core.tools.JPair;
import pss.game.models.avatar.BizAvatarBase;
import pss.game.models.avatar.GuiAvatars;

public class BizPlayerBase extends JRecord {

	protected JLong pIdplayer = new JLong();
	protected JString pName = new JString();
	protected JString pPassword = new JString();
	protected JLong pIdavatar = new JLong();
	protected JDate pBirthday = new JDate();
	protected JString pCompany = new JString();


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Getter & Setters methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	public void setIdplayer(long zValue) throws Exception {    pIdplayer.setValue(zValue);  }
	public long getIdplayer()	throws Exception {     return pIdplayer.getValue();  }
	public boolean isNullIdplayer() throws Exception { return  pIdplayer.isNull(); } 
	public void setNullToIdplayer() throws Exception {  pIdplayer.setNull(); } 
	public void setName(String zValue) throws Exception {    pName.setValue(zValue);  }
	public String getName()	throws Exception {     return pName.getValue();  }
	public boolean isNullName() throws Exception { return  pName.isNull(); } 
	public void setNullToName() throws Exception {  pName.setNull(); } 
	public void setPassword(String zValue) throws Exception {    pPassword.setValue(zValue);  }
	public String getPassword()	throws Exception {     return pPassword.getValue();  }
	public boolean isNullPassword() throws Exception { return  pPassword.isNull(); } 
	public void setNullToPassword() throws Exception {  pPassword.setNull(); } 
	public void setIdavatar(long zValue) throws Exception {    pIdavatar.setValue(zValue);  }
	public long getIdavatar()	throws Exception {     return pIdavatar.getValue();  }
	public boolean isNullIdavatar() throws Exception { return  pIdavatar.isNull(); } 
	public void setNullToIdavatar() throws Exception {  pIdavatar.setNull(); } 
	public void setBirthday(Date zValue) throws Exception {    pBirthday.setValue(zValue);  }
	public Date getBirthday()	throws Exception {     return pBirthday.getValue();  }
	public boolean isNullBirthday() throws Exception { return  pBirthday.isNull(); } 
	public void setNullToBirthday() throws Exception {  pBirthday.setNull(); } 
	public void setCompany(String zValue) throws Exception {    pCompany.setValue(zValue);  }
	public String getCompany()	throws Exception {     return pCompany.getValue();  }
	public boolean isNullCompany() throws Exception { return  pCompany.isNull(); } 
	public void setNullToCompany() throws Exception {  pCompany.setNull(); } 


  /**
   * Constructor de la Clase
   */
  public BizPlayerBase() throws Exception {
  }


	public void createProperties() throws Exception {
		addItem( "id_player", pIdplayer );
		addItem( "name", pName );
		addItem( "password", pPassword );
		addItem( "id_avatar", pIdavatar );
		addItem( "birthday", pBirthday );
		addItem( "company", pCompany );
  }
  /**
   * Adds the fixed object properties
   */
	public void createFixedProperties() throws Exception {
		addFixedItem( KEY, "id_player", "id", false, false, 64 );
		addFixedItem( FIELD, "name", "Nombre", true, true, 250 );
		addFixedItem( FIELD, "password", "clave", true, true, 250 );
		addFixedItem( FIELD, "id_avatar", "Avatar", true, true, 64 );
		addFixedItem( FIELD, "birthday", "Fecha cumpleaños", true,false, 10 );
		addFixedItem( FIELD, "company", "Compañia", true,false, 15 );
  }
	
  @Override
  public void createControlProperties() throws Exception {
  	this.addControlsProperty("id_avatar", createControlCombo(GuiAvatars.class,"id_avatar", new JPair<String, String>("company","company") ));
  	super.createControlProperties();
  }
  /**
   * Returns the table name
   */
	public String GetTable() { return "gms_player"; }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Functionality methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	transient BizAvatarBase objAvatar;

	public BizAvatarBase getObjAvatar() throws Exception {
		if (isNullIdavatar())
			return null;
		if (objAvatar != null)
			return objAvatar;
		BizAvatarBase fam = new BizAvatarBase();
		fam.dontThrowException(true);
		if (!fam.read(getIdavatar()))
			return null;
		return objAvatar = fam;
	}


  /**
   * Default read() method
   */
	public boolean read( long zIdplayer ) throws Exception { 
		addFilter( "id_player",  zIdplayer ); 
		return read(); 
  } 
	
	public boolean readByName( String name ) throws Exception { 
		addFilter( "name",  name ); 
		return read(); 
  } 
}
