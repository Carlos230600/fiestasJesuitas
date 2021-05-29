package org.jesuitas.TFG.controllers;

import org.jesuitas.TFG.modelo.administrador.Administrador;
import org.jesuitas.TFG.persistencia.services.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @PostMapping("/administradorRegistro")
    public ResponseEntity<Administrador> nuevoAdministrador(@RequestBody Administrador nuevoAdministrador) {
        try {
            //En caso de querer registrar un usuario cuyo nombre ya esté en el sistema
            //devolveremos un código de error 409 - Conflict.
            if(!administradorService.findById(nuevoAdministrador.getIdAdministrador()).isPresent()){
                return ResponseEntity.status(HttpStatus.CREATED).body(administradorService.nuevoAdministrador(nuevoAdministrador));
            }else {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @GetMapping("/administradores")
    public ResponseEntity<List<Administrador>> getAdministradores() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(administradorService.findAll());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @GetMapping("/administrador/{id}")
    public Administrador getAdministrador(@PathVariable String id) {
        try {
            //Administrador no existe
            if(administradorService.findById(id)==null){
                return null;
            }
            Administrador administrador = administradorService.findById(id).get();
            return administrador;
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }


    @DeleteMapping("/administrador/eliminar")
    public ResponseEntity deleteAdministrador(@RequestBody Administrador administrador){
        return null;
    }

    @DeleteMapping("/administrador/eliminar/{id}")
    public ResponseEntity deleteAdministradorById(@PathVariable String id){
        try {
            //Usuario no existe
            if(administradorService.findById(id)==null){
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            administradorService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    /*@PutMapping("/updateAdministrador")
    public ResponseEntity updateAdministrador(RequestBody Administrador administrador){
        return null;
    }*/

}
