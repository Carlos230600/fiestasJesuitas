package org.jesuitas.TFG.controllers;

import org.jesuitas.TFG.modelo.usuario.*;
import org.jesuitas.TFG.persistencia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UserService userService;

    @Autowired(required = false)
    private UserDTOConverter converter;

    //Registrará un nuevo usuario
    @PostMapping("/usuarioRegistro")
    public ResponseEntity<User> nuevoUsuario(@RequestBody User nuevoUser) {
        try {
            //En caso de querer registrar un usuario cuyo nombre ya esté en el sistema
            //devolveremos un código de error 409 - Conflict.
            if(!userService.findByUserName(nuevoUser.getUsername()).isPresent()){
                return ResponseEntity.status(HttpStatus.CREATED).body(userService.nuevoUsuario(nuevoUser));
            }else {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }


    //Mostrará todos los usuarios registrados en el sistema
    @GetMapping("/usuarios")
    public ResponseEntity<List<User>> getUsuarios() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }


    @GetMapping("/usuario/{id}")
    public UserDTO getUsuario(@PathVariable String id) {
        try {
            //Usuario no existe
            if(userService.findById(id)==null){
               return null;
            }
            User usuario = userService.findById(id).get();
            return converter.convertirUsuarioaDTO(usuario);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }


    @DeleteMapping("/usuario/eliminar")
    public ResponseEntity deleteUsuario(@RequestBody User user){
        return null;
    }

    @DeleteMapping("/usuario/eliminar/{id}")
    public ResponseEntity deleteUsuarioById(@PathVariable String id){
        try {
            //Usuario no existe
            if(userService.findById(id)==null){
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            userService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @PutMapping("/updateUsuario/{id}")
    public ResponseEntity updateUsuario(@RequestBody User user, @PathVariable String id){
        try{
            //Usuario no encontrado
            if(userService.findById(id)==null){
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }

            User usuarioantiguo = userService.findById(id).get();
            usuarioantiguo.setUsername(user.getUsername());
            usuarioantiguo.setPassword(user.getPassword());
            usuarioantiguo.setRoles(user.getRoles());
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
}
