import java.io.IOException;
import java.util.Scanner;
import java.io.*;

public class ProjectFour {
    public static boolean checkUsername(String username) {
        boolean out = true;
        try {
            FileReader fr = new FileReader("users.txt");
            BufferedReader bfr = new BufferedReader(fr);
            String line = "";
            while (line != null) {
                String pass = "";
                String accounttype = "";
                String name = "";
                line = bfr.readLine();
                line = bfr.readLine();
                if (line != null) {
                    name = line.substring(10, line.length());
                }
                line = bfr.readLine();

                if (name.equals(username)) {
                    out = false;
                }
            }
            bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }
    public static String checkLogIn(String username, String password) {
        String output = "";
        try {
            FileReader fr = new FileReader("users.txt");
            BufferedReader bfr = new BufferedReader(fr);
            String line = "";
            while (line != null) {
                String pass = "";
                String accounttype = "";
                String name = "";
                line = bfr.readLine();
                if (line != null) {
                    accounttype = line;
                }
                line = bfr.readLine();
                if (line != null) {
                    name = line.substring(10, line.length());
                }
                
                line = bfr.readLine();
                if (line != null) {
                    pass = line.substring(10, line.length());
                }

                if (name.equals(username)) {
                    if (pass.equals(password)) {
                        line = null;
                        output = accounttype;
                    }
                }
            }
            
            bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public static void signUp(String username, String password, String type) {
        try {
            FileOutputStream fos = new FileOutputStream("users.txt", true); 
            PrintWriter pw = new PrintWriter(fos);
            pw.println(type);
            pw.println("username: " + username);
            pw.println("password: " + password);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Log in");
        System.out.println("2. Sign up");
        int loginMenu = Integer.parseInt(scanner.nextLine());

        boolean ongoing = true;
        boolean signup = false;
        String username = "";
        String password = "";
        String checklogin = "";
        String type = "";
        Student student = null;
        Teacher teacher = null;
        boolean firstteacher = true;
        boolean firststudent = true;
        while (ongoing) {
            if (loginMenu == 1) {
                if (!signup) {
                    System.out.println("Enter your username:");
                    username = scanner.nextLine();
                    System.out.println("Enter your password:");
                    password = scanner.nextLine();
                    checklogin = checkLogIn(username, password);
                } else {
                    checklogin = type;
                }
    
                
                if (checklogin.equals("teacher")) { 
                    type = "teacher";                   
                    if (firstteacher) {
                        teacher = new Teacher(username);
                        firstteacher = false;
                    }
                    teacher.viewMenu();
                    int choice = Integer.parseInt(scanner.nextLine());
                    if (choice == 1) {
                        //teacher.createQuiz(scanner);
                    } else if (choice == 2) {
                        //teacher.editQuiz(scanner);
                    } else if (choice == 3) {
                        //teacher.deleteQuiz(scanner);
                    } else if (choice == 4) {
                        //teacher.viewSubmission(scanner);
                    } else if (choice == 5) {
                        //ongoing = false;
                    }
                    signup = true;
                } 
                if (checklogin.equals("student")) {
                    type = "student"; 
                    if (firststudent) {
                        student = new Student(username);
                        firststudent = false;
                    }
                    student.viewMenu();
                    int choice = Integer.parseInt(scanner.nextLine());
                    if (choice == 1) {
                        student.takeQuiz(scanner);
                    } else if (choice == 2) {
                        student.viewSubmission(scanner);
                    } else if (choice == 3) {
                        ongoing = false;
                    }
                    signup = true;
                }
                if (checklogin.equals("")) {
                    System.out.println("Incorrect username or password! Plese try again.");
                }
            } else if (loginMenu == 2) {
                System.out.println("Enter account type (teacher/student):");
                type = scanner.nextLine();
                System.out.println("Enter username:");
                username = scanner.nextLine();
                System.out.println("Enter password:");
                password = scanner.nextLine();

                boolean checkusername = checkUsername(username);
                String typeerror = "";
                if (!type.equals("teacher") && !type.equals("student")) {
                    typeerror = "incorrect";
                    if (!checkusername) {
                        typeerror = "both";
                    }
                    checkusername = false;
                }

                if (checkusername) {
                    signUp(username, password, type);
                    System.out.println("Account created!");
                    loginMenu = 1;
                    signup = true;
                } else {
                    if (typeerror.equals("incorrect")) {
                        System.out.println("Wrong account type!");
                        System.out.println("--------------------------------");
                    } else if (typeerror.equals("both")) {
                        System.out.println("Wrong account type!");
                        System.out.println("This username is unavailble!");
                        System.out.println("--------------------------------");
                    } else {
                        System.out.println("This username is unavailble!");
                        System.out.println("--------------------------------");
                    }
                    
                }
    
            } else {
                System.out.println("Invalid input! Try again.");
            }
        }

    }
}

