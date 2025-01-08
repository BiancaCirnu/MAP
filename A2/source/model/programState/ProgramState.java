package source.model.programState;

import source.exception.MyException;
import source.model.adt.*;
import source.model.statement.IStatement;
import source.model.value.IValue;

import java.io.BufferedReader;

public class ProgramState implements IProgramState {
    IMyDictionary<String, IValue> symbolTable;
    IMyStack<IStatement> executionStack;
    IMyList<String> output;
    IMyDictionary<String, BufferedReader> fileTable;
    IMyHeap heap;
    public static int currentID = 0;
    int programId;
    public ProgramState(){
        symbolTable = new MyDictionary<String, IValue>();
        executionStack = new MyStack<IStatement>();
        output = new MyList<>();
        fileTable = new MyDictionary<>();
        heap = new MyHeap();
        currentID += 1;
        programId = currentID ;
    }

    public ProgramState(IStatement statement){
        this.symbolTable = new MyDictionary<>();
        this.executionStack = new MyStack<>();
        this.output = new MyList<>();
        this.fileTable = new MyDictionary<>();
        this.heap = new MyHeap();
        currentID += 1;
        programId = currentID ;

        executionStack.push(statement);
    }

    public ProgramState(IMyDictionary<String, IValue> symbolTable, IMyStack<IStatement> executionStack, IMyList<String> output, IMyDictionary<String, BufferedReader> fileTable, IMyHeap heap){
        this.symbolTable = symbolTable;
        this.executionStack = executionStack;
        this.output = output;
        this.fileTable = fileTable;
        this.heap = heap;
        currentID += 1;
        programId = currentID ;    }

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
        return "Symbol Table " + programId +" :\n" + symbolTable.toString() + "Execution stack: " + programId +" :\n"+ executionStack.toString() + "Output " + programId +" :\n" + output + "FileTable " + programId +" :\n" + fileTable.toString() + "Heap:\n"+heap.toString() + "Program Id: " + programId + '\n';
    }

    // deep copy
    public ProgramState deepCopy(){
        return new ProgramState((this.symbolTable).deepCopy(), (this.executionStack).deepCopy(), (this.output).deepCopy(), (this.fileTable).deepCopy(), heap);
    }

    public Boolean isNotCompleted(){
        return !(executionStack.isEmpty());
    }
    public ProgramState oneStep() throws MyException {
        IMyStack<IStatement> stack = this.getExecutionStack();
        if (stack.isEmpty()) {
            throw new MyException("Stack is empty");
        }
        IStatement statement = stack.pop();
        return statement.execute(this);
    }
}
