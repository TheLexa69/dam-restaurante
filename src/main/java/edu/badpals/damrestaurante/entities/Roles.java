package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Roles {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_rol", nullable = false)
    private int idRol;
    @Basic
    @Column(name = "nombre_rol", nullable = false, length = 100)
    private String nombreRol;
    @OneToMany(mappedBy = "rolesByIdRol")
    private Collection<UsuarioActual> usuarioActualsByIdRol;

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roles roles = (Roles) o;
        return idRol == roles.idRol && Objects.equals(nombreRol, roles.nombreRol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRol, nombreRol);
    }

    public Collection<UsuarioActual> getUsuarioActualsByIdRol() {
        return usuarioActualsByIdRol;
    }

    public void setUsuarioActualsByIdRol(Collection<UsuarioActual> usuarioActualsByIdRol) {
        this.usuarioActualsByIdRol = usuarioActualsByIdRol;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "idRol=" + idRol +
                ", nombreRol='" + nombreRol + '\'' +
                '}';
    }
}