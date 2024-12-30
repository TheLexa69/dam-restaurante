package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "ped_prod", schema = "luachea", catalog = "")
public class PedProd {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_ped_prod", nullable = false)
    private int idPedProd;
    @Basic
    @Column(name = "id_ped", nullable = false, insertable = false, updatable = false)
    private int idPed;
    @Basic
    @Column(name = "id_prod", nullable = false, insertable = false, updatable = false)
    private int idProd;
    @Basic
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    @Basic
    @Column(name = "precio", nullable = false, precision = 0)
    private double precio;
    @ManyToOne
    @JoinColumn(name = "id_ped", referencedColumnName = "id_ped", nullable = false)
    private Pedidos pedidosByIdPed;
    @ManyToOne
    @JoinColumn(name = "id_prod", referencedColumnName = "id_comida", nullable = false)
    private CartaComida cartaComidaByIdProd;

    public int getIdPedProd() {
        return idPedProd;
    }

    public void setIdPedProd(int idPedProd) {
        this.idPedProd = idPedProd;
    }

    public int getIdPed() {
        return idPed;
    }

    public void setIdPed(int idPed) {
        this.idPed = idPed;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedProd pedProd = (PedProd) o;
        return idPedProd == pedProd.idPedProd && idPed == pedProd.idPed && idProd == pedProd.idProd && cantidad == pedProd.cantidad && Double.compare(precio, pedProd.precio) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedProd, idPed, idProd, cantidad, precio);
    }

    public Pedidos getPedidosByIdPed() {
        return pedidosByIdPed;
    }

    public void setPedidosByIdPed(Pedidos pedidosByIdPed) {
        this.pedidosByIdPed = pedidosByIdPed;
    }

    public CartaComida getCartaComidaByIdProd() {
        return cartaComidaByIdProd;
    }

    public void setCartaComidaByIdProd(CartaComida cartaComidaByIdProd) {
        this.cartaComidaByIdProd = cartaComidaByIdProd;
    }

    @Override
    public String toString() {
        return "PedProd{" +
                "idPedProd=" + idPedProd +
                ", idPed=" + idPed +
                ", idProd=" + idProd +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                '}';
    }
}