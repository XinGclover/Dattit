
package com.Daddit.app;

import org.springframework.data.jpa.repository.JpaRepository;


public interface VoteRepository extends JpaRepository<Vote, Long>{
    
}
