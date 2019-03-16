package Algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Work01 {
    
    static int Max = 1000000; // 최대
    static String command; // 명령어
    static String word; // 찾을 단어
    static String temp; // 빈 칸, ', - 지운 찾을 단어
    static int count; // 총단어수
    static String []voca; // 단어모음
    static String []part; // 품사모음
    static String []sub; // 설명모음
    static String []tmp; // 빈 칸, ', - 지운 단어모음
    static String buffer;
    private static Scanner keyboard;
    
    public static void main(String[] args) {
        
        voca = new String [Max];
        part = new String [Max];
        sub = new String [Max];
        tmp = new String [Max];
        
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
                word = word.substring(1);
                temp = word.replaceAll(" ","");
                temp = temp.replaceAll("'","");
                temp = temp.replaceAll("-","");
                int num;
                
                if(word.equalsIgnoreCase(temp)) // 하나의 단어로 구성된 경우
                    num = find_b(0,count-1,word);
                else
                    num = find_d(0,count-1,temp); // 두 개 이상의 단어 혹은 특수문자로 구성된 경우
                
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
                    tmp[count] = voca[count].replaceAll(" ","");
                    tmp[count] = tmp[count].replaceAll("'", "");
                    tmp[count] = tmp[count].replaceAll("-", "");
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
    
    public static int find_b(int begin, int end, String word2) {
        
        int middle = (begin + end)/2;
        if(end < begin)
            return end;
        if(voca[middle].equalsIgnoreCase(word2))
            return middle;
        else if(voca[middle].compareToIgnoreCase(word2) < 0)
            begin = middle + 1;
        else
            end = middle - 1;
        return find_b(begin, end, word2);
        
    }
    
    public static int find_d(int begin, int end, String word2) {
        
        int middle = (begin + end)/2;
        if(end < begin)
            return end;
        if(tmp[middle].equalsIgnoreCase(word2))
            return middle;
        else if(tmp[middle].compareToIgnoreCase(word2) < 0)
            begin = middle + 1;
        else
            end = middle - 1;
        return find_d(begin, end, word2);
        
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
        else if(tmp[num].equalsIgnoreCase(temp)) {
            
            int buf = num;
            
            while(tmp[buf].equalsIgnoreCase(word)) {
                if(buf == 0)
                    break;
                buf--;
            }
            printWordNotFound(buf);
        }
        else {
            printWordNotFound(num);
        }
    }
    
    public static void printWordNotFound(int num) {
        
        System.out.println("Not found.");
        System.out.println(voca[num] + " " + part[num]);
        System.out.println("- - -");
        System.out.println(voca[num+1] + " " + part[num+1]);
        
    }
}
