import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
    private String userName;

    //Scanner scanner = new Scanner(System.in);

    public Student(String userName) {
        this.userName = userName;
    }

    public String getUsername() {
        return this.userName;
    }

    public void takeQuiz() {
        ArrayList<String> quizList = new ArrayList<>();
        File quiz = new File("quiz.txt");

        if (quiz.length() == 0) {
            // see if quiz.txt is empty
            System.out.println("There is no quiz!");
        } else {
            // if not empty
            System.out.println("Choose a quiz!");
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

            // display the chosen quiz
            ArrayList<String> displayQuiz = new ArrayList<>();
            int chooseQuiz = scanner.nextInt();
            String chosenQuiz = quizList.get(chooseQuiz - 1) + ".txt";
            try {
                BufferedReader bfr = new BufferedReader(new FileReader(chosenQuiz));
                String line = bfr.readLine();
                while (line != null) {
                    displayQuiz.add(line);
                    line = bfr.readLine();
                }
                bfr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println();
            for (int i = 0; i < displayQuiz.size(); i++) {
                System.out.println(displayQuiz.get(i));
            }
            System.out.println();

            // way of how student submit the answer
            System.out.println("Select a way to submit your answer:");
            System.out.println("1.Manually input");
            System.out.println("2.upload a file");
            int quizNumber = displayQuiz.size() / 6; // see how many quizzes
            int chooseWay = scanner.nextInt();
            scanner.nextLine();
            if (chooseWay == 1) { // manually input the answer
                String[] answer = new String[quizNumber];
                for (int i = 1; i <= quizNumber; i++) {
                    System.out.println("Please type an answer for question " + i + ":");
                    String chosenAnswer = scanner.nextLine();
                    answer[i - 1] = chosenAnswer;
                }


                // submission file
                String submission = getQuizName() + "_" + getUserName() + ".txt";
                System.out.println(submission);
                Date date = new Date();
                Timestamp timestamp = new Timestamp(date.getTime());
                try { // create the submission file with format of: quizName, userName, Answers, timestamp
                    PrintWriter pw = new PrintWriter(new FileOutputStream(submission));
                    pw.println(getQuizName());
                    pw.println(getUserName());
                    for (int i = 1; i <= quizNumber; i++) {
                        pw.println("Question " + i + " answer is: " + answer[i - 1]);
                    }
                    pw.println(timestamp);
                    pw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (chooseWay == 2) { // upload an answer file
                System.out.println("Please input your File Path:"); // assume only answer included
                String path = scanner.nextLine();
                //File myObj = new File("C:\\Users\\15472\\IdeaProjects\\PJ04\\test.txt");
                File uploadFile = new File(path);
                ArrayList<String> readFile = new ArrayList<>();
                String submission = getQuizName() + "_" + getUserName() + ".txt";

                if (uploadFile.exists()) {
                    // read the file
                    try {
                        BufferedReader bfr = new BufferedReader(new FileReader(uploadFile));
                        String line = bfr.readLine();
                        while (line != null) {
                            readFile.add(line);
                            line = bfr.readLine();
                        }
                        bfr.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // write file to submission
                    try { // create the submission file with format of: quizName, userName, Answers, timestamp
                        Date date = new Date();
                        Timestamp timestamp = new Timestamp(date.getTime());
                        PrintWriter pw = new PrintWriter(new FileOutputStream(submission));
                        pw.println(getQuizName());
                        pw.println(getUserName());
                        pw.println("Answers:");
                        for (int i = 1; i <= readFile.size(); i++) {
                            pw.println(readFile.get(i - 1));
                        }
                        pw.println(timestamp);
                        pw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("The file does not exist.");
                }


            }


        }


    }
    
     public void viewSubmission(Scanner scanner) {
        ArrayList<String> quizList = new ArrayList<>();
        File quiz = new File("quiz.txt");
        if (quiz.length() == 0) {
            // see if quiz.txt is empty
            System.out.println("No quiz created!");
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

            if (chooseQuiz <= quizList.size() && chooseQuiz > 0) { // see if the input range between 1 - quizList.size()

                // chosen quiz name
                String chosenGrade = "quiz0" + chooseQuiz +  "_" + getUserName() + "_grade.txt";
                File gradeBook = new File(chosenGrade);

                if (gradeBook.exists()) { // see if the file exists
                    // read from user's grade book
                    ArrayList<String> point = new ArrayList<>();
                    try { // read quiz0x_completed
                        BufferedReader bfr = new BufferedReader(new FileReader(gradeBook));
                        String line = bfr.readLine();
                        while (line != null) {
                            point.add(line);
                            line = bfr.readLine();
                        }
                        bfr.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    for (String s: point){
                        System.out.println(s);
                    }
                } else {
                    System.out.println("Your teacher hasn't graded your quiz yet!");
                }
            } else {
                System.out.println("Invalid input, please type a valid number!");
            }
        }
    }
}
