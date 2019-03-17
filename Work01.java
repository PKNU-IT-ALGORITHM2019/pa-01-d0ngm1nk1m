package Algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Work01 {
    
    static int Max = 1000000; // 최대
    static int count; // 총단어수
    static String command; // 명령어
    static String word; // 찾을 단어
    static String word_t; // 빈 칸, ', - 지운 찾을 단어
    static String []voca; // 단어모음
    static String []part; // 품사모음
    static String []sub; // 설명모음
    static String []voca_t; // 빈 칸, ', - 지운 단어모음
    static String buffer;
    private static Scanner keyboard;
    
    public static void main(String[] args) {
        
        voca = new String [Max];
        part = new String [Max];
        sub = new String [Max];
        voca_t = new String [Max];
        
        while(true){
            
            System.out.print("$ ");
            keyboard = new Scanner(System.in);
            
            command = keyboard.next();
            if(command.equals("read")) {
                word = keyboard.next();
                read(word);
            }
            else if(command.equals("size"))
                System.out.println(count);
            else if(command.equals("find")) {
                word = keyboard.nextLine();
                word = word.trim();
                word_t = cleanup(word,word_t);
                int num;
                
                if(word.equalsIgnoreCase(word_t)) // 하나의 단어로 구성된 경우
                    num = find(0,count-1,word,voca);
                else                                // 두 개 이상의 단어 혹은 특수문자로 구성된 경우
                    num = find(0,count-1,word_t,voca_t);
                
                printWord(num);
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
                
                if(len == num2 + 1)
                    voca[count] = buffer.substring(0);
                else {
                    voca[count] = buffer.substring(0,num1-1);
                    part[count] = buffer.substring(num1,num2+1);
                    sub[count] = buffer.substring(num2+2);
                    voca_t[count] = cleanup(voca[count],voca_t[count]);
                }
                
                if(inFile.hasNext())
                    buffer = inFile.nextLine();
                count++;
            }
            
            inFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file");
            System.exit(9);
        }
    }
    
    public static String cleanup(String str1, String str2) {
        
        str2 = str1.replaceAll(" ","");
        str2 = str2.replaceAll("'", "");
        str2 = str2.replaceAll("-", "");
        return str2;
    }
    
    public static int find(int begin, int end, String target, String []string) {
        
        int middle = (begin + end)/2;
        if(end < begin)
            return end;
        if(string[middle].equalsIgnoreCase(target))
            return middle;
        else if(string[middle].compareToIgnoreCase(target) < 0)
            begin = middle + 1;
        else
            end = middle - 1;
        return find(begin, end, target, string);
        
    }
    
    public static void printWord(int num) {
        
        if(voca[num].equalsIgnoreCase(word)) {
            
            int buf = num;
            int k = 0;
            
            while(voca[buf].equalsIgnoreCase(word)) {
                if(buf == 0)
                    break;
                buf--;
            }
            
            if(buf == 0)
                num = buf;
            else
                num = ++buf;
            
            while(voca[buf].equalsIgnoreCase(word)) {
                k++;
                buf++;
            }
            
            System.out.println("Found " + k + " items.");
            
            for(int i = 0; i < k; i++)
                System.out.println(voca[num+i] + " " + part[num+i] + " " + sub[num+i]);
        }
        else if(voca_t[num].equalsIgnoreCase(word_t)) {
            
            int buf = num;
            
            while(voca_t[buf].equalsIgnoreCase(word_t)) {
                if(buf == 0)
                    break;
                buf--;
            }
            printWordNotFound(buf);
        }
        else
            printWordNotFound(num);
    }
    
    public static void printWordNotFound(int num) {
        
        System.out.println("Not found.");
        System.out.println(voca[num] + " " + part[num]);
        System.out.println("- - -");
        System.out.println(voca[num+1] + " " + part[num+1]);
        
    }
}
