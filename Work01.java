package Algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Work01 {
    
    static int Max = 1000000;
    static String command;
    static String word;
    static int count;
    static String []voca;
    static String []part;
    static String []sub;
    static String buffer;
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        voca = new String [Max];
        part = new String [Max];
        sub = new String [Max];
        
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
                int num = find(0,count-1,word);
                System.out.println(voca[num]);
            }
            else if(command.equals("exit"))
                break;
        }
    }
    
    
    public static void read(String word2) {
        try {
            Scanner    inFile = new Scanner(new File(word2));
            
            while(inFile.hasNext()) { // detect End of File
                buffer = inFile.nextLine();
                int len = buffer.length();
                int num1 = buffer.indexOf("(");
                int num2 = buffer.indexOf(")");
                
                if(len == num2 + 1) {
                    voca[count] = buffer.substring(0);
                }
                else {
                    voca[count] = buffer.substring(0,num1-1);
                    part[count] = buffer.substring(num1,num2+1);
                    sub[count] = buffer.substring(num2+2);
                }
                
                if(inFile.hasNext()) {
                    buffer = inFile.nextLine();
                }
                count++;
            }
            
            inFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file");
            System.exit(9);
        }
        
    }
    
    public static int find(int begin, int end, String word2) {
        
        int middle = (begin + end)/2;
        if(end <= begin)
            return middle;
        if(voca[middle].equalsIgnoreCase(word2))
            return middle;
        else if(voca[middle].compareToIgnoreCase(word2) < 0)
            begin = middle + 1;
        else
            end = middle - 1;
        return find(begin, end, word2);
        
    }
    
}
