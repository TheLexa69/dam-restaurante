package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "carta_alergenos", schema = "luachea", catalog = "")
public class CartaAlergenos {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_carta_alergenos", nullable = false)
    private int idCartaAlergenos;
    @Basic
    @Column(name = "id_alergeno", nullable = false)
    private int idAlergeno;
    @Basic
    @Column(name = "id_comida", nullable = false)
    private int idComida;

    public int getIdCartaAlergenos() {
        return idCartaAlergenos;
    }

    public void setIdCartaAlergenos(int idCartaAlergenos) {
        this.idCartaAlergenos = idCartaAlergenos;
    }

    public int getIdAlergeno() {
        return idAlergeno;
    }

    public void setIdAlergeno(int idAlergeno) {
        this.idAlergeno = idAlergeno;
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
        CartaAlergenos that = (CartaAlergenos) o;
        return idCartaAlergenos == that.idCartaAlergenos && idAlergeno == that.idAlergeno && idComida == that.idComida;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCartaAlergenos, idAlergeno, idComida);
    }
}