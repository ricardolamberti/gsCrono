package pss.game.models.variable.logica;

import pss.core.tools.JTools;
import pss.game.models.variable.BizVariableBase;
import pss.game.models.variableOption.BizVariableOptionBase;

public class VariableLogicaRangeFloat  extends VariableLogicaBase {
	public boolean useRange() {
		return true;
	};
	public boolean useListValue() {
		return false;
	};


	public BizVariableOptionBase getOneValue(BizVariableBase var,boolean question) throws Exception {
		double rand = (Math.random()*(var.getRangeMax()-var.getRangeMin())+var.getRangeMin());
		return new BizVariableOptionBase(JTools.formatDouble(rand));
	}

	public BizVariableOptionBase getOneValue(BizVariableBase var, String operator, String valuecomp) throws Exception  {
		double compValue = Double.parseDouble(valuecomp);
		double max = var.getRangeMax();
		double min = var.getRangeMin();
		double diff = 0;
		switch (operator) {
			case "<":
				max = (double)compValue-.001>max?max:(double) compValue - .001;
				break;
			case ">":
				min = (double)compValue-.001<min?min:(double) compValue + .001;
				break;
			case "!=":
				diff = (double) compValue;
				break;
			case "<=":
				max = (double)compValue>max?max: (double) compValue;
				break;
			case ">=":
				min = (double)compValue<min?min:(double) compValue;
				break;
			default:
		}

		double rand = (Math.random() * (max - min)) + min;
		if (operator.equals("!=")) {
			if (rand == diff)
				rand = (Math.random() * (max - min)) + min;
			else if (rand == diff && diff != min)
				rand = min;
			else
				rand = max;
		}
		
		return new BizVariableOptionBase(JTools.formatDouble(rand));

	}
}
