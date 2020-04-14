package application;

import java.util.*;

public class Quiz {
	private List<Question> questions;
	private Queue<Question> questionsQueue;
	private Question currentQuestion;
	private int questionsCorrect;
	private int questionsAmount;
	
	public Quiz(String fileNameIn, boolean isRandomized) {
		questions = ParseQuizText.makeQuizData(fileNameIn);
		if(isRandomized) {
			randomizeQuiz();
		}
		int count = 1;
		for(Question q : questions) {
			q.setQuestionNum(count++);
		}
		questionsQueue = questionsToQueue(questions);
		questionsAmount = questions.size();
	}
	
	private Queue<Question> questionsToQueue(List <Question> questionsIn){
		LinkedList<Question> queue = new LinkedList<>();
		queue.addAll(questionsIn);
		return queue;
	}
	
	private void randomizeQuiz(){
		Collections.shuffle(questions);
		int questionCount = 0;
		for(Question q : questions) {
			q.setQuestionNum(++questionCount);
		}
	}
	
	public Question getNextQuestion() {
		currentQuestion = questionsQueue.poll();
		return currentQuestion;
	}
	
	public Question getCurrentQuestion() {
		return currentQuestion;
	}
	
	public int getQuestionsAmount() {
		return questionsAmount;
	}
	
	public boolean checkAnswer(Answer answerIn) {
		if(answerIn.equals(currentQuestion.getCorrectAnswer())) {
			questionsCorrect++;
			return true;
		} else {
			return false;
		}
	}
	
	public double calculateScore() {
		return ((questionsCorrect*100)/questionsAmount);
	}
	
	
}
