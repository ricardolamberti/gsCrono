package pss.game.models.preGeneratedQuestions;


import pss.core.services.fields.JBoolean;
import pss.core.services.fields.JLong;
import pss.core.services.fields.JString;
import pss.core.services.records.JRecord;
import pss.core.tools.JPair;
import pss.game.models.difficulty.GuiDifficulties;
import pss.game.models.gameplayer.GuiGamePlayers;
import pss.game.models.level.GuiLevels;
import pss.game.models.question.GuiQuestions;
import pss.game.models.questionType.BizQuestionTypeBase;
import pss.game.models.questionType.GuiQuestionTypes;

public class BizPreGeneratedQuestionBase extends JRecord {

	protected JLong pId = new JLong();
	protected JLong pGameplayerId = new JLong();
	protected JLong pLevelId = new JLong();
	protected JLong pQuestionId = new JLong();
	protected JString pRenderedQuestion = new JString();
	protected JLong pDifficultyId = new JLong();
	protected JString pCompany = new JString();
	protected JBoolean pUsed = new JBoolean();
	protected JString pDifficultyName = new JString();
	protected JString pStyleImage = new JString();
	protected JLong pNumImage1 = new JLong();
	protected JString pImage1 = new JString();
	protected JLong pNumImage2 = new JLong();
	protected JString pImage2 = new JString();
	protected JLong pQuestionTypeid = new JLong();
	protected JString pQuestionTypeName = new JString();


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Getter & Setters methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	public void setId(long zValue) throws Exception {    pId.setValue(zValue);  }
	public long getId()	throws Exception {     return pId.getValue();  }
	public boolean isNullId() throws Exception { return  pId.isNull(); } 
	public void setNullToId() throws Exception {  pId.setNull(); } 
	public void setGameplayerId(long zValue) throws Exception {    pGameplayerId.setValue(zValue);  }
	public long getGameplayerId()	throws Exception {     return pGameplayerId.getValue();  }
	public boolean isNullGameplayerId() throws Exception { return  pGameplayerId.isNull(); } 
	public void setNullToGameplayerId() throws Exception {  pGameplayerId.setNull(); } 
	public void setLevelId(long zValue) throws Exception {    pLevelId.setValue(zValue);  }
	public long getLevelId()	throws Exception {     return pLevelId.getValue();  }
	public boolean isNullLevelId() throws Exception { return  pLevelId.isNull(); } 
	public void setNullToLevelId() throws Exception {  pLevelId.setNull(); } 
	public void setQuestionId(long zValue) throws Exception {    pQuestionId.setValue(zValue);  }
	public long getQuestionId()	throws Exception {     return pQuestionId.getValue();  }
	public boolean isNullQuestionId() throws Exception { return  pQuestionId.isNull(); } 
	public void setNullToQuestionId() throws Exception {  pQuestionId.setNull(); } 
	public void setRenderedQuestion(String zValue) throws Exception {    pRenderedQuestion.setValue(zValue);  }
	public String getRenderedQuestion()	throws Exception {     return pRenderedQuestion.getValue();  }
	public boolean isNullRenderedQuestion() throws Exception { return  pRenderedQuestion.isNull(); } 
	public void setNullToRenderedQuestion() throws Exception {  pRenderedQuestion.setNull(); } 
	public void setDifficultyId(long zValue) throws Exception {    pDifficultyId.setValue(zValue);  }
	public long getDifficultyId()	throws Exception {     return pDifficultyId.getValue();  }
	public boolean isNullDifficultyId() throws Exception { return  pDifficultyId.isNull(); } 
	public void setNullToDifficultyId() throws Exception {  pDifficultyId.setNull(); } 
	public void setCompany(String zValue) throws Exception {    pCompany.setValue(zValue);  }
	public String getCompany()	throws Exception {     return pCompany.getValue();  }
	public boolean isNullCompany() throws Exception { return  pCompany.isNull(); } 
	public void setNullToCompany() throws Exception {  pCompany.setNull(); } 
	public void setUsed(boolean zValue) throws Exception {    pUsed.setValue(zValue);  }
	public boolean isUsed()	throws Exception {     return pUsed.getValue();  }
	public void setNumImage1(long zValue) throws Exception {   pNumImage1.setValue(zValue);  }
	public long getNumImage1()	throws Exception {     return pNumImage1.getValue();  }
	public void setNumImage2(long zValue) throws Exception {   pNumImage2.setValue(zValue);  }
	public long getNumImage2()	throws Exception {     return pNumImage2.getValue();  }
	public void setImage1(String zValue) throws Exception {    pImage1.setValue(zValue);  }
	public String getImage1()	throws Exception {     return pImage1.getValue();  }
	public void setImage2(String zValue) throws Exception {    pImage2.setValue(zValue);  }
	public String getImage2()	throws Exception {     return pImage2.getValue();  }
	public void setStyleImage(String zValue) throws Exception {    pStyleImage.setValue(zValue);  }
	public String getStyleImage()	throws Exception {     return pStyleImage.getValue();  }
	public void setQuestionTypeid(long zValue) throws Exception {
		pQuestionTypeid.setValue(zValue);
	}

	public long getQuestionTypeid() throws Exception {
		return pQuestionTypeid.getValue();
	}

	public boolean isNullQuestionTypeid() throws Exception {
		return pQuestionTypeid.isNull();
	}

	public void setNullToQuestionTypeid() throws Exception {
		pQuestionTypeid.setNull();
	}

	transient BizQuestionTypeBase objQuestionType;

	public BizQuestionTypeBase getObjQuestionType() throws Exception {
		if (isNullQuestionTypeid())
			return null;
		if (objQuestionType != null)
			return objQuestionType;
		BizQuestionTypeBase fam = new BizQuestionTypeBase();
		fam.dontThrowException(true);
		if (!fam.read(getQuestionTypeid()))
			return null;
		return objQuestionType = fam;
	}

  /**
   * Constructor de la Clase
   */
  public BizPreGeneratedQuestionBase() throws Exception {
  }


	public void createProperties() throws Exception {
		addItem( "id", pId );
		addItem( "gameplayer_id", pGameplayerId );
		addItem( "level_id", pLevelId );
		addItem( "question_id", pQuestionId );
		addItem( "question_type_id", pQuestionTypeid);
		addItem( "rendered_question", pRenderedQuestion );
		addItem( "style_image", pStyleImage );
		addItem( "num_image1", pNumImage1 );
		addItem( "image1", pImage1 );
		addItem( "num_image2", pNumImage2 );
		addItem( "image2", pImage2 );
		addItem( "difficulty_id", pDifficultyId );
		addItem( "company", pCompany );
		addItem( "used", pUsed );
		addItem( "difficulty_name", pDifficultyName );
		addItem( "company", pCompany );
		addItem("question_type_name", pQuestionTypeName);
}
  /**
   * Adds the fixed object properties
   */
	public void createFixedProperties() throws Exception {
		addFixedItem( KEY, "id", "id", false, false, 64 );
		addFixedItem( FIELD, "gameplayer_id", "gameplayer_id", true,false, 64 );
		addFixedItem( FIELD, "level_id", "level_id", true,false, 64 );
		addFixedItem( FIELD, "question_id", "question_id", true,false, 64 );
		addFixedItem( FIELD, "question_type_id", "Tipo pregunta", true, false, 64);
		addFixedItem( FIELD, "rendered_question", "Pregunta", true, true, 0 );
		addFixedItem( FIELD, "difficulty_id", "difficulty_id", true,false, 64 );
		addFixedItem( FIELD, "style_image", "style_image", true, false, 50 );
		addFixedItem( FIELD, "num_image1", "num_image1", true, false, 15 );
		addFixedItem( FIELD, "image1", "image1", true, false, 255 );
		addFixedItem( FIELD, "num_image2", "num_image2", true, false, 15 );
		addFixedItem( FIELD, "image2", "image2", true, false, 255 );
		addFixedItem( FIELD, "used", "Usado", true, false, 1 );
		addFixedItem( FIELD, "company", "company", true, false, 15);
		addFixedItem( FOREIGN, "difficulty_name", "Dificultad", true, false, 1 );
		addFixedItem(FOREIGN, "question_type_name", "Tipo pregunta", true, false, 250);
  }
	
	
  @Override
  public void createControlProperties() throws Exception {
		this.addControlsProperty("question_type_id", createControlCombo(GuiQuestionTypes.class, "id", new JPair<String, String>("company", "company")));
		this.addControlsProperty("gameplayer_id", createControlCombo(GuiGamePlayers.class,"id_gameplayer", new JPair<String, String>("company","company") ));
  	this.addControlsProperty("level_id", createControlCombo(GuiLevels.class,"id", new JPair<String, String>("company","company") ));
  	this.addControlsProperty("question_id", createControlCombo(GuiQuestions.class,"id", new JPair<String, String>("company","company") ));
  	this.addControlsProperty("difficulty_id", createControlCombo(GuiDifficulties.class,"id", new JPair<String, String>("company","company") ));
  	super.createControlProperties();
  }
  /**
   * Returns the table name
   */
	public String GetTable() { return "gms_pre_generated_questions"; }

	@Override
	public String GetTableTemporal() throws Exception {
		String sql = "";
		sql += "select gms_pre_generated_questions.*,gms_difficulty.name as difficulty_name,gms_question_type.name as question_type_name \n" + 
		       "from gms_pre_generated_questions \n" + 
				   "join gms_difficulty on gms_difficulty.id=gms_pre_generated_questions.difficulty_id\n"+
					 "join gms_question_type on gms_question_type.id=gms_pre_generated_questions.question_type_id\n";
		  

		return "(" + sql + ") as " + GetTable();
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Functionality methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void processInsert() throws Exception {
		setUsed(false);
		super.processInsert();
	}
	@Override
	public void processUpdate() throws Exception {
		
		if (pUsed.isNull())
			setUsed(false);
		super.processUpdate();
	}
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
