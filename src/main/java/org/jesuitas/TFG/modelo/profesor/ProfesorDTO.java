package org.jesuitas.TFG.modelo.profesor;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfesorDTO {

    private String nombre;
    private String telefono;

    @Override
    public String toString() {
        return "ProfesorDTO{" +
                "nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
