package com.Daddit.app.controllers;

import com.Daddit.app.models.Dad;
import com.Daddit.app.services.DadService;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

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

//    @GetMapping("/{name}/{password}")
//    public Dad getDad(@PathVariable String name, @PathVariable String password) {
//        return dadService.findDadByUsernameandPassword(name, password);
//    }

//    @PostMapping("addDad")
//    public ResponseEntity<HttpStatus> addStudent(@RequestBody Dad dad) {
//        Dad result = dadService.addDad(dad);
//        
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();
//        
//        return ResponseEntity.created(location).build();
//    }@RequestBody Map<String, String> body
    @PostMapping("/addDad")
    public Dad newDad(@RequestBody Map<String, String> body) {
        
        String username = body.get("username");
        String password = body.get("password");
        System.out.println(username + password);
        Dad newDad = new Dad(username, password);
        return dadService.addDad(newDad);
    }

//   @PostMapping("/login")
//    public ResponseEntity<Dad> getLoginResult(@RequestBody Map<String, String> login) {
//        System.out.println(login.get("username"));
//        Dad logindad = dadService.logInDad(login.get("username"), login.get("password"));
//        if (logindad != null) {
//            System.out.println(logindad);
//            return ResponseEntity.ok(logindad);
//        }
//        else{
//            return ResponseEntity.badRequest().body(logindad);
//        }
//    }
   @PostMapping("/login")
    public Dad getLoginResult(@RequestBody Map<String, String> login) {
        System.out.println(login.get("login_username"));
        Dad logindad = dadService.logInDad(login.get("login_username"), login.get("login_password"));
        if (logindad != null) {
            System.out.println(logindad);
            return logindad;
        }
        else{
            return null;
        }
    }
}
