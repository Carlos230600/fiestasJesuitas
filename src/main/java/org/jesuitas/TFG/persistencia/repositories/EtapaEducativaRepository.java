package org.jesuitas.TFG.persistencia.repositories;

import org.jesuitas.TFG.modelo.etapaEducativa.EtapaEducativa;
import org.jesuitas.TFG.modelo.favorito.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface EtapaEducativaRepository extends JpaRepository<EtapaEducativa, String> {
}
