package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Mesas {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_mesa", nullable = false)
    private int idMesa;
    @Basic
    @Column(name = "enumMesa", nullable = false, length = 20)
    private String enumMesa;
    @OneToMany(mappedBy = "mesasByIdMesa")
    private Collection<Reservas> reservasByIdMesa;

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

    public Collection<Reservas> getReservasByIdMesa() {
        return reservasByIdMesa;
    }

    public void setReservasByIdMesa(Collection<Reservas> reservasByIdMesa) {
        this.reservasByIdMesa = reservasByIdMesa;
    }

    @Override
    public String toString() {
        return "Mesas{" +
                "idMesa=" + idMesa +
                ", enumMesa='" + enumMesa + '\'' +
                '}';
    }
}