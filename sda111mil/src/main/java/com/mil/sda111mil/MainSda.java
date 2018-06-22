 /* Clase Alumno: 
 * 
 * Programa:     Sistema de Asistencias
 * Autores:      Ricardo Alvarez
 *               Argüello, Iván
 *      	 Bersano, Verónica
 * 		 Bonello, Erardo
 * 		 Perez, José
 * 		 Rubino, Aldana 
 * 		 Santos Wagner, Karina
 * 		 Simaro, Federico
 * Url Trabajo:             
 * Escrito:      21 de Junio de 2018
 * Compilador:   JDK 1.8.0
 * Plataforma:   Windows 10 x64
 * Descripción del Programa: El programa permite tomar asistencia de los cursos de un establecimiento educativo.
 *  
 */

package com.mil.sda111mil;

import com.mil.sda111mil.Asistencia.ClaveAsistencia;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author CF 02
 */
public class MainSda {
    
    /**
     * El método solicita DNI y contraseña al usuario y lo válida.
     * @param sessionFactory
     * @return el preceptor logueado o nulo si no es válido.
     */
    private static Preceptor ingresarUsuario(SessionFactory sessionFactory) {

        Preceptor p = null;

        while (p == null) {
            System.out.println("Ingrese su DNI:");
            Scanner scanner = new Scanner(System.in);
            String dni = scanner.nextLine();

            System.out.println("Ingrese su Contraseña:");
            String contrasena = scanner.nextLine();

            p = validarContrasena(sessionFactory, dni, contrasena);
           
            if (p == null) {
                System.out.println("Su Usuario es Incorrecto");

            } else {
                System.out.println("Login Exitoso");
                return p;
            }
        }
        return null;
    }
    
    /**
     * Este método verifica en la base de datos que se encuentre el precptor con el DNI y la contraseña ingresados.
     * @param sessionFactory
     * @param dni
     * @param contrasena
     * @return el preceptor.
     */
    private static Preceptor validarContrasena(SessionFactory sessionFactory, String dni, String contrasena) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        /*Query q = session.createQuery("from Preceptor where dni= :dni and contasenia= :contrasena",Preceptor.class);
       q.setParameter("dni", dni);
       q.setParameter("contasena",contrasena);
       return 
         */
        Preceptor p = session.get(Preceptor.class, Integer.parseInt(dni));
        if (p != null && p.getContrasenia().equals(contrasena)) {
            return p;
        } else {
            return null;
        }
       
    }
    
    /**
     * Este método permite buscar alumnos a través del nombre y apellido ingresados.
     * @param sessionFactory
     * @return una lista de alumnos que cumplan con lo ingresado.
     */
    private static List<Alumno> buscarAlumno(SessionFactory sessionFactory){
        String nombre;
        String apellido;
        ArrayList <Alumno> aBuscado = new ArrayList <Alumno>();
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CriteriaQuery<Alumno> q = session.getCriteriaBuilder().createQuery(Alumno.class);
        q.select(q.from(Alumno.class));

        List<Alumno> alumnos = session.createQuery(q).list();

        System.out.println("Ingrese el Apellido del Alumno");
        Scanner scannerString = new Scanner(System.in);
        apellido  = scannerString.nextLine();

        System.out.println("Ingrese el Nombre del Alumno");
        nombre = scannerString.nextLine();

        for (Alumno a : alumnos) {
            
            if (a != null && a.getApellido().equalsIgnoreCase(apellido)&& a.getNombre().equalsIgnoreCase(nombre)){
                
                aBuscado.add(a);
            }
        }
        
        System.out.println("Lista de Alumnos Buscado: \n" + " " + aBuscado);
        //System.out.println(aBuscado);
        session.close();
        return aBuscado;
        
    }

    /**
     * Este método permiter tomar la asistencia de los alumnos por cursos y la almacena en la base de datos.
     * El scanner solicita que se ingrese el turno en que se va a tomar la asistencia:
     * 0 = turno mañana
     * 1 = contraturno tarde
     * @param sessionFactory
     * @param p (preceptor)
     */
    private static void tomarAsistencia(SessionFactory sessionFactory, Preceptor p) {
        p.mostrarCursos();
        Curso c = elegirCurso(p);
        Date fecha = new Date();
                
        System.out.println("Ingrese el turno en que va a tomar asistencia");
        int turno;
        Scanner scannerString= new Scanner(System.in);
        turno= scannerString.nextInt();
        
        System.out.println(c);
        guardarAsistencia(sessionFactory, p, c, fecha, turno);
    }

    /**
     * Este método permite al preceptor elegir el curso al que va a tomar asistencia.
     * @param p (preceptor)
     * @return
     */
    private static Curso elegirCurso(Preceptor p) {
        Curso cursoSeleccionado = null;
        String curso;
        System.out.println("Ingrese el curso");
        Scanner scannerString = new Scanner(System.in);
        curso = scannerString.nextLine();
        System.out.println("Ingrese la division");
        String division;
        division = scannerString.nextLine();
        

        for (Curso curso1 : p.getCursos()) {
            if ((curso1 != null && curso1.getCurso() == Integer.parseInt(curso)) && (curso1.getDivision().equals(division))) {
                cursoSeleccionado = curso1;

                if (cursoSeleccionado == null) {
                    System.out.println("Curso inexistente");
                } 
            }

        }        
        return cursoSeleccionado;
    }
    
    /**
     *
     * Este método guarda la asistencia en la base de datos a medida que la misma se va realizando.
     * @param sessionFactory
     * @param p (preceptor)
     * @param c (curso)
     * @param fecha
     * @param turno
     */
    private static void guardarAsistencia(SessionFactory sessionFactory, Preceptor p, Curso c, Date fecha, int turno) {
        Session session = sessionFactory.openSession();
        
        for (Alumno a : c.getAlumnos()) {
            if (a != null) {
                System.out.println(a.getApellido() + " " + a.getNombre());
                Scanner scanner = new Scanner(System.in);
                int asistencia = scanner.nextInt();

                ClaveAsistencia asist = new ClaveAsistencia();
                asist.setAlumno(a);
                asist.setFecha(fecha);
                asist.setTurno(turno);

                Asistencia as = new Asistencia();
                as.setAsistencias(asistencia);
                as.setClaveAsistencia(asist);
                
                try {
                session.beginTransaction();
                session.save(as);
                session.getTransaction().commit();
                } catch (Exception e) {
                    System.out.println("El alumno " + a.getApellido() + " ya registraba asistencia en la fecha / turno");
                }
            }
        }
        session.close();
    }

    /* ----------------------------------------------*/

    /**
     * Método main del SDA.
     * @param args
     */

    public static void main(String[] args) {

        //establece la conexion local
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // obtiene los valores de hibernate.cfg.xml
                .build();
        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();      
        
        int num;
        
        Preceptor p = ingresarUsuario(sessionFactory);

        do {
            System.out.println("Presione la funcion a realizar: 1<Buscar Alumno>       2<Tomar Asistencia>      3<Consultar Asistencia-pendiente>      4<ABM-pendiente>");
            Scanner scannerInt = new Scanner(System.in);
            num = scannerInt.nextInt();
            
            switch (num) {
                case 1:{
                    buscarAlumno(sessionFactory);
                    break;
                }
                case 2:{
                    tomarAsistencia(sessionFactory, p);
                    break;
                }
                case 3:{
                    //consultarAsistencia(sessionFactory);
                    break;
                }
                case 4:{
                    //metodoAbm(sessionFactory)
                }
        }
    }while (num!=0);  
    System.out.println("TERMINO");

    }        
}