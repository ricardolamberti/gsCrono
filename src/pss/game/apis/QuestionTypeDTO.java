package pss.game.apis;

public class QuestionTypeDTO {
  private long id;
  private String description;

  // Getters y Setters
  public long getId() {
      return id;
  }

  public void setId(long id) {
      this.id = id;
  }

  public String getDescription() {
      return description;
  }

  public void setDescription(String description) {
      this.description = description;
  }
}
