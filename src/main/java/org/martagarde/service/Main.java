package org.martagarde.service;

import org.martagarde.datastr.MyStack;
import org.martagarde.datastr.nodes.MyNodeS;
import org.martagarde.model.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MyStack<MyNodeS<Student>> studentStack = new MyStack<MyNodeS<Student>>();
        MyStack<MyNodeS<Integer>> intStack = new MyStack<MyNodeS<Integer>>();

        MyNodeS intNode1 = new MyNodeS(12);
        MyNodeS intNode2 = new MyNodeS(15);
        MyNodeS intNode3 = new MyNodeS(7);
        MyNodeS intNode4 = new MyNodeS(21);
        MyNodeS intNode5 = new MyNodeS(89);

        Student s1 = new Student("Janis", "Berzins", new int[3]);
        Student s2 = new Student("Anna", "Panna", new int[3]);
        Student s3 = new Student("Lauris", "Reiniks", new int[3]);
        Student s4 = new Student("Annija", "Ozola", new int[3]);
        Student s5 = new Student("Smaida", "Jauka", new int[3]);

        MyNodeS studNode1 = new MyNodeS<>(s1);
        MyNodeS studNode2 = new MyNodeS<>(s2);
        MyNodeS studNode3 = new MyNodeS<>(s3);
        MyNodeS studNode4 = new MyNodeS<>(s4);
        MyNodeS studNode5 = new MyNodeS<>(s5);

        try {
            intStack.myPush(intNode1);
            intStack.myPush(intNode2);
            intStack.myPush(intNode3);
            intStack.myPush(intNode4);
            intStack.myPush(intNode5);

            intStack.print();
            System.out.println();

            studentStack.myPush(studNode1);
            studentStack.myPush(studNode2);
            studentStack.myPush(studNode3);
            studentStack.myPush(studNode4);
            studentStack.myPush(studNode5);

            studentStack.print();
            System.out.println();

            File checkFile = new File("C:/Users/mgard/Downloads/DataStrMD1Faili/UserController.java");
            codeCheck(checkFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public static void codeCheck(File readFile) throws Exception{
        if(!readFile.exists() || !readFile.canRead()) throw new Exception("File can't be read");
        System.out.println("File is being read...");
        try {
            Scanner myScanner = new Scanner(readFile);
            String line = myScanner.nextLine();
            char[] lineInChars = line.toCharArray();
            MyStack<MyNodeS<Character>> bracketsStack = new MyStack<MyNodeS<Character>>();
            MyNodeS bracketNode;
            int lineCounter = 1;
            int problemLine = 0;


            while(myScanner.hasNextLine()){
                System.out.println("New line is being read...");
                System.out.println(lineInChars);
                for(int i = 0; i < lineInChars.length; i++){

                    System.out.println(lineInChars[i]);

                    if(lineInChars[i] == ';'){
                        System.out.println("End of line.");
                        break;
                    }else
                    if(lineInChars[i] == '/' && lineInChars[i+1] == '/'){ //ja ir komentars tad izlaist
                        System.out.println("Line has comment.");
                        break;
                    } else
                    if(lineInChars[i] == '/' && lineInChars[i+1] == '*'){ //ja ir komentars, atrast kur beidzas
                        System.out.println("Comment started");
                        while(!(lineInChars[lineInChars.length - 1] == '/' && lineInChars[lineInChars.length - 2] == '*')){  // kamer nav komentara beigas turpina meklet
                            line = myScanner.nextLine();
                            lineCounter++;
                            lineInChars = line.toCharArray();
                        }
                        System.out.println("Comment ended.");
                        break;
                    } else

                    if(lineInChars[i] == '[' || lineInChars[i] == ']' || lineInChars[i] == '{' || lineInChars[i] == '}' || lineInChars[i] == '(' || lineInChars[i] == ')'){
                        System.out.println("Added bracket.");
                        bracketNode = new MyNodeS<>(lineInChars[i]);
                        bracketsStack.myPush(bracketNode);

                        bracketsStack.print();
                    }
                }
                if(!bracketLineCheck(bracketsStack)){
                    problemLine = lineCounter;
                    throw new Exception("Bracket problem in line " + problemLine);
                }

                do{
                    lineCounter++;
                    line = myScanner.nextLine();
                }while(line.isEmpty());

                lineInChars = line.toCharArray();
            }
            if(!bracketLineCheck(bracketsStack)){
                throw new Exception("Bracket problem in line " + problemLine);
            }
            System.out.println("File finished checking. A total of " + lineCounter + " lines.");
            myScanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean bracketLineCheck(MyStack<MyNodeS<Character>> stack) throws Exception {
        System.out.println("Checking brackets...");
        if(stack.isStackEmpty()) return true;

        MyStack<MyNodeS<Character>> checkStack = new MyStack<>();
        checkStack.copyStack(checkStack, stack);

        char currentBracket;
        int square = 0;
        int figure = 0;
        int regular = 0;
        //true => all good   false => not good

        for(int i = 0; i < checkStack.howManyElementsInStack() - 1; i++){
            currentBracket = (char) checkStack.myTop().getElement();
            switch (currentBracket){
                case '[':
                    square++;
                    break;
                case '{':
                    figure++;
                    break;
                case '(':
                    regular++;
                    break;
                case ']':
                    square--;
                    if(square == -1){
                        System.out.println("Nuh-uh");
                        return false;
                    }
                    break;
                case '}':
                    figure--;
                    if(figure == -1){
                        System.out.println("Nuh-uh");
                        return false;
                    }
                    break;
                case ')':
                    regular--;
                    if(regular == -1){
                        System.out.println("Nuh-uh");
                        return false;
                    }
                    break;
            }
            checkStack.myPop();
        }

        if(stack.howManyElementsInStack() > 3 && (square != 0 || figure != 0 || regular != 0)){
            System.out.println("Nuh-uh");
            return false;
        }else{
            System.out.println("Yippee!");
            return true;
        }
    }



}