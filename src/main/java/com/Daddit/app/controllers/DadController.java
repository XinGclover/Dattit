package com.Daddit.app.controllers;

import com.Daddit.app.services.DadService;
import com.Daddit.app.models.Dad;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public Optional<Dad> getDad(@PathVariable String name, @PathVariable String password) {
        return dadService.findDadByUsernameandPassword(name, password);
    }

    @PostMapping("addDad")
    public ResponseEntity<HttpStatus> addStudent(@RequestBody Dad dad) {
        Dad result = dadService.addDad(dad);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/newDad")
    public Dad newDad(@RequestParam Map<String, String> body) {
        Dad newDad = new Dad(body.get("username"), body.get("password"));
        return dadService.addDad(newDad);
    }

    @PostMapping("/login")
    public ResponseEntity<Dad> getLoginResult(@RequestBody Dad login) {
        Dad logindad = dadService.logInDad(login.getUsername(), login.getPassword());
        if (logindad != null) {
            return ResponseEntity.ok(logindad);
        }
        else{
            return ResponseEntity.badRequest().body(logindad);
        }
    }
}
