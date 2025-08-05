package pss.game.models.variable.logica;

import pss.game.models.variable.BizVariableBase;
import pss.game.models.variableOption.BizVariableOption;
import pss.game.models.variableOption.BizVariableOptionBase;

public class VariableLogicaListValue extends VariableLogicaBase {
	public boolean useRange() {
		return false;
	};
	public boolean useListValue() {
		return true;
	};
	@Override
	public BizVariableOptionBase getOneValue(BizVariableBase var) throws Exception {
		long pos = (long)(Math.random()*var.getTotalOptions());
		
		BizVariableOption option = new BizVariableOption();
		option.dontThrowException(true);
		if (!option.readByOrden(var.getId(),pos)) return null;
		
//		if (question || option.isNullValueanswer()) return option.getValuequestion();
		return option;
	}
	public BizVariableOptionBase getOneValue(BizVariableBase var, String operator, String valuecomp) throws Exception  {
		long pos = (long)(Math.random()*var.getTotalOptions());
		
		BizVariableOption option = new BizVariableOption();
		option.dontThrowException(true);
		if (!option.readByOrden(var.getId(),pos)) return null;
		
		return option;

}
}

