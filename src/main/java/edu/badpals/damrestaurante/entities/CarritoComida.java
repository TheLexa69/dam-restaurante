package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "carrito_comida", schema = "luachea", catalog = "")
public class CarritoComida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carritoComida", nullable = false)
    private int id_carritoComida;
    @Basic
    @Column(name = "id_carrito", nullable = false)
    private int idCarrito;
    @Basic
    @Column(name = "id_comida", nullable = false)
    private int idComida;
    @Basic
    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public int getIdComida() {
        return idComida;
    }

    public void setIdComida(int idComida) {
        this.idComida = idComida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_carritoComida() {
        return id_carritoComida;
    }

    public void setId_carritoComida(int id_carritoComida) {
        this.id_carritoComida = id_carritoComida;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarritoComida that = (CarritoComida) o;
        return id_carritoComida == that.id_carritoComida;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_carritoComida,idCarrito, idComida, cantidad);
    }
}