
package com.Daddit.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    /*
    vi kanske behöver querymetoder?
    
    - findTopPost
    - findTopFiveposts
    - findByDad
    - findByCategory
    
    */
}
