package application;

import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;

public abstract class ParseQuizText {
	
	private static ArrayList<String> parseTextToString(String fileNameIn) {
		ArrayList<String> questions = new ArrayList<>();
		try{
			BufferedReader br = new BufferedReader(new FileReader(fileNameIn));
			String tempLine = null;
			outer: while((tempLine = br.readLine()) != null) {
				if(tempLine.startsWith("#") || tempLine.startsWith("//") || (tempLine.trim().equals(""))) {
					continue;
				} else if(Character.isWhitespace(tempLine.charAt(0))){
					questions.add(tempLine);
					continue;
				} else {
					String s = tempLine;
					while(!(Character.isWhitespace(tempLine.charAt(0)))) {
						tempLine = br.readLine();
						if(Character.isWhitespace(tempLine.charAt(0))) {
							questions.add(s);
							questions.add(tempLine);
							continue outer;
						}
						s += (" " + tempLine);
					}
					questions.add(s);
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return questions;
	}
	
	private static ArrayList<Question> formQuestions(ArrayList<String> stringData){
		ArrayList<Question> questions = new ArrayList<>();
		ListIterator<String> it = stringData.listIterator();
		while(it.hasNext()) {
			String questionString = it.next();
			int questionNumSlice = questionString.indexOf(".")+2;
			if(questionNumSlice <= 8) {
				questionString = questionString.substring(questionNumSlice);
			}
			ArrayList<Answer> tempAnswers = new ArrayList<>();
			while(it.hasNext() && Character.isWhitespace(stringData.get(it.nextIndex()).charAt(0))) {
				String answerTxt = it.next();
				boolean isCorrect = false;
				if (answerTxt.endsWith(" *")) {
					isCorrect = true;
				}
				answerTxt = answerTxt.replace("*", "").trim().substring(3);
				tempAnswers.add(new Answer(answerTxt, isCorrect));
			}
			questions.add(new Question(questionString, tempAnswers));
		}
		return questions;
	}
	
	public static ArrayList<Question> makeQuizData(String fileNameIn){
		return formQuestions(parseTextToString(fileNameIn));
	}
}
