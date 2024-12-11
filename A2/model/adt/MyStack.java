package model.adt;

import model.modelExceptions.MyEmptyStackException;

import java.util.Stack;

public class MyStack<Elem> implements IMyStack<Elem> {
    public Stack<Elem> stack;
    public MyStack() {
        stack = new Stack<Elem>();
    }
    public MyStack(Stack<Elem> stack) {
        this.stack = stack;
    }

    @Override
    public void push(Elem elem) {
        stack.push(elem);
    }

    @Override
    public Elem pop() throws MyEmptyStackException {
        if (stack.isEmpty()) {
            throw new MyEmptyStackException("The stack is empty");
        }
        return stack.pop();
    }

    @Override
    public int getSize(){
        return stack.size();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public Stack<Elem> getStack() {
        return stack;
    }

    public String toString() {
        String str = "";

        for (int i =stack.size()-1; i>=0; i--) {
            str += (stack.get(i)).toString()+'\n';
        }
        return str;
    }
    public IMyStack<Elem> deepCopy() {
        MyStack<Elem> newStack = new MyStack<>();
        Stack<Elem> tempStack = new Stack<>();
        while (!stack.isEmpty()) {
            Elem elem = stack.pop();
            tempStack.push(elem);
        }
        while (!tempStack.isEmpty()) {
            Elem elem = tempStack.pop();
            stack.push(elem);
            newStack.push(elem);
        }

        return newStack;
    }

}
