package pss.core.win.submits;

import pss.core.tools.PssLogger;
import pss.core.win.JWin;
import pss.core.winUI.forms.JBaseForm;

public class JActQuery extends JAct {

	public JActQuery(JWin zResult) {
		super(zResult);
		historyAction=true;
	}

//	@Override
//	public void Do() throws Exception {
//		this.getForm().Show();
//	}
	@Override
	public JBaseForm getForm() throws Exception {
		if (this.getWinResult()==null) {
			PssLogger.logDebug("error");
			throw new Exception("bad request");

		}
		return this.getWinResult().createQueryForm(this, !canReReadForm());
	}

	public JBaseForm getFormFlat() throws Exception {
		return this.getWinResult().createQueryFormFlat(this);
  }


	@Override
	public boolean isQueryAction() throws Exception {
		return true;
	}
	public boolean isTargetAction() throws Exception {
		return true;
	}

}
