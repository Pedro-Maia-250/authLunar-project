package com.lunarvoid.authLunar.entidades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lunarvoid.authLunar.enums.UserRules;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotNull
    private Integer rule;

    protected User(){}

    public User(String username, String password, UserRules rules){
        this.username = username;
        this.password = password;
        this.rule = rules.getCode();
    }

    public Long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRules getRule() {
        return UserRules.valueOf(this.rule);
    }

    public void setRule(UserRules rules) {
        this.rule = rules.getCode();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> rules = new ArrayList<>();
        switch (getRule()){
            case UserRules.ALTO:
                rules.add(new SimpleGrantedAuthority("ROLE_" + UserRules.ALTO.name()));
            case UserRules.MEDIO:
                rules.add(new SimpleGrantedAuthority("ROLE_" + UserRules.MEDIO.name()));
            case UserRules.BAIXO:
                rules.add(new SimpleGrantedAuthority("ROLE_" + UserRules.BAIXO.name()));
            break;
        }
        return rules;
    }

    @Override
    public @Nullable String getPassword() {
       return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
}
