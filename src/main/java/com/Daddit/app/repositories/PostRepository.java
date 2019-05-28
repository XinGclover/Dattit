package com.Daddit.app.repositories;

import com.Daddit.app.models.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /*
    vi kanske behöver querymetoder? 
    
    - findTopPost
    - findTopFiveposts
    - findByDad x
    - findByCategory
    
     */
    
    
    List<Post> findByOrderByCreatedDesc();
    

}
