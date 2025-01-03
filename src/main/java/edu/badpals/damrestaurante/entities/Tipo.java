package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Tipo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_tipo", nullable = false)
    private int idTipo;
    @Basic
    @Column(name = "nombre_tipo", nullable = true, length = 100)
    private String nombreTipo;
    @OneToMany(mappedBy = "tipoByTipo")
    private Collection<CartaComida> cartaComidasByIdTipo;

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tipo tipo = (Tipo) o;
        return idTipo == tipo.idTipo && Objects.equals(nombreTipo, tipo.nombreTipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTipo, nombreTipo);
    }

    public Collection<CartaComida> getCartaComidasByIdTipo() {
        return cartaComidasByIdTipo;
    }

    public void setCartaComidasByIdTipo(Collection<CartaComida> cartaComidasByIdTipo) {
        this.cartaComidasByIdTipo = cartaComidasByIdTipo;
    }

    @Override
    public String toString() {
        return "Tipo{" +
                "idTipo=" + idTipo +
                ", nombreTipo='" + nombreTipo + '\'' +
                '}';
    }
}