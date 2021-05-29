package org.jesuitas.TFG.persistencia.services;

import org.jesuitas.TFG.modelo.profesor.Profesor;
import org.jesuitas.TFG.persistencia.repositories.ProfesorRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfesorService extends BaseService<Profesor, String, ProfesorRepository>{

    public Profesor nuevoProfesor(Profesor profesor) {
        return this.repositorio.save(profesor);
    }

}
