package org.jesuitas.TFG.controllers;

import org.jesuitas.TFG.modelo.favorito.Favorito;
import org.jesuitas.TFG.modelo.usuario.User;
import org.jesuitas.TFG.persistencia.services.FavoritoService;
import org.jesuitas.TFG.persistencia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;

    @Autowired
    private UserService userService;

    @PostMapping("/favoritoRegistro")
    public ResponseEntity<Favorito> nuevoFavorito(@RequestBody Favorito nuevoFavorito) {
        try {
            //En caso de querer registrar un usuario cuyo nombre ya esté en el sistema
            //devolveremos un código de error 409 - Conflict.
            if(!favoritoService.findById(nuevoFavorito.getIdFavorito()).isPresent()){
                return ResponseEntity.status(HttpStatus.CREATED).body(favoritoService.nuevoFavorito(nuevoFavorito));
            }else {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @GetMapping("/favoritos")
    public ResponseEntity<List<Favorito>> getFavoritos() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(favoritoService.findAll());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }


    @GetMapping("/favoritobyUserId/{id}")
    public ResponseEntity<List<Favorito>> getFavoritosByUserName(@PathVariable String id) {
        try {
            //Optional<User> user = Optional.of( userService.findById(id).get());

              //No hago control de si existe usuario porque las llamadas las hare despues de haberme autenticado.
              List<Favorito> listaFavoritosUsuario=new ArrayList<Favorito>();
              List<Favorito> allFavoritos =favoritoService.findAll();

              for(Favorito favorito:allFavoritos){
                  if(favorito.getIdUsuario().equalsIgnoreCase(id)){
                      listaFavoritosUsuario.add(favorito);
                  }else{
                      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                  }
              }
              return ResponseEntity.status(HttpStatus.OK).body(listaFavoritosUsuario);

          }catch (NoSuchElementException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }


    @GetMapping("/favorito/{id}")
    public Favorito getFavorito(@PathVariable String id) {
        try {
            //Usuario no existe
            if(favoritoService.findById(id)==null){
                return null;
            }
            Favorito favorito = favoritoService.findById(id).get();
            return favorito;
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }


    @DeleteMapping("/favoritos/eliminar")
    public ResponseEntity deleteFavorito(@RequestBody Favorito favorito){
        return null;
    }

    @DeleteMapping("/favorito/eliminar/{id}")
    public ResponseEntity deleteFavoritoById(@PathVariable String id){
        try {
            //Favorito no existe
            if(favoritoService.findById(id)==null){
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            favoritoService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    /*@PutMapping("/updateFavorito")
    public ResponseEntity updateFavorito(RequestBody Favorito favorito){
        return null;
    }*/

}
