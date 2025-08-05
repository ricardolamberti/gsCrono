package pss.game.consola;



import pss.common.security.BizUsuario;
import pss.core.services.records.JRecord;
import pss.core.win.JWin;
import pss.core.win.actions.BizAction;
import pss.core.win.submits.JAct;
import pss.core.win.submits.JActNew;
import pss.core.win.submits.JActWins;
import pss.core.winUI.forms.JBaseForm;
import pss.game.gameBusiness.BizGameUser;
import pss.game.gameBusiness.GuiGameCompany;
import pss.game.models.avatar.BizAvatar;
import pss.game.models.avatar.GuiAvatar;
import pss.game.models.avatar.GuiAvatars;
import pss.game.models.familyQuestion.GuiFamilyQuestion;
import pss.game.models.familyQuestion.GuiFamilyQuestions;
import pss.game.models.game.BizGame;
import pss.game.models.game.GuiGame;
import pss.game.models.game.GuiGames;
import pss.game.models.player.BizPlayer;
import pss.game.models.player.GuiPlayer;
import pss.game.models.player.GuiPlayers;
import pss.game.models.variable.BizVariable;
import pss.game.models.variable.GuiVariable;
import pss.game.models.variable.GuiVariables;

public class GuiGameConsola extends JWin {


  /**
   * Constructor de la Clase
   */
  public GuiGameConsola() throws Exception {
  }


  public JRecord ObtenerDato()   throws Exception { return new BizGameConsola(); }
  public int GetNroIcono()   throws Exception { return 10057; }
  public String GetTitle()   throws Exception { return "Consola"; }
  public Class<? extends JBaseForm> getFormBase() throws Exception {
  	return FormGameConsola.class;
  }
  public String  getKeyField() throws Exception { return "usuario"; }
  public String  getDescripField() { return "usuario"; }
  public BizGameConsola GetcDato() throws Exception { return (BizGameConsola) this.getRecord(); }
//  public String getHelpTag() { return "consola";}
  
  public void createActionMap() throws Exception {
		this.addAction(10, "Jugadores", null, 10027, true, true ).setHelp("Permite el alta, baja y modificación de jugadores, asi como también su consulta");
		this.addAction(11, "Jugadores", null, 10027, true, true ).setHelp("Permite el alta, baja y modificación de jugadores, asi como también su consulta");
		this.addAction(20, "Juegos", null, 10027, true, true ).setHelp("Permite el alta, baja y modificación de juegos, asi como también su consulta");
		this.addAction(21, "Juegos", null, 10027, true, true ).setHelp("Permite el alta, baja y modificación de juegos, asi como también su consulta");
		this.addAction(30, "Avatars", null, 10027, true, true ).setHelp("Permite el alta, baja y modificación de avatar, asi como también su consulta");
		this.addAction(31, "Avatars", null, 10027, true, true ).setHelp("Permite el alta, baja y modificación de avatar, asi como también su consulta");
		this.addAction(50, "Variables", null, 10027, true, true ).setHelp("Permite el alta, baja y modificación de variables, asi como también su consulta");
		this.addAction(51, "Variables", null, 10027, true, true ).setHelp("Permite el alta, baja y modificación de variables, asi como también su consulta");


  }
	public boolean forceCleanHistory() throws Exception {
		return true;
	}
  
  @Override
  public boolean OkAction(BizAction a) throws Exception {
  	if (a.getId()==10) return hasPlayers();
  	if (a.getId()==11) return !hasPlayers();
  	if (a.getId()==20) return hasGame();
  	if (a.getId()==21) return !hasGame();
  	if (a.getId()==30) return hasAvatar();
  	if (a.getId()==31) return !hasAvatar();
  	if (a.getId()==50) return hasVariable();
  	if (a.getId()==51) return !hasVariable();

  	return super.OkAction(a);
  }
  public boolean hasPlayers() throws Exception {
  	BizPlayer p = new BizPlayer();
		p.addFilter("company", BizUsuario.getUsr().getCompany());
		return p.selectCount()>0;
	}
  
  public boolean hasGame() throws Exception {
  	BizGame p = new BizGame();
		p.addFilter("company", BizUsuario.getUsr().getCompany());
		return p.selectCount()>0;
	}
  public boolean hasAvatar() throws Exception {
  	BizAvatar p = new BizAvatar();
		p.addFilter("company", BizUsuario.getUsr().getCompany());
		return p.selectCount()>0;
	}
  

  public boolean hasVariable() throws Exception {
  	BizVariable p = new BizVariable();
		p.addFilter("company", BizUsuario.getUsr().getCompany());
		return p.selectCount()>0;
	}

  
  
  
	@Override
	public JAct getSubmitFor(BizAction a) throws Exception {
		if (a.getId() == 10) return new JActWins(this.getPlayers());
		if (a.getId() == 11) return new JActNew(this.getNewPlayer(),0);
		if (a.getId() == 20) return new JActWins(this.getGames());
		if (a.getId() == 21) return new JActNew(this.getNewGame(),0);
		if (a.getId() == 30) return new JActWins(this.getAvatars());
		if (a.getId() == 31) return new JActNew(this.getNewAvatar(),0);

		if (a.getId() == 50) return new JActWins(this.getVariables());
		if (a.getId() == 51) return new JActNew(this.getNewVariable(),0);
	


		return null;
	}

	public GuiGameCompany getCompany() throws Exception {
		GuiGameCompany c = new GuiGameCompany();
		c.setRecord(BizGameUser.getUsrGame().getObjCompany());
		return c;
	}


	public GuiPlayers getPlayers() throws Exception {
		GuiPlayers personas = new GuiPlayers();
		personas.getRecords().addFilter("company", BizUsuario.getUsr().getCompany());
		personas.getRecords().addOrderBy("name","ASC");
		return personas;
	}
	public GuiPlayer getNewPlayer() throws Exception {
		GuiPlayer personas = new GuiPlayer();
		personas.GetcDato().addFilter("company", BizUsuario.getUsr().getCompany());
		return personas;
	}
	public GuiGames getGames() throws Exception {
		GuiGames personas = new GuiGames();
		personas.getRecords().addFilter("company", BizUsuario.getUsr().getCompany());
		personas.getRecords().addOrderBy("game","ASC");
		return personas;
	}
	public GuiGame getNewGame() throws Exception {
		GuiGame personas = new GuiGame();
		personas.GetcDato().addFilter("company", BizUsuario.getUsr().getCompany());
		return personas;
	}
	public GuiFamilyQuestions getFamilyQuestions() throws Exception {
		GuiFamilyQuestions personas = new GuiFamilyQuestions();
		personas.getRecords().addFilter("company", BizUsuario.getUsr().getCompany());
		personas.getRecords().addOrderBy("id","ASC");
		return personas;
	}
	public GuiFamilyQuestion getNewFamilyQuestion() throws Exception {
		GuiFamilyQuestion personas = new GuiFamilyQuestion();
		personas.GetcDato().addFilter("company", BizUsuario.getUsr().getCompany());
		return personas;
	}
	public GuiAvatars getAvatars() throws Exception {
		GuiAvatars personas = new GuiAvatars();
		personas.getRecords().addFilter("company", BizUsuario.getUsr().getCompany());
		personas.getRecords().addOrderBy("avatar","ASC");
		return personas;
	}
	public GuiAvatar getNewAvatar() throws Exception {
		GuiAvatar personas = new GuiAvatar();
		personas.GetcDato().addFilter("company", BizUsuario.getUsr().getCompany());
		return personas;
	}
	public GuiVariables getVariables() throws Exception {
		GuiVariables personas = new GuiVariables();
		personas.getRecords().addFilter("company", BizUsuario.getUsr().getCompany());
		personas.getRecords().addOrderBy("id","ASC");
		return personas;
	}
	public GuiVariable getNewVariable() throws Exception {
		GuiVariable personas = new GuiVariable();
		personas.GetcDato().addFilter("company", BizUsuario.getUsr().getCompany());
		return personas;
	}



}
