package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Reservas", schema = "luachea", catalog = "")
public class Reservas {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_reservas", nullable = false)
    private int idReservas;
    @Basic
    @Column(name = "id_usuario", nullable = true)
    private Integer idUsuario;
    @Basic
    @Column(name = "id_restaurante", nullable = true, length = 10)
    private String idRestaurante;
    @Basic
    @Column(name = "id_mesa", nullable = true)
    private Integer idMesa;
    @Basic
    @Column(name = "fecha_reserva", nullable = false)
    private Date fechaReserva;
    @Basic
    @Column(name = "turno", nullable = true)
    private Object turno;
    @Basic
    @Column(name = "reservaAceptada", nullable = true)
    private Object reservaAceptada;

    public int getIdReservas() {
        return idReservas;
    }

    public void setIdReservas(int idReservas) {
        this.idReservas = idReservas;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(String idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Object getTurno() {
        return turno;
    }

    public void setTurno(Object turno) {
        this.turno = turno;
    }

    public Object getReservaAceptada() {
        return reservaAceptada;
    }

    public void setReservaAceptada(Object reservaAceptada) {
        this.reservaAceptada = reservaAceptada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservas reservas = (Reservas) o;
        return idReservas == reservas.idReservas && Objects.equals(idUsuario, reservas.idUsuario) && Objects.equals(idRestaurante, reservas.idRestaurante) && Objects.equals(idMesa, reservas.idMesa) && Objects.equals(fechaReserva, reservas.fechaReserva) && Objects.equals(turno, reservas.turno) && Objects.equals(reservaAceptada, reservas.reservaAceptada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReservas, idUsuario, idRestaurante, idMesa, fechaReserva, turno, reservaAceptada);
    }
}