package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "carta_comida", schema = "luachea", catalog = "")
public class CartaComida {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_comida", nullable = false)
    private int idComida;
    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Basic
    @Column(name = "descripcion", nullable = true, length = 300)
    private String descripcion;
    @Basic
    @Column(name = "tipo", nullable = false, insertable = false, updatable = false)
    private int tipo;
    @Basic
    @Column(name = "subtipo", nullable = true, insertable = false, updatable = false)
    private Integer subtipo;
    @Basic
    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;
    @Basic
    @Column(name = "fecha_fin", nullable = true)
    private Date fechaFin;
    @Basic
    @Column(name = "precio", nullable = false, precision = 0)
    private double precio;
    @Basic
    @Column(name = "disponible", nullable = false)
    private byte disponible;
    @Basic
    @Column(name = "img", nullable = false, length = 100)
    private String img;
    @OneToMany(mappedBy = "cartaComidaByIdComida")
    private Collection<CartaAlergenos> cartaAlergenosByIdComida;
    @ManyToOne
    @JoinColumn(name = "tipo", referencedColumnName = "id_tipo", nullable = false)
    private Tipo tipoByTipo;
    @ManyToOne
    @JoinColumn(name = "subtipo", referencedColumnName = "id_subtipo")
    private Subtipo subtipoBySubtipo;
    @OneToMany(mappedBy = "cartaComidaByIdProd")
    private Collection<PedProd> pedProdsByIdComida;

    public int getIdComida() {
        return idComida;
    }

    public void setIdComida(int idComida) {
        this.idComida = idComida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Integer getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(Integer subtipo) {
        this.subtipo = subtipo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public byte getDisponible() {
        return disponible;
    }

    public void setDisponible(byte disponible) {
        this.disponible = disponible;
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
        CartaComida that = (CartaComida) o;
        return idComida == that.idComida && tipo == that.tipo && Double.compare(precio, that.precio) == 0 && disponible == that.disponible && Objects.equals(nombre, that.nombre) && Objects.equals(descripcion, that.descripcion) && Objects.equals(subtipo, that.subtipo) && Objects.equals(fechaInicio, that.fechaInicio) && Objects.equals(fechaFin, that.fechaFin) && Objects.equals(img, that.img);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idComida, nombre, descripcion, tipo, subtipo, fechaInicio, fechaFin, precio, disponible, img);
    }

    public Collection<CartaAlergenos> getCartaAlergenosByIdComida() {
        return cartaAlergenosByIdComida;
    }

    public void setCartaAlergenosByIdComida(Collection<CartaAlergenos> cartaAlergenosByIdComida) {
        this.cartaAlergenosByIdComida = cartaAlergenosByIdComida;
    }

    public Tipo getTipoByTipo() {
        return tipoByTipo;
    }

    public void setTipoByTipo(Tipo tipoByTipo) {
        this.tipoByTipo = tipoByTipo;
    }

    public Subtipo getSubtipoBySubtipo() {
        return subtipoBySubtipo;
    }

    public void setSubtipoBySubtipo(Subtipo subtipoBySubtipo) {
        this.subtipoBySubtipo = subtipoBySubtipo;
    }

    public Collection<PedProd> getPedProdsByIdComida() {
        return pedProdsByIdComida;
    }

    public void setPedProdsByIdComida(Collection<PedProd> pedProdsByIdComida) {
        this.pedProdsByIdComida = pedProdsByIdComida;
    }

    @Override
    public String toString() {
        return "CartaComida{" +
                "idComida=" + idComida +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tipo=" + tipo +
                ", subtipo=" + subtipo +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", precio=" + precio +
                ", disponible=" + disponible +
                ", img='" + img + '\'' +
                '}';
    }
}