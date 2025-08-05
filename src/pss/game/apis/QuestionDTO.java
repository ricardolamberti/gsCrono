package pss.game.apis;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class QuestionDTO {

	private long id;
  private String renderedQuestion;
  private List<OptionDTO> options = new ArrayList<>();
  private String styleImage;
  private String image1;
  private String image2;
  private long  numImage1;
  private long  numImage2;
  
  public void setRenderedQuestion(String renderedQuestion) {
      this.renderedQuestion = renderedQuestion;
  }

  public void addOption(long id,String formula,String extradata, boolean isCorrect) {
      this.options.add(new OptionDTO(id,formula,extradata, isCorrect));
  }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<OptionDTO> getOptions() {
		return options;
	}

	public void setOptions(List<OptionDTO> options) {
		this.options = options;
	}

	public String getRenderedQuestion() {
		return renderedQuestion;
	}

	public String getStyleImage() {
		return styleImage;
	}

	public void setStyleImage(String styleImage) {
		this.styleImage = styleImage;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public long getNumImage1() {
		return numImage1;
	}

	public void setNumImage1(long numImage1) {
		this.numImage1 = numImage1;
	}

	public long getNumImage2() {
		return numImage2;
	}

	public void setNumImage2(long numImage2) {
		this.numImage2 = numImage2;
	}
	
	

  
  // Getters y Setters
}

