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
    @OneToMany(mappedBy = "usuarioActualByIdUsuario")
    private Collection<Reservas> reservasByIdUsuario;
    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuarioByIdUsuario;
    @OneToMany(mappedBy = "usuarioActualByIdUsuarioPasado")
    private Collection<UsuarioPasado> usuarioPasadosByIdUsuario;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioActual that = (UsuarioActual) o;
        return idUsuario == that.idUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario);
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
            '}';
}
}