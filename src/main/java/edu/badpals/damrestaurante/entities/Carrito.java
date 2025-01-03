package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Carrito {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_carro", nullable = false)
    private int idCarro;
    @Basic
    @Column(name = "id_usuario", nullable = false, insertable = false, updatable = false)
    private int idUsuario;
    @Basic
    @Column(name = "comida_cantidad", nullable = true, length = -1)
    private String comidaCantidad;
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuarioByIdUsuario;
    @OneToMany(mappedBy = "carritoByIdCarrito")
    private Collection<Pedidos> pedidosByIdCarro;

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getComidaCantidad() {
        return comidaCantidad;
    }

    public void setComidaCantidad(String comidaCantidad) {
        this.comidaCantidad = comidaCantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carrito carrito = (Carrito) o;
        return idCarro == carrito.idCarro && idUsuario == carrito.idUsuario && Objects.equals(comidaCantidad, carrito.comidaCantidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCarro, idUsuario, comidaCantidad);
    }

    public Usuario getUsuarioByIdUsuario() {
        return usuarioByIdUsuario;
    }

    public void setUsuarioByIdUsuario(Usuario usuarioByIdUsuario) {
        this.usuarioByIdUsuario = usuarioByIdUsuario;
    }

    public Collection<Pedidos> getPedidosByIdCarro() {
        return pedidosByIdCarro;
    }

    public void setPedidosByIdCarro(Collection<Pedidos> pedidosByIdCarro) {
        this.pedidosByIdCarro = pedidosByIdCarro;
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "idCarro=" + idCarro +
                ", idUsuario=" + idUsuario +
                ", comidaCantidad='" + comidaCantidad + '\'' +
                '}';
    }
}