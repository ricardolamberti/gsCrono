package pss.game.models.game;

import pss.core.services.records.JRecord;
import pss.core.win.JWin;
import pss.core.win.actions.BizAction;
import pss.core.win.submits.JAct;
import pss.core.win.submits.JActWins;
import pss.core.winUI.forms.JBaseForm;
import pss.game.models.difficulty.GuiDifficulties;
import pss.game.models.familyQuestion.GuiFamilyQuestions;
import pss.game.models.gameplayer.GuiGamePlayers;
import pss.game.models.level.GuiLevels;
import pss.game.models.question.GuiQuestions;
import pss.game.models.questionType.GuiQuestionTypes;

public class GuiGame extends JWin {

	/**
	 * Constructor de la Clase
	 */
	public GuiGame() throws Exception {
	}

	public JRecord ObtenerDato() throws Exception {
		return new BizGame();
	}

	public int GetNroIcono() throws Exception {
		return 10023;
	}

	public String GetTitle() throws Exception {
		return "Juego";
	}

	public Class<? extends JBaseForm> getFormBase() throws Exception {
		return FormGame.class;
	}

	public String getKeyField() throws Exception {
		return "id_game";
	}

	public String getDescripField() {
		return "game";
	}

	public BizGame GetcDato() throws Exception {
		return (BizGame) this.getRecord();
	}

	public void createActionMap() throws Exception {
		this.addAction(10, "Suscriptores", null, 10027, false, false).setHelp("Información de los jugadores subscriptos al juego.");
		this.addAction(20, "Familias Preguntas", null, 10027, false, false).setHelp("Información de los familia preguntas al juego.");
		this.addAction(30, "Dificultades", null, 10027, false, false).setHelp("Información de la dificultades al juego.");
		this.addAction(40, "Niveles", null, 10027, false, false).setHelp("Información de la niveles del juego.");
		this.addAction(50, "Tipo de preguntas", null, 10027, false, false).setHelp("Información de los tipos de preguntas.");
		this.addAction(60, "Preguntas", null, 10027, false, false).setHelp("Información de los tipos de preguntas.");
		super.createActionMap();
	}

	@Override
	public boolean OkAction(BizAction a) throws Exception {
		return super.OkAction(a);
	}

	@Override
	public JAct getSubmitFor(BizAction a) throws Exception {
		if (a.getId() == 10)
			return new JActWins(getSuscriptores());
		if (a.getId() == 20)
			return new JActWins(getFamiliaGrupos());
		if (a.getId() == 30)
			return new JActWins(getDificultades());
		if (a.getId() == 40)
			return new JActWins(getNiveles());
		if (a.getId() == 50)
			return new JActWins(getTypeQuestions());
		if (a.getId() == 60)
			return new JActWins(getQuestions());
		return super.getSubmitFor(a);
	}

	public GuiGamePlayers getSuscriptores() throws Exception {
		GuiGamePlayers segs = new GuiGamePlayers();
		segs.getRecords().addFilter("company", GetcDato().getCompany());
		segs.getRecords().addFilter("id_game", GetcDato().getIdgame());
		segs.getRecords().addOrderBy("date", "DESC");
		return segs;

	}

	public GuiFamilyQuestions getFamiliaGrupos() throws Exception {
		GuiFamilyQuestions segs = new GuiFamilyQuestions();
		segs.getRecords().addFilter("company", GetcDato().getCompany());
		segs.getRecords().addFilter("game_id", GetcDato().getIdgame());
		segs.getRecords().addOrderBy("name", "DESC");
		return segs;

	}

	public GuiDifficulties getDificultades() throws Exception {
		GuiDifficulties segs = new GuiDifficulties();
		segs.getRecords().addFilter("company", GetcDato().getCompany());
		segs.getRecords().addFilter("id_game", GetcDato().getIdgame());
		segs.getRecords().addOrderBy("name", "DESC");
		return segs;

	}

	public GuiLevels getNiveles() throws Exception {
		GuiLevels segs = new GuiLevels();
		segs.getRecords().addFilter("company", GetcDato().getCompany());
		segs.getRecords().addFilter("game_id", GetcDato().getIdgame());
		segs.getRecords().addOrderBy("level_number", "ASC");
		return segs;

	}

	public GuiQuestionTypes getTypeQuestions() throws Exception {
		GuiQuestionTypes segs = new GuiQuestionTypes();
		segs.getRecords().addFilter("company", GetcDato().getCompany());
		segs.getRecords().addFilter("game_id", GetcDato().getIdgame());
		segs.getRecords().addOrderBy("name", "DESC");
		return segs;

	}

	public GuiQuestions getQuestions() throws Exception {
		GuiQuestions segs = new GuiQuestions();
		segs.getRecords().addFilter("company", GetcDato().getCompany());
		segs.getRecords().addFilter("game_id", GetcDato().getIdgame());
		segs.getRecords().addOrderBy("statement", "DESC");
		return segs;

	}

}
