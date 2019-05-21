/*
 * Javautveckling 2018
 */
package com.Daddit.app;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Marcus D Jensen
 */
public interface VoteRepository extends JpaRepository<Vote, Long>{
    
}
