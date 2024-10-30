package model.programState;

import model.adt.*;
import model.statement.IStatement;
import model.value.IValue;

public class ProgramState implements IProgramState {
    IMyDictionary<String, IValue> symbolTable;
    IMyStack<IStatement> executionStack;
    IMyList<String> output;

    public ProgramState(){
        symbolTable = new MyDictionary<String, IValue>();
        executionStack = new MyStack<IStatement>();
        output = new MyList<>();
    }

    public ProgramState(IStatement statement){
        this.symbolTable = new MyDictionary<>();
        this.executionStack = new MyStack<>();
        this.output = new MyList<>();
        executionStack.push(statement);
    }

    public ProgramState(IMyDictionary<String, IValue> symbolTable, IMyStack<IStatement> executionStack, IMyList<String> output){
        this.symbolTable = symbolTable;
        this.executionStack = executionStack;
        this.output = output;
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

    //to string
    public String toString(){
        return "Symbol Table:\n" + symbolTable.toString() + "Execution stack:\n"+ executionStack.toString() + "Output:\n" + output;
    }

    // deep copy
    public ProgramState deepCopy(){
        return new ProgramState((this.symbolTable).deepCopy(), (this.executionStack).deepCopy(), (this.output).deepCopy());
    }
}
