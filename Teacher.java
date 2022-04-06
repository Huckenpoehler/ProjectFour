import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Teacher {
    private String userName;
    private String password;

    public Teacher(String userName) {
        this.userName = userName;
    }


    /**	createQuiz
	 * 
	 * 	Method exclusive to Teacher. Prompts for a question prompt
	 * 	and answer choices, and then 1) creates a reference in quiz.txt
	 * 	and 2) creates a new file titled 'quiz0X.txt'.
	 */
	public void createQuiz(Scanner scanner) {
		
        try {
    		System.out.println("Please enter the question prompt: ");
    		String quizCreate = scanner.next();
    		String[] answerChoices = {new String("a. "), new String("b. "), new String("c. "), new String("d. ")};
            for (int num = 0; num <= 3; num++) {
            	System.out.println("Please enter the answer choice for option " + answerChoices[num]);
            	quizCreate.concat(answerChoices[num] + scanner.next());
            }
            BufferedReader bfr = new BufferedReader(new FileReader("quiz.txt"));
            // determining quiz number and adding to quiz.txt
            int quizNumber = 1;
            while (bfr.readLine() != null) quizNumber++;
            bfr.close();
            FileWriter directoryWriter = new FileWriter("quiz.txt");
            directoryWriter.write("quiz0" + quizNumber);
            directoryWriter.close();
            // creating new quiz file and writing to it
            FileWriter newQuizWriter = new FileWriter(new File("quiz0" + quizNumber));
            newQuizWriter.write(quizCreate);
            newQuizWriter.close();
            // determining randomization
            System.out.println("Would you like to randomize the order of the questions (Y/N)? ");
            boolean randomize;
            String input = scanner.next();
            if (input.equals("Y"))
            	randomize = true;
            else if (input.equals("N"))
            	randomize = false;
            // catch tomfoolery
            else {
            	while (input != "Y" || input != "N") {
            		System.out.println("Please enter either Y, for yes, or N, for no.");
            		System.out.println("Would you like to randomize the order of the questions (Y/N)? ");
            		input = scanner.next();
            	}
            }
            // TODO: implement question order randomization ...
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**	editQuiz
	 * 
	 * 	Method exclusive to Teacher. Reads from the quiz file directory and
	 *  overwrites a question and answer set within a selected quiz with 
	 *  a new question and answer set.
	 */
	public void editQuiz(Scanner scanner) {
		
		/** begin duplicated code **/
		ArrayList<String> quizList = new ArrayList<>();
        File quiz = new File("quiz.txt");
        if (quiz.length() == 0) {
            // see if quiz.txt is empty
            System.out.println("There is no quiz!");
        } else {
            // if not empty
            // read from the quiz list
            try {
                BufferedReader bfr = new BufferedReader(new FileReader("quiz.txt"));
                String line = bfr.readLine();
                while (line != null) {
                    quizList.add(line);
                    line = bfr.readLine();
                }
                bfr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // display the quiz list
        for (int i = 0; i < quizList.size(); i++) {
            System.out.print(i + 1 + ".");
            System.out.println(quizList.get(i));
        }

        // select a quiz to view
        System.out.println("Type a number to select a quiz:");
        int chooseQuiz = scanner.nextInt();
        ArrayList<String> quizRead = new ArrayList<>();
        // chosen quiz name
        String chosenQuiz = "quiz0" + chooseQuiz + ".txt";
        File quizToEdit = new File(chosenQuiz);
        if (chooseQuiz <= quizList.size() && chooseQuiz > 0) { // see if the input range between 1 - quizList.size()
            if (quizToEdit.exists()) { // see if the file exists
                try { // read quiz0x
                    BufferedReader bfr = new BufferedReader(new FileReader(quizToEdit));
                    String line = bfr.readLine();
                    while (line != null) {
                        quizRead.add(line);
                        line = bfr.readLine();
                    }
                    bfr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        /** end duplicated code **/
        
        // prompting for the question to be edited
        System.out.println("Please input the number of the question you would like to edit: ");
        int questionNumber = scanner.nextInt();
        String newQuestion = questionNumber + ".\n";
        int questionIndex = (questionNumber - 1) * 6;
        quizRead.set(questionIndex, newQuestion);
        questionIndex++;
        // prompting for new question prompt
        System.out.println("Please enter the new question prompt: ");
        quizRead.set(questionIndex, scanner.next());
        // creating String array to be indexed through
        String[] answerChoices = {new String("a. "), new String("b. "), new String("c. "), new String("d. ")};
        // prompting for new question options
        for (int num = 0; num <= 3; num++) {
        	questionIndex++;
        	System.out.println("Please enter the new answer choice for option " + answerChoices[num]);
        	quizRead.set(questionIndex, "\n" + answerChoices[num] + scanner.next());
        }
        try { // overwrite quiz0x
            FileWriter fw = new FileWriter(quizToEdit, false);
            for (int i = 0; i < quizRead.size(); i++) {
            	fw.write(quizRead.get(i));
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Quiz " + chooseQuiz + " successfully updated.");
	}
    
    public void viewMenu() {
        System.out.println("1. Create a new Quiz");
        System.out.println("2. Edit a quiz");
        System.out.println("3. Delete a quiz");
        System.out.println("4. View a student's submissions");
    }

    public void viewSubmission(Scanner scanner) {

        ArrayList<String> quizList = new ArrayList<>();
        File quiz = new File("quiz.txt");
        if (quiz.length() == 0) {
            // see if quiz.txt is empty
            System.out.println("There is no quiz!");
        } else {
            // if not empty
            // read from the quiz list
            try {
                BufferedReader bfr = new BufferedReader(new FileReader("quiz.txt"));
                String line = bfr.readLine();
                while (line != null) {
                    quizList.add(line);
                    line = bfr.readLine();
                }
                bfr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // display the quiz list
            for (int i = 0; i < quizList.size(); i++) {
                System.out.print(i + 1 + ".");
                System.out.println(quizList.get(i));
            }

            // select a quiz to view
            System.out.println("Type a number to select a quiz:");
            int chooseQuiz = scanner.nextInt();

            // chosen quiz name
            String chosenQuiz = "quiz0" + chooseQuiz + "_Completed.txt";
            File completedQuiz = new File(chosenQuiz);
            if (chooseQuiz <= quizList.size() && chooseQuiz > 0) { // see if the input range between 1 - quizList.size()
                if (completedQuiz.exists()) { // see if the file exists
                    ArrayList<String> studentList = new ArrayList<>();
                    try { // read quiz0x_completed
                        BufferedReader bfr = new BufferedReader(new FileReader(completedQuiz));
                        String line = bfr.readLine();
                        while (line != null) {
                            studentList.add(line);
                            line = bfr.readLine();
                        }
                        bfr.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // print student that completed this quiz
                    for (String s : studentList) {
                        System.out.println(s);
                    }

                    // ask teacher to choose a student to view his submission
                    System.out.println("Type in one's name to view the submission:");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    String submission = "quiz0" + chooseQuiz + "_" + name + ".txt";

                    // check student submission file
                    File studentSubmission = new File(submission);
                    if (studentSubmission.exists()){
                        // read from that file
                        ArrayList<String> studentAnswer = new ArrayList<>();
                        try { // read quiz0x_completed
                            BufferedReader bfr = new BufferedReader(new FileReader(studentSubmission));
                            String line = bfr.readLine();
                            while (line != null) {
                                studentAnswer.add(line);
                                line = bfr.readLine();
                            }
                            bfr.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        for (String s: studentAnswer){
                            System.out.println(s);
                        }

                        System.out.println();

                        // grading part
                        System.out.println("Would you like to grade it? (1/2)");
                        System.out.println("1.Yes");
                        System.out.println("2.No");
                        int grade = scanner.nextInt();
                        if (grade == 2){
                            System.out.println("View session end!");

                        } else if (grade == 1){
                            String gradeBook = "quiz0" + chooseQuiz + "_" + name + "_grade.txt";

                            try { // create the grading book
                                PrintWriter pw = new PrintWriter(new FileOutputStream(gradeBook));
                                for (int i = 1; i <= studentAnswer.size() - 3; i++) {
                                    System.out.println("Points for question " + i + " answer is: ");
                                    pw.print("Points for question " + i + " answer is: ");
                                    int point = scanner.nextInt(); // assume the grade is an int
                                    pw.println(point);
                                }
                                pw.close();
                                System.out.println("Grading end!");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        } else {
                            System.out.println("Invalid Input!");
                        }

                    } else {
                        System.out.println("Input invalid!");
                    }

                } else {
                    System.out.println("No students complete this quiz!");
                }
            } else {
                System.out.println("Invalid input, please type a valid number!");
            }

        }
    }
}
