package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Trabajador", schema = "luachea", catalog = "")
public class Trabajador {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_trabajador", nullable = false)
    private int idTrabajador;
    @Basic
    @Column(name = "nie_trabajador", nullable = true, length = 9)
    private String nieTrabajador;
    @Basic
    @Column(name = "pasaporte_trabajador", nullable = true, length = 12)
    private String pasaporteTrabajador;
    @Basic
    @Column(name = "nombre", nullable = false, length = 40)
    private String nombre;
    @Basic
    @Column(name = "apellido1", nullable = false, length = 40)
    private String apellido1;
    @Basic
    @Column(name = "apellido2", nullable = true, length = 40)
    private String apellido2;
    @Basic
    @Column(name = "correo", nullable = false, length = 40)
    private String correo;
    @Basic
    @Column(name = "fecha", nullable = false)
    private Timestamp fecha;
    @Basic
    @Column(name = "num_telef", nullable = false, length = 9)
    private String numTelef;
    @Basic
    @Column(name = "id_rol", nullable = false)
    private int idRol;
    @Basic
    @Column(name = "estado_trabajador", nullable = false)
    private Object estadoTrabajador;
    @Basic
    @Column(name = "trabajando", nullable = false)
    private Object trabajando;
    @Basic
    @Column(name = "contraseña", nullable = false, length = 255)
    private String contraseña;

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getNieTrabajador() {
        return nieTrabajador;
    }

    public void setNieTrabajador(String nieTrabajador) {
        this.nieTrabajador = nieTrabajador;
    }

    public String getPasaporteTrabajador() {
        return pasaporteTrabajador;
    }

    public void setPasaporteTrabajador(String pasaporteTrabajador) {
        this.pasaporteTrabajador = pasaporteTrabajador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getNumTelef() {
        return numTelef;
    }

    public void setNumTelef(String numTelef) {
        this.numTelef = numTelef;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public Object getEstadoTrabajador() {
        return estadoTrabajador;
    }

    public void setEstadoTrabajador(Object estadoTrabajador) {
        this.estadoTrabajador = estadoTrabajador;
    }

    public Object getTrabajando() {
        return trabajando;
    }

    public void setTrabajando(Object trabajando) {
        this.trabajando = trabajando;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trabajador that = (Trabajador) o;
        return idTrabajador == that.idTrabajador && idRol == that.idRol && Objects.equals(nieTrabajador, that.nieTrabajador) && Objects.equals(pasaporteTrabajador, that.pasaporteTrabajador) && Objects.equals(nombre, that.nombre) && Objects.equals(apellido1, that.apellido1) && Objects.equals(apellido2, that.apellido2) && Objects.equals(correo, that.correo) && Objects.equals(fecha, that.fecha) && Objects.equals(numTelef, that.numTelef) && Objects.equals(estadoTrabajador, that.estadoTrabajador) && Objects.equals(trabajando, that.trabajando) && Objects.equals(contraseña, that.contraseña);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTrabajador, nieTrabajador, pasaporteTrabajador, nombre, apellido1, apellido2, correo, fecha, numTelef, idRol, estadoTrabajador, trabajando, contraseña);
    }
}