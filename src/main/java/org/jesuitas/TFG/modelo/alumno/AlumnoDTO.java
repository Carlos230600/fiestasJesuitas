package org.jesuitas.TFG.modelo.alumno;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
public class AlumnoDTO {
//DTO sin dni

    private String identificador;
    private String nombre;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaNacimiento;
    private String telefono;
    private String direccion;

    public AlumnoDTO() {}

    public AlumnoDTO(String identificador, String nombre, Date fechaNacimiento, String telefono, String direccion) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.direccion = direccion;
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
        return "AlumnoDTO{" +
                "identificador='" + identificador + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
