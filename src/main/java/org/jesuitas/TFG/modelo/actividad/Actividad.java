package org.jesuitas.TFG.modelo.actividad;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="actividad")
public class Actividad {

    @Id
    @Column(name="idActividad")
    @NotNull
    private String idActividad;

    @Column
    @NotNull
    private String tituloActividad;

    @Column
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaInicio;

    @Column
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaFin;

    @Column
    @NotNull
    private String descripcion;

    @Column
    @NotNull
    private String imagen;

    @Column
    @NotNull
    private String idEtapaEducativa;

    @Override
    public String toString() {
        return "Actividad{" +
                "idActividad='" + idActividad + '\'' +
                ", tituloActividad='" + tituloActividad + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", idEtapaEducativa='" + idEtapaEducativa + '\'' +
                '}';
    }
}
