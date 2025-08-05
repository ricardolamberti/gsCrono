package pss.game.apis;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OptionDTO {
	private long id;
	private String formula;
	private String extradata;
	private boolean isCorrect;

	public OptionDTO(long id, String formula, String extradato,boolean isCorrect) {
		this.id = id;
		this.formula = formula;
		this.isCorrect = isCorrect;
		this.extradata= extradato;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getExtradata() {
		return extradata;
	}

	public void setExtradata(String extradata) {
		this.extradata = extradata;
	}
	

	// Getters y Setters

}
