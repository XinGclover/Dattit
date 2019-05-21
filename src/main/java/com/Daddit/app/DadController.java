package com.Daddit.app;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Optional<Dad> getDad(@PathVariable String name, @PathVariable String password) {
        return dadService.findDadByUsernameandPassword(name, password);
    }
    
    
}
