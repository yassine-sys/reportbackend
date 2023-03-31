package com.example.backend.services;

import com.example.backend.dao.FunctionRepository;
import com.example.backend.dao.SubModuleRepository;
import com.example.backend.entities.Function;
import com.example.backend.entities.Module;
import com.example.backend.entities.ResourceNotFoundException;
import com.example.backend.entities.SubModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "SubModuleService")
public class SubModuleServiceImpl implements SubModuleService{

    SubModuleRepository subModuleRepository;

    FunctionRepository functionRepository;

    public SubModuleServiceImpl(SubModuleRepository subModuleRepository, FunctionRepository functionRepository) {
        this.subModuleRepository = subModuleRepository;
        this.functionRepository = functionRepository;
    }

    @Override
    public SubModule addSubModule(SubModule subModule) {
        return subModuleRepository.save(subModule);
    }

    @Override
    public List<SubModule> getListSubModule() {
        return subModuleRepository.findAll();
    }

    @Override
    public void deleteSubModule(Long Id) {

        SubModule subModule = subModuleRepository.getOne(Id);
        if (subModule != null)
        {
            subModuleRepository.delete(subModule);
        }

    }

    @Override
    public SubModule findById(Long Id) {
        return subModuleRepository.getOne(Id);
    }

    @Override
    public SubModule editSubModule(Long Id,SubModule subModule) throws ResourceNotFoundException{
        SubModule submodule = subModuleRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("subModule not found for this id :: " + Id));
       submodule.setSubModuleName(subModule.getSubModuleName());
       submodule.setPath(subModule.getPath());
       submodule.setModule(subModule.getModule());
       submodule.setFunctions(subModule.getFunctions());

       final SubModule updatedSubmodule = subModuleRepository.save(submodule);

        return updatedSubmodule;
    }
}
