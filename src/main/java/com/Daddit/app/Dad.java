package com.Daddit.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Dad {
    
    @Id
    @GeneratedValue
    private long id;
    private String username;
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    private boolean moderator;

    public Dad() {
    }
    
    public Dad(String username, String password, boolean moderator) {
        this.username = username;
        this.password = password;
        this.moderator = moderator;
    }
    
    public Dad(String username, boolean moderator) {
        this.username = username;
        this.moderator = moderator;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isModerator() {
        return moderator;
    }

    public void setModerator(boolean moderator) {
        this.moderator = moderator;
    }

    
    
    
}
