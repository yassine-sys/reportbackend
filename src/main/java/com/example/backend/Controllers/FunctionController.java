package com.example.backend.Controllers;

import com.example.backend.entities.Function;
import com.example.backend.services.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/function")
public class FunctionController {

    @Autowired
    private FunctionService funcService;

    @RequestMapping(value = "/list")
    public List<Function> allFunctions(){
        return funcService.getListFunction();
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        funcService.deleteFunc(id);
    }

    @RequestMapping(value="/add",method= RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addFunction(@RequestBody Function function) {
        funcService.addFunction(function);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Function> updateFunction(@PathVariable("id") long id, @RequestBody Function function) {

        Function updatedFunction=funcService.updateFunction(id, function);

        return ResponseEntity.ok(updatedFunction);
    }

}
