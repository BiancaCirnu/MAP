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
        for (Elem elem : stack) {
            str += elem.toString()+'\n';
        }
        return str;
    }
    public IMyStack<Elem> deepCopy(){
        return new MyStack<Elem>(stack);
    }
}
