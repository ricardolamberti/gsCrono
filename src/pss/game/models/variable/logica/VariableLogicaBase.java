package pss.game.models.variable.logica;

import pss.game.models.variable.BizVariableBase;
import pss.game.models.variableOption.BizVariableOptionBase;

public class VariableLogicaBase implements IVariableLogica {
	public boolean useRange() {
		return false;
	};
	public boolean useListValue() {
		return false;
	};
	
	@Override
	public BizVariableOptionBase getOneValue(BizVariableBase var) throws Exception {
		return null;
	}
	
	@Override
	public BizVariableOptionBase getOneValue(BizVariableBase var, String operator, String valuecomp) throws Exception {
		return null;
	}
}
