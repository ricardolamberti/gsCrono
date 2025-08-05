package pss.game.models.playerAnswers;

import java.util.Date;

import pss.common.help.GuiQuestions;
import pss.core.services.fields.JDate;
import pss.core.services.fields.JFloat;
import pss.core.services.fields.JLong;
import pss.core.services.fields.JString;
import pss.core.services.records.JRecord;
import pss.core.tools.JPair;
import pss.game.models.difficulty.BizDifficultyBase;
import pss.game.models.difficulty.GuiDifficulties;
import pss.game.models.gameplayer.GuiGamePlayers;
import pss.game.models.level.BizLevelBase;
import pss.game.models.level.GuiLevels;
import pss.game.models.preGeneratedQuestions.BizPreGeneratedQuestionBase;
import pss.game.models.questionType.BizQuestionTypeBase;
import pss.game.models.questionType.GuiQuestionTypes;

public class BizPlayerAnswerBase extends JRecord {

	protected JLong pId = new JLong();
	protected JString pCompany = new JString();
	protected JLong pGameplayerId = new JLong();
	protected JDate pDate = new JDate();
	
	protected JLong pQuestionId = new JLong();
	protected JString pQuestion = new JString();
	protected JLong pAnswerId = new JLong();
	protected JString pGivenAnswer = new JString();
	protected JFloat pPointsAwarded = new JFloat();
	
	protected JLong pQuestionTypeId = new JLong();
	protected JString pQuestionType = new JString();
	protected JLong pDifficultyId = new JLong();
	protected JString pDifficulty = new JString();
	protected JLong pLevelId = new JLong();
	protected JLong pLevel = new JLong();


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
	public void setGivenAnswer(String zValue) throws Exception {    pGivenAnswer.setValue(zValue);  }
	public String getGivenAnswer()	throws Exception {     return pGivenAnswer.getValue();  }
	public boolean isNullGivenAnswer() throws Exception { return  pGivenAnswer.isNull(); } 
	public void setNullToGivenAnswer() throws Exception {  pGivenAnswer.setNull(); } 
	public void setPointsAwarded(double zValue) throws Exception {    pPointsAwarded.setValue(zValue);  }
	public double getPointsAwarded()	throws Exception {     return pPointsAwarded.getValue();  }
	public boolean isNullPointsAwarded() throws Exception { return  pPointsAwarded.isNull(); } 
	public void setNullToPointsAwarded() throws Exception {  pPointsAwarded.setNull(); } 
	public void setDate(Date zValue) throws Exception {    pDate.setValue(zValue);  }
	public Date getDate()	throws Exception {     return pDate.getValue();  }
	public boolean isNullDate() throws Exception { return  pDate.isNull(); } 
	public void setNullToDate() throws Exception {  pDate.setNull(); } 
	public void setCompany(String zValue) throws Exception {    pCompany.setValue(zValue);  }
	public String getCompany()	throws Exception {     return pCompany.getValue();  }
	public boolean isNullCompany() throws Exception { return  pCompany.isNull(); } 
	public void setNullToCompany() throws Exception {  pCompany.setNull(); } 

	public void setAnswerId(long zValue) throws Exception {
		pAnswerId.setValue(zValue);
	}

	public long getAnswerId() throws Exception {
		return pAnswerId.getValue();
	}

	public boolean isNullAnswerId() throws Exception {
		return pAnswerId.isNull();
	}

	public void setNullToAnswerId() throws Exception {
		pAnswerId.setNull();
	}
	public void setLevel(long zValue) throws Exception {
		pLevel.setValue(zValue);
	}

	public long getLevel() throws Exception {
		return pLevel.getValue();
	}

	public boolean isNullLevel() throws Exception {
		return pLevel.isNull();
	}

	public void setNullToLevel() throws Exception {
		pLevel.setNull();
	}
	public void setQuestionTypeId(long zValue) throws Exception {
		pQuestionTypeId.setValue(zValue);
	}

	public long getQuestionTypeId() throws Exception {
		return pQuestionTypeId.getValue();
	}

	public boolean isNullQuestionTypeId() throws Exception {
		return pQuestionTypeId.isNull();
	}

	public void setNullToQuestionTypeId() throws Exception {
		pQuestionTypeId.setNull();
	}

	public void setQuestionType(String zValue) throws Exception {
		pQuestionType.setValue(zValue);
	}

	public String getQuestionType() throws Exception {
		return pQuestionType.getValue();
	}

	public boolean isNullQuestionType() throws Exception {
		return pQuestionType.isNull();
	}

	public void setNullToQuestionType() throws Exception {
		pQuestionType.setNull();
	}

	public void setDifficultyId(long zValue) throws Exception {
		pDifficultyId.setValue(zValue);
	}

	public long getDifficultyId() throws Exception {
		return pDifficultyId.getValue();
	}

	public boolean isNullDifficultyId() throws Exception {
		return pDifficultyId.isNull();
	}

	public void setNullToDifficultyId() throws Exception {
		pDifficultyId.setNull();
	}

	public void setDifficulty(String zValue) throws Exception {
		pDifficulty.setValue(zValue);
	}

	public String getDifficulty() throws Exception {
		return pDifficulty.getValue();
	}

	public boolean isNullDifficulty() throws Exception {
		return pDifficulty.isNull();
	}

	public void setNullToDifficulty() throws Exception {
		pDifficulty.setNull();
	}

	public void setQuestion(String zValue) throws Exception {
		pQuestion.setValue(zValue);
	}

	public String getQuestion() throws Exception {
		return pQuestion.getValue();
	}

	public boolean isNullQuestion() throws Exception {
		return pQuestion.isNull();
	}

	public void setNullToQuestion() throws Exception {
		pQuestion.setNull();
	}
  /**
   * Constructor de la Clase
   */
  public BizPlayerAnswerBase() throws Exception {
  }


	public void createProperties() throws Exception {
		addItem( "id", pId );
		addItem( "gameplayer_id", pGameplayerId );
		addItem( "question_id", pQuestionId );
		addItem( "answer_id", pAnswerId );
		addItem( "given_answer", pGivenAnswer );
		addItem( "points_awarded", pPointsAwarded );
		addItem( "date", pDate );
		addItem( "company", pCompany );
		addItem( "question", pQuestion );
		addItem( "level_id", pLevelId );
		addItem( "level", pLevel );
		addItem( "question_type_id", pQuestionTypeId );
		addItem( "question_type", pQuestionType );
		addItem( "difficulty_id", pDifficultyId );
		addItem( "difficulty", pDifficulty );
  }
  /**
   * Adds the fixed object properties
   */
	public void createFixedProperties() throws Exception {
		addFixedItem( KEY, "id", "id", false, false, 64 );
		addFixedItem( FIELD, "gameplayer_id", "gameplayer_id", true,false, 64 );
		addFixedItem( FIELD, "question_id", "question_id", true,false, 64 );
		addFixedItem( FIELD, "answer_id", "answer_id", true,false, 64 );
		addFixedItem( FIELD, "given_answer", "given_answer", true, true, 255 );
		addFixedItem( FIELD, "points_awarded", "points_awarded", true, true, 10,2 );
		addFixedItem( FIELD, "date", "date", true, true, 18 );
		addFixedItem( FIELD, "company", "company", true, true, 15 );
		addFixedItem( FIELD, "question", "question", true, true, 1000 );
		addFixedItem( FIELD, "level_id", "level_id", true,false, 64 );
		addFixedItem( FIELD, "level", "level", true,false, 64 );
		addFixedItem( FIELD, "question_type_id", "question_type_id", true, false, 18 );
		addFixedItem( FIELD, "question_type", "question_type", true, false, 50 );
		addFixedItem( FIELD, "difficulty_id", "difficulty_id", true, false, 18 );
		addFixedItem( FIELD, "difficulty", "difficulty", true, true, 50 );
  }
	
  @Override
  public void createControlProperties() throws Exception {
  	this.addControlsProperty("gameplayer_id", createControlCombo(GuiGamePlayers.class,"id_gameplayer", new JPair<String, String>("company","company") ));
  	this.addControlsProperty("level_id", createControlCombo(GuiLevels.class,"id", new JPair<String, String>("company","company") ));
  	this.addControlsProperty("question_id", createControlCombo(GuiQuestions.class,"id", new JPair<String, String>("company","company") ));
  	this.addControlsProperty("difficulty_id", createControlCombo(GuiDifficulties.class,"id", new JPair<String, String>("company","company") ));
  	this.addControlsProperty("question_type_id", createControlCombo(GuiQuestionTypes.class,"id", new JPair<String, String>("company","company") ));
  	super.createControlProperties();
  }
  
  /**
   * Returns the table name
   */
	public String GetTable() { return "gms_player_answers"; }


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
	
	transient BizQuestionTypeBase objQuestionType;

	public BizQuestionTypeBase getObjQuestionType() throws Exception {
		if (isNullQuestionTypeId())
			return null;
		if (objQuestionType != null)
			return objQuestionType;
		BizQuestionTypeBase fam = new BizQuestionTypeBase();
		fam.dontThrowException(true);
		if (!fam.read(getQuestionTypeId()))
			return null;
		return objQuestionType = fam;
	}
	
	transient BizDifficultyBase objDifficulty;

	public BizDifficultyBase getObjDifficulty() throws Exception {
		if (isNullDifficultyId())
			return null;
		if (objDifficulty != null)
			return objDifficulty;
		BizDifficultyBase fam = new BizDifficultyBase();
		fam.dontThrowException(true);
		if (!fam.read(getDifficultyId()))
			return null;
		return objDifficulty = fam;
	}

	transient BizPreGeneratedQuestionBase objQuestion;

	public BizPreGeneratedQuestionBase getObjQuestion() throws Exception {
		if (isNullQuestionId())
			return null;
		if (objQuestion != null)
			return objQuestion;
		BizPreGeneratedQuestionBase fam = new BizPreGeneratedQuestionBase();
		fam.dontThrowException(true);
		if (!fam.read(getQuestionId()))
			return null;
		return objQuestion = fam;
	}
	transient BizLevelBase objLevel;

	public BizLevelBase getObjLevel() throws Exception {
		if (isNullLevelId())
			return null;
		if (objLevel != null)
			return objLevel;
		BizLevelBase fam = new BizLevelBase();
		fam.dontThrowException(true);
		if (!fam.read(getLevelId()))
			return null;
		return objLevel = fam;
	}


	@Override
	public void processInsert() throws Exception {
		this.setQuestion(getObjQuestion().getRenderedQuestion());
		this.setDifficultyId(getObjQuestion().getDifficultyId());
		this.setQuestionTypeId(getObjQuestion().getQuestionTypeid());
		this.setLevelId(getObjQuestion().getLevelId());
		this.setDifficulty(getObjDifficulty().getName());
		this.setQuestionType(getObjQuestionType().getName());
		this.setLevel(getObjLevel().getLevelNumber());
		this.setCompany(getObjQuestion().getCompany());
		this.setLevel(getObjLevel().getNumQuestions());
		super.processInsert();
		
		getObjQuestion().setUsed(true);
		getObjQuestion().processUpdate();
	}

}
