package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "carrito_comida", schema = "luachea", catalog = "")
@IdClass(CarritoComidaPK.class)
public class CarritoComida {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_carrito", nullable = false)
    private int idCarrito;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarritoComida that = (CarritoComida) o;
        return idCarrito == that.idCarrito && idComida == that.idComida && cantidad == that.cantidad;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCarrito, idComida, cantidad);
    }
}