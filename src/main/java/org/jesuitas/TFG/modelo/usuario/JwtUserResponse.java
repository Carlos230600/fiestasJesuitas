package org.jesuitas.TFG.modelo.usuario;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JwtUserResponse extends User implements Serializable {

    private String token;

    //indicaremos otro nombre al builder
    @Builder(toBuilder = true,builderMethodName = "jwtUserResponseBuilder")
    public JwtUserResponse(String id,String username, Set<UserRole> roles, String token) {
        this.setId(id);
        this.setUsername(username);
        this.setRoles(roles);
        this.token = token;
    }


}
