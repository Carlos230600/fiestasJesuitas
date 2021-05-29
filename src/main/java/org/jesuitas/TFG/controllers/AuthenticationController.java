package org.jesuitas.TFG.controllers;

import lombok.RequiredArgsConstructor;
import org.jesuitas.TFG.configurations.security.jwt.JwtTokenProvider;
import org.jesuitas.TFG.modelo.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private final AuthenticationManager authenticationManager;
    // Para poder generar el token

    @Autowired
    private final JwtTokenProvider tokenProvider;


    // Converter si utilizamos DTO en nuestro caso no hará falta
    @Autowired
    private final UserDTOConverter converter;


    @PostMapping("/auth/login")
    // se puede devolver un ResponseEntity con el JwtUserResponse
    public JwtUserResponse login(@RequestBody LoginRequest loginRequest) {

        //Comprobar que existe ese usuario con login

        // crearemos un authentication con los argumentos recibidos de la petición
        // si está autenticado nos creará el authentication.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        // guardamos en el contexto de seguridad si se ha logueado
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // crearemos la respuesta con su token correspondiente.
        User user = (User) authentication.getPrincipal();
        String jwtToken = tokenProvider.generateToken(authentication);

        // devolveremos el jwtUserResponse creado utilizando la siguiente función por
        // ejemplo.
        return convertUserEntityAndTokenToJwtUserResponse(user, jwtToken);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/me")
    public UserDTO me(@AuthenticationPrincipal User user) {
        // devolveremos el user autenticado.
        return converter.convertirUsuarioaDTO(user);
    }

    private JwtUserResponse convertUserEntityAndTokenToJwtUserResponse(User user, String jwtToken) {
        return JwtUserResponse.jwtUserResponseBuilder()
                .id(user.getId())
                .username(user.getUsername())
                .roles(user.getRoles())
                .token(jwtToken).build();
    }

}
