package org.jesuitas.TFG.modelo.favorito;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="favorito")
@AllArgsConstructor
@NoArgsConstructor
public class Favorito {

    @Id
    @NotNull
    @Column(name="idFavorito")
    private String idFavorito;

    @NotNull
    @Column
    private String idUsuario;

    @NotNull
    @Column
    private String idActividad;

    @Override
    public String toString() {
        return "Favorito{" +
                "idFavorito='" + idFavorito + '\'' +
                ", idUsuario='" + idUsuario + '\'' +
                ", idActividad='" + idActividad + '\'' +
                '}';
    }
}
