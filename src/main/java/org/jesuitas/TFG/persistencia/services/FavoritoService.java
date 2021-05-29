package org.jesuitas.TFG.persistencia.services;

import org.jesuitas.TFG.modelo.alumno.Alumno;
import org.jesuitas.TFG.modelo.favorito.Favorito;
import org.jesuitas.TFG.persistencia.repositories.FavoritoRepository;
import org.springframework.stereotype.Service;

@Service
public class FavoritoService extends BaseService<Favorito, String, FavoritoRepository>{

    public Favorito nuevoFavorito(Favorito favorito) {
        return this.repositorio.save(favorito);
    }

}
