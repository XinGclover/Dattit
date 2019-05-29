package com.Daddit.app.controllers;

import com.Daddit.app.models.Vote;
import com.Daddit.app.services.VoteService;
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
    
    private VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }
    
    @GetMapping("/getAll")
    public List<Vote> getAllPosts() {
        return voteService.findAllVotes();
    }

}
