package org.jesuitas.TFG.persistencia.repositories;

import org.jesuitas.TFG.modelo.actividad.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad,String> {
}
