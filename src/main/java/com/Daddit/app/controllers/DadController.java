package com.Daddit.app.controllers;

import com.Daddit.app.models.Dad;
import com.Daddit.app.services.DadService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/dad")
public class DadController {
    
    @Autowired
    private DadService dadService;
    
    @GetMapping("/getAll")
    public List<Dad> getAllDads() {
        return dadService.findAllDads();
    }
    
    @GetMapping("/{name}/{password}")
    public Dad getDad(@PathVariable String name, @PathVariable String password) {
        return dadService.findDadByUsernameandPassword(name, password);
    }
    
//    @PostMapping("addDad")
//    public ResponseEntity<HttpStatus> addStudent(@RequestBody Dad dad) {
//        Dad result = dadService.addDad(dad);
//        
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();
//        
//        return ResponseEntity.created(location).build();
//    }
    
    @PostMapping("/addDad")
    public Dad newDad(@RequestParam Map<String, String> body) {
        Dad newDad = new Dad(body.get("username"), body.get("password"));
        return dadService.addDad(newDad);
    }
    
}
