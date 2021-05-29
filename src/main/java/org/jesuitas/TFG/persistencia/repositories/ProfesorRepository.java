package org.jesuitas.TFG.persistencia.repositories;

import org.jesuitas.TFG.modelo.profesor.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor,String> {
}
