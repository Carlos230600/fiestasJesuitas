package org.jesuitas.TFG.controllers;

import org.jesuitas.TFG.modelo.alumno.Alumno;
import org.jesuitas.TFG.modelo.alumno.AlumnoDTO;
import org.jesuitas.TFG.modelo.alumno.AlumnoDTOConverter;
import org.jesuitas.TFG.persistencia.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @Autowired(required = false)
    private AlumnoDTOConverter alumnoDTOConverter;

    @PostMapping("/alumnoRegistro")
    public ResponseEntity<Alumno> nuevoAlumno(@RequestBody Alumno nuevoAlumno) {
        try {
            //En caso de querer registrar un usuario cuyo nombre ya esté en el sistema
            //devolveremos un código de error 409 - Conflict.
            if(!alumnoService.findById(nuevoAlumno.getIdentificador()).isPresent()){
                return ResponseEntity.status(HttpStatus.CREATED).body(alumnoService.nuevoAlumno(nuevoAlumno));
            }else {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @GetMapping("/alumnos")
    public ResponseEntity<List<Alumno>> getAlumnos() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(alumnoService.findAll());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @GetMapping("/alumno/{id}")
    public AlumnoDTO getAlumno(@PathVariable String id) {
        try {
            //Alumno no existe
            if(alumnoService.findById(id)==null){
                return null;
            }
            Alumno alumno = alumnoService.findById(id).get();
            return alumnoDTOConverter.transformarAAlumnoDTO(alumno);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }


    @DeleteMapping("/alumno/eliminar")
    public ResponseEntity deleteAlumno(@RequestBody Alumno alumno){
        return null;
    }

    @DeleteMapping("/alumno/eliminar/{id}")
    public ResponseEntity deleteAlumnoById(@PathVariable String id){
        try {
            //Usuario no existe
            if(alumnoService.findById(id)==null){
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            alumnoService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    /*@PutMapping("/updateAlumno")
    public ResponseEntity updateAlumno(RequestBody Alumno alumno){
        return null;
    }*/

}
