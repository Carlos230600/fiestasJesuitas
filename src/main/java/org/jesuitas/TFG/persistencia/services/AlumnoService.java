package org.jesuitas.TFG.persistencia.services;

import org.jesuitas.TFG.modelo.alumno.Alumno;
import org.jesuitas.TFG.persistencia.repositories.AlumnoRepository;
import org.springframework.stereotype.Service;


@Service
public class AlumnoService extends BaseService<Alumno, String, AlumnoRepository>{

    public Alumno nuevoAlumno(Alumno alumno) {
        return this.repositorio.save(alumno);
    }

}
