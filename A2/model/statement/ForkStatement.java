package model.statement;

import exception.MyException;
import model.programState.ProgramState;

public class ForkStatement implements IStatement{
    IStatement statement;
    public ForkStatement(IStatement statement){
        this.statement = statement;
    };
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        statement.execute(state);
        ProgramState newThread = new ProgramState(state.getSymbolTable().deepCopy(), state.getExecutionStack().deepCopy(),
                state.getOutput(), state.getFileTable(), state.getHeap());
        return newThread;
    }
}
