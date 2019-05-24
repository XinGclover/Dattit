package com.Daddit.app.services;

import com.Daddit.app.models.Vote;
import com.Daddit.app.repositories.VoteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("voteService")
public class VoteService {

    @Autowired
    private VoteRepository voteRepo;
    
    public List<Vote> findAllVotes() {
        return voteRepo.findAll();
    }
}
