package org.jesuitas.TFG.persistencia.repositories;

import org.jesuitas.TFG.modelo.administrador.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador,String> {
}
