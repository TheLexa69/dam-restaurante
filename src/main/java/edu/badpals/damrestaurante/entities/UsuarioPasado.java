package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "usuario_pasado", schema = "luachea", catalog = "")
public class UsuarioPasado {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_datos_usuario", nullable = false)
    private int idDatosUsuario;
    @Basic
    @Column(name = "id_usuario", nullable = false)
    private int idUsuario;
    @Basic
    @Column(name = "id_usuario_pasado")
    private int idUsuarioPasado;

    public int getIdDatosUsuario() {
        return idDatosUsuario;
    }

    public void setIdDatosUsuario(int idDatosUsuario) {
        this.idDatosUsuario = idDatosUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdUsuarioPasado() {
        return idUsuarioPasado;
    }

    public void setIdUsuarioPasado(Integer idUsuarioPasado) {
        this.idUsuarioPasado = idUsuarioPasado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioPasado that = (UsuarioPasado) o;
        return idDatosUsuario == that.idDatosUsuario && idUsuario == that.idUsuario && idUsuarioPasado == that.idUsuarioPasado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDatosUsuario, idUsuario, idUsuarioPasado);
    }
}