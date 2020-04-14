package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class QuizController implements Initializable {
	@FXML
	private ToggleGroup answerBtns;
	@FXML
	private RadioButton answer1Btn;
	@FXML
	private RadioButton answer2Btn;
	@FXML
	private RadioButton answer3Btn;
	@FXML
	private RadioButton answer4Btn;
	@FXML
	private RadioButton answer5Btn;
	@FXML
	private TitledPane questionNumText;
	@FXML
	private Label questionText;
	@FXML
	private Button nextBtn;
	
	public void nextQuestion(ActionEvent event) {
		Student currentStudent = Student.getCurrentStudent();
		Quiz currentQuiz = currentStudent.getCurrentQuiz();
		checkAnswer(currentQuiz);
		Question question = currentQuiz.getNextQuestion();
		List<Answer> answers = question.getAnswers();
		questionText.setText(question.getQuestionTxt());
		questionNumText.setText("Question " + question.getQuestionNum());
		answerBtns.getSelectedToggle().setSelected(false);;
		answer1Btn.setDisable(true);
		answer2Btn.setDisable(true);
		answer3Btn.setDisable(true);
		answer4Btn.setDisable(true);
		answer5Btn.setDisable(true);
		answer1Btn.setText("");
		answer2Btn.setText("");
		answer3Btn.setText("");
		answer4Btn.setText("");
		answer5Btn.setText("");
		switch(answers.size()) {
		case 5:
			answer5Btn.setDisable(false);
			answer5Btn.setText(answers.get(4).getAnswerTxt());
		case 4:
			answer4Btn.setDisable(false);
			answer4Btn.setText(answers.get(3).getAnswerTxt());
		case 3:
			answer3Btn.setDisable(false);
			answer3Btn.setText(answers.get(2).getAnswerTxt());
		case 2:
			answer2Btn.setDisable(false);
			answer2Btn.setText(answers.get(1).getAnswerTxt());
		case 1:
			answer1Btn.setDisable(false);
			answer1Btn.setText(answers.get(0).getAnswerTxt());
		}	
		if(question.getQuestionNum() == currentQuiz.getQuestionsAmount()) {
			nextBtn.setText("Submit Quiz");
			nextBtn.setOnAction(e -> {
				try {
					submitQuiz(e);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
		}
		
	}
	
	private void checkAnswer(Quiz quiz) {
		Toggle selected = answerBtns.getSelectedToggle();
		List<Answer> answers = quiz.getCurrentQuestion().getAnswers();
		if(selected == answer1Btn) {
			quiz.checkAnswer(answers.get(0));
		}
		if(selected == answer2Btn) {
			quiz.checkAnswer(answers.get(1));
		}
		if(selected == answer3Btn) {
			quiz.checkAnswer(answers.get(2));
		}
		if(selected == answer4Btn) {
			quiz.checkAnswer(answers.get(3));
		}
		if(selected == answer5Btn) {
			quiz.checkAnswer(answers.get(4));
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Student currentStudent = Student.getCurrentStudent();
		Quiz currentQuiz = currentStudent.getCurrentQuiz();
		Question question = currentQuiz.getNextQuestion();
		List<Answer> answers = question.getAnswers();
		questionText.setText(question.getQuestionTxt());
		questionNumText.setText("Question " + question.getQuestionNum());
		answer1Btn.setDisable(true);
		answer2Btn.setDisable(true);
		answer3Btn.setDisable(true);
		answer4Btn.setDisable(true);
		answer5Btn.setDisable(true);
		answer1Btn.setText("");
		answer2Btn.setText("");
		answer3Btn.setText("");
		answer4Btn.setText("");
		answer5Btn.setText("");
		switch(answers.size()) {
		case 5:
			answer5Btn.setDisable(false);
			answer5Btn.setText(answers.get(4).getAnswerTxt());
		case 4:
			answer4Btn.setDisable(false);
			answer4Btn.setText(answers.get(3).getAnswerTxt());
		case 3:
			answer3Btn.setDisable(false);
			answer3Btn.setText(answers.get(2).getAnswerTxt());
		case 2:
			answer2Btn.setDisable(false);
			answer2Btn.setText(answers.get(1).getAnswerTxt());
		case 1:
			answer1Btn.setDisable(false);
			answer1Btn.setText(answers.get(0).getAnswerTxt());
		}	
	}
	
	public void submitQuiz(ActionEvent event) throws IOException {
		Student currentStudent = Student.getCurrentStudent();
		Quiz currentQuiz = currentStudent.getCurrentQuiz();
		checkAnswer(currentQuiz);
		Parent resultView = FXMLLoader.load(getClass().getResource("ScoreMenu.fxml"));
		Scene resultScene = new Scene(resultView);
		Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
		window.setScene(resultScene);
		window.show();
	}
}
