package pss.game.apis;

public class PlayerAnswerDTO {
	private long gameplayerId;
  private long questionId;
  private long answerId;
  private String givenAnswer;
  private double pointsAwarded;
  
  public long getGameplayerId() {
		return gameplayerId;
	}
	public void setGameplayerId(long gameplayerId) {
		this.gameplayerId = gameplayerId;
	}
	public long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}
	public String getGivenAnswer() {
		return givenAnswer;
	}
	public void setGivenAnswer(String givenAnswer) {
		this.givenAnswer = givenAnswer;
	}
	public double getPointsAwarded() {
		return pointsAwarded;
	}
	public void setPointsAwarded(double pointsAwarded) {
		this.pointsAwarded = pointsAwarded;
	}
	public long getAnswerId() {
		return answerId;
	}
	public void setAnswerId(long answerId) {
		this.answerId = answerId;
	}
	
  // Getters y Setters
  
}
