package com.Daddit.app.services;

import com.Daddit.app.models.Dad;
import com.Daddit.app.repositories.DadRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dadService")
public class DadService {

    @Autowired
    private DadRepository dadRepo;

   public Optional<Dad> findDadById(long Id) {
        return dadRepo.findById(Id);
    }

    public Optional<Dad> findDadByUsernameandPassword(String username, String password) {
        return dadRepo.findByUsernameAndPassword(username, password);
    }
    
    public List<Dad> findAllDads() {
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
