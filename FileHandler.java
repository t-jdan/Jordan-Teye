import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Jordan Ayiku Teyes
 */

public class FileHandler {

    /**
     * Allows files to be opened without having to call
     * File class every time
     * @param filename
     * @return A scanner with an open file
     */
    public static Scanner useFile(String filename){
        try{
            File file = new File(filename);
            Scanner info = new Scanner(file);
            return info;
        }catch(FileNotFoundException fnfe){
            System.out.println("An error occurred");
            fnfe.printStackTrace();
        }
        return null;
    }

    
    // Creates a new file of required format
    static void createFile(String filename){
        try{
            File output = new File("filename");
            if(output.createNewFile()){
                System.out.println("File created: " + output.getName());
            }else{
                System.out.println("File already exists");
            }
        }catch(IOException ioe){
            System.out.println("An error occurred");
        }

    }

    // Allows the user to write to a file with only
    // by taking in the string as a paramater
    static FileWriter writeToFile(String filename){
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(filename);
            System.out.println("Successfully wrote to file " + filename);
        } catch (IOException ioe) {
            System.out.println("An error occurred.");
        }
        return myWriter;
    }
}
