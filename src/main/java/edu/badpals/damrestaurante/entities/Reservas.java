package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
public class Reservas {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_reservas", nullable = false)
    private int idReservas;
    @Basic
    @Column(name = "id_usuario", nullable = true, insertable = false, updatable = false)
    private Integer idUsuario;
    @Basic
    @Column(name = "id_restaurante", nullable = true, length = 10, insertable = false, updatable = false)
    private String idRestaurante;
    @Basic
    @Column(name = "id_mesa", nullable = true, insertable = false, updatable = false)
    private Integer idMesa;
    @Basic
    @Column(name = "fecha_reserva", nullable = false)
    private Date fechaReserva;
    @Basic
    @Column(name = "turno", nullable = true)
    private Turno turno;
    @Basic
    @Column(name = "reservaAceptada", nullable = true)
    private Byte reservaAceptada;
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private UsuarioActual usuarioActualByIdUsuario;
    @ManyToOne
    @JoinColumn(name = "id_restaurante", referencedColumnName = "cif")
    private Empresa empresaByIdRestaurante;
    @ManyToOne
    @JoinColumn(name = "id_mesa", referencedColumnName = "id_mesa")
    private Mesas mesasByIdMesa;

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

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Byte getReservaAceptada() {
        return reservaAceptada;
    }

    public void setReservaAceptada(Byte reservaAceptada) {
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

    public UsuarioActual getUsuarioActualByIdUsuario() {
        return usuarioActualByIdUsuario;
    }

    public void setUsuarioActualByIdUsuario(UsuarioActual usuarioActualByIdUsuario) {
        this.usuarioActualByIdUsuario = usuarioActualByIdUsuario;
    }

    public Empresa getEmpresaByIdRestaurante() {
        return empresaByIdRestaurante;
    }

    public void setEmpresaByIdRestaurante(Empresa empresaByIdRestaurante) {
        this.empresaByIdRestaurante = empresaByIdRestaurante;
    }

    public Mesas getMesasByIdMesa() {
        return mesasByIdMesa;
    }

    public void setMesasByIdMesa(Mesas mesasByIdMesa) {
        this.mesasByIdMesa = mesasByIdMesa;
    }

    @Override
    public String toString() {
        return "Reservas{" +
                "idReservas=" + idReservas +
                ", idUsuario=" + idUsuario +
                ", idRestaurante='" + idRestaurante + '\'' +
                ", idMesa=" + idMesa +
                ", fechaReserva=" + fechaReserva +
                ", turno=" + turno +
                ", reservaAceptada=" + reservaAceptada +
                '}';
    }
}