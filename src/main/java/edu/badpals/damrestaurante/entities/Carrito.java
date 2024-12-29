package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Carrito {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_carro", nullable = false)
    private int idCarro;
    @Basic
    @Column(name = "id_usuario", nullable = false)
    private int idUsuario;
    @Basic
    @Column(name = "comida_cantidad", nullable = true, length = -1)
    private String comidaCantidad;
    @Basic
    @Column(name = "id_ped", nullable = true)
    private Integer idPed;

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getComidaCantidad() {
        return comidaCantidad;
    }

    public void setComidaCantidad(String comidaCantidad) {
        this.comidaCantidad = comidaCantidad;
    }

    public Integer getIdPed() {
        return idPed;
    }

    public void setIdPed(Integer idPed) {
        this.idPed = idPed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carrito carrito = (Carrito) o;
        return idCarro == carrito.idCarro && idUsuario == carrito.idUsuario && Objects.equals(comidaCantidad, carrito.comidaCantidad) && Objects.equals(idPed, carrito.idPed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCarro, idUsuario, comidaCantidad, idPed);
    }
}