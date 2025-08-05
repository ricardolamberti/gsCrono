package pss.game.models.familyQuestion.logica;

public class FamiliaLogicaBase implements IFamiliaLogica {
	public boolean isRendered() {
		return false;
	}
	
	public String validate(String statement) throws Exception {
		if (statement.indexOf("<")!=-1 && statement.indexOf(">")!=-1) return ("No admite variables en modo SIMPLE");
		return "";
	}
}
