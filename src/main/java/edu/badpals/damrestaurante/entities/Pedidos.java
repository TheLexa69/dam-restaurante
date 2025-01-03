package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Pedidos {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_ped", nullable = false)
    private int idPed;
    @Basic
    @Column(name = "id_usuario", nullable = false)
    private int idUsuario;
    @Basic
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    @Basic
    @Column(name = "enviado", nullable = false)
    private byte enviado;
    @Basic
    @Column(name = "restaurante", nullable = false, length = 10, insertable = false, updatable = false)
    private String restaurante;
    @Basic
    @Column(name = "id_carrito", nullable = false, insertable = false, updatable = false)
    private int idCarrito;
    @OneToMany(mappedBy = "pedidosByIdPed")
    private Collection<Factura> facturasByIdPed;
    @OneToMany(mappedBy = "pedidosByIdPed")
    private Collection<PedProd> pedProdsByIdPed;
    @ManyToOne
    @JoinColumn(name = "restaurante", referencedColumnName = "cif", nullable = false)
    private Empresa empresaByRestaurante;
    @ManyToOne
    @JoinColumn(name = "id_carrito", referencedColumnName = "id_carro", nullable = false)
    private Carrito carritoByIdCarrito;

    public int getIdPed() {
        return idPed;
    }

    public void setIdPed(int idPed) {
        this.idPed = idPed;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public byte getEnviado() {
        return enviado;
    }

    public void setEnviado(byte enviado) {
        this.enviado = enviado;
    }

    public String getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedidos pedidos = (Pedidos) o;
        return idPed == pedidos.idPed && idUsuario == pedidos.idUsuario && enviado == pedidos.enviado && idCarrito == pedidos.idCarrito && Objects.equals(fecha, pedidos.fecha) && Objects.equals(restaurante, pedidos.restaurante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPed, idUsuario, fecha, enviado, restaurante, idCarrito);
    }

    public Collection<Factura> getFacturasByIdPed() {
        return facturasByIdPed;
    }

    public void setFacturasByIdPed(Collection<Factura> facturasByIdPed) {
        this.facturasByIdPed = facturasByIdPed;
    }

    public Collection<PedProd> getPedProdsByIdPed() {
        return pedProdsByIdPed;
    }

    public void setPedProdsByIdPed(Collection<PedProd> pedProdsByIdPed) {
        this.pedProdsByIdPed = pedProdsByIdPed;
    }

    public Empresa getEmpresaByRestaurante() {
        return empresaByRestaurante;
    }

    public void setEmpresaByRestaurante(Empresa empresaByRestaurante) {
        this.empresaByRestaurante = empresaByRestaurante;
    }

    public Carrito getCarritoByIdCarrito() {
        return carritoByIdCarrito;
    }

    public void setCarritoByIdCarrito(Carrito carritoByIdCarrito) {
        this.carritoByIdCarrito = carritoByIdCarrito;
    }

    @Override
    public String toString() {
        return "Pedidos{" +
                "idPed=" + idPed +
                ", idUsuario=" + idUsuario +
                ", fecha=" + fecha +
                ", enviado=" + enviado +
                ", restaurante='" + restaurante + '\'' +
                '}';
    }
}