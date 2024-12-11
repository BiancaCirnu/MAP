package model.statement;

import exception.MyException;
import model.adt.IMyStack;
import model.adt.MyStack;
import model.programState.ProgramState;

public class ForkStatement implements IStatement{
    IStatement statement;
    public ForkStatement(IStatement statement){
        this.statement = statement;
    };
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IMyStack myStackCopy  =new MyStack();
        myStackCopy.push(statement);
        ProgramState newThread = new ProgramState(state.getSymbolTable().deepCopy(),myStackCopy,
                state.getOutput(), state.getFileTable(), state.getHeap());
        return newThread;
    }
    public String toString(){
        return "fork(" + statement.toString() + ")";
    }
}
