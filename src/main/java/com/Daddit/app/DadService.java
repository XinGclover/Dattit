package com.Daddit.app;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dadService")
public class DadService {

    @Autowired
    private DadRepository dadRepo;
    
    private Dad loginDad;

    Optional<Dad> findDadById(long Id) {
        return dadRepo.findById(Id);
    }

    Optional<Dad> findDadByUsernameandPassword(String username, String password) {
        return dadRepo.findByUsernameAndPassword(username, password);
    }

    List<Dad> findAllDads() {
        return dadRepo.findAll();
    }

    public Dad addDad(Dad dad) {
        //check if dad exist
        if (!dadRepo.findByUsername(dad.getUsername()).isPresent()) {
            //add a dad
            dadRepo.save(dad);

            //return true or false
            return dad;
        } else {
            return null;
        }
    }

    public String logInDad(String username, String password) {
        Optional<Dad> dOptional = dadRepo.findByUsername(username);
        if (dOptional.isPresent() && dOptional.filter(dad -> dad.getPassword().equals(password)).isPresent()) {
            loginDad=dOptional.get();
            if (dOptional.get().isModerator() == false) {               
                return "user";
            } else {
                return "admin";
            }
        }
        return "Log in fail.";

    }

}
