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
import java.sql.Timestamp;
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

    public static List<CartaComida> getCarta(EntityManager em) {

        Query query = em.createQuery("select cc from CartaComida cc");
        List<CartaComida> carta = query.getResultList();

        return carta;
    }

    public static List<UsuarioActual> getUsers(EntityManager em) {

        Query query = em.createQuery("select cc from UsuarioActual cc");
        List<UsuarioActual> carta = query.getResultList();

        return carta;
    }

    //    Esta funcion se debe usar teniendo en cuenta que si no se encuentra una
//    mesa con la cantidad de comensales adecuada va a devolver null
    public static Mesas getMesaPorComensales(EntityManager em, int comensales) {

        Query query = em.createQuery("select cc from Mesas cc where ocupada = false order by cc.cupo ASC");
        List<Mesas> mesas = query.getResultList();
        for (Mesas mesa : mesas) {
            if (mesa.getCupo() > comensales) {
                return mesa;
            }
        }
        return null;
    }

    public static UsuarioActual authenticateUser(EntityManager em, String mail, String pwd) {
        try {
            List<UsuarioActual> usuariosActuales = DatabaseConnection.getUsers(em);
            Query query = em.createQuery("select cc from Usuario cc where correo = :mail and contraseña = :pwd");
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

    public static UsuarioActual crearUsuairoActual(EntityManager em, String mail, String pwd) {
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
        em.persist(usuario);

        UsuarioActual usuarioActual = new UsuarioActual();
        usuarioActual.setUsuarioByIdUsuario(usuario);
        em.persist(usuarioActual);

        return usuarioActual;
    }

    public static void updatePerfil(EntityManager em, UsuarioActual usuarioActual, String nuevoNombre, String nuevoApellido1, String nuevoApellido2, Timestamp nuevaFecha, String nuevoNumTelef, String nuevoNif, String nuevaDireccion, String nuevoCp) {
        try {
            em.getTransaction().begin();

            usuarioActual = em.find(UsuarioActual.class, usuarioActual.getIdUsuario());
            if (usuarioActual != null) {

                Usuario usuario = usuarioActual.getUsuarioByIdUsuario();

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

    private static void crearUsuarioPasadoRefUsuarioNuevo(EntityManager em, UsuarioActual usuarioActual, Usuario usuarioNuevo) {
        UsuarioPasado usuarioPasado = new UsuarioPasado();
        usuarioPasado.setUsuarioByIdUsuario(usuarioNuevo);
        usuarioPasado.setIdUsuario(usuarioNuevo.getIdUsuario());
        usuarioPasado.setUsuarioActualByIdUsuarioPasado(usuarioActual);
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
}