package org.jesuitas.TFG.controllers;

import org.jesuitas.TFG.modelo.profesor.Profesor;
import org.jesuitas.TFG.modelo.profesor.ProfesorDTO;
import org.jesuitas.TFG.modelo.profesor.ProfesorDTOConverter;
import org.jesuitas.TFG.persistencia.services.ProfesorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private ProfesorDTOConverter profesorDTOConverter;

    @PostMapping("/profesorRegistro")
    public ResponseEntity<Profesor> nuevoUsuario(@RequestBody Profesor nuevoProfesor) {
        try {
            //En caso de querer registrar un usuario cuyo nombre ya esté en el sistema
            //devolveremos un código de error 409 - Conflict.
            if(!profesorService.findById(nuevoProfesor.getIdentificador()).isPresent()){
                return ResponseEntity.status(HttpStatus.CREATED).body(profesorService.nuevoProfesor(nuevoProfesor));
            }else {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @GetMapping("/profesores")
    public ResponseEntity<List<Profesor>> getProfesores() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(profesorService.findAll());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @GetMapping("/profesor/{id}")
    public ProfesorDTO getProfesor(@PathVariable String id) {
        try {
            //Profesor no existe
            if(profesorService.findById(id)==null){
                return null;
            }
            Profesor profesor = profesorService.findById(id).get();
            ProfesorDTO profesorDTO = profesorDTOConverter.transformarAProfesorDTO(profesor);
            return profesorDTO;
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @DeleteMapping("/profesor/eliminar")
    public ResponseEntity deleteProfesor(@RequestBody Profesor profesor){
        return null;
    }

    @DeleteMapping("/profesor/eliminar/{id}")
    public ResponseEntity deleteProfesorById(@PathVariable String id){
        try {
            //Usuario no existe
            if(profesorService.findById(id)==null){
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            profesorService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    /*@PutMapping("/updateProfesor")
    public ResponseEntity updateProfesor(RequestBody Profesor profesor){
        return null;
    }*/

}
