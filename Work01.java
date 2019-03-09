package Algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Work01 {
    
    static int Max = 1000000;
    static String command;
    static String word;
    static int count;
    static String []main;
    static String []sub;
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        main = new String [Max];
        sub = new String [Max];
        int n = 0;
        
        while(true){
            
            System.out.print("$ ");
            Scanner keyboard = new Scanner(System.in);
            
            command = keyboard.next();
            if(command.equals("read")) {
                word = keyboard.next();
                read(word);
            }
            else if(command.equals("size")) {
                System.out.println(count);
            }
            else if(command.equals("find")) {
                word = keyboard.next();
                find(word);
            }
            else if(command.equals("exit"))
                break;
        }
    }
    
    
    public static void read(String word2) {
        try {
            Scanner    inFile = new Scanner(new File(word2));
            
            while(inFile.hasNext()) { // detect End of File
                main[count] = inFile.next();
                sub[count] = inFile.nextLine();
                count++;
            }
            
            inFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file");
            System.exit(9);
        }
        
    }
    
    public static void find(String word2) {
        
        
        
        
        
    }
    
}
