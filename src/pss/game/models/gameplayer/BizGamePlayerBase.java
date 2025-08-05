package pss.game.models.gameplayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import pss.common.customList.config.relation.JRelations;
import pss.core.services.JExec;
import pss.core.services.fields.JDate;
import pss.core.services.fields.JLong;
import pss.core.services.fields.JString;
import pss.core.services.records.JRecord;
import pss.core.services.records.JRecords;
import pss.core.tools.JPair;
import pss.core.tools.collections.JIterator;
import pss.game.models.game.BizGameBase;
import pss.game.models.game.GuiGames;
import pss.game.models.level.BizLevelBase;
import pss.game.models.player.BizPlayerBase;
import pss.game.models.player.GuiPlayers;
import pss.game.models.preGeneratedQuestionOption.BizpreGeneratedQuestionOptionBase;
import pss.game.models.preGeneratedQuestions.BizPreGeneratedQuestionBase;
import pss.game.models.question.BizQuestionBase;
import pss.game.models.question.BizQuestionBase.ResultParser;
import pss.game.models.questionOption.BizQuestionOptionBase;

public class BizGamePlayerBase extends JRecord {

	protected JLong pIdgameplayer = new JLong();
	protected JLong pIdgame = new JLong();
	protected JLong pIdplayer = new JLong();
	protected JDate pDate = new JDate ();
	protected JLong pPoints = new JLong();
	protected JLong pLevel = new JLong();
	protected JLong pGems = new JLong();
	protected JString pCompany = new JString();
	protected JString pPlayerName = new JString();
	protected JString pGameName = new JString();


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Getter & Setters methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	public void setIdgameplayer(long zValue) throws Exception {    pIdgameplayer.setValue(zValue);  }
	public long getIdgameplayer()	throws Exception {     return pIdgameplayer.getValue();  }
	public boolean isNullIdgameplayer() throws Exception { return  pIdgameplayer.isNull(); } 
	public void setNullToIdgameplayer() throws Exception {  pIdgameplayer.setNull(); } 
	public void setIdgame(long zValue) throws Exception {    pIdgame.setValue(zValue);  }
	public long getIdgame()	throws Exception {     return pIdgame.getValue();  }
	public boolean isNullIdgame() throws Exception { return  pIdgame.isNull(); } 
	public void setNullToIdgame() throws Exception {  pIdgame.setNull(); } 
	public void setIdplayer(long zValue) throws Exception {    pIdplayer.setValue(zValue);  }
	public long getIdplayer()	throws Exception {     return pIdplayer.getValue();  }
	public boolean isNullIdplayer() throws Exception { return  pIdplayer.isNull(); } 
	public void setNullToIdplayer() throws Exception {  pIdplayer.setNull(); } 
	public void setDate(Date zValue) throws Exception {    pDate.setValue(zValue);  }
	public Date getDate()	throws Exception {     return pDate.getValue();  }
	public boolean isNullDate() throws Exception { return  pDate.isNull(); } 
	public void setNullToDate() throws Exception {  pDate.setNull(); } 
	public void setPoints(long zValue) throws Exception {    pPoints.setValue(zValue);  }
	public long getPoints()	throws Exception {     return pPoints.getValue();  }
	public boolean isNullPoints() throws Exception { return  pPoints.isNull(); } 
	public void setNullToPoints() throws Exception {  pPoints.setNull(); } 
	public void setLevel(long zValue) throws Exception {    pLevel.setValue(zValue);  }
	public long getLevel()	throws Exception {     return pLevel.getValue();  }
	public boolean isNullLevel() throws Exception { return  pLevel.isNull(); } 
	public void setNullToLevel() throws Exception {  pLevel.setNull(); } 
	public void setGems(long zValue) throws Exception {    pGems.setValue(zValue);  }
	public long getGems()	throws Exception {     return pGems.getValue();  }
	public boolean isNullGems() throws Exception { return  pGems.isNull(); } 
	public void setNullToGems() throws Exception {  pGems.setNull(); } 
	public void setCompany(String zValue) throws Exception {    pCompany.setValue(zValue);  }
	public String getCompany()	throws Exception {     return pCompany.getValue();  }
	public boolean isNullCompany() throws Exception { return  pCompany.isNull(); } 
	public void setNullToCompany() throws Exception {  pCompany.setNull(); } 

	public void setPlayerName(String zValue) throws Exception {    pPlayerName.setValue(zValue);  }
	public String getPlayerName()	throws Exception {     return pPlayerName.getValue();  }
	public boolean isNullPlayerName() throws Exception { return  pPlayerName.isNull(); } 
	public void setNullToPlayerName() throws Exception {  pPlayerName.setNull(); } 
	public void setGameName(String zValue) throws Exception {    pGameName.setValue(zValue);  }
	public String getGameName()	throws Exception {     return pGameName.getValue();  }
	public boolean isNullGameName() throws Exception { return  pGameName.isNull(); } 
	public void setNullToGameName() throws Exception {  pGameName.setNull(); } 


  /**
   * Constructor de la Clase
   */
  public BizGamePlayerBase() throws Exception {
  }


	public void createProperties() throws Exception {
		addItem( "id_gameplayer", pIdgameplayer );
		addItem( "id_game", pIdgame );
		addItem( "id_player", pIdplayer );
		addItem( "date", pDate );
		addItem( "points", pPoints );
		addItem( "level", pLevel );
		addItem( "gems", pGems );
		addItem( "company", pCompany );
		addItem( "player_name", pPlayerName );
		addItem( "game_name",   pGameName );
	}
  /**
   * Adds the fixed object properties
   */
	public void createFixedProperties() throws Exception {
		addFixedItem( KEY, "id_gameplayer", "id", false, false, 64 );
		addFixedItem( FIELD, "id_game", "Juego", true, true, 64 );
		addFixedItem( FIELD, "id_player", "Jugador", true, true, 64 );
		addFixedItem( FIELD, "date", "Fecha suscripción", true,false, 18 );
		addFixedItem( FIELD, "points", "Puntos", true,false, 18 );
		addFixedItem( FIELD, "level", "Nivel", true,false, 18 );
		addFixedItem( FIELD, "gems", "Gemas", true,false, 18 );
		addFixedItem( FIELD, "company", "Compañia", true,false, 15 );
		addFixedItem( FOREIGN, "player_name", "Jugador", true,false, 15 );
		addFixedItem( FOREIGN, "game_name", "Juego", true,false, 15 );
  }
  @Override
  public void createControlProperties() throws Exception {
  	this.addControlsProperty("id_player", createControlCombo(GuiPlayers.class,"id_player", new JPair<String, String>("company","company") ));
  	this.addControlsProperty("id_game", createControlCombo(GuiGames.class,"id_game", new JPair<String, String>("company","company") ));
  	super.createControlProperties();
  }

  /**
   * Returns the table name
   */
	public String GetTable() { return "gms_gameplayer"; }

	@Override
	public String GetTableTemporal() throws Exception {
		String sql = "";
		sql += "select gms_gameplayer.*,gms_player.name as player_name,gms_game.game as game_name \n" + 
		       "from gms_gameplayer \n" + "join gms_player on gms_player.id_player=gms_gameplayer.id_player\n" +
				    "join gms_game on gms_game.id_game=gms_gameplayer.id_game   ";

		return "(" + sql + ") as " + GetTable();
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Functionality methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


  /**
   * Default read() method
   */
	public boolean read( long zIdgameplayer ) throws Exception { 
		addFilter( "id_gameplayer",  zIdgameplayer ); 
		return read(); 
  } 
	public boolean readByIdPlayerIdGame( long zIdPlayer, long zIdGame ) throws Exception { 
		addFilter( "id_game",  zIdGame ); 
		addFilter( "id_player",  zIdPlayer ); 
		return read(); 
  } 
	
	@Override
	protected boolean loadForeignFields() throws Exception {
		return true;
	}
	
	

	
	public void execProcessGenerateQuestions(String questionTypeId) throws Exception {
		JExec exec = new JExec(null, null) {
			public void Do() throws Exception {
				processGenerateQuestions(questionTypeId);
			}
		};
		exec.execute();
	}
	
	public  List<BizQuestionBase> getAzarQuestions(long difficulty,String questionTypeId, long minimo) throws Exception {
		long added=0; // cuando diferenci simples de multiples deberia solo poner las simples una sola vez
		boolean first=true;
		List<BizQuestionBase> questionList = new ArrayList<>();
		while (added < minimo) {
			JRecords<BizQuestionBase> recs = new JRecords<BizQuestionBase>(BizQuestionBase.class);
			recs.addJoin(JRelations.JOIN , "gms_family", "gms_family.id=gms_question.family_id");
			recs.addFilter("difficulty_id", difficulty);
			recs.addFilter("game_id", getIdgame());
			recs.addFilter("gms_family.type", first?"SIMPLE":"MULTIPLE");
			recs.addFilter("company", getCompany());
			if (questionTypeId !=null) {
				recs.addJoin(JRelations.JOIN,"gms_question_type","gms_question_type.id=gms_question.question_type_id");
				recs.addFilter("gms_question_type","type", questionTypeId,"=");	
			}
			JIterator<BizQuestionBase> it = recs.getStaticIterator();
			if (!it.hasMoreElements() && !first) break;
			while (it.hasMoreElements()) {
				questionList.add(it.nextElement());
				added++;
			}
			first=false;
		}
    Collections.shuffle(questionList);
    return questionList;
	}
	
	public long lastLevel() throws Exception {
		String sSQL="SELECT MAX(l.level_number) AS highest_level\n" + 
				"FROM gms_pre_generated_questions pq\n" + 
				"JOIN gms_level l ON pq.level_id = l.id\n" + 
				"WHERE pq.company = 'your_company'\n" + 
				"  AND pq.gameplayer_id = "+getIdgameplayer()+";\n";
		
		return (long)getSqlValue(sSQL,"highest_level");
	}
	transient BizGameBase objGame;
	transient BizPlayerBase objPlayer;

	public BizPlayerBase getObjPlayer() throws Exception {
		if (isNullIdplayer())	return null;
		if (objPlayer != null) return objPlayer;
		BizPlayerBase fam = new BizPlayerBase();
		fam.dontThrowException(true);
		if (!fam.read(getIdplayer()))
			return null;
		return objPlayer = fam;
	}
		
	public BizGameBase getObjGame() throws Exception {
		if (isNullIdgame())	return null;
		if (objGame != null) return objGame;
		BizGameBase fam = new BizGameBase();
		fam.dontThrowException(true);
		if (!fam.read(getIdgame()))
			return null;
		return objGame = fam;
	}
	

	public void processGenerateQuestions(String questionTypeId) throws Exception {
		
		long level = getLevel();
		JRecords<BizLevelBase> recs = new JRecords<BizLevelBase>(BizLevelBase.class);
		recs.addFilter("game_id", getIdgame());
		recs.addFilter("company", getCompany());
		recs.addFilter("level_number", level);
		JIterator<BizLevelBase> it = recs.getStaticIterator();
		while (it.hasMoreElements()) {
			BizLevelBase objLevel = it.nextElement();
			List<BizQuestionBase> questionList = getAzarQuestions(objLevel.getDifficultyId(),questionTypeId,objLevel.getNumQuestions()*2);

			addQuestions(level,objLevel.getDifficultyId(), questionList,objLevel.getNumQuestions());
			break; // only one
		}
		
	}

	public void addQuestions(long level, long difficulty,List<BizQuestionBase> questions, long numQuestions ) throws Exception {
		int q=0;
    for (BizQuestionBase quest : questions) {
    	if (q>=numQuestions) break;
        BizPreGeneratedQuestionBase newQuest = new BizPreGeneratedQuestionBase();
        newQuest.setCompany(getCompany());
        newQuest.setGameplayerId(getIdgameplayer());
        newQuest.setLevelId(level);
        newQuest.setDifficultyId(difficulty);
        newQuest.setQuestionId(quest.getId());
        ResultParser parser = quest.getRenderedStatement();
        newQuest.setRenderedQuestion(parser.statment);
        newQuest.setImage1(parser.image1);
        newQuest.setImage2(parser.image2);
        newQuest.setNumImage1(parser.numImage1);
        newQuest.setNumImage2(parser.numImage2);
        newQuest.setStyleImage(parser.styleImage);
        newQuest.setQuestionTypeid(quest.getQuestionTypeid());
        // check preguntas repetidas
        
        newQuest.processInsert();
        q++;
        JRecords<BizQuestionOptionBase> recsOps = new JRecords<BizQuestionOptionBase>(BizQuestionOptionBase.class);
        recsOps.addFilter("question_id", quest.getId());
        recsOps.addFilter("company", getCompany());
        JIterator<BizQuestionOptionBase> itOps = recsOps.getStaticIterator();
        while (itOps.hasMoreElements()) {
            BizQuestionOptionBase questOps = itOps.nextElement();
            BizpreGeneratedQuestionOptionBase newQuestOp = new BizpreGeneratedQuestionOptionBase();
            newQuestOp.setCompany(getCompany());
            newQuestOp.setPreQuestionid(newQuest.getId());
            newQuestOp.setIscorrect(questOps.getIscorrect());
            newQuestOp.setAnswerFormula(quest.getRenderedOptionStatement(questOps).statment);
            newQuestOp.setExtraDatos(quest.getRenderedOptionExtraStatement(questOps).statment);
            newQuestOp.processInsert();
        }
    }
}
}
