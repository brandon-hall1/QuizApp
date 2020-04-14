package application;

public class Answer {
	private String answerTxt;
	private boolean correct;
	
	public Answer(String answerTxtIn, boolean correctIn){
		correct = correctIn;
		answerTxt = answerTxtIn;
	}

	public String getAnswerTxt() {
		return answerTxt;
	}

	public boolean isCorrect() {
		return correct;
	}
	
	public String toString() {
		return answerTxt;
	}
}
