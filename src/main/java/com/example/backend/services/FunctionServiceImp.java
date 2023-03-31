package com.example.backend.services;

import com.example.backend.dao.FunctionRepository;
import com.example.backend.entities.Function;
import com.example.backend.entities.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("FunctionService")

public class FunctionServiceImp implements FunctionService{
    @Autowired
    FunctionRepository funcRepo;
    @PersistenceContext
    private EntityManager em;

    @Override
    public Function addFunction(Function f) {
        return funcRepo.save(f);
    }

    @Override
    public List<Function> getListFunction() {
        return  funcRepo.findAll();
    }

    @Override
    public void deleteFunc(Long id) {
        Function f = funcRepo.getOne(id);
        if(f!=null){
            funcRepo.delete(f);
        }
    }

    @Override
    public Function updateFunction(long id, Function function) throws ResourceNotFoundException{

        Function function1=funcRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("function not found for this id :: " + id));
        function1.setFunctionName(function.getFunctionName());
        function1.setGroup(function.getGroup());
        function1.setSubModule(function.getSubModule());

        final Function updatedFunction = funcRepo.save(function1);

        return updatedFunction;
    }
}
