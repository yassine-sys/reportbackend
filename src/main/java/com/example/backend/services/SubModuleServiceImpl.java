package com.example.backend.services;

import com.example.backend.dao.FunctionRepository;
import com.example.backend.dao.SubModuleRepository;
import com.example.backend.entities.Function;
import com.example.backend.entities.Module;
import com.example.backend.entities.SubModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public SubModule editSubModule(SubModule subModule,Long Id){
        System.out.println(subModule.getSubModuleName());
        SubModule sub =  subModuleRepository.getOne(Id);
        if (sub!=null){
            sub.setSubModuleName(subModule.getSubModuleName());
            sub.setModule(subModule.getModule());
            List<Function> functions=new ArrayList<>();
            if(subModule.getFunctions()!=null){
                for (Function function:subModule.getFunctions()){
                    Function existingFunction = functionRepository.getOne(subModule.getId());
                    existingFunction.setSubModule(sub);
                    functions.add(existingFunction);
                }
                List<Function> existingFunctions = sub.getFunctions();
                for (Function existingFunction:existingFunctions){
                    if (!functions.contains(existingFunction)){
                        existingFunction.setSubModule(null);
                    }
                }
                sub.setFunctions(functions);
            }
        }
        return subModuleRepository.saveAndFlush(sub);
    }
}
