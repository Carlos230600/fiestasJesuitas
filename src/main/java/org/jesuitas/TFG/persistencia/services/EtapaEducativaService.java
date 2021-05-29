package org.jesuitas.TFG.persistencia.services;

import org.jesuitas.TFG.modelo.administrador.Administrador;
import org.jesuitas.TFG.modelo.etapaEducativa.EtapaEducativa;
import org.jesuitas.TFG.persistencia.repositories.EtapaEducativaRepository;
import org.springframework.stereotype.Service;

@Service
public class EtapaEducativaService extends BaseService<EtapaEducativa, String, EtapaEducativaRepository>{

    public EtapaEducativa nuevaEtapaEducativa(EtapaEducativa etapaEducativa) {
        return this.repositorio.save(etapaEducativa);
    }

}
