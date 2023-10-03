import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Author authors = new Author();
        User users = new User();
        Quote quotes = new Quote();
        QuoteManager qm = new QuoteManager();
        admin_or_user(authors, users, quotes,qm);
    }

    public static void admin_login(int choice,Author authors, User users, Quote quotes,QuoteManager qm){
        Admin admins = new Admin();
        switch (choice){
            case 1:
                quotes.show();
                choice = admin_choice();
                admin_login(choice,authors,users,quotes,qm);          //donotchange
                break;
            case 2:
                authors.quotes_to_authors();
                System.out.println(authors.print_list_of_authors());
                choice = admin_choice();
                admin_login(choice,authors,users,quotes,qm);            //donotchange
                break;
            case 3:
                System.out.println(admins.list_of_users());
                choice = admin_choice();
                admin_login(choice,authors,users,quotes,qm);       //donoechange
                break;
            case 4:
                users.setLogged(true);                          //donotchange
                users.admin_press_4();
                run(authors, users,quotes,qm);
            case 5:
                admin_or_user( authors,users,quotes,qm);       //donotchange
            case 6:
                admins.delete_user();                           //works . do not change!
                choice = admin_choice();
                admin_login(choice,authors,users,quotes,qm);
                break;
            case 7:
                System.exit(0);                         //donotchange
            default:
                choice = admin_choice();
                admin_login(choice,authors,users,quotes,qm);           //tochange
                break;

        }
    }
    public static void admin_or_user(Author authors, User users, Quote quotes,QuoteManager qm){
        Admin admins = new Admin();
        Scanner inp = new Scanner(System.in);
        System.out.println("---------------------------");
        System.out.println("1.Admin Login");
        System.out.println("2.User Login");
        System.out.println("3.Quit");
        System.out.println("---------------------------");
        int choice = inp.nextInt();
        inp.nextLine();
        switch (choice){
            case 1:
                if(admins.admin_login()){
                    int choice2 = admin_choice();
                    admin_login(choice2,authors,users,quotes,qm);
                    break;
                }else{
                    admin_or_user( authors,users,quotes,qm);
                    break;
                }
            case 2:
                run(authors, users,quotes,qm);
                break;
            case 3:
                System.exit(0);
            default:
                admin_or_user( authors,users,quotes,qm);
                break;
        }
    }

    public static void run(Author authors, User users, Quote quotes, QuoteManager qm){
        int first_choice = first_choice();
        switch (first_choice){
            case 1:
                call_after_user_selects_1( authors, users, quotes,qm);
                break;
            case 2:
                authors.quotes_to_authors();
                System.out.println(authors.print_list_of_authors());
                run(authors, users,quotes,qm);
                break;
            case 3:
                if(users.isLogged()){
                    quotes.create(users.getName());
                }
                else{
                    System.out.println("User not logged in");
                }
                run(authors, users,quotes,qm);
                break;
            case 4:
                if(users.isLogged()){
                    authors.add_author();
                }
                else{
                    System.out.println("User not logged in");
                }
                run(authors, users,quotes,qm);
                break;
            case 5:
                authors.search_author();
                run(authors, users,quotes,qm);
                break;
            case 6:
                if(users.isLogged()){                                                                               //this calls another function that is after random
                    String[] text = qm.rand_quote(qm.readtext(), qm.readauthor(), qm.readeditor());
                    after_random(authors,users,quotes,text,qm);
                }
                else{
                    System.out.println("User not logged in");
                }
                run(authors, users,quotes,qm);
                break;
            case 7:
                users.CreateAccount();
                run(authors, users,quotes,qm);
                break;
            case 8:
                if(users.isLogged()){
                    System.out.println("There is already someone logged in");
                }
                else{
                    users.Login();
                }
                run(authors, users,quotes,qm);
                break;
            case 9:
                users.Logout();
                admin_or_user(authors, users, quotes,qm);
                break;
            case 10:
                System.exit(0);
        }
    }

    public static int first_choice(){
        Scanner input = new Scanner(System.in);
        System.out.println("------------------------------");
        System.out.println("Welcome, User What do you want to do?");
        System.out.println("1. List all the quotes.");                      //To be done by Sushant
        System.out.println("2. List all the authors.");                     //To be done by Gaurab(done)
        System.out.println("3. Add a quote");               //logged is true                //To be done by Sushant
        System.out.println("4. Add an author");            //logged is true                 //To be done by Gaurab(done)
        System.out.println("5. Search for an author");                      //To be done by Gaurab(done)
        System.out.println("6. Get a Random Quote");        //logged is true                //To be done by Sushant
        System.out.println("7. Create an account");                         //To be done by Binayak(done)
        System.out.println("8. Log in to your account");       //ifnotloggedinalready              //To be done by Binayak(done)
        System.out.println("9. Logout");                    //To be done by Gaurab(done)
        System.out.println("10. Quit");                                        //To be done by Binayak(done)
        System.out.println("------------------------------");
        int choice = input.nextInt();
        input.nextLine();
        return choice;
    }

    public static int admin_choice(){
        Scanner input = new Scanner(System.in);
        System.out.println("------------------------------");
        System.out.println("Welcome, Admin What do you want to do?");
        System.out.println("1. List all the quotes.");                      //To be done by Sushant
        System.out.println("2. List all the authors.");
        System.out.println("3. List all the users.");                      //To be done by Sushant
        System.out.println("4. Go to user-view");
        System.out.println("5. Log Out and Go Back");
        System.out.println("6. Delete a User");
        System.out.println("7. Quit");
        System.out.println("------------------------------");
        int choice = input.nextInt();
        input.nextLine();
        return choice;
    }

    public static void after_random(Author authors, User users, Quote quotes, String[] text,QuoteManager qm) {         //this need to be fixed
        users.setLogged(true);
        char temp = next_random_choice_input();
        if (temp == 'M') {
            text = qm.rand_quote(qm.readtext(), qm.readauthor(), qm.readeditor());
            after_random(authors,users,quotes,text,qm);
        } else if (temp == 'E') {                               //edit -->Binayak(WORKS)
            if (text[1].equalsIgnoreCase(users.getName()) || users.getName().equalsIgnoreCase("admin")) {
                qm.edit_quotes_after_random(text);
            } else{
                System.out.println("Editor mismatch");
            }
            after_random(authors,users,quotes,text,qm);
        } else if (temp == 'D') {                                   //delete -->Binayak(WORKS)
            if (text[1].equalsIgnoreCase(users.getName()) || users.getName().equalsIgnoreCase("admin")) {
                qm.delete_quotes_after_random(text);
            } else{
                System.out.println("Editor mismatch");
            }
            after_random(authors,users,quotes,text,qm);
        } else if (temp == 'A') {
            if(users.isLogged()){
                quotes.create(users.getName());
            }
            else{
                System.out.println("User not logged in");
            }
            run(authors, users,quotes,qm);
        } else if (temp == 'B'){
            run(authors, users,quotes,qm);
        } else{
            System.out.println("Invalid Command");
            run(authors, users,quotes,qm);
        }
    }

    public static char next_random_choice_input(){                  //this works-donottouch //this method is to be called after a user chooses random
        Scanner sd = new Scanner(System.in);
        System.out.println("------------------------------------------------------------");
        System.out.println("M. Show another random quote");
        System.out.println("E. Edit quote");
        System.out.println("D. Delete quote");
        System.out.println("A. Add a  quote");
        System.out.println("B. Back to previous menu");
        System.out.println("------------------------------------------------------------");
        char cd = sd.next().toUpperCase().charAt(0);
        sd.nextLine();
        return cd;
    }


    public static void call_after_user_selects_1(Author authors, User users, Quote quotes, QuoteManager qm) {
        Scanner input2 = new Scanner(System.in);
        int currentPage = 1;
        while (true) {
            if (currentPage == 1) {
                qm.printColumn(qm.readtext(), qm.readauthor(), qm.readeditor(), currentPage);
            }
            char input = after_user_SELECTS_1();
            if (input == 'n') {
                currentPage++;
                qm.printColumn(qm.readtext(), qm.readauthor(), qm.readeditor(), currentPage);
            } else if (input == 'p') {
                currentPage--;
                qm.printColumn(qm.readtext(), qm.readauthor(), qm.readeditor(), currentPage);
            } else if (input == 'e') {//edit
                System.out.println("Which number of the quote do you want to edit?");
                int index2 = input2.nextInt();
                input2.nextLine();
                index2-=1;
                if ( qm.readeditor()[index2].equalsIgnoreCase(users.getName())|| users.getName().equalsIgnoreCase("admin")) {

                    qm.edit_quotes_after_list(qm.readeditor(), qm.readauthor(), qm.readtext(),index2);
                } else{
                    System.out.println("Editor Mismatch");
                }
                run(authors, users,quotes,qm);

            } else if (input == 'd') {//edit
                System.out.println("Which number of the quote do you want to delete?");
                int index2 = input2.nextInt();
                input2.nextLine();
                index2-=1;
                if ( qm.readeditor()[index2].equalsIgnoreCase(users.getName())|| users.getName().equalsIgnoreCase("admin")) {

                    qm.delete_quotes_after_list(qm.readeditor(), qm.readauthor(), qm.readtext(),index2);
                } else{
                    System.out.println("Editor Mismatch");
                }
                run(authors, users,quotes,qm);

            } else if (input == 'a') { // add
                if(users.isLogged()){
                    quotes.create(users.getName());
                }
                else{
                    System.out.println("User not logged in");
                }
                run(authors, users,quotes,qm);
                break;

            } else {
                run(authors, users, quotes, qm);
            }
        }
    }


    public static char after_user_SELECTS_1(){
        Scanner input = new Scanner(System.in);
        System.out.println("---------------------------------------");
        System.out.println("Do you want to go to the next, previous page or exit? (P/N/X)");
        System.out.println("E. Edit the quote.");
        System.out.println("D. Delete the quote.");
        System.out.println("A. add the quote.");
        System.out.println("Enter anything else to go back to the previous screen");
        System.out.println("---------------------------------");
        return input.next().toLowerCase().charAt(0);
    }
}


