package source.repository;

import source.model.programState.ProgramState;

import java.util.List;

public interface IRepository {
    void setProgramStateList(List<ProgramState>states);
}
