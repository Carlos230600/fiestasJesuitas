package org.jesuitas.TFG.modelo.administrador;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "administrador")
public class Administrador {

    @Id
    @NotNull
    @Column(name="idAdministrador")
    private String idAdministrador;

    @NotNull
    @Column(name="nombreAdministrador")
    private String nombre;


}
