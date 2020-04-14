package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

public class MenuController implements Initializable {
	@FXML
	private TextField studentFirstName;
	@FXML
	private TextField studentLastName;
	@FXML
	private Button startButton;
	@FXML
	private RadioButton randomToggle;
	@FXML
	private TitledPane menuTitleText;
	
	@FXML
	public void startQuiz(ActionEvent event) throws IOException {
		if(studentFirstName.getText().trim().equals("")|| studentLastName.getText().trim().equals("")) {
			return;
		}
		String firstName = studentFirstName.getText().trim();
		String lastName = studentLastName.getText().trim();
		boolean isRandomized = randomToggle.isSelected();
		Student student = new Student(firstName, lastName);
		student.newQuiz("quiz.txt", isRandomized);
		Parent quizView = FXMLLoader.load(getClass().getResource("QuizInProgress.fxml"));
		Scene quizScene = new Scene(quizView);
		Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
		window.setScene(quizScene);
		window.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		studentFirstName.setText("");
		studentLastName.setText("");
		randomToggle.setSelected(false);
		menuTitleText.setText("Main Menu");
	}
}
