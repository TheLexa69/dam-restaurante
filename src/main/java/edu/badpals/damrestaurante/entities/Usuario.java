package edu.badpals.damrestaurante.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public class Usuario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_usuario", nullable = false)
    private int idUsuario;
    @Basic
    @Column(name = "nombre", nullable = false, length = 40)
    private String nombre;
    @Basic
    @Column(name = "apellido1", nullable = false, length = 40)
    private String apellido1;
    @Basic
    @Column(name = "apellido2", nullable = true, length = 40)
    private String apellido2;
    @Basic
    @Column(name = "fecha", nullable = false)
    private Timestamp fecha;
    @Basic
    @Column(name = "num_telef", nullable = false, length = 9)
    private String numTelef;
    @Basic
    @Column(name = "NIF", nullable = true, length = 9)
    private String nif;
    @Basic
    @Column(name = "direccion", nullable = true, length = 1000)
    private String direccion;
    @Basic
    @Column(name = "cp", nullable = true, length = 5)
    private String cp;
    @Basic
    @Column(name = "img", nullable = false, length = 100)
    private String img;
    @Basic
    @Column(name = "correo", nullable = false, length = 40)
    private String correo;
    @Basic
    @Column(name = "contraseña", nullable = false, length = 255)
    private String contraseña;
    @OneToMany(mappedBy = "usuarioByIdUsuario")
    private Collection<Carrito> carritosByIdUsuario;
    @OneToMany(mappedBy = "usuarioByIdUsuario")
    private Collection<Factura> facturasByIdUsuario;
    @OneToOne(mappedBy = "usuarioByIdUsuario")
    private UsuarioActual usuarioActualByIdUsuario;
    @OneToMany(mappedBy = "usuarioByIdUsuario")
    private Collection<UsuarioPasado> usuarioPasadosByIdUsuario;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getNumTelef() {
        return numTelef;
    }

    public void setNumTelef(String numTelef) {
        this.numTelef = numTelef;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return idUsuario == usuario.idUsuario && Objects.equals(nombre, usuario.nombre) && Objects.equals(apellido1, usuario.apellido1) && Objects.equals(apellido2, usuario.apellido2) && Objects.equals(fecha, usuario.fecha) && Objects.equals(numTelef, usuario.numTelef) && Objects.equals(nif, usuario.nif) && Objects.equals(direccion, usuario.direccion) && Objects.equals(cp, usuario.cp) && Objects.equals(img, usuario.img) && Objects.equals(correo, usuario.correo) && Objects.equals(contraseña, usuario.contraseña);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, nombre, apellido1, apellido2, fecha, numTelef, nif, direccion, cp, img, correo, contraseña);
    }

    public Collection<Carrito> getCarritosByIdUsuario() {
        return carritosByIdUsuario;
    }

    public void setCarritosByIdUsuario(Collection<Carrito> carritosByIdUsuario) {
        this.carritosByIdUsuario = carritosByIdUsuario;
    }

    public Collection<Factura> getFacturasByIdUsuario() {
        return facturasByIdUsuario;
    }

    public void setFacturasByIdUsuario(Collection<Factura> facturasByIdUsuario) {
        this.facturasByIdUsuario = facturasByIdUsuario;
    }

    public UsuarioActual getUsuarioActualByIdUsuario() {
        return usuarioActualByIdUsuario;
    }

    public void setUsuarioActualByIdUsuario(UsuarioActual usuarioActualByIdUsuario) {
        this.usuarioActualByIdUsuario = usuarioActualByIdUsuario;
    }

    public Collection<UsuarioPasado> getUsuarioPasadosByIdUsuario() {
        return usuarioPasadosByIdUsuario;
    }

    public void setUsuarioPasadosByIdUsuario(Collection<UsuarioPasado> usuarioPasadosByIdUsuario) {
        this.usuarioPasadosByIdUsuario = usuarioPasadosByIdUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", fecha=" + fecha +
                ", numTelef='" + numTelef + '\'' +
                ", nif='" + nif + '\'' +
                ", direccion='" + direccion + '\'' +
                ", cp='" + cp + '\'' +
                ", img='" + img + '\'' +
                ", correo='" + correo + '\'' +
                ", contraseña='" + contraseña + '\'' +
                '}';
    }
}