package org.jesuitas.TFG.controllers;

import org.jesuitas.TFG.modelo.actividad.Actividad;
import org.jesuitas.TFG.modelo.usuario.User;
import org.jesuitas.TFG.modelo.usuario.UserDTO;
import org.jesuitas.TFG.persistencia.services.ActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class ActividadController {

    @Autowired
    private ActividadService actividadService;

    @PostMapping("/actividadRegistro")
    public ResponseEntity<Actividad> nuevaActividad(@RequestBody Actividad nuevaActividad) {
        try {
            //En caso de querer registrar un usuario cuyo nombre ya esté en el sistema
            //devolveremos un código de error 409 - Conflict.
            if(!actividadService.findById(nuevaActividad.getIdActividad()).isPresent()){
                return ResponseEntity.status(HttpStatus.CREATED).body(actividadService.nuevaActividad(nuevaActividad));
            }else {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @GetMapping("/actividades")
    public ResponseEntity<List<Actividad>> getActividades() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(actividadService.findAll());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @GetMapping("actividadbyNombre/{name}")
    public Actividad getActividadByName(@PathVariable String name){
        try{
            List<Actividad> listaActividades=actividadService.findAll();

            for(Actividad actividad: listaActividades){
                if(name.equalsIgnoreCase(actividad.getTituloActividad())){
                    return new Actividad(actividad.getIdActividad(),
                            actividad.getTituloActividad(),
                            actividad.getFechaInicio(),
                            actividad.getFechaFin(),
                            actividad.getDescripcion(),
                            actividad.getImagen(),
                            actividad.getIdEtapaEducativa());
                }
            }
            return null;
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }


    @GetMapping("/actividad/{id}")
    public Actividad getActividad(@PathVariable String id) {
        try {
            //Actividad no existe
            if(actividadService.findById(id)==null){
                return null;
            }
            Actividad actividad = actividadService.findById(id).get();
            return actividad;
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }


    @DeleteMapping("/actividad/eliminar")
    public ResponseEntity deleteActividad(@RequestBody Actividad actividad){
        return null;
    }

    @DeleteMapping("/actividad/eliminar/{id}")
    public ResponseEntity deleteActividadById(@PathVariable String id){
        try {
            //Actividad no existe
            if(actividadService.findById(id)==null){
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            actividadService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    /*@PutMapping("/updateActividad")
    public ResponseEntity updateActividad(RequestBody Actividad actividad){
        return null;
    }*/

}
