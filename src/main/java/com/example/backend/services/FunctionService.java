package com.example.backend.services;

import com.example.backend.entities.Function;

import java.util.List;

public interface FunctionService {
    Function addFunction(Function f);
    List<Function> getListFunction();
    void deleteFunc(Long id);

    Function updateFunction(long id, Function function);
}
