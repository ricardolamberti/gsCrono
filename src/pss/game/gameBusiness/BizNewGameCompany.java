package  pss.game.gameBusiness;

import pss.common.regions.company.BizNewCompany;
import pss.common.regions.company.JCompanyBusinessModules;

public class BizNewGameCompany extends BizNewCompany {




	public BizNewGameCompany() throws Exception {
	}

	protected String getBusiness() throws Exception {
		return JCompanyBusinessModules.TURNOS;
	}
	

	
	@Override
	public void createProperties() throws Exception {
		super.createProperties();

	}

	@Override
	public void createFixedProperties() throws Exception {
		super.createFixedProperties();
	}

	@Override
	public void processInsert() throws Exception {
		super.processInsert();
		}
	

}
