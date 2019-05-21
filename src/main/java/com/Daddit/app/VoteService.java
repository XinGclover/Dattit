package com.Daddit.app;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("voteService")
public class VoteService {

    @Autowired
    private VoteRepository voteRepo;
    
    List<Vote> findAllVotes() {
        return voteRepo.findAll();
    }
}
