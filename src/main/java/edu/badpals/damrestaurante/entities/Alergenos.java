package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Alergenos {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_alergeno", nullable = false)
    private int idAlergeno;
    @Basic
    @Column(name = "nombre_alergeno", nullable = false, length = 100)
    private String nombreAlergeno;
    @Basic
    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;
    @Basic
    @Column(name = "img", nullable = true, length = 100)
    private String img;

    public int getIdAlergeno() {
        return idAlergeno;
    }

    public void setIdAlergeno(int idAlergeno) {
        this.idAlergeno = idAlergeno;
    }

    public String getNombreAlergeno() {
        return nombreAlergeno;
    }

    public void setNombreAlergeno(String nombreAlergeno) {
        this.nombreAlergeno = nombreAlergeno;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alergenos alergenos = (Alergenos) o;
        return idAlergeno == alergenos.idAlergeno && Objects.equals(nombreAlergeno, alergenos.nombreAlergeno) && Objects.equals(descripcion, alergenos.descripcion) && Objects.equals(img, alergenos.img);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAlergeno, nombreAlergeno, descripcion, img);
    }
}