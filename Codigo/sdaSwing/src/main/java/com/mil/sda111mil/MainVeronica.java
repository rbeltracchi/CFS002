/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mil.sda111mil;

import com.mil.sda111mil.beans.Responsable;
import com.mil.sda111mil.beans.Alumno;
import com.mil.sda111mil.beans.Curso;
import com.mil.sda111mil.beans.Asistencia;
import com.mil.sda111mil.beans.Telefono;
import com.mil.sda111mil.beans.Telefono.Numero;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

/**
 *
 * @author Veronica
 */
public class MainVeronica {

    private static void listarAlumnosManana(SessionFactory sessionFactory) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CriteriaQuery<Alumno> q = session.getCriteriaBuilder().createQuery(Alumno.class);
        q.select(q.from(Alumno.class));

        List<Alumno> l = session.createQuery(q).list();
        System.out.println("Listado de Alumnos turno: Mañana");
        for (Alumno alumno : l) {
            //System.out.println(alumno.getApellido() + " " + alumno.getNombre());
            System.out.println(alumno.toString());
        }

        session.getTransaction().commit();
    }

    private static void listarTelefonos(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CriteriaQuery<Telefono> tel = session.getCriteriaBuilder().createQuery(Telefono.class);
        tel.select(tel.from(Telefono.class));
        List<Telefono> l = session.createQuery(tel).list();
        System.out.println("Lista Telefonos Responsables:");
        for (Telefono telefono : l) {
            System.out.println(" " + telefono.getResponsable() + telefono.getNumero());
        }

        session.getTransaction().commit();
    }

    private static void mostrarAsistencia(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CriteriaQuery<Asistencia> asistencia = session.getCriteriaBuilder().createQuery(Asistencia.class);
        asistencia.select(asistencia.from(Asistencia.class));
        List<Asistencia> l = session.createQuery(asistencia).list();
        System.out.println("Listado de Asistencia");
        for (Asistencia asistencia1 : l) {
            System.out.println(asistencia1.getClaveAsistencia());

        }
        session.getTransaction().commit();
    }

    private static void mostrarAlumnosA_Cargo(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CriteriaQuery<Alumno> aCargo = session.getCriteriaBuilder().createQuery(Alumno.class);
        aCargo.select(aCargo.from(Alumno.class));
        List<Alumno> l = session.createQuery(aCargo).list();
        System.out.println("Listado de Alumnos A Cargo:");
        for (Alumno alumno : l) {
            System.out.println(alumno.getResponsable() + " " + "Alumno a Cargo: " + alumno.getApellido() + " " + alumno.getNombre());
        }

        session.getTransaction().commit();
    }

    private static void listarCursos(SessionFactory sessionFactory) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CriteriaQuery<Curso> q = session.getCriteriaBuilder().createQuery(Curso.class);
        q.select(q.from(Curso.class));
        List<Curso> l = session.createQuery(q).list();

        for (Curso curso : l) {

            System.out.println("Curso: " + curso.getCurso() + " " + curso.getDivision());
        }

        session.getTransaction().commit();
    }

    private static Alumno buscarAlumno(SessionFactory sessionFactory) {
        String nombre;
        String apellido;
        int curso;
        String division;
        Alumno result = null;

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CriteriaQuery<Alumno> q = session.getCriteriaBuilder().createQuery(Alumno.class);
        q.select(q.from(Alumno.class));

        List<Alumno> alumnos = session.createQuery(q).list();

        System.out.println("Ingrese el Apellido del Alumno");
        Scanner scannerString = new Scanner(System.in);
        apellido = scannerString.nextLine();

        System.out.println("Ingrese el Nombre del Alumno");
        nombre = scannerString.nextLine();

        System.out.println("Ingrese el curso");
        Scanner scanner = new Scanner(System.in);
        curso = scanner.nextInt();

        System.out.println("Ingrese la Division");
        division = scannerString.nextLine();

        for (Alumno alumno : alumnos) {
            if (alumno != null && alumno.getApellido().equalsIgnoreCase(apellido) && alumno.getNombre().equals(nombre) && alumno.getCurso().getCurso() == curso && alumno.getCurso().getDivision().equals(division)) {
//          if ((alumno1 != null && alumno1.getApellido().equals(apellido)) && (alumno1.getNombre().equals(nombre)) && (alumno1.getCurso().getCurso()== Integer.parseInt(curso))) {                  
                result = alumno;
            }
        }

        return result;
    }

    private static void mostrarAlumno(Alumno alumno) {
        if (alumno != null) {
            System.out.println("Los datos del Alumno:");
            System.out.println(alumno.toString());
            System.out.println("Tabla Asistencia");
            System.out.println(" " + alumno.getAsistencia());
            System.out.println("Datos del Responsable");
            System.out.println(alumno.getResponsable());
        } else {
            System.out.println("Alumno Inválido");
        }
    }

    private static List<Alumno> buscarAlum(SessionFactory sessionFactory) {
        String nombre;
        String apellido;
        ArrayList<Alumno> aBuscado = new ArrayList<Alumno>();
        //List <Alumno> abuscado = null;

        Session session = sessionFactory.openSession();
        session.beginTransaction();

//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<Alumno> q = cb.createQuery(Alumno.class);
//        //CriteriaQuery<Alumno> q = session.getCriteriaBuilder().createQuery(Alumno.class);
//        q.select(q.from(Alumno.class));
//        q.orderBy(q.g;
//        
//        List<Alumno> alumnos = session.createQuery(q).list();
        Query q = session.createQuery("from Alumno order by apellido asc", Alumno.class);
        List<Alumno> alumnos = q.list();
        for (Alumno alumno : alumnos) {
            System.out.println(alumno.getApellido() + " " + alumno.getNombre());
        }
        System.out.println("Ingrese el Apellido del Alumno");
        Scanner scannerString = new Scanner(System.in);
        apellido = scannerString.nextLine();

        System.out.println("Ingrese el Nombre del Alumno");
        nombre = scannerString.nextLine();

        for (Alumno a : alumnos) {

            if (a != null && a.getApellido().equalsIgnoreCase(apellido) && a.getNombre().equalsIgnoreCase(nombre)) {

                aBuscado.add(a);
            }
        }
        session.close();
        System.out.println("Lista de Alumnos Buscado: ");
        System.out.println(aBuscado);
        return aBuscado;
    }

    private static void solicitar_DatoAlumno(SessionFactory sessionFactory) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String apellidoA, nombreA, domicilioA,curso,division, apellidoR, nombreR, domicilioR;
        int dni_A, dni_R, tipo, cod_Area, num_Tel;
       
        Scanner scanner = new Scanner(System.in);

        System.out.println("Agregando Alumno ");

        //Solicitamos la informacion del Alumno
        Scanner scannerString = new Scanner(System.in);
        System.out.println("Ingrese el Apellido");
        apellidoA = scannerString.nextLine();

        System.out.println("Ingrese el Nombre");
        nombreA = scannerString.nextLine();

        System.out.println("Ingrese el DNI");
        dni_A = scanner.nextInt();

        System.out.println("Ingrese el domicilio");
        domicilioA = scannerString.nextLine();

        System.out.println("Lista de curso: ");
        listarCursos(sessionFactory);

        System.out.println("Ingrese el curso");
        curso = scannerString.nextLine();

        System.out.println("Ingrese la division");
        division = scannerString.nextLine();
        
        Query query = session.createQuery("from Curso where curso = :CUR and division = :division", Curso.class);
        query.setParameter("CUR", Integer.parseInt(curso));
        query.setParameter("division", division);

        Curso c = (Curso) query.uniqueResult();
        System.out.println("El curso Seleccionado es: " + c);
                 
                
                

        //Solicitamos la informacion del Responsable del alumno
        System.out.println("Ingrese el Apellido del Responsable");
        apellidoR = scannerString.nextLine();

        System.out.println("Ingrese el Nombre del Responsable");
        nombreR = scannerString.nextLine();

        System.out.println("Ingrese el DNI del Responsable");
        dni_R = scanner.nextInt();

        System.out.println("Ingrese 1 si es Celular o 0 si es Fijo");
        tipo = scanner.nextInt();

        System.out.println("Ingrese el codigo de area sin el 0");
        cod_Area = scanner.nextInt();

        System.out.println("Ingrese el numero si es celular sin el 15");
        num_Tel = scanner.nextInt();
        
        Responsable respon = crearResponsable(sessionFactory,apellidoR,nombreR,dni_R);
        Alumno a = crearAlumno(sessionFactory,apellidoA, nombreA, dni_A, domicilioA,c,respon);
        
        
        session.save(a);
        session.getTransaction().commit();
        session.close();

    }

    private static Alumno crearAlumno(SessionFactory sessionFactory, String apellidoA, String nombreA, int dni_A, String domicilioA, Curso cur_Selec, Responsable respon) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Alumno a = new Alumno();
        a.setApellido(apellidoA);
        a.setNombre(nombreA);
        a.setDni(dni_A);
        a.setDireccion(apellidoA);
        a.setCurso(cur_Selec);
        a.setResponsable(respon);

        session.save(a);
        session.getTransaction().commit();
        return a;
    }

    private static void modificarAlumno(SessionFactory sessionFactory) {
        String nombre, apellido;
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Scanner scannerString = new Scanner(System.in);
        System.out.println("Ingrese el Apellido del Alumno");
        apellido = scannerString.nextLine();
        System.out.println("Ingrese el Nombre del Alumno");
        nombre = scannerString.nextLine();

        Query q = session.createQuery("from Alumno where apellido = :apellido and nombre = :nombre");
        int dni_A;
        List<Alumno> a = q.list();
        for (Alumno alumno : a) {
            System.out.println("Alumno: " + alumno.getApellido() + " " + alumno.getNombre() + " DNI: " + alumno.getDni());
        }
        System.out.println("Ingrese el DNI del Alumno Seleccionado");
        Scanner scanner = new Scanner(System.in);
        dni_A = scanner.nextInt();
        Query query = session.createQuery("from Alumno where id_dni_a = : dni_A");
        int num;
        do {
            System.out.println("Presione la opcion del dato que desea modificar: 1<Direccion>   2<Telefono del Responsable>");
            Scanner scannerInt = new Scanner(System.in);
            num = scannerInt.nextInt();

            switch (num) {
                case 1: {
                    modificar_direccion(sessionFactory,dni_A);
                    break;
                }
                case 2: {
                    //modificarTelefono(sessionFactory, p);
                    break;
                }
                case 3: {
                    //consultarAsistencia(sessionFactory);
                    break;
                }
                case 4: {
                    //metodoABM(sessionFactory);
                }
            }
        } while (num != 0);

    }
    
    private static void modificar_direccion(SessionFactory sessionFactory,int dni_A){
       
        String nuevaDireccion;
        System.out.println("Ingrese la nueva Direccion");
        Scanner scannerString = new Scanner(System.in);
        nuevaDireccion = scannerString.nextLine();
        
       
        Session session = sessionFactory.openSession();
        session.beginTransaction();
       
       
       Query query = session.createQuery("update Alumno set domicilio = :nuevaDireccion" + "where dni_a  = :dni_A");
       query.setParameter("dni_a", dni_A);
       query.setParameter("nuevaDireccion","Modificado");
       int result = query.executeUpdate();
       session.getTransaction().commit();
       session.close();
       
        
    }

    private static void bajaAlumno(SessionFactory sessionFactory) {
        String nombre, apellido;
        int dni;
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Scanner scannerString = new Scanner(System.in);
        System.out.println("Ingrese el Apellido del Alumno");
        apellido = scannerString.nextLine();
        System.out.println("Ingrese el Nombre del Alumno");
        nombre = scannerString.nextLine();

        Query q = session.createQuery("from Alumno where apellido = :apellido and nombre = :nombre");
        List<Alumno> a = q.list();
        for (Alumno alumno : a) {
            System.out.println("Alumno: " + alumno.getApellido() + " " + alumno.getNombre() + " DNI: " + alumno.getDni());
        }

        System.out.println("Ingrese el DNI del Alumno que desea dar de Baja");
        Scanner scanner = new Scanner(System.in);
        dni = scanner.nextInt();

        Query qu = session.createQuery("delete Alumno where dni_a = :dni_Borrar");
        qu.setParameter("dni_Borrar", dni);
        int result = qu.executeUpdate();
        session.getTransaction().commit();
    }

    private static Responsable crearResponsable(SessionFactory sessionFactory, String apellidoR, String nombreR, int dni_R) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Responsable respon = new Responsable();
        respon.setApellido(apellidoR);
        respon.setNombre(nombreR);
        respon.setDni(dni_R);

        session.save(respon);
        session.getTransaction().commit();
        session.close();
        return respon;
    }

    private static void crearTelefono(SessionFactory sessionFactory, int cod_Area, int num_Tel, int tipo, Responsable responsable) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Telefono tel = new Telefono(cod_Area, num_Tel, tipo, responsable);

        session.save(tel);
        session.getTransaction().commit();
        session.close();
    }

    private static void modificarDatos(SessionFactory sessionFactory, int dni_A) {
        String direccionNueva;
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Scanner scannerString = new Scanner(System.in);
        System.out.println("Ingrese la Nueva Direccion: ");
        direccionNueva = scannerString.nextLine();
        int i = dni_A;
        session.beginTransaction();
        System.out.println("Modificando direccion" + i);
        Query query = session.createQuery("update Alumno set direccion = :dni_A" + " where direccion = :nuevoDireccion");
        query.setParameter("dni_A", i);
        query.setParameter("nuevoDireccion", "Modificado");
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
       
   }
   
   
   

    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // obtiene los valores de hibernate.cfg.xml
                .build();
        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
        //Telefono telefono = new Telefono();
        listarTelefonos(sessionFactory);

        //Asistencia asistencia = new Asistencia();
        //mostrarAsistencia(sessionFactory);

        //Alumno aCargo = new Alumno();
        //mostrarAlumnosA_Cargo(sessionFactory);

        //Curso curso = new Curso();
        //listarCursos(sessionFactory);

        

        //Preceptor p = new Preceptor();
        //elegirCurso(p);
        
        //Buscamos un alumno
        //mostrarAlumno( buscarAlumno(sessionFactory) );
        //buscarAlum(sessionFactory);
        
        //agregarAlumno(sessionFactory);
        

    }
}
