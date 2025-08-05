package pss.game.gameBusiness;

import pss.common.customList.config.BizCustomListModules;
import pss.common.customList.config.relation.JRelations;
import pss.common.personalData.GuiPersona;
import pss.common.regions.company.BizCompany;
import pss.common.regions.company.GuiCompany;
import pss.common.regions.company.GuiNewCompany;
import pss.common.regions.company.JCompanyBusiness;
import pss.common.security.BizUsuario;
import pss.common.security.GuiUsuario;
import pss.core.tools.collections.JList;
import pss.core.tools.collections.JMap;
import pss.core.win.JBaseWin;
import pss.core.win.JWin;
import pss.core.win.JWins;


public class JCompanyBusinessGame extends JCompanyBusiness {

	public static final String FORMAT_DATE = "yyyyMMdd";
	public static final String FORMAT_DATETIME = "yyyyMMddHHmm";
		// Classes
		
	public BizCompany getInstance() throws Exception {return new BizGameCompany();}
  @Override
	public JWin getSpecialNode() throws Exception {return new GuiGameCompany();}
  @Override
	public BizUsuario getUserInstance() throws Exception {return new BizGameUser();}
	@Override
	public GuiUsuario getUserWinInstance() throws Exception {
		return new GuiGameUser();
	}
	public Class getPersonasClass() throws Exception { 	
		return GuiPersona.class;
	}
	
	public String getPreviewFlag() throws Exception {
		return  JWins.PREVIEW_SI;
	}

	
	public String getTipoUsuarioDefault() {
		return "usuario"; 
	}
	
	// Labels 
	  
	  @Override
		public String getLabelArticles() throws Exception {return "Games";}
	  @Override
		public String getLabelArticle() throws Exception {return "Game";}
	  @Override
		public String getLabelSubCliente() throws Exception {return "Jugador";}
	  @Override
		public String getLabelSubClientes() throws Exception {return "Jugadores";}
	  @Override
		public String getLabelRegionOrigen() throws Exception {return "Region";}
	  @Override
		public String getLabelRegionOrigenPlural() throws Exception {return "Regiones";}
	  @Override
		public String getLabelRegionOrigenFiltro() throws Exception {return "Region";}
	  @Override
		public String getLabelReporteTerminal() throws Exception {return "Terminal";}
	  @Override
		public String getLabelNodo() throws Exception {return "Nodo";}


	
		public boolean isSqlEventProcessInService() throws Exception { 	
			return true;
		}
		public String getHelp() throws Exception {
			return "do-help";
		}
		
	  public String getCompanyDefault() throws Exception {
	  	return "DEFAULT";
	  }

	  // Defaults
	  @Override
		public boolean getDefaultCuentaContado() {return true;} 
	  @Override
		public String getDefaultOperationForReportsCustomers() {return "";}
		@Override
		public JList<String> getProductTypes() throws Exception {
			return null;
		}
		@Override
		public boolean isValidOperation(String operation) throws Exception {
			return false;
		}
		@Override
		public boolean isValidOperationForCustomers(String operation) throws Exception {
			return false;
		}
		@Override
		public void createBusinessHomePages() throws Exception {
			addPage("pss.game.GuiModuloGame_10", "Consola Game");
			addPage("pss.game.GuiModuloGame_20", "Consola Game pacientes");
		}
		
	  @Override
		public GuiNewCompany getNewWinInstance() throws Exception {return new GuiNewGameCompany();}
	  @Override
		public GuiCompany getWinInstance() throws Exception {return new GuiGameCompany();}

		public void attachRelationMap(BizCustomListModules self,JRelations rels) throws Exception {
			self.attachModule(1, BizCustomListModules.SET_GAME_CLASS, rels);
		}
		
		public JMap<String, String> getEventCodes() throws Exception {
			return null;
		}
		@Override
		public JWins getBusinessModules() throws Exception {
				return null;
		}
		
	  public String getWinListToolbarPosDefault() throws Exception {
		  return JBaseWin.TOOLBAR_IN;
	  }
	  public String getFormToolbarPosDefault() throws Exception {
		  return JBaseWin.TOOLBAR_TOP;
	  }
	  
	  public boolean isFilterBarOpenByDefault() {
	  	return true;
	  }

  


}
