package com.Daddit.app;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dadService")
public class DadService {

    @Autowired
    private DadRepository dadRepo;

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
        }
        else {
            return null;
        }
    }

}
