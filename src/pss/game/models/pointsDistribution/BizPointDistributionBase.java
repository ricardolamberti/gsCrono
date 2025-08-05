package pss.game.models.pointsDistribution;

import pss.core.services.fields.JFloat;
import pss.core.services.fields.JLong;
import pss.core.services.fields.JString;
import pss.core.services.records.JRecord;
import pss.core.tools.JPair;
import pss.game.models.difficulty.GuiDifficulties;

public class BizPointDistributionBase extends JRecord {

	protected JLong pId = new JLong();
	protected JLong pDifficultyId = new JLong();
	protected JFloat pCorrectPercentage = new JFloat();
	protected JFloat pPointsAwarded = new JFloat();
	protected JString pCompany = new JString();


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Getter & Setters methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	public void setId(long zValue) throws Exception {    pId.setValue(zValue);  }
	public long getId()	throws Exception {     return pId.getValue();  }
	public boolean isNullId() throws Exception { return  pId.isNull(); } 
	public void setNullToId() throws Exception {  pId.setNull(); } 
	public void setDifficultyId(long zValue) throws Exception {    pDifficultyId.setValue(zValue);  }
	public long getDifficultyId()	throws Exception {     return pDifficultyId.getValue();  }
	public boolean isNullDifficultyId() throws Exception { return  pDifficultyId.isNull(); } 
	public void setNullToDifficultyId() throws Exception {  pDifficultyId.setNull(); } 
	public void setCorrectPercentage(double zValue) throws Exception {    pCorrectPercentage.setValue(zValue);  }
	public double getCorrectPercentage()	throws Exception {     return pCorrectPercentage.getValue();  }
	public boolean isNullCorrectPercentage() throws Exception { return  pCorrectPercentage.isNull(); } 
	public void setNullToCorrectPercentage() throws Exception {  pCorrectPercentage.setNull(); } 
	public void setPointsAwarded(double zValue) throws Exception {    pPointsAwarded.setValue(zValue);  }
	public double getPointsAwarded()	throws Exception {     return pPointsAwarded.getValue();  }
	public boolean isNullPointsAwarded() throws Exception { return  pPointsAwarded.isNull(); } 
	public void setNullToPointsAwarded() throws Exception {  pPointsAwarded.setNull(); } 
	public void setCompany(String zValue) throws Exception {    pCompany.setValue(zValue);  }
	public String getCompany()	throws Exception {     return pCompany.getValue();  }
	public boolean isNullCompany() throws Exception { return  pCompany.isNull(); } 
	public void setNullToCompany() throws Exception {  pCompany.setNull(); } 


  /**
   * Constructor de la Clase
   */
  public BizPointDistributionBase() throws Exception {
  }


	public void createProperties() throws Exception {
		addItem( "id", pId );
		addItem( "difficulty_id", pDifficultyId );
		addItem( "correct_percentage", pCorrectPercentage );
		addItem( "points_awarded", pPointsAwarded );
		addItem( "company", pCompany );
  }
  /**
   * Adds the fixed object properties
   */
	public void createFixedProperties() throws Exception {
		addFixedItem( KEY, "id", "id", false, false, 64 );
		addFixedItem( FIELD, "difficulty_id", "difficulty_id", true,false, 64 );
		addFixedItem( FIELD, "correct_percentage", "correct_percentage", true, true, 5,2 );
		addFixedItem( FIELD, "points_awarded", "points_awarded", true, true, 10,2 );
		addFixedItem( FIELD, "company", "company", true, true, 15 );
  }
	
  @Override
  public void createControlProperties() throws Exception {
  	this.addControlsProperty("difficulty_id", createControlCombo(GuiDifficulties.class,"id", new JPair<String, String>("company","company") ));
  	super.createControlProperties();
  }
  /**
   * Returns the table name
   */
	public String GetTable() { return "gms_points_distribution"; }


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
