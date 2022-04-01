import java.io.IOException;
import java.util.Scanner;

public class ProjectFour {
    public static String checkLogIn(String username, String password) {
        String output = "";
        try {
            FileReader fr = new FileReader("usernamespasswords.txt");
            BufferedReader bfr = new BufferedReader(fr);
            String line = "";
            while (line != null) {
                line = bfr.readLine();
                String accounttype = line;
                line = bfr.readLine();
                String name = line;
                line = bfr.readLine();
                String pass = line;

                if (name.equals(username)) {
                    if (pass.equals(password)) {
                        
                    }
                }
            }
            
            bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

    

        System.out.println("1. Log in");
        System.out.println("2. Sign up");
        int loginMenu = Integer.parseInt(scanner.nextLine());

        if (loginMenu == 1) {
            System.out.println("Enter your username:");
            String username = scanner.nextLine();
            System.out.println("Enter your password:");
            String password = scanner.nextLine();

            String checklogin = checkLogIn(username, password);

            //teachers crete, edit, quizzes, create, edit, and delete
        } else if (loginMenu == 2) {

        } else {
            System.out.println("Invalid input! Try again.");
        }

    }
}
