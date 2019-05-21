
package com.Daddit.app;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadRepository extends JpaRepository<Dad, Long>{
    
    public Optional<Dad> findByUsernameAndPassword(String username, String password);
}
