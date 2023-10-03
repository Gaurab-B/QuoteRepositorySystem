import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.ArrayList;

//USER CLASS WORK --DO NOT TOUCH -->GAURAB
//email;password;name
public class User {
    private String email;
    private String password;
    private boolean Logged;     //TRUE = LOGIN ,, FALSE = LOGOUT
    private ArrayList<String> list_of_emails;
    private String name;

    public User(){
        this.list_of_emails = new ArrayList<String>();
        this.list_of_emails = get_email();
    }

    //THIS GETS LIST OF EMAILS FROM THE CSV FILE AND STORES IN A ARRAYLIST -->GAURAB
    public ArrayList<String> get_email(){
        String filePath = "users.csv";
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            String headers = scanner.nextLine();
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                if (!row.equals(headers)) {
                    String[] data = row.split(";");
                    list_of_emails.add(data[0]);
                }

            }
            scanner.close();
        } catch (FileNotFoundException e) {             //-->Good Job -->Sushant
            e.printStackTrace();
        }
        return  list_of_emails;
    }
    //THIS CREATES AN ACCOUNT -->GAURAB
    public void CreateAccount(){
        try {
            FileWriter csvWriter = new FileWriter("users.csv",true);     //(A,TRUE) MAKES IT STORE IN THE SAME FILEE -->GAURAB
            Scanner user_input = new Scanner(System.in);
            System.out.println("--------------------------------------------------------");
            System.out.println("State your name: ");
            name = user_input.nextLine();
            System.out.println("State your email address: ");
            email = user_input.nextLine();
            System.out.println("State your password: ");
            password = user_input.nextLine();
            System.out.println("--------------------------------------------------------");
            boolean element_found = false;
            for (String element : list_of_emails) {
                if (element.equalsIgnoreCase(email)) {
                    System.out.println("Email already in use");
                    element_found = true;
                }
            }
            if (!element_found) {
                String data = email + ";" + password + ";" + name;
                csvWriter.append(data);
                csvWriter.append("\n");
                csvWriter.flush();
                csvWriter.close();
                System.out.println("Account Created and stored on the file");
                list_of_emails.add(email);
            }
        } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    //THIS IS FOR LOGIN -->GAURAB
    public void Login(){
        Scanner user_input = new Scanner(System.in);
        System.out.println("---------------------------------------------------------");
        System.out.println("Enter your email address");
        String email_address = user_input.nextLine();
        System.out.println("Enter your password");
        String password = user_input.nextLine();
        System.out.println("---------------------------------------------------------");
        this.name = setName(email_address);
        String filePath = "users.csv";
        File file = new File(filePath);

        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                String[] data = row.split(";");
                if (data[0].equalsIgnoreCase(email_address) && data[1].equals(password)) {
                    this.setLogged(true);
                    System.out.println("Log in successful.");
                    break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }                                                                       //Works
        if (!isLogged()) {
            System.out.println("Error");
        }


    }
    public void admin_press_4(){
        this.name = "admin";
        this.password = "admin";
        this.email = "admin";
    }

    //THIS IS LOGOUT, IT SETS LOGGED TO FALSE.
    public void Logout(){
        this.Logged = false;
    }


    public String setName(String email){            //sets name for login
        String filePath = "users.csv";
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                String[] data = row.split(";");
                if(data[0].equalsIgnoreCase(email)) {
                    return data[2];
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }








//GETTERS AND SETTERS CAUSE WHY NOT --->GAURAB

    public boolean isLogged() {
        return Logged;
    }
    public void setLogged(boolean logged) {
        Logged = logged;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getName(){
        return this.name;
    }

    public void setName2(String name){
        this.name = name;
    }
}

