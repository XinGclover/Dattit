package com.Daddit.app.controllers;

import com.Daddit.app.services.VoteService;
import com.Daddit.app.models.Vote;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/vote")
public class VoteController {
    
    @Autowired
    private VoteService voteService;
    
    @GetMapping("/getAll")
    public List<Vote> getAllPosts() {
        return voteService.findAllVotes();
    }

}
