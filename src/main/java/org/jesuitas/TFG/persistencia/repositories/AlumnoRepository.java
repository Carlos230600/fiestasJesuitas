package org.jesuitas.TFG.persistencia.repositories;

import org.jesuitas.TFG.modelo.alumno.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlumnoRepository extends JpaRepository<Alumno,String> {

}
