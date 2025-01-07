package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Empresa {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cif", nullable = false, length = 10)
    private String cif;
    @Basic
    @Column(name = "nombreLocal", nullable = false, length = 120)
    private String nombreLocal;
    @Basic
    @Column(name = "nombre_sociedad", nullable = false, length = 120)
    private String nombreSociedad;
    @Basic
    @Column(name = "direccion", nullable = false, length = 60)
    private String direccion;
    @Basic
    @Column(name = "ciudad", nullable = false, length = 20)
    private String ciudad;
    @Basic
    @Column(name = "cp", nullable = true)
    private Integer cp;
    @Basic
    @Column(name = "telefono", nullable = false)
    private int telefono;
    @Basic
    @Column(name = "logo", nullable = false, length = 100)
    private String logo;

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public String getNombreSociedad() {
        return nombreSociedad;
    }

    public void setNombreSociedad(String nombreSociedad) {
        this.nombreSociedad = nombreSociedad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empresa empresa = (Empresa) o;
        return telefono == empresa.telefono && Objects.equals(cif, empresa.cif) && Objects.equals(nombreLocal, empresa.nombreLocal) && Objects.equals(nombreSociedad, empresa.nombreSociedad) && Objects.equals(direccion, empresa.direccion) && Objects.equals(ciudad, empresa.ciudad) && Objects.equals(cp, empresa.cp) && Objects.equals(logo, empresa.logo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cif, nombreLocal, nombreSociedad, direccion, ciudad, cp, telefono, logo);
    }
}