package pss.game.models.variable.logica;

import pss.game.models.variable.BizVariableBase;
import pss.game.models.variableOption.BizVariableOptionBase;

public interface IVariableLogica {
	public boolean useRange();
	public boolean useListValue();
	public BizVariableOptionBase getOneValue(BizVariableBase var) throws Exception;
	public BizVariableOptionBase getOneValue(BizVariableBase var,String operator,String valuecomp) throws Exception;
}
