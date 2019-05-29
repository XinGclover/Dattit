package com.Daddit.app.services;

import com.Daddit.app.models.Vote;
import com.Daddit.app.repositories.VoteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("voteService")
public class VoteService {

    private VoteRepository voteRepo;

    @Autowired
    public VoteService(VoteRepository voteRepo) {
        this.voteRepo = voteRepo;
    }
    
    
    public List<Vote> findAllVotes() {
        return voteRepo.findAll();
    }
}
