package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "datos_usuario", schema = "luachea", catalog = "")
public class DatosUsuario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_datos_usuario", nullable = false)
    private int idDatosUsuario;
    @Basic
    @Column(name = "id_usuario", nullable = true)
    private Integer idUsuario;
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
    @Column(name = "fecha", nullable = false)
    private Timestamp fecha;
    @Basic
    @Column(name = "num_telef", nullable = false, length = 9)
    private String numTelef;
    @Basic
    @Column(name = "NIF", nullable = true, length = 9)
    private String nif;
    @Basic
    @Column(name = "direccion", nullable = true, length = 1000)
    private String direccion;
    @Basic
    @Column(name = "cp", nullable = true, length = 5)
    private String cp;

    public int getIdDatosUsuario() {
        return idDatosUsuario;
    }

    public void setIdDatosUsuario(int idDatosUsuario) {
        this.idDatosUsuario = idDatosUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatosUsuario that = (DatosUsuario) o;
        return idDatosUsuario == that.idDatosUsuario && Objects.equals(idUsuario, that.idUsuario) && Objects.equals(nombre, that.nombre) && Objects.equals(apellido1, that.apellido1) && Objects.equals(apellido2, that.apellido2) && Objects.equals(fecha, that.fecha) && Objects.equals(numTelef, that.numTelef) && Objects.equals(nif, that.nif) && Objects.equals(direccion, that.direccion) && Objects.equals(cp, that.cp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDatosUsuario, idUsuario, nombre, apellido1, apellido2, fecha, numTelef, nif, direccion, cp);
    }
}