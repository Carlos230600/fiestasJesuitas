package org.jesuitas.TFG.persistencia.services;

import org.jesuitas.TFG.modelo.usuario.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.findByUserName(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException(username + " not found");
        }
    }


    public UserDetails loadUserById(String idUser) throws UsernameNotFoundException {
        Optional<User> user = userService.findById(idUser);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException(idUser + " not found");
        }
    }
}
