package org.jesuitas.TFG.modelo.alumno;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jesuitas.TFG.modelo.profesor.Profesor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="alumno")
public class Alumno {

    @Id
    @NotNull
    @Column(name="idAlumno")
    private String identificador;

    @Column
    private String dni;

    @Column
    private String nombre;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaNacimiento;

    @Column
    private String telefono;

    @Column
    private String direccion;


    //Añadir Favoritos mediante array

    public Alumno() {}

    public Alumno(String identificador, String dni, String nombre, Date fechaNacimiento,String telefono,String direccion) {
        this.identificador=identificador;
        this.dni=dni;
        this.nombre=nombre;
        this.fechaNacimiento=fechaNacimiento;
        this.telefono=telefono;
        this.direccion=direccion;
    }


    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "identificador='" + identificador + '\'' +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
