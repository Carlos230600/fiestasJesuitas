package org.jesuitas.TFG.controllers;

import org.jesuitas.TFG.modelo.actividad.Actividad;
import org.jesuitas.TFG.modelo.etapaEducativa.EtapaEducativa;
import org.jesuitas.TFG.persistencia.services.EtapaEducativaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class EtapaEducativaController {

    @Autowired
    EtapaEducativaService etapaEducativaService;

    @GetMapping("etapaEducativa/{id}")
    public EtapaEducativa getEtapaEducativaById(@PathVariable String id){
        try {
            //EtapaEducativa no existe
            if(etapaEducativaService.findById(id)==null){
                return null;
            }
            EtapaEducativa etapaEducativa = etapaEducativaService.findById(id).get();
            return etapaEducativa;
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

}
