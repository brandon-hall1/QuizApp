package application;
import java.util.*;
public class Question {
	private int questionNum;
	private String questionTxt;
	private List<Answer> answers = new ArrayList<>();
	private Answer correctAnswer;
	
	public Question(String questionTxtIn, ArrayList<Answer> answersIn) {
		questionTxt = questionTxtIn;
		answers = answersIn;
		for(Answer a : answers) {
			if(a.isCorrect()) {
				correctAnswer = a;
			}
		}
	}
	
	public Answer getCorrectAnswer() {
		return correctAnswer;
	}

	public int getQuestionNum() {
		return questionNum;
	}
	
	public void setQuestionNum(int questionNumIn) {
		questionNum = questionNumIn;
	}

	public String getQuestionTxt() {
		return questionTxt;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	@Override
	public String toString() {
		return "Question [questionNum=" + questionNum + ", questionTxt=" + questionTxt + ", answers=" + answers + "]";
	}
	
}
