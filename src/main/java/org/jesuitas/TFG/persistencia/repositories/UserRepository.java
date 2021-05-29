package org.jesuitas.TFG.persistencia.repositories;

import org.jesuitas.TFG.modelo.usuario.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByUsername(String username);

    /*@Query(value = "select u from usuario u where u.nombreusuario = :nombreusuario"	, nativeQuery = true)
    public User findByName(@Param("nombreLogin") String nombreusuario);

    @Query(value = "select u from usuario u where u.id = :idUsuario"	, nativeQuery = true)
    public User findByID(@Param("id") String id);*/

}
