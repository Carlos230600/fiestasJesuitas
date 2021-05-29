package org.jesuitas.TFG.persistencia.services;

import org.jesuitas.TFG.modelo.actividad.Actividad;
import org.jesuitas.TFG.persistencia.repositories.ActividadRepository;
import org.springframework.stereotype.Service;

@Service
public class ActividadService extends BaseService<Actividad, String, ActividadRepository>{

    public Actividad nuevaActividad(Actividad actividad) {
        return this.repositorio.save(actividad);
    }

}
