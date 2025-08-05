package pss.game.models.variable.logica;

import pss.game.models.variable.BizVariableBase;
import pss.game.models.variableOption.BizVariableOptionBase;

public class VariableLogicaRange extends VariableLogicaBase {
	public boolean useRange() {
		return true;
	};
	public boolean useListValue() {
		return false;
	};


	public BizVariableOptionBase getOneValue(BizVariableBase var) throws Exception {
		long rand = (long)Math.ceil(Math.random()*(var.getRangeMax()-var.getRangeMin())+var.getRangeMin());
		return new BizVariableOptionBase(""+rand);
	}
	
	
	public BizVariableOptionBase getOneValue(BizVariableBase var, String operator, String valuecomp) throws Exception  {
		  double compValue = Double.parseDouble(valuecomp);
		  long max = var.getRangeMax();
		  long min = var.getRangeMin();
		  long diff=0;
	    switch (operator) {
	        case "<":
	             max = (long)compValue-1>max?max:(long)compValue-1; break;
	        case ">":
            min = (long)compValue+1<min?min:(long)compValue+1; break;
	        case "!=":
            diff =  (long)compValue; break;
	        case "<=":
            max = (long)compValue>max?max:(long)compValue; break;
	        case ">=":
            min = (long)compValue+1<min?min:(long)compValue; break;
	        default:
	    }
	    
			long rand = (long)Math.ceil(Math.random()*(max-min)+min);
			if (operator.equals("!=")) {
				if (rand==diff) rand = (long)Math.ceil(Math.random()*(max-min)+min);
				else if (rand==diff && diff!=min) rand = min;
				else rand = max;
			}
			return new BizVariableOptionBase(""+rand);

	}

}
