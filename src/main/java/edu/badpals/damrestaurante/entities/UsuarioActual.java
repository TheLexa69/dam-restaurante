package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "usuario_actual", schema = "luachea", catalog = "")
public class UsuarioActual {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_usuario", nullable = false)
    private int idUsuario;
    @Basic
    @Column(name = "id_rol", nullable = false, insertable = false, updatable = false)
    private int idRol;
    @OneToMany(mappedBy = "usuarioActualByIdUsuario")
    private Collection<Reservas> reservasByIdUsuario;
    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuarioByIdUsuario;
    @ManyToOne
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol", nullable = false)
    private Roles rolesByIdRol;
    @OneToMany(mappedBy = "usuarioActualByIdUsuarioPasado")
    private Collection<UsuarioPasado> usuarioPasadosByIdUsuario;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioActual that = (UsuarioActual) o;
        return idUsuario == that.idUsuario && idRol == that.idRol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idRol);
    }

    public Collection<Reservas> getReservasByIdUsuario() {
        return reservasByIdUsuario;
    }

    public void setReservasByIdUsuario(Collection<Reservas> reservasByIdUsuario) {
        this.reservasByIdUsuario = reservasByIdUsuario;
    }

    public Usuario getUsuarioByIdUsuario() {
        return usuarioByIdUsuario;
    }

    public void setUsuarioByIdUsuario(Usuario usuarioByIdUsuario) {
        this.usuarioByIdUsuario = usuarioByIdUsuario;
    }

    public Roles getRolesByIdRol() {
        return rolesByIdRol;
    }

    public void setRolesByIdRol(Roles rolesByIdRol) {
        this.rolesByIdRol = rolesByIdRol;
    }

    public Collection<UsuarioPasado> getUsuarioPasadosByIdUsuario() {
        return usuarioPasadosByIdUsuario;
    }

    public void setUsuarioPasadosByIdUsuario(Collection<UsuarioPasado> usuarioPasadosByIdUsuario) {
        this.usuarioPasadosByIdUsuario = usuarioPasadosByIdUsuario;
    }

    @Override
public String toString() {
    return "UsuarioActual{" +
            "idUsuario=" + idUsuario +
            ", idRol=" + idRol +
            '}';
}
}