import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

//This works , do not touch -->Gaurab
public class Admin extends User{
    public Admin(){
    }

    public ArrayList<String> list_of_users(){                           //WORKS
            String filePath = "users.csv";
            File file = new File(filePath);
            ArrayList<String> list_of_users = new ArrayList<>();
            try {
                Scanner scanner = new Scanner(file);
                scanner.nextLine();

                while (scanner.hasNextLine()) {
                    String row = scanner.nextLine();
                    String[] data = row.split(";");
                    list_of_users.add(data[2]);
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        return list_of_users;
    }

    public boolean admin_login(){                                   //WORKS
        Scanner admin = new Scanner(System.in);
        System.out.println("-----------------------------------");
        System.out.println("Enter your name");
        String name = admin.nextLine();
        System.out.println("Enter your password");
        String password = admin.nextLine();
        System.out.println("-----------------------------------");

        if(name.equals("admin") && password.equals("admin")){
            return true;
        } else{
            System.out.println("LOGIN FAILED");
        }
        return false;
    }

    public void delete_user() {                                     //WORKS
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the user to be deleted:");
        String user_to_delete = input.nextLine();
        String filePath = "users.csv";
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            String headerRow = scanner.nextLine();
            boolean userFound = false;
            ArrayList<String> rows = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                String[] data = row.split(";");
                if (data[2].equalsIgnoreCase(user_to_delete)) {
                    userFound = true;
                    continue;
                }
                rows.add(row);
            }
            scanner.close();

            if (!userFound) {
                System.out.println("User '" + user_to_delete + "' was not found in the CSV file.");
                return;
            }

            // Write the modified data back to the CSV file
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(headerRow + "\n"); // Write the header row
            for (String row : rows) {
                fileWriter.write(row + "\n"); // Write the remaining rows
            }
            fileWriter.flush();
            fileWriter.close();
            System.out.println("User '" + user_to_delete + "' has been deleted.");
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the user.");
            e.printStackTrace();
        }
    }



}
