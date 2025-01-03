package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Factura {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_factura", nullable = false)
    private int idFactura;
    @Basic
    @Column(name = "id_usuario", nullable = false, insertable = false, updatable = false)
    private int idUsuario;
    @Basic
    @Column(name = "cif_empresa", nullable = false, length = 10, insertable = false, updatable = false)
    private String cifEmpresa;
    @Basic
    @Column(name = "fecha", nullable = false)
    private Timestamp fecha;
    @Basic
    @Column(name = "total", nullable = false, precision = 0)
    private double total;
    @Basic
    @Column(name = "modo_pago", nullable = false, insertable = false, updatable = false)
    private int modoPago;
    @Basic
    @Column(name = "id_ped", nullable = false, insertable = false, updatable = false)
    private int idPed;
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuarioByIdUsuario;
    @ManyToOne
    @JoinColumn(name = "cif_empresa", referencedColumnName = "cif", nullable = false)
    private Empresa empresaByCifEmpresa;
    @ManyToOne
    @JoinColumn(name = "modo_pago", referencedColumnName = "id_modo_pago", nullable = false)
    private ModoPago modoPagoByModoPago;
    @ManyToOne
    @JoinColumn(name = "id_ped", referencedColumnName = "id_ped", nullable = false)
    private Pedidos pedidosByIdPed;

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCifEmpresa() {
        return cifEmpresa;
    }

    public void setCifEmpresa(String cifEmpresa) {
        this.cifEmpresa = cifEmpresa;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getModoPago() {
        return modoPago;
    }

    public void setModoPago(int modoPago) {
        this.modoPago = modoPago;
    }

    public int getIdPed() {
        return idPed;
    }

    public void setIdPed(int idPed) {
        this.idPed = idPed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factura factura = (Factura) o;
        return idFactura == factura.idFactura && idUsuario == factura.idUsuario && Double.compare(total, factura.total) == 0 && modoPago == factura.modoPago && idPed == factura.idPed && Objects.equals(cifEmpresa, factura.cifEmpresa) && Objects.equals(fecha, factura.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFactura, idUsuario, cifEmpresa, fecha, total, modoPago, idPed);
    }

    public Usuario getUsuarioByIdUsuario() {
        return usuarioByIdUsuario;
    }

    public void setUsuarioByIdUsuario(Usuario usuarioByIdUsuario) {
        this.usuarioByIdUsuario = usuarioByIdUsuario;
    }

    public Empresa getEmpresaByCifEmpresa() {
        return empresaByCifEmpresa;
    }

    public void setEmpresaByCifEmpresa(Empresa empresaByCifEmpresa) {
        this.empresaByCifEmpresa = empresaByCifEmpresa;
    }

    public ModoPago getModoPagoByModoPago() {
        return modoPagoByModoPago;
    }

    public void setModoPagoByModoPago(ModoPago modoPagoByModoPago) {
        this.modoPagoByModoPago = modoPagoByModoPago;
    }

    public Pedidos getPedidosByIdPed() {
        return pedidosByIdPed;
    }

    public void setPedidosByIdPed(Pedidos pedidosByIdPed) {
        this.pedidosByIdPed = pedidosByIdPed;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "idFactura=" + idFactura +
                ", idUsuario=" + idUsuario +
                ", cifEmpresa='" + cifEmpresa + '\'' +
                ", fecha=" + fecha +
                ", total=" + total +
                '}';
    }
}