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
    @Column(name = "id_usuario", nullable = false, insertable = false, updatable = false)
    private int idUsuario;
    @Basic
    @Column(name = "id_usuario_pasado", nullable = false, insertable = false, updatable = false)
    private int idUsuarioPasado;
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuarioByIdUsuario;
    @ManyToOne
    @JoinColumn(name = "id_usuario_pasado", referencedColumnName = "id_usuario", nullable = false)
    private UsuarioActual usuarioActualByIdUsuarioPasado;

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

    public void setIdUsuarioPasado(int idUsuarioPasado) {
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

    public Usuario getUsuarioByIdUsuario() {
        return usuarioByIdUsuario;
    }

    public void setUsuarioByIdUsuario(Usuario usuarioByIdUsuario) {
        this.usuarioByIdUsuario = usuarioByIdUsuario;
    }

    public UsuarioActual getUsuarioActualByIdUsuarioPasado() {
        return usuarioActualByIdUsuarioPasado;
    }

    public void setUsuarioActualByIdUsuarioPasado(UsuarioActual usuarioActualByIdUsuarioPasado) {
        this.usuarioActualByIdUsuarioPasado = usuarioActualByIdUsuarioPasado;
    }

    @Override
    public String toString() {
        return "UsuarioPasado{" +
                "idDatosUsuario=" + idDatosUsuario +
                ", idUsuario=" + idUsuario +
                ", idUsuarioPasado=" + idUsuarioPasado +
                '}';
    }
}