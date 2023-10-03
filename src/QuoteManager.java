import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.Date;
//Made by:Sushant, edited by Gaurab
public class QuoteManager extends Quote{

    public QuoteManager() {
    }


    public String[] readtext() {
        String[] data;
        int col = 1;
        String filepath = "quotes.csv";
        ArrayList<String> colData = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(filepath));
            scanner.nextLine();

            while (scanner.hasNext()) {
                String currentLine = scanner.nextLine();
                data = currentLine.split(";");
                if (data.length > col) {
                    colData.add(data[col]);
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error occurred");
            return null;
        }

        return colData.toArray(new String[1]);


    }

    public String[] readauthor() {
        String[] data;
        int col = 0;
        String filepath = "quotes.csv";
        ArrayList<String> colData = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(filepath));
            scanner.nextLine();

            while (scanner.hasNext()) {
                String currentLine = scanner.nextLine();
                data = currentLine.split(";");
                if (data.length > col) {
                    colData.add(data[col]);
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error Occurred");
            return null;
        }

        return colData.toArray(new String[1]);


    }

    public String[] readeditor() {
        String[] data;
        int col = 3;
        String filepath = "quotes.csv";
        ArrayList<String> colData = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(filepath));
            scanner.nextLine();

            while (scanner.hasNext()) {
                String currentLine = scanner.nextLine();
                data = currentLine.split(";");
                if (data.length > col) {
                    colData.add(data[col]);
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error occurred");
            return null;
        }

        return colData.toArray(new String[1]);
    }


    public void printColumn(String[] textdata, String[] authordata, String[] editordata, int currentPage) {
        int last_page = (int)Math.ceil((double)textdata.length / 5);
        if (currentPage < 1 || currentPage > last_page) {
            System.out.println("Invalid page number.");
            return;
        }
        int startIdx = (currentPage - 1) * 5;
        int endIdx = Math.min(startIdx + 5, textdata.length);
        System.out.println("Page " + currentPage + ":");

        for (int i = startIdx; i < endIdx; i++) {
            System.out.println((i + 1) + ". '" + textdata[i] + "'");
            System.out.println("- " + authordata[i]);
            System.out.println("Added by " + editordata[i]);
            System.out.println("---------------------------------");
        }
        System.out.println("Page: " + currentPage );
        if (endIdx == textdata.length) {
            System.out.println("That was the last page.");
        }
        System.out.println("Page: 1" + "------------------ Page: " + last_page);
    }

    public void delete_quotes_after_list(String[] editordata, String[] authordata, String[] textdata, int index) {
        Scanner input = new Scanner(System.in);
        String filePath = "quotes.csv";
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            String headerRow = scanner.nextLine();
            ArrayList<String> rows = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                String[] data = row.split(";");
                // Skip the row to be deleted
                if (data[0].equals(authordata[index]) && data[1].equals(textdata[index]) && data[3].equals(editordata[index])) {
                    continue; // Skip the row to be deleted
                }
                rows.add(row);

            }
            scanner.close();

            // Write the modified data back to the CSV file
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(headerRow + "\n"); // Write the header row
            for (String row : rows) {
                fileWriter.write(row + "\n"); // Write the remaining rows
            }
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Quote has been deleted.");
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the quote.");
            e.printStackTrace();
        }
    }


    public void edit_quotes_after_list(String[] editordata, String[] authordata, String[] textdata, int index) {
        Scanner input = new Scanner(System.in);
        String filePath = "quotes.csv";
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            String headerRow = scanner.nextLine();
            ArrayList<String> rows = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                String[] data = row.split(";");
                // Skip the row to be deleted
                if (data[0].equals(authordata[index]) && data[1].equals(textdata[index]) && data[3].equals(editordata[index])) {
                    System.out.println("-------------------------");
                    System.out.println("Enter new quote");
                    data[1] = input.nextLine();
                    System.out.println("Enter new author");
                    data[0] = input.nextLine();
                    data[2] = String.valueOf(new Date());
                    System.out.println("-------------------------");
                    row = String.join(";", data); // Join the modified data back into a row
                }
                rows.add(row);

            }
            scanner.close();

            // Write the modified data back to the CSV file
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(headerRow + "\n"); // Write the header row
            for (String row : rows) {
                fileWriter.write(row + "\n"); // Write the remaining rows
            }
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Quote has been edited.");
        } catch (IOException e) {
            System.out.println("An error occurred while editing the quote.");
            e.printStackTrace();
        }
    }
    public String[] rand_quote(String[] textdata, String[] authordata, String[] editordata) {
        Random random = new Random();
        int x = random.nextInt(textdata.length);
        if (textdata[x].equals("")) {
            rand_quote(textdata, authordata, editordata);
        } else {
            System.out.println("---------------------------------");
            System.out.println("Here's a random quote:");
            System.out.println();
            System.out.println("'" + textdata[x] + "'");
            System.out.println("- " + authordata[x]);
            System.out.println("Added by " + editordata[x]);
            System.out.println("---------------------------------");
        }
        String[] text_editor = {textdata[x], editordata[x]};
        return text_editor;
    }


    public void delete_quotes_after_random(String[] text_data) {                              //make a method to check editor and if true then call this.-->Gaurab
        String filePath = "quotes.csv";
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            String headerRow = scanner.nextLine();
            ArrayList<String> rows = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                String[] data = row.split(";");
                // Set the values of the row to null if the user matches
                if (data[1].equalsIgnoreCase(text_data[0])) {
                    continue;
                }
                rows.add(row);
            }
            scanner.close();


            // Write the modified data back to the CSV file
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(headerRow + "\n"); // Write the header row
            for (String row : rows) {
                fileWriter.write(row + "\n"); // Write the remaining rows
            }
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Quote has been deleted.");
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the quote.");
            e.printStackTrace();
        }
    }


    public void edit_quotes_after_random(String[] text_data) {                              //make a method to check editor and if true then call this. too/-->Gaurab
        String filePath = "quotes.csv";
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            String headerRow = scanner.nextLine();
            ArrayList<String> rows = new ArrayList<>();
            Scanner input = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                String[] data = row.split(";");
                // Set the values of the row to edited_values if the user matches
                if (data[1].equalsIgnoreCase(text_data[0])) {
                    System.out.println("-------------------------");
                    System.out.println("Enter new quote");
                    data[1] = input.nextLine();
                    System.out.println("Enter new author");
                    data[0] = input.nextLine();
                    System.out.println("Enter new date");
                    data[2] = input.nextLine();
                    System.out.println("-------------------------");
                    row = String.join(";", data); // Join the modified data back into a row
                }
                rows.add(row);
            }
            scanner.close();


            // Write the modified data back to the CSV file
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(headerRow + "\n"); // Write the header row
            for (String row : rows) {
                fileWriter.write(row + "\n"); // Write the remaining rows
            }
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Quote has been edited.");
        } catch (IOException e) {
            System.out.println("An error occurred while editing the quote.");
            e.printStackTrace();
        }
    }




}