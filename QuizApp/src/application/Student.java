package application;

import java.util.ArrayList;

public class Student {
	private static long currentID;
	private static Student currentStudent;
	private Quiz currentQuiz;
	private long studentID;
	private String firstName;
	private String lastName;
	private ArrayList<Quiz> finishedQuizzes;
	
	{
		currentID++;
	}
	

	public Student(String firstNameIn, String lastNameIn) {
		studentID = currentID;
		firstName = firstNameIn;
		lastName = lastNameIn;
		currentStudent = this;
	}
	
	public static Student getCurrentStudent() {
		return currentStudent;
	}
	public long getStudentID() {
		return studentID;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}

	public Quiz getCurrentQuiz() {
		return currentQuiz;
	}
	
	public ArrayList<Quiz> getFinishedQuizzes() {
		return finishedQuizzes;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + (int) (studentID ^ (studentID >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (studentID != other.studentID)
			return false;
		return true;
	}
	
	public void newQuiz(String fileNameIn, boolean isRandomized) {
		currentQuiz = new Quiz(fileNameIn, isRandomized);
	}

	
}
