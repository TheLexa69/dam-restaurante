package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.util.List;
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
    @Basic
    @Column(name = "id_empresa", nullable = false, length = 10)
    private String idEmpresa;
    @Basic
    @Column(name = "cupo", nullable = false)
    private int cupo;
    @Basic
    @Column(name = "ocupada", nullable = false)
    private byte ocupada;
    @OneToMany(mappedBy = "mesa")
    private List<Reservas> reservas;

    public List<Reservas> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reservas> reservas) {
        this.reservas = reservas;
    }

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

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public byte getOcupada() {
        return ocupada;
    }

    public void setOcupada(byte ocupada) {
        this.ocupada = ocupada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mesas mesas = (Mesas) o;
        return idMesa == mesas.idMesa && cupo == mesas.cupo && ocupada == mesas.ocupada && Objects.equals(enumMesa, mesas.enumMesa) && Objects.equals(idEmpresa, mesas.idEmpresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMesa, enumMesa, idEmpresa, cupo, ocupada);
    }
}