package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Subtipo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_subtipo", nullable = false)
    private int idSubtipo;
    @Basic
    @Column(name = "nombre_subtipo", nullable = false, length = 100)
    private String nombreSubtipo;
    @OneToMany(mappedBy = "subtipoBySubtipo")
    private Collection<CartaComida> cartaComidasByIdSubtipo;

    public int getIdSubtipo() {
        return idSubtipo;
    }

    public void setIdSubtipo(int idSubtipo) {
        this.idSubtipo = idSubtipo;
    }

    public String getNombreSubtipo() {
        return nombreSubtipo;
    }

    public void setNombreSubtipo(String nombreSubtipo) {
        this.nombreSubtipo = nombreSubtipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subtipo subtipo = (Subtipo) o;
        return idSubtipo == subtipo.idSubtipo && Objects.equals(nombreSubtipo, subtipo.nombreSubtipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSubtipo, nombreSubtipo);
    }

    public Collection<CartaComida> getCartaComidasByIdSubtipo() {
        return cartaComidasByIdSubtipo;
    }

    public void setCartaComidasByIdSubtipo(Collection<CartaComida> cartaComidasByIdSubtipo) {
        this.cartaComidasByIdSubtipo = cartaComidasByIdSubtipo;
    }

    @Override
    public String toString() {
        return "Subtipo{" +
                "idSubtipo=" + idSubtipo +
                ", nombreSubtipo='" + nombreSubtipo + '\'' +
                '}';
    }
}