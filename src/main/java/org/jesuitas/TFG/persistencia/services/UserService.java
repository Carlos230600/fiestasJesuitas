package org.jesuitas.TFG.persistencia.services;

import org.jesuitas.TFG.modelo.usuario.User;
import org.jesuitas.TFG.modelo.usuario.UserRole;
import org.jesuitas.TFG.persistencia.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
public class UserService extends BaseService<User, String, UserRepository>{

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public Optional<User> findByUserName(String username) {
        return this.repositorio.findByUsername(username);
    }

    // Metodo que nos permitirá registrar un nuevo user en el sistema
    public User nuevoUsuario(User user) {
        Set<UserRole> defaultRoles = new HashSet<UserRole>();
        user.setId(String.valueOf(Math.abs(new Random().nextInt())));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // En caso de no haber asignado un rol en la petición, añadimos uno por defecto
        if (user.getRoles() == null) {
            defaultRoles.add(UserRole.USUARIO);
            user.setRoles(defaultRoles);
        }
        else if (user.getRoles().size() == 0) {
            defaultRoles.add(UserRole.USUARIO);
            user.setRoles(defaultRoles);
        }
        return this.repositorio.save(user);
    }

}
