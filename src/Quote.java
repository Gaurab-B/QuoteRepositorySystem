import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Date;

//by Binayak
public class Quote {
    private String Author;
    private String Text;
    private String Editor;
    private Date AddDate;

    public Quote(){
    }

    //this checks if editor/the person who adds a code is a user or not.       -->Gaurab
    public boolean check_editor(String editor){
        String filePath = "users.csv";
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("File " + filePath + " does not exist.");
            return false;
        }

        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                String[] data = row.split(";");
                if ((data.length >= 3 && data[2].equalsIgnoreCase(editor)) || data[2].equalsIgnoreCase("admin")) {
                    return true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return  false;
    }
    public void create(String name){                                           //this method creates a code ---->Gaurab
        try{
            FileWriter fileWriter = new FileWriter("quotes.csv",true);
            Scanner input = new Scanner(System.in);
            System.out.println("---------------------------------");
            boolean editor_check = check_editor(name);
            if (!editor_check ){
                System.out.println("Error");
                create(name);
            }
            System.out.println("Enter the name of the author");
            Author = input.nextLine();
            System.out.println("Enter the quote");
            Text = input.nextLine();
            AddDate = new Date();
            Editor = name;
            System.out.println("---------------------------------");
            String data = Author + ";" + Text + ";" + AddDate + ";" + Editor;
            fileWriter.append(data);
            fileWriter.append("\n");
            System.out.println("Data add success");
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    //This method shows the list of quotes -->Gaurab
    public void show() {
        String filePath = "quotes.csv";
        File file = new File(filePath);
        int i = 1;

        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                String[] data = row.split(";");
                System.out.println("Quote : " + i);
                System.out.println("----------------------------------");
                System.out.println("\"" + data[1] + "\"");
                System.out.println(data[0]);
                System.out.println("Added by : " + data[3] + " on " + data[2]);
                System.out.println("----------------------------------");
                i += 1;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

















//Getters and Setters cause Java does it for me.  -->Gaurab

    public String getAuthor() {
        return Author;
    }


    public void setAuthor(String author) {
        Author = author;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getEditor() {
        return Editor;
    }

    public void setEditor(String editor) {
        Editor = editor;
    }

    public Date getAddDate() {
        return AddDate;
    }

    public void setAddDate(Date addDate) {
        AddDate = addDate;
    }
}
