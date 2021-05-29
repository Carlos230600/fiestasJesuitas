package org.jesuitas.TFG.persistencia.repositories;

import org.jesuitas.TFG.modelo.favorito.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, String>{

}
