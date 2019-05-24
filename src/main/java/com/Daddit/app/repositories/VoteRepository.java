
package com.Daddit.app.repositories;

import com.Daddit.app.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VoteRepository extends JpaRepository<Vote, Long>{
    
}
