package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Mesas", schema = "luachea", catalog = "")
public class Mesas {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_mesa", nullable = false)
    private int idMesa;
    @Basic
    @Column(name = "enumMesa", nullable = false, length = 20)
    private String enumMesa;

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public String getEnumMesa() {
        return enumMesa;
    }

    public void setEnumMesa(String enumMesa) {
        this.enumMesa = enumMesa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mesas mesas = (Mesas) o;
        return idMesa == mesas.idMesa && Objects.equals(enumMesa, mesas.enumMesa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMesa, enumMesa);
    }
}