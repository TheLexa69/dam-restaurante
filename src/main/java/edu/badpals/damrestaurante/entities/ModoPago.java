package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "modo_pago", schema = "luachea", catalog = "")
public class ModoPago {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_modo_pago", nullable = false)
    private int idModoPago;
    @Basic
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    public int getIdModoPago() {
        return idModoPago;
    }

    public void setIdModoPago(int idModoPago) {
        this.idModoPago = idModoPago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModoPago modoPago = (ModoPago) o;
        return idModoPago == modoPago.idModoPago && Objects.equals(nombre, modoPago.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idModoPago, nombre);
    }
}