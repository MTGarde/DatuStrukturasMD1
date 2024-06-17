package org.martagarde.datastr;

import org.martagarde.datastr.nodes.MyNodeS;

import java.util.Stack;

public class MyStack<Ttype> {
    private MyNodeS first;
    private int length;


    public MyStack(){
        first = null;
        length = -1;
    }


    public boolean isStackEmpty(){
        if(length == -1){
            return true;
        }
        return false;
    }

    public int howManyElementsInStack(){
        return length + 1;
    }

    public void myPush(MyNodeS element) throws Exception{
        if(element == null) throw new Exception("Invalid element");

        element.setRefrenceNext(first);
        first = element;

        length++;
    }

    public void myPop() throws Exception{
        if(length == -1) throw new Exception("Stack is already empty");

        first = first.getRefrenceNext();

        length--;
    }

    public MyNodeS myTop(){
        return first;
    }

    public void print() throws Exception{
        if(length == -1)
            throw new Exception("Stack is empty.");

        MyNodeS printNode = first;
        for(int i = 0; i <= length; i++){
            System.out.print(printNode);
            printNode = printNode.getRefrenceNext();
        }
    }
    public void copyStack(MyStack newStack, MyStack stack) throws Exception{
        if(stack.length == -1)
            throw new Exception("Stack is empty.");

        MyNodeS copyNode = stack.first;
        for( int i = 0; i < stack.length; i++){
            newStack.myPush(copyNode);
            copyNode = copyNode.getRefrenceNext();
        }
    }

    public void emptyStack() throws Exception{
        if(length == -1)
            System.out.println("Stack is already empty");
        else
            do{
                myPop();
                length--;
            }while(length > -1);
    }
}
