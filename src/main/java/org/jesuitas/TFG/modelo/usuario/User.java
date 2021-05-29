package org.jesuitas.TFG.modelo.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements UserDetails {

    @Id
    @Column(name = "idUser")
    @NotNull
    String id;

    @Column(unique = true)
    String username;

    @Column
    String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    Set<UserRole> roles;

    private static final long serialVersionUID = -8596969777924519849L;


    //Constructor para usuario sin login
    public User(String id, Set<UserRole> roles) {
        this.id = id;
        this.roles = roles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set result = new HashSet<>();
        for (UserRole userRole : roles) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + userRole.name());
            result.add(simpleGrantedAuthority);
        }
        return result;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
