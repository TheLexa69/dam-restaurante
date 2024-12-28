package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Pedidos", schema = "luachea", catalog = "")
public class Pedidos {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_ped", nullable = false)
    private int idPed;
    @Basic
    @Column(name = "id_usuario", nullable = false)
    private int idUsuario;
    @Basic
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    @Basic
    @Column(name = "enviado", nullable = false)
    private Object enviado;
    @Basic
    @Column(name = "restaurante", nullable = false, length = 10)
    private String restaurante;

    public int getIdPed() {
        return idPed;
    }

    public void setIdPed(int idPed) {
        this.idPed = idPed;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Object getEnviado() {
        return enviado;
    }

    public void setEnviado(Object enviado) {
        this.enviado = enviado;
    }

    public String getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedidos pedidos = (Pedidos) o;
        return idPed == pedidos.idPed && idUsuario == pedidos.idUsuario && Objects.equals(fecha, pedidos.fecha) && Objects.equals(enviado, pedidos.enviado) && Objects.equals(restaurante, pedidos.restaurante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPed, idUsuario, fecha, enviado, restaurante);
    }
}