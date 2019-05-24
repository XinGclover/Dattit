
package com.Daddit.app.repositories;

import com.Daddit.app.models.Dad;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadRepository extends JpaRepository<Dad, Long>{
    
    public Optional<Dad> findByUsername(String username);
    
    public Dad findByUsernameAndPassword(String username, String password);
    
    
}
