package source.model.statement;

import source.exception.MyException;
import source.model.adt.IMyDictionary;
import source.model.adt.IMyStack;
import source.model.adt.MyStack;
import source.model.programState.ProgramState;
import source.model.type.IType;

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

    @Override
    public IMyDictionary<String, IType> typeCheck(IMyDictionary<String, IType> typeEnvironment) throws MyException {
        IMyDictionary<String, IType> typeEnv1 = statement.typeCheck(typeEnvironment);
        return typeEnv1;
    }
}
