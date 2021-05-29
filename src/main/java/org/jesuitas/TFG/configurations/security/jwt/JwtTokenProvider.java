package org.jesuitas.TFG.configurations.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.jesuitas.TFG.modelo.usuario.User;
import org.jesuitas.TFG.modelo.usuario.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    // Cabecera utilizada para mandar el token
    public static final String TOKEN_HEADER = "Authorization";
    // prefijo del token en la cabecera
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    // Podemos inyectar el secreto aqui desde la clave "jwt.secret" del fichero de
    // propiedades.

    @Value("${jwt.secret:EnUnLugarDeLaManchaDeCuyoNombreNoQuieroAcordarmeNoHaMuchoTiempoQueViviaUnHidalgo}")
    private String jwtSecreto;
    // aqui lo mismo, podemos darle valor a través de la propiedad
    // "jwt.token-expiration"
    @Value("${jwt.token-expiration:86400}") // milisegundos
    private int jwtDuracionTokenEnSegundos;

    //Metodo de generación del token, que recibe un usuario autenticado
    // (Authentication)
    public String generateToken(Authentication authentication) {
        // se obtiene el UserEntity, que es el objeto que tiene los valores del usuario
        // autenticado.
        User user = (User) authentication.getPrincipal();
        // Generaremos la fecha de expiración. Para ello utilizaremos java.util.Date con
        // el valor (tiempo actual + expiración*1000)
        Date tokenExpirationDate = new Date(System.currentTimeMillis() + (jwtDuracionTokenEnSegundos * 1000));
        // Finalmente construiremos el token.
        return Jwts.builder()
                // Firmamos utilizando el secreto (en bytes) y el algoritmo (HS512)
                .signWith(Keys.hmacShaKeyFor(jwtSecreto.getBytes()), SignatureAlgorithm.HS512)
                // Indicamos el parametro typ con el tipo de token que hemos creado arriba
                .setHeaderParam("typ", TOKEN_TYPE)
                // Como subject pasaremos el id de usuario
                .setSubject(user.getId())
                // La fecha de creación es la actual
                .setIssuedAt(new Date())
                // Fecha de expriación creada anteriormente
                .setExpiration(tokenExpirationDate)
                // Podemos añadir la información que deseemos, por ejemplo el nombre completo
                // del usuario y los roles (los roles irán como una lista separada por comas)
                .claim("username", user.getUsername())
                .claim("roles", user.getRoles().stream().map(UserRole::name).collect(Collectors.joining(", ")))
                .compact();
    }

    // A partir de un token, obtendremos el id del usuario.
    public String getUserIdFromJWT(String token) {
        // objtengo los claims del token primero parseando
        Claims claims = Jwts.parser()
                // luego establezco la clave con el mismo secreto que antes
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecreto.getBytes()))
                // finalmente parseo los claims
                .parseClaimsJws(token).getBody();
        // de los claims devolveremos el id que está en getSubject
        return claims.getSubject();
    }

    // método que comprueba que el token es correcto. A través de excepciones.
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecreto.getBytes()).parseClaimsJws(authToken);
            return true;
        } catch (Exception ex) { // error en la firma
            ex.printStackTrace();
        }
        // catch (MalformedJwtException ex) {// token mal formado
        // } catch (ExpiredJwtException ex) { // el token ha expirado
        // } catch (UnsupportedJwtException ex) { // no está soportado JWT
        // } catch (IllegalArgumentException ex) { // hay claims erroneos
        // }
        return false;
    }

}
