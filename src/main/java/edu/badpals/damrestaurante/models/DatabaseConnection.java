package edu.badpals.damrestaurante.models;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import edu.badpals.damrestaurante.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseConnection {

    public static Firestore db;

    //Firebase
    public static void connect() {
        try {
            FileInputStream refreshToken = new FileInputStream("dam-restaurante.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .build();

            FirebaseApp.initializeApp(options);

            db = FirestoreClient.getFirestore();

            System.out.println("Conectados a Firestore");
        } catch (IOException e) {
            System.out.println("Error al conectar a Firestore" + e.getMessage());
//            e.printStackTrace();
        }

    }

    public static EntityManager connectEm() {
        try {
            return Persistence.createEntityManagerFactory("default").createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<CartaComida> getCarta(EntityManager em, Empresa empresa) {

        Query query = em.createQuery("select cc from CartaComida cc where cc.idEmpresa = :idEmpresa");
        query.setParameter("idEmpresa", empresa.getCif());
        List<CartaComida> carta = query.getResultList();

        return carta;
    }

    public static List<UsuarioActual> getUsers(EntityManager em) {

        Query query = em.createQuery("select cc from UsuarioActual cc");
        List<UsuarioActual> carta = query.getResultList();

        return carta;
    }

    public static UsuarioActual authenticateUser(EntityManager em, String mail, String pwd) {
        try {
            List<UsuarioActual> usuariosActuales = DatabaseConnection.getUsers(em);
            Query query = em.createQuery("select cc from Usuario cc where correo = :mail and contraseña = :pwd");
            pwd = DatabaseConnection.hashPassword(pwd);
            query.setParameter("mail", mail);
            query.setParameter("pwd", pwd);
            query.setMaxResults(1);
            Usuario user = (Usuario) query.getSingleResult();
            for (UsuarioActual usuarioActual : usuariosActuales) {
                if (usuarioActual.getIdUsuario() == user.getIdUsuario()) {
                    return usuarioActual;
                }
            }
            return null;
        } catch (NoResultException e) {
            System.out.println("No se encontro el user con estos datos");
            return null;
        }
    }

    public static void updatePerfil(EntityManager em, UsuarioActual usuarioActual, String nuevoNombre, String nuevoApellido1, String nuevoApellido2, Timestamp nuevaFecha, String nuevoNumTelef, String nuevoNif, String nuevaDireccion, String nuevoCp) {
        try {
            em.getTransaction().begin();

            usuarioActual = em.find(UsuarioActual.class, usuarioActual.getIdUsuario());
            if (usuarioActual != null) {

                Usuario usuario = em.find(Usuario.class, usuarioActual.getIdUsuario());

                Usuario usuarioNuevo = crearNuevoUsuarioSobreUsuario(em, usuario);

                crearUsuarioPasadoRefUsuarioNuevo(em, usuarioActual, usuarioNuevo);

                usuario.setNombre(nuevoNombre);
                usuario.setApellido1(nuevoApellido1);
                usuario.setApellido2(nuevoApellido2);
                usuario.setFecha(nuevaFecha);
                usuario.setNumTelef(nuevoNumTelef);
                usuario.setNif(nuevoNif);
                usuario.setDireccion(nuevaDireccion);
                usuario.setCp(nuevoCp);

                em.merge(usuario);


            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public static Usuario crearUsuairo(EntityManager em, String mail, String pwd) {
        pwd = DatabaseConnection.hashPassword(pwd);

        Usuario usuario = new Usuario();
        Date date = new Date();

        usuario.setNombre("");
        usuario.setApellido1("");
        usuario.setApellido2("");
        usuario.setFecha(new Timestamp(date.getTime()));
        usuario.setNumTelef("");
        usuario.setNif("");
        usuario.setDireccion("");
        usuario.setCp("");
        usuario.setCorreo(mail);
        usuario.setContraseña(pwd);
        usuario.setImg("");

        em.getTransaction().begin();
        try {
            em.persist(usuario);
            em.createNativeQuery("INSERT INTO usuario_actual (id_usuario) VALUES (?)")
                    .setParameter(1, usuario.getIdUsuario())
                    .executeUpdate();
            em.getTransaction().commit();
            return usuario;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        }
    }


    private static void crearUsuarioPasadoRefUsuarioNuevo(EntityManager em, UsuarioActual usuarioActual, Usuario usuarioNuevo) {
        UsuarioPasado usuarioPasado = new UsuarioPasado();
        usuarioPasado.setIdUsuario(usuarioNuevo.getIdUsuario());
        usuarioPasado.setIdUsuarioPasado(usuarioActual.getIdUsuario());
        em.persist(usuarioPasado);
    }

    private static Usuario crearNuevoUsuarioSobreUsuario(EntityManager em, Usuario usuario) {
        Usuario usuarioNuevo = new Usuario();
        usuarioNuevo.setNombre(usuario.getNombre());
        usuarioNuevo.setApellido1(usuario.getApellido1());
        usuarioNuevo.setApellido2(usuario.getApellido2());
        usuarioNuevo.setFecha(usuario.getFecha());
        usuarioNuevo.setNumTelef(usuario.getNumTelef());
        usuarioNuevo.setNif(usuario.getNif());
        usuarioNuevo.setDireccion(usuario.getDireccion());
        usuarioNuevo.setCp(usuario.getCp());
        usuarioNuevo.setImg(usuario.getImg());
        usuarioNuevo.setCorreo("");
        usuarioNuevo.setContraseña("");
        em.persist(usuarioNuevo);
        return usuarioNuevo;
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addToCarrito(EntityManager em, CartaComida cartaComida, UsuarioActual user, Empresa empresa) {
        Carrito carrito = getCarrito(em, user, empresa);
        CarritoComida carritoComida = getCarritoComida(em, carrito, cartaComida);
        if (carritoComida != null) {
            carritoComida.setCantidad(carritoComida.getCantidad() + 1);
            em.getTransaction().begin();
            try {
                em.merge(carritoComida);
                em.getTransaction().commit();
            } catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                e.printStackTrace();
            }
        }
    }

    private static CarritoComida getCarritoComida(EntityManager em, Carrito carrito, CartaComida cartaComida) {
        try {
            Query query = em.createQuery("SELECT cc FROM CarritoComida cc WHERE cc.idCarrito = :idCarrito AND cc.idComida = :idComida");
            query.setParameter("idCarrito", carrito.getIdCarro());
            query.setParameter("idComida", cartaComida.getIdComida());
            return (CarritoComida) query.getSingleResult();
        } catch (NoResultException e) {
            crearCarritoComida(em, carrito, cartaComida);
            return getCarritoComida(em, carrito, cartaComida);
        }
    }

    private static void crearCarritoComida(EntityManager em, Carrito carrito, CartaComida cartaComida) {
        CarritoComida carritoComida = new CarritoComida();
        carritoComida.setIdComida(cartaComida.getIdComida());
        carritoComida.setIdCarrito(carrito.getIdCarro());
        carritoComida.setCantidad(0);
        em.getTransaction().begin();
        try {
            em.persist(carritoComida);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    private static Carrito getCarrito(EntityManager em, UsuarioActual usera, Empresa empresa) {
        Usuario user = em.find(Usuario.class, usera.getIdUsuario());
        try {
            Query query = em.createQuery("SELECT c FROM Carrito c WHERE c.idUsuario = :userId");
            query.setParameter("userId", user.getIdUsuario());
            return (Carrito) query.getSingleResult();
        } catch (NoResultException e) {
            crearCarrito(em, user, empresa);
            return getCarrito(em, usera, empresa);

        }
    }

    private static void crearCarrito(EntityManager em, Usuario user, Empresa empresa) {
        Date date = new Date();

        Factura factura = new Factura();
        factura.setIdUsuario(user.getIdUsuario());
        factura.setFecha(new Timestamp(date.getTime()));
        factura.setTotal(0);
        factura.setCifEmpresa(empresa.getCif());


        Carrito carritoNuevo = new Carrito();
        carritoNuevo.setIdUsuario(user.getIdUsuario());
        carritoNuevo.setComidaCantidad("");
        carritoNuevo.setIdFactura(factura.getIdFactura());

        em.getTransaction().begin();
        try {
            em.persist(factura);
            em.createNativeQuery("INSERT INTO carrito (id_usuario,comida_cantidad,id_factura) VALUES (?,?,?)")
                    .setParameter(1, user.getIdUsuario())
                    .setParameter(2, "")
                    .setParameter(3, factura.getIdFactura())
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }

    }

    public static Usuario getUserByUserActual(EntityManager em, UsuarioActual user) {
        return em.find(Usuario.class, user.getIdUsuario());
    }

    public static Empresa getEmpresa(EntityManager em, String nombre) {
        Query query = em.createQuery("SELECT c FROM Empresa c WHERE c.nombreLocal = :nombre");
        query.setParameter("nombre", nombre);

        return (Empresa) query.getSingleResult();
    }

    public static List<CarritoComida> getCarritoComida(EntityManager em, UsuarioActual usera) {
        List<CartaComida> comidas = new ArrayList<>();
        Usuario user = em.find(Usuario.class, usera.getIdUsuario());
        Query query = em.createQuery("SELECT c FROM Carrito c WHERE c.idUsuario = :userId");
        query.setParameter("userId", user.getIdUsuario());
        Carrito carrito = (Carrito) query.getSingleResult();
        query = em.createQuery("SELECT c FROM CarritoComida c WHERE c.idCarrito = :carritoId");
        query.setParameter("carritoId", carrito.getIdCarro());
        List<CarritoComida> carritoComidas = query.getResultList();
        return carritoComidas;
    }

    public static CartaComida getComida(EntityManager em, int idComida) {
        return em.find(CartaComida.class, idComida);
    }

    //ENSEÑAMOS EN EL LIST VIEW LAS MESAS LIBRES PARA ESE TURNO Y FECHA
    public static List<Mesas> getMesasPorFechaTurno(EntityManager em, Turno turno, java.sql.Date fecha, int comensales, Empresa empresa) {
        Query query = em.createQuery(
                "SELECT m FROM Mesas m WHERE m.idEmpresa = :empresa AND m.cupo >= :comensales " +
                        "AND m.idMesa NOT IN (SELECT r.idMesa FROM Reservas r WHERE r.idRestaurante = :idrestaurante AND r.fechaReserva = :fecha AND r.turno = :turno)"
        );
        query.setParameter("empresa", empresa.getCif());
        query.setParameter("comensales", comensales);
        query.setParameter("idrestaurante", empresa.getCif());
        query.setParameter("fecha", fecha);
        query.setParameter("turno", turno);


        try {
            List<Mesas> mesasLibres = query.getResultList();
            return mesasLibres;
        } catch (NoResultException e) {
            return new ArrayList<>();
        }

    }

    //EN ESTA RESERVAMOS LAS MESAS
    public static void reservarMesa(EntityManager em, Turno turno, java.sql.Date fecha, Empresa empresa, UsuarioActual user, Mesas mesa){
        Reservas reserva = new Reservas();
        reserva.setFechaReserva(fecha);
        reserva.setTurno(turno);
        reserva.setIdMesa(mesa.getIdMesa());
        reserva.setIdRestaurante(empresa.getCif());
        reserva.setIdUsuario(user.getIdUsuario());
        reserva.setReservaAceptada((byte) 1);
        em.getTransaction().begin();
        try {
            em.persist(reserva);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }


    }

    public static List<Empresa> getEmpresas(EntityManager em){
        Query query = em.createQuery("select cc from Empresa cc");
        List<Empresa> empresas = query.getResultList();

        return empresas;
    }

    public static Empresa getEmpresaPorNombre(EntityManager em,String nombre){
        Query query = em.createQuery("select cc from Empresa cc where nombreLocal = :nombre");
        query.setParameter("nombre",nombre);
        Empresa empresa = (Empresa) query.getSingleResult();
        return empresa;
    }

    public static CartaComida getInfoComida(EntityManager em, int id_comida) {

        Query query = em.createQuery("select cc from CartaComida cc where idComida = :id_comida");
        query.setParameter("id_comida", id_comida);
        CartaComida cartaComida = (CartaComida) query.getSingleResult();
        return cartaComida;

    }

    public static CartaAlergenos getInfoComidaAlergenos(EntityManager em, int id_comida) {

        Query query = em.createQuery("select ca from CartaAlergenos ca where idComida = :id_comida");
        query.setParameter("id_comida", id_comida);
        CartaAlergenos CartaAlergenos = (CartaAlergenos) query.getSingleResult();
        return CartaAlergenos;

    }

    public static Alergenos getInfoAlergenos(EntityManager em, int id_alergeno) {

        Query query = em.createQuery("select al from Alergenos al where idAlergeno = :id_alergeno");
        query.setParameter("id_alergeno", id_alergeno);
        Alergenos alergenos = (Alergenos) query.getSingleResult();
        return alergenos;

    }


}