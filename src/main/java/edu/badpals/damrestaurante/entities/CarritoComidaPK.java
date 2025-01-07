package edu.badpals.damrestaurante.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class CarritoComidaPK implements Serializable {
    @Column(name = "id_carrito", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCarrito;
    @Column(name = "id_comida", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComida;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarritoComidaPK that = (CarritoComidaPK) o;
        return idCarrito == that.idCarrito && idComida == that.idComida;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCarrito, idComida);
    }
}