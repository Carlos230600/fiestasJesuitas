package org.jesuitas.TFG.modelo.profesor;

import org.jesuitas.TFG.modelo.alumno.Alumno;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="profesor")
public class Profesor {

    @Id
    @Column(name = "idProfesor")
    private String identificador;
    @Column
    private String nombre;
    @Column
    private String telefono;


    public Profesor() {}

    public Profesor(String identificador,String nombre,String telefono) {
        this.identificador=identificador;
        this.nombre=nombre;
        this.telefono=telefono;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "identificador='" + identificador + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
