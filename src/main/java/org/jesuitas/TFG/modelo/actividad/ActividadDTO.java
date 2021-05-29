package org.jesuitas.TFG.modelo.actividad;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActividadDTO {

    private String tituloActividad;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaInicio;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaFin;

    private String descripcion;

    private String imagen;

    private String idEtapaEducativa;

    @Override
    public String toString() {
        return "ActividadDTO{" +
                "tituloActividad='" + tituloActividad + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", idEtapaEducativa='" + idEtapaEducativa + '\'' +
                '}';
    }
}
