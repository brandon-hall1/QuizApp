package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ResultsController implements Initializable{
	@FXML
	private Label studentNameText;
	@FXML
	private Label studentIDText;
	@FXML
	private Label gradeText;
	@FXML
	private Button mainMenuBtn;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Student currentStudent = Student.getCurrentStudent();
		Quiz currentQuiz = currentStudent.getCurrentQuiz();
		studentNameText.setText(currentStudent.getLastName() + ", " + currentStudent.getFirstName());
		studentIDText.setText("Student ID: " + currentStudent.getStudentID());
		gradeText.setText("Grade: " + currentQuiz.calculateScore());
	}
	
	public void onButtonPress(ActionEvent event) throws IOException {
		Parent menuView = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		Scene menuScene = new Scene(menuView);
		Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
		window.setScene(menuScene);
		window.show();
	}
}
