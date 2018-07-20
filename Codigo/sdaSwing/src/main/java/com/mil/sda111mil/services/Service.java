/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mil.sda111mil.services;

import com.mil.sda111mil.beans.Responsable;
import com.mil.sda111mil.beans.Preceptor;
import com.mil.sda111mil.beans.Alumno;
import com.mil.sda111mil.beans.Asistencia;
import com.mil.sda111mil.beans.Asistencia.ClaveAsistencia;
import com.mil.sda111mil.beans.Calendario;
import com.mil.sda111mil.beans.Calendario.DiasNoHabiles;
import com.mil.sda111mil.beans.Curso;
import com.mil.sda111mil.beans.Telefono;
import com.mil.sda111mil.beans.Telefono.Numero;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

/**
 *
 * @author CF02
 */
public class Service {

    /**
     *
     */
    private static SessionFactory sessionFactory;

    /**
     *
     */
    private static Session session;

    /**
     *
     * @return
     */
    public static Session getSession() {
        if (sessionFactory == null) {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure() // obtiene los valores de hibernate.cfg.xml
                    .build();
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();
        }
        if (session == null || !session.isOpen()) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    /**
     *
     * @return
     */
    private static SessionFactory iniciarSessionFactory() {
        //establece la conexion local
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // obtiene los valores de hibernate.cfg.xml
                .build();
        SessionFactory sessionFactory2 = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();

        return sessionFactory2;
    }

    /**
     *
     * @param sessionFactory
     * @return
     */
    private static Session iniciarSession(SessionFactory sessionFactory) {
        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();
        return session2;
    }

    /**
     *
     */
    public static void cerrarSession() {
        Session s = getSession();
        s.close();
    }

    /**
     *
     * @param sessionFactory
     */
    private static void cerrarSessionFactory(SessionFactory sessionFactory) {
        sessionFactory.close();
    }

    /**
     *
     * @param dni
     * @param contrasena
     * @return
     */
    public static Preceptor validarUsuario(String dni, String contrasena) {
        Preceptor result;
        Session s = getSession();
        Preceptor p = s.get(Preceptor.class, Integer.parseInt(dni));
        if (p != null && p.getContrasenia().equals(contrasena)) {
            result = p;
        } else {
            result = null;
        }
        return result;
    }
    // si el boolean libre es true devuelve los libres, si es false devuelve los casi libres

    /**
     *
     * @param p
     * @param libre
     * @return
     */
    public static List<Alumno> getAlumnosLibres(Preceptor p, boolean libre) {

        List<Alumno> alumnosResultado = new ArrayList<>();
        Date fin = new Date();

        Date inicio = Service.getFechaInicio();
        for (Curso c : p.getCursos()) {
            if (c != null) {
                for (Alumno alumno : c.getAlumnos()) {
                    double cant = alumno.getAsistenciasTotales(inicio, fin);

                    if (libre == true && cant > 9) {
                        alumnosResultado.add(alumno);
                    }
                    if (libre == false && cant > 6 && cant < 9) {
                        alumnosResultado.add(alumno);
                    }
                }
            }
        }
        return alumnosResultado;
    }

    /**
     *
     * @return
     */
    public static List<Alumno> listarAlumnos() {
        Session s = getSession();
        Query q;
        q = s.createQuery("from Alumno order by apellido, nombre");
        return q.list();
    }

    /**
     * Este método permite encontrar alumnos a través del nombre y apellido
     * ingresados.
     *
     * @param nombre
     * @param apellido
     * @return una lista de alumnos que cumplan con lo ingresado.
     */
    public static List<Alumno> encontrarAlumno(String nombre, String apellido) {

        Session s = getSession();

        Query q;
        if ((nombre == null || nombre.isEmpty()) && (apellido == null || apellido.isEmpty())) {
            q = s.createQuery("from Alumno");

        } else if (nombre == null || nombre.isEmpty()) {
            q = s.createQuery("from Alumno where apellido like :apellido ");
            q.setParameter("apellido", apellido + "%");
        } else if (apellido == null || apellido.isEmpty()) {
            q = s.createQuery("from Alumno where nombre like :nombre ");
            q.setParameter("nombre", nombre + "%");
        } else {
            q = s.createQuery("from Alumno where apellido like :apellido AND nombre like :nombre ");
            q.setParameter("nombre", nombre + "%");
            q.setParameter("apellido", apellido + "%");
        }

        return q.list();
    }

    /**
     *
     * @param nombre
     * @param apellido
     * @return
     */
    public static List<Preceptor> encontrarPreceptor(String nombre, String apellido) {

        Session s = getSession();

        Query q;
        if ((nombre == null || nombre.isEmpty()) && (apellido == null || apellido.isEmpty())) {
            q = s.createQuery("from Preceptor");

        } else if (nombre == null || nombre.isEmpty()) {
            q = s.createQuery("from Preceptor where apellido like :apellido");
            q.setParameter("apellido", apellido + "%");
        } else if (apellido == null || apellido.isEmpty()) {
            q = s.createQuery("from Preceptor where nombre like :nombre");
            q.setParameter("nombre", nombre + "%");
        } else {
            q = s.createQuery("from Preceptor where apellido like :apellido AND nombre like :nombre ");
            q.setParameter("nombre", nombre + "%");
            q.setParameter("apellido", apellido + "%");
        }

        return q.list();
    }

    /**
     *
     * @return
     */
    public static List<Preceptor> listarPreceptores() {
        Session s = getSession();
        Query q;
        q = s.createQuery("from Preceptor order by apellido, nombre");
        return q.list();
    }

    /**
     *
     * @param txt_dni_a
     * @return
     */
    public static Alumno listarPorDni(String txt_dni_a) {

        Session s = getSession();
        Alumno alum = s.get(Alumno.class, Integer.parseInt(txt_dni_a));
        return alum;
    }

    /**
     *
     * @param txt_dni_p
     * @return
     */
    public static Preceptor listarPreceptorPorDni(String txt_dni_p) {

        Session s = getSession();
        s.beginTransaction();

        Preceptor preceptor = s.get(Preceptor.class, Integer.parseInt(txt_dni_p));

        s.getTransaction().commit();
        //session.close();
        return preceptor;
    }

    /**
     *
     * @param txt_dni_r
     * @return
     */
    public static Responsable listarPorDni3(String txt_dni_r) {

        Session s = getSession();

        s.beginTransaction();

        Responsable responsable = s.get(Responsable.class, Integer.parseInt(txt_dni_r));

        s.getTransaction().commit();
        return responsable;
    }

    /**
     *
     * @param nombre
     * @param apellido
     * @return
     */
    public static List<Responsable> encontrarResponsable(String nombre, String apellido) {

        Session s = getSession();
        Query q;
        if ((nombre == null || nombre.isEmpty()) && (apellido == null || apellido.isEmpty())) {
            q = s.createQuery("from Responsable");

        } else if (nombre == null || nombre.isEmpty()) {
            q = s.createQuery("from Responsable where apellido like :apellido ");
            q.setParameter("apellido", apellido + "%");
        } else if (apellido == null || apellido.isEmpty()) {
            q = s.createQuery("from Responsable where nombre like :nombre ");
            q.setParameter("nombre", nombre + "%");
        } else {
            q = s.createQuery("from Responsable where apellido like :apellido AND nombre like :nombre ");
            q.setParameter("nombre", nombre + "%");
            q.setParameter("apellido", apellido + "%");
        }

        return q.list();
    }

    /**
     *
     * @return
     */
    public static List<Responsable> listarResponsables() {
        Session s = getSession();
        Query q;
        q = s.createQuery("from Responsable order by apellido, nombre");
        return q.list();
    }

    /**
     *
     * @return
     */
    public static List<Curso> listarCursos() {

        Session s = getSession();
        CriteriaQuery<Curso> q = s.getCriteriaBuilder().createQuery(Curso.class);
        q.select(q.from(Curso.class));
        return s.createQuery(q).list();
    }

    /**
     *
     * @return
     */
    public static List<Curso> listarCursosDisponibles() {
        Session s = getSession();
        Query query = s.createQuery("from Curso where preceptor_dni_p is NULL", Curso.class);
        return query.list();
    }

    /**
     *
     * @param dniResponsable
     * @return
     */
    public static Responsable listarResponsablePorDni(String dniResponsable) {

        Session s = getSession();
        Responsable resp = s.get(Responsable.class, Integer.parseInt(dniResponsable));
        return resp;
    }

    /**
     *
     * @param apellidoR
     * @param nombreR
     * @param dni_R
     * @param codAreaRespon
     * @param numTelRespon
     * @return
     */
    public static Responsable crearResponsable(String apellidoR, String nombreR, int dni_R, int codAreaRespon, int numTelRespon) {

        Responsable respon = null;
        Session s = getSession();
        try {
            respon = listarResponsablePorDni(String.valueOf(dni_R));
            if (respon == null) {
                s.beginTransaction();
                respon = new Responsable();
                respon.setApellido(apellidoR);
                respon.setNombre(nombreR);
                respon.setDni(dni_R);
                s.save(respon);

                Telefono t = new Telefono();
                Numero n = new Numero();
                n.setCodigoArea(codAreaRespon);
                n.setNroTelefono(numTelRespon);
                t.setNumero(n);
                t.setTipo(0);
                t.setResponsable(respon);
                s.save(t);
                s.getTransaction().commit();
                s.close();
            } else {
                modificarResponsable(apellidoR, nombreR, dni_R, codAreaRespon, numTelRespon);
            }
        } catch (Exception e) {
            System.out.println(e);
            s.getTransaction().rollback();
        }
        return respon;
    }

    /**
     *
     * @param s
     * @param apellidoR
     * @param nombreR
     * @param dni_R
     */
    private static void updateResponsable(Session s, String apellidoR, String nombreR, int dni_R) {
        Query query = s.createQuery("update Responsable set apellido = :apellido_resp, nombre = :nombre_resp where dni_r = :dni_R");
        query.setParameter("dni_R", dni_R);
        query.setParameter("apellido_resp", apellidoR);
        query.setParameter("nombre_resp", nombreR);
        query.executeUpdate();
    }

    /**
     *
     * @param apellidoR
     * @param nombreR
     * @param dni_R
     * @param codAreaRespon
     * @param numTelRespon
     */
    public static void modificarResponsable(String apellidoR, String nombreR, int dni_R, int codAreaRespon, int numTelRespon) {
        Session s = getSession();
        s.beginTransaction();
        try {
            updateResponsable(s, apellidoR, nombreR, dni_R);

            Query query = s.createQuery("update Telefono set cod_area = :cod_Area, nro_telefono = :num_Tel, tipo = :tipo where responsable_dni_r = :dni_R");
            query.setParameter("dni_R", dni_R);
            query.setParameter("cod_Area", codAreaRespon);
            query.setParameter("num_Tel", numTelRespon);
            query.setParameter("tipo", 0);
            query.executeUpdate();
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
        }
        s.close();
    }

    /**
     *
     * @param apellidoA
     * @param nombreA
     * @param dni_A
     * @param domicilioA
     * @param cur_SelecId
     * @param fechaNacimiento
     * @param sexoA
     * @param respon
     */
    public static void crearAlumno(String apellidoA, String nombreA, int dni_A, String domicilioA, int cur_SelecId, Date fechaNacimiento, String sexoA, Responsable respon) {

        Session s = getSession();
        s.beginTransaction();
        try {
            Alumno a = new Alumno();
            a.setApellido(apellidoA);
            a.setNombre(nombreA);
            a.setDni(dni_A);
            a.setDireccion(domicilioA);
            Curso c = new Curso();
            c.setIdcurso(cur_SelecId);
            a.setCurso(c);
            a.setResponsable(respon);
            a.setFechaNacimiento(fechaNacimiento);
            a.setSexo(sexoA);
            s.save(a);
            s.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            s.getTransaction().rollback();
        }
        s.close();
    }

    /**
     *
     * @param dni_a
     */
    public static void bajaAlumno(int dni_a) {
        Session s = getSession();
        s.beginTransaction();
        try {
            Query qu = s.createQuery("delete Alumno where dni_a = :dni_Borrar");
            qu.setParameter("dni_Borrar", dni_a);
            qu.executeUpdate();
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
        }
        s.close();
    }

    /**
     *
     * @param as
     * @return
     */
    public static int guardarAsistencia(Asistencia as) {

        Session s = getSession();
        try {
            s.beginTransaction();
            s.save(as);
            s.getTransaction().commit();
            s.refresh(as);
            return 0;
        } catch (Exception e) {
            s.getTransaction().rollback();

            return 1;
        }
    }

    /**
     *
     * @param p
     */
    public static void actualizarPreceptor(Preceptor p) {

        Session s = getSession();
        s.refresh(p);
    }

    /**
     *
     * @param asist
     * @return
     */
    public static Asistencia validarAsistencia(ClaveAsistencia asist) {

        Session s = getSession();
        Asistencia a = s.get(Asistencia.class, asist);

        return a;

    }

    /**
     *
     * @param dni_a
     * @param apellido
     * @param nombre
     * @param domicilio
     * @param fecha_nac
     * @param curso
     * @param genero
     */
    public static void modificarAlumno(Integer dni_a, String apellido, String nombre, String domicilio, Date fecha_nac, int curso, String genero) {
        Session s = getSession();
        s.beginTransaction();
        try {
            Query qu = s.createQuery("update Alumno set apellido = :apellido_a, nombre = :nombre_a, domicilio = :domicilio_a, curso_idcurso = :curso_a, fecha_nacimiento = :fecha_nac_a, sexo = :sexo_a where dni_a = :dni_alu");
            qu.setParameter("dni_alu", dni_a);
            qu.setParameter("apellido_a", apellido);
            qu.setParameter("nombre_a", nombre);
            qu.setParameter("domicilio_a", domicilio);
            qu.setParameter("curso_a", curso);
            qu.setParameter("fecha_nac_a", fecha_nac);
            qu.setParameter("sexo_a", genero);
            qu.executeUpdate();
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
        }
        s.close();
    }

    /**
     *
     * @return
     */
    public static Date getFechaInicio() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String stringInicio = "2018-06-13";
        Date inicio = null;
        try {
            inicio = sdf.parse(stringInicio);
        } catch (ParseException ex) {
        }
        return inicio;
    }

    /**
     *
     * @param turno
     * @param fecha
     * @return
     */
    public static List<Asistencia> obtenerAlumnosAsistencia(int turno, Date fecha) {

        Session s = getSession();
        Query q;
        q = s.createQuery("from Asistencia where turno = :turno and fecha = :fecha");

        q.setParameter("turno", turno);
        q.setParameter("fecha", fecha);

        return q.list();

    }

    /**
     *
     * @param dni
     * @param fechaIngresada
     * @param turno
     * @param nuevaAsistencia
     */
    public static void modificarAsistencia(int dni, Date fechaIngresada, int turno, int nuevaAsistencia) {

        Session s = getSession();
        s.beginTransaction();

        Query query = session.createQuery("update Asistencia set asistencia = :nuevaAsistencia where alumno_dni_a = :dni_A and turno = :turno and fecha = :fechaIngresada");
        query.setParameter("dni_A", dni);
        query.setParameter("nuevaAsistencia", nuevaAsistencia);
        query.setParameter("turno", turno);
        query.setParameter("fechaIngresada", fechaIngresada);

        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();

    }

    /**
     *
     * @param dni_p
     * @param nombre_p
     * @param apellido_p
     * @param domicilio_p
     * @param telefono_p
     * @param password
     * @param genero
     */
    public static void crearPreceptor(int dni_p, String nombre_p, String apellido_p, String domicilio_p, String telefono_p, String password, String genero) {
        Session s = getSession();
        s.beginTransaction();
        try {
            Preceptor p = new Preceptor();
            p.setApellido(apellido_p);
            p.setNombre(nombre_p);
            p.setDni(dni_p);
            p.setDireccion(domicilio_p);
            p.setTelefono(telefono_p);
            p.setContrasenia(password);
            p.setSexo(genero);
            s.save(p);
            s.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            s.getTransaction().rollback();
        }
        s.close();
    }

    /**
     *
     * @param dni_p
     * @param listCursos
     */
    public static void asignarCursoPreceptor(int dni_p, List<String> listCursos) {
        Session s = getSession();
        s.beginTransaction();
        try {
            for (String curso : listCursos) {
                String[] c = curso.split("°");
                c[0] = c[0].trim();
                c[1] = c[1].trim();
                Query qu = s.createQuery("update Curso set preceptor_dni_p = :dni_p where curso = :curso_c AND division = :division_c");
                qu.setParameter("dni_p", dni_p);
                qu.setParameter("curso_c", Integer.valueOf(c[0]));
                qu.setParameter("division_c", c[1]);
                qu.executeUpdate();
            }
            s.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            s.getTransaction().rollback();
        }
        s.close();
    }

    /**
     *
     * @param dni_p
     */
    public static void bajaPreceptor(Integer dni_p) {
        Session s = getSession();
        s.beginTransaction();
        try {
            Query qu = session.createQuery("delete Preceptor where dni_p = :dni_Borrar");
            qu.setParameter("dni_Borrar", dni_p);
            qu.executeUpdate();
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
        }
        s.close();
    }

    /**
     *
     * @param dni_p
     */
    public static void quitarCursoPreceptor(Integer dni_p) {
        Session s = getSession();
        s.beginTransaction();
        try {
            Query qu = s.createQuery("update Curso set preceptor_dni_p = null where preceptor_dni_p = :dni_p");
            qu.setParameter("dni_p", dni_p);
            qu.executeUpdate();
            s.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            s.getTransaction().rollback();
        }
        s.close();
    }

    /**
     *
     * @param dni_p
     * @param apellido_p
     * @param nombre_p
     * @param domicilio_p
     * @param telefono_p
     * @param genero
     * @param password
     */
    public static void modificarPreceptor(Integer dni_p, String apellido_p, String nombre_p, String domicilio_p, String telefono_p, String genero, String password) {
        Session s = getSession();
        s.beginTransaction();
        try {
            Query qu = s.createQuery("update Preceptor set apellido = :apellido_p, nombre = :nombre_p, domicilio = :domicilio_p, telefono = :telefono_p, sexo = :sexo_p, contrasenia = :contrasenia_p where dni_p = :dni_p");
            qu.setParameter("dni_p", dni_p);
            qu.setParameter("apellido_p", apellido_p);
            qu.setParameter("nombre_p", nombre_p);
            qu.setParameter("domicilio_p", domicilio_p);
            qu.setParameter("sexo_p", genero);
            qu.setParameter("contrasenia_p", password);
            qu.setParameter("telefono_p", telefono_p);

            qu.executeUpdate();
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
        }
        s.close();
    }

    /**
     *
     * @return
     */
    public static List<Calendario> getFeriados() {
        Session s = getSession();
        Query q;
        try {
            q = s.createQuery("from Calendario");
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
        return q.list();

    }

    /**
     *
     * @param dni_r
     */
    public static void bajaResponsable(Integer dni_r) {
        Session s = getSession();
        s.beginTransaction();
        try {
            Query qu = session.createQuery("delete Responsable where dni_r = :dni_Borrar");
            qu.setParameter("dni_Borrar", dni_r);
            qu.executeUpdate();
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
        }
        s.close();
    }

    /**
     *
     * @param dni_r
     * @param apellido
     * @param nombre
     */
    public static void actualizarResponsable(int dni_r, String apellido, String nombre) {
        Session s = getSession();
        s.beginTransaction();
        try {
            updateResponsable(s, apellido, nombre, dni_r);
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
        }
        s.close();
    }

    /**
     *
     * @param dni
     */
    public static void borrarTelefonoResponsable(String dni) {
        Session s = getSession();
        s.beginTransaction();
        try {
            Query qu = session.createQuery("delete Telefono where responsable_dni_r = :dni_Borrar");
            qu.setParameter("dni_Borrar", dni);
            qu.executeUpdate();
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
        }
        s.close();
    }

    /**
     *
     * @param t
     */
    public static void agregarTelefonoResponsable(Telefono t) {
        Session s = getSession();
        s.beginTransaction();
        try {
            s.save(t);
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
        }
        s.close();
    }

    /**
     *
     * @param respon
     */
    public static void crearResponsable(Responsable respon) {
        Session s = getSession();
        s.beginTransaction();
        try {
            s.save(respon);
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
        }
        s.close();
    }

    /**
     *
     * @param desde
     * @param hasta
     * @param descripcion
     *
     */
    public static void crearFeriado(Date desde, Date hasta, String descripcion) {
        Session s = getSession();
        s.beginTransaction();
        Calendario feriado = new Calendario();
        DiasNoHabiles dia = new DiasNoHabiles();

        try {

            feriado.setDescripcion(descripcion);
            dia.setFechaDesde(desde);
            dia.setFechaHasta(hasta);
            feriado.setDiasNoHabiles(dia);
            s.save(feriado);
            s.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            s.getTransaction().rollback();
        }
        s.close();
    }

    /**
     *
     * @param desde
     * @param hasta
     */
    public static void eliminarFeriado(Date desde, Date hasta) {
        Session s = getSession();
        s.beginTransaction();
        try {
            Query qu = s.createQuery("delete Calendario where fechaDesde = :desde AND fechaHasta = :hasta");
            qu.setParameter("desde", desde);
            qu.setParameter("hasta", hasta);
            qu.executeUpdate();
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
        }
        s.close();
    }

    /**
     *
     * @param cantAusencias
     * @param p
     * @return
     */
    public static List<Alumno> getAlumnosRecurrentes(int cantAusencias, Preceptor p) {
        Session s = getSession();
        List<Alumno> ausentes = new ArrayList<>();
        
        for (Curso c : p.getCursos()) {
            for (Alumno alumno : c.getAlumnos()) {
                if (inasistencia(String.valueOf(alumno.getDni()), cantAusencias)) {
                    ausentes.add(alumno);
             }
             }   
        }

        return ausentes;
    }

    /**
     *
     * @param dniAlumno
     * @param cantAusencias
     * @return
     */
    private static boolean inasistencia(String dniAlumno, int cantAusencias) {
        Query query;
        Session s = getSession();
        Calendar calendar = Calendar.getInstance(); //obtiene la fecha de hoy
        Date f1 = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int diasHabiles = 1;
        while (diasHabiles < cantAusencias) {
            calendar.add(Calendar.DATE, -1);
            Date f3 = calendar.getTime();

            query = session.createQuery(
                    "from Calendario where :fecha between fechaDesde and fechaHasta", Calendario.class);
            query.setParameter("fecha", dateFormat.format(f3));

            List<Calendario> listCalendario = query.list();

            if (listCalendario == null || listCalendario.isEmpty()) {
                diasHabiles = diasHabiles + 1;
            }
        }
        Date f2 = calendar.getTime();
        query = session.createQuery(
                "from Asistencia where alumno_dni_a= :dni_a and fecha between :fecha2 and :fecha1", Asistencia.class);
        query.setParameter("dni_a", dniAlumno);
        query.setParameter("fecha1", dateFormat.format(f1));
        query.setParameter("fecha2", dateFormat.format(f2));
        List<Asistencia> listAsistencia = query.list();
        
        if(listAsistencia == null || listAsistencia.isEmpty()){
            return false;
        }
        
        for (Asistencia a : listAsistencia) {
            if (a.getAsistencias() != 2) {
                return false;
            }

        }
        return true;
    }

}
