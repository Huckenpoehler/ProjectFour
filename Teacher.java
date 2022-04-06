import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Teacher {
    private String userName;
    private String password;

    public Teacher(String userName) {
        this.userName = userName;
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
