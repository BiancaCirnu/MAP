package model.programState;

import model.adt.*;
import model.statement.IStatement;
import model.value.IValue;

import java.io.BufferedReader;

public class ProgramState implements IProgramState {
    IMyDictionary<String, IValue> symbolTable;
    IMyStack<IStatement> executionStack;
    IMyList<String> output;
    IMyDictionary<String, BufferedReader> fileTable;
    IMyHeap heap;

    public ProgramState(){
        symbolTable = new MyDictionary<String, IValue>();
        executionStack = new MyStack<IStatement>();
        output = new MyList<>();
        fileTable = new MyDictionary<>();
        heap = new MyHeap();
    }

    public ProgramState(IStatement statement){
        this.symbolTable = new MyDictionary<>();
        this.executionStack = new MyStack<>();
        this.output = new MyList<>();
        this.fileTable = new MyDictionary<>();
        this.heap = new MyHeap();
        executionStack.push(statement);
    }

    public ProgramState(IMyDictionary<String, IValue> symbolTable, IMyStack<IStatement> executionStack, IMyList<String> output, IMyDictionary<String, BufferedReader> fileTable, IMyHeap heap){
        this.symbolTable = symbolTable;
        this.executionStack = executionStack;
        this.output = output;
        this.fileTable = fileTable;
        this.heap = heap;
    }

    //getters
    public IMyDictionary<String, IValue> getSymbolTable(){
        return symbolTable;
    }
    public IMyStack<IStatement> getExecutionStack(){
        return executionStack;
    }
    public IMyList<String> getOutput(){
        return output;
    }
    public IMyDictionary<String, BufferedReader> getFileTable(){
        return fileTable;
    }
    public IMyHeap getHeap(){
        return heap;
    }
    //to string
    public String toString(){
        return "Symbol Table:\n" + symbolTable.toString() + "Execution stack:\n"+ executionStack.toString() + "Output:\n" + output + "FileTable:\n" + fileTable.toString() + "Heap:\n"+heap.toString();
    }

    // deep copy
    public ProgramState deepCopy(){
        return new ProgramState((this.symbolTable).deepCopy(), (this.executionStack).deepCopy(), (this.output).deepCopy(), (this.fileTable).deepCopy(), heap);
    }
}
