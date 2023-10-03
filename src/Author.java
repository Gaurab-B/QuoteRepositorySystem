import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
                                //AUTHOR WORKS
                                //By Gaurab
public class Author {
    Scanner inp = new Scanner(System.in);
    private ArrayList<String> list_of_authors;

    public Author() {
        this.list_of_authors = new ArrayList<>();
        this.list_of_authors = print_list_of_authors();
    }
    //The code takes value from quotes and stores the name of authors in a different authors csv file. --->Gaurab
    public void quotes_to_authors() {
        File file1 = new File("quotes.csv");
        try {
            Scanner scanner = new Scanner(file1);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                String[] data = row.split(";");
                String author_name = data[0];
                if (!list_of_authors.contains(author_name)) {
                    try {
                        FileWriter fileWriter = new FileWriter("authors.csv", true);
                        fileWriter.append(author_name);
                        fileWriter.append("\n");
                        fileWriter.flush();
                        fileWriter.close();
                        list_of_authors.add(author_name);
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    //This code prints the list of authors from the authors csv file -->Gaurab
    public ArrayList<String> print_list_of_authors() {
        File file = new File("authors.csv");
        list_of_authors = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                String[] data = row.split(";");
                list_of_authors.add(data[0]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return list_of_authors;
    }
    //This code adds the author to the authors.csv file.        --->Gaurab
    public void add_author(){
        System.out.println("------------------------------");
        System.out.println("State the name of the author");
        System.out.println("--------------------------");
        String author_name = inp.nextLine();
        try{
            FileWriter fileWriter = new FileWriter("authors.csv",true);
            fileWriter.append(author_name);
            System.out.println("Author successfully added");
            fileWriter.append("\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    //This code searches the author name in the authors.csv file.           --->Gaurab
    public void search_author(){
        System.out.println("------------------------------");
        System.out.println("State the name of the author");
        System.out.println("--------------------------");
        String author_name = inp.nextLine();

        String filePath = "authors.csv";
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                String[] data = row.split(";");
                if (data[0].equalsIgnoreCase(author_name)){
                    System.out.println("Author Found");
                    return;
                }
            }
            System.out.println("Author Not Found");
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}