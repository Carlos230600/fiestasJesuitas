package org.jesuitas.TFG.modelo.usuario;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private String username;
    private Set<UserRole> roles;

}
