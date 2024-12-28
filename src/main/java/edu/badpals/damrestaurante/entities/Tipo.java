package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Tipo", schema = "luachea", catalog = "")
public class Tipo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_tipo", nullable = false)
    private int idTipo;
    @Basic
    @Column(name = "nombre_tipo", nullable = true, length = 100)
    private String nombreTipo;

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
}