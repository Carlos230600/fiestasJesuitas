package org.jesuitas.TFG.persistencia.services;

import org.jesuitas.TFG.modelo.administrador.Administrador;
import org.jesuitas.TFG.modelo.alumno.Alumno;
import org.jesuitas.TFG.persistencia.repositories.AdministradorRepository;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService extends BaseService<Administrador, String, AdministradorRepository>{


    public Administrador nuevoAdministrador(Administrador administrador) {
        return this.repositorio.save(administrador);
    }

}
