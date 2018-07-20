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

import com.mil.sda111mil.beans.Responsable;
import com.mil.sda111mil.beans.Preceptor;
import com.mil.sda111mil.beans.Alumno;
import com.mil.sda111mil.beans.Calendario;
import com.mil.sda111mil.beans.Asistencia;
import com.mil.sda111mil.beans.Curso;
import com.mil.sda111mil.beans.Asistencia.ClaveAsistencia;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

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
                    System.out.println("El alumno " + a.getApellido() + " ya registraba asistencia en la fe cha / turno");
                }
            }
        }
        session.close();
    }

    /**
     * Este método permite seleccionar la funcion que desea realizar en el menú consultar asistencia.
     * @param sessionFactory
     */
    private static void consultarAsistencia(SessionFactory sessionFactory) {

        int num;

        System.out.println("Presione la funcion a realizar: 1<Consultar Asistencia por Alumno>       2<Consultar Asistencia por Curso>      3<Consultar Asistencia por Faltas Consecutivas>      4<Consultar Asistencia por Fecha>");
        Scanner scannerInt = new Scanner(System.in);
        num = scannerInt.nextInt();

        switch (num) {
            case 1:
                asistenciaAlumno(sessionFactory);
                break;
            case 2:
                asistenciaCurso(sessionFactory);
                break;
            case 3:
                consultarFaltasConsecutivas(sessionFactory);
                break;

        }

    }

    /**
     * Este metodo muestra la asistencia total del alumno consultado
     * @param sessionFactory
     */
    private static void asistenciaAlumno(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println("Ingrese nombre del alumno");
        Scanner scannerString = new Scanner(System.in);
        String nombre;
        nombre = scannerString.nextLine();

        System.out.println("Ingrese el apellido del alumno");
        String apellido;
        apellido = scannerString.nextLine();

        Query q = session.createQuery("from Alumno where nombre = :nombre and apellido = :apellido", Alumno.class);
        q.setParameter("nombre", nombre);
        q.setParameter("apellido", apellido);
        List<Alumno> alumnos = q.list();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String stringInicio = "2018-06-13 00:00:00";
        String stringFin = "2018-06-22 00:00:00";
        Date fechaHoy = new Date();
        Date inicio;
        Date fin;
        try {
            inicio = sdf.parse(stringInicio);
            fin = sdf.parse(stringFin);

            for (Alumno alumno : alumnos) {

                System.out.println("Alumno: " + alumno.getApellido() + " " + alumno.getNombre() + " - DNI: " + alumno.getDni() + " - Curso: "
                        + alumno.getCurso().getCurso() + " " + alumno.getCurso().getDivision() + " - Faltas: " + alumno.getAsistenciasTotales(inicio, fechaHoy));
            }
        } catch (ParseException ex) {

        }
        session.close();
    }

    /**
     * Este método muestra la asistencia total de todos los alumnos de un curso.
     * @param sessionFactory
     */
    private static void asistenciaCurso(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println("Ingrese el curso");
        Scanner scannerString = new Scanner(System.in);
        String curso;
        curso = scannerString.nextLine();
        System.out.println("Ingrese la division");
        String division;
        division = scannerString.nextLine();

        Query q = session.createQuery("from Curso where curso = :CUR and division = :division", Curso.class);
        q.setParameter("CUR", Integer.parseInt(curso));
        q.setParameter("division", division);
//        List<Curso> c = q.list();
//        Curso cur = c.get(0);
        Curso c = (Curso) q.uniqueResult();

        for (Alumno alumno : c.getAlumnos()) {

            System.out.println("Alumno: " + alumno.getApellido() + " " + alumno.getNombre() + " - DNI: " + alumno.getDni() + " - Curso: "
                    + alumno.getCurso().getCurso() + " " + alumno.getCurso().getDivision() + " - Faltas: " + alumno.getAsistenciasTotales(null, null));

        }
        session.close();
    }
      private static void consultarFaltasConsecutivas(SessionFactory sessionFactory) {
          int cantAusencias = 3;
          System.out.println("Los alumnos que faltaron "+cantAusencias+" días seguidos o más son = ");
                           List<Alumno> listAlumno = listarAlumnosAusentes(cantAusencias, sessionFactory);
                           for(Alumno a : listAlumno){
                               System.out.println(a.getApellido() + " " + a.getNombre());
                           }
      }
        
    private static List<Alumno> consultarAlumnosLibres(SessionFactory sessionFactory, Preceptor p) {

       Session session = sessionFactory.openSession();
       session.beginTransaction();

       List<Alumno> alumnosLibres = new ArrayList<>();
       List<Alumno> alumnosCasiLibres = new ArrayList<>();

       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

       String stringInicio = "2018-06-13";      
       Date inicio = null;
       Date fin = new Date();

       try {
           inicio = sdf.parse(stringInicio);          
       } catch (ParseException ex) {

       }

       System.out.println("Los alumnos libres son:");
       for (Curso c : p.getCursos()) {
           if (c != null) {
               for (Alumno alumno : c.getAlumnos()) {
                   double libre = alumno.getAsistenciasTotales(inicio, fin);

                   if (libre > 9) {
                       alumnosLibres.add(alumno);
                       System.out.println("Alumnos Libres: ");
                       System.out.println("Alumno: " + alumno.getApellido() + " " + alumno.getNombre() + " - DNI: " + alumno.getDni() + " - Curso: "
                               + alumno.getCurso().getCurso() + " " + alumno.getCurso().getDivision() + " - Responsable: " + alumno.getResponsable().getApellido() + " - Telefono: "
                               + alumno.getResponsable().getTelefonos().get(0).getNumero() + " - Faltas: " + libre);
                   }
                   else if (libre >6 && libre <9){
                       
                       alumnosCasiLibres.add(alumno);
                       System.out.println("Alumnos que estan al limite de faltas: ");
                       System.out.println("Alumno: " + alumno.getApellido() + " " + alumno.getNombre() + " - DNI: " + alumno.getDni() + " - Curso: "
                               + alumno.getCurso().getCurso() + " " + alumno.getCurso().getDivision()
                               + " - Responsable: " + alumno.getResponsable().getApellido() + " - Telefono: "
                               + alumno.getResponsable().getTelefonos().get(0).getNumero() + " - Faltas: " + libre);
                   }
                         
               }
           }
       }

       session.close();
       return alumnosLibres;

   }
    private static boolean inasistencia(String dniAlumno, int cantAusencias, SessionFactory sessionFactory) {
       Query query;
       try (Session session = sessionFactory.openSession()) {
           session.beginTransaction();
           Calendar calendar =Calendar.getInstance(); //obtiene la fecha de hoy
           Date f1 = calendar.getTime();
           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
           int diasHabiles = 1;
           while (diasHabiles < cantAusencias) {
               calendar.add(Calendar.DATE, -1);
               Date f3 = calendar.getTime();
 
               query = session.createQuery(
                    "from Calendario where :fecha between fechaDesde and fechaHasta", Calendario.class);
              query.setParameter("fecha",dateFormat.format(f3));

              List<Calendario> listCalendario = query.list();
               
               if (listCalendario == null || listCalendario.isEmpty()) {
                   diasHabiles = diasHabiles + 1;
               }
           }
           Date f2 = calendar.getTime();
           query = session.createQuery(
                   "from Asistencia where alumno_dni_a= :dni_a and fecha between :fecha2 and :fecha1", Asistencia.class);
           query.setParameter("dni_a",dniAlumno);
           query.setParameter("fecha1",dateFormat.format(f1));
           query.setParameter("fecha2",dateFormat.format(f2));
           List<Asistencia> listAsistencia = query.list();
         
           for(Asistencia a : listAsistencia){
              if(a.getAsistencias()!=2){
                   return false;
               }
              
              session.close();
           }
           return true;
       }
       
   }
private static List<Alumno> listarAlumnosAusentes(int cantAusencias, SessionFactory sessionFactory){
       List<Alumno> ausentes = new ArrayList<>();
       try (Session session = sessionFactory.openSession()) {
           session.beginTransaction();
           CriteriaQuery<Alumno> q = session.getCriteriaBuilder().createQuery(Alumno.class);
           q.select(q.from(Alumno.class));

           List<Alumno> alumnos = session.createQuery(q).list();
           
           for(Alumno alumno : alumnos){
               if (inasistencia(String.valueOf(alumno.getDni()), cantAusencias, sessionFactory)){
                   ausentes.add(alumno);
               }
           }
         
           return ausentes;
       }
   }

    private static void metodoABM(SessionFactory sessionFactory) {
        int num;

        do {
            System.out.println("Presione la funcion a realizar: 1<Agregar Alumno>       2<Modificar Alumno>      3<Baja de Alumno>      4<Agregar Preceptor>   5<Modificar Preceptor>   6<Baja de Preceptor>");
        Scanner scannerInt = new Scanner(System.in);
        num = scannerInt.nextInt();
            switch (num) {
                case 1:
                    solicitar_DatoAlumno(sessionFactory);
                    break;
                case 2:
                    modificarAlumno(sessionFactory);
                    break;
                case 3:
                    bajaAlumno(sessionFactory);
                    break;
                case 4:
                    agregarPreceptor(sessionFactory);
                    break;
                case 6:
                    bajaPreceptor(sessionFactory);
                    break;
            }
            
        }while (num != 0);
    }
    
        private static void solicitar_DatoAlumno(SessionFactory sessionFactory) {
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
       
        String apellidoA,nombreA,domicilioA,curso,division,apellidoR,nombreR,domicilioR;
       int dni_A,dni_R,tipo,cod_Area,num_Tel;
       
       
       Scanner scanner = new Scanner(System.in);
             
       System.out.println("Agregando Alumno " );
     
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
        System.out.println("El Curso Seleccionado es: " + c);
       
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
       crearAlumno(sessionFactory,apellidoA,nombreA,dni_A,domicilioA,c,respon);
   }
   private static void crearAlumno(SessionFactory sessionFactory,String apellidoA,String nombreA,int dni_A,String domicilioA, Curso cur_Selec, Responsable respon){
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
       session.close();
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
   
   private static void modificarAlumno(SessionFactory sessionFactory) {
        String nombre, apellido;
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Scanner scannerString = new Scanner(System.in);
        System.out.println("Ingrese el Apellido del Alumno");
        apellido = scannerString.nextLine();
        System.out.println("Ingrese el Nombre del Alumno");
        nombre = scannerString.nextLine();

        Query q = session.createQuery("from Alumno where apellido = :apellido and nombre = :nombre" , Alumno.class);
        q.setParameter("apellido", apellido);
        q.setParameter("nombre", nombre);
        
        int dni_A;
        List<Alumno> a = q.list();
        for (Alumno alumno : a) {
           // Alumno alumno = new Alumno();
            System.out.println("Alumno: " + alumno.getApellido() + " " + alumno.getNombre() + " DNI: " + alumno.getDni());
        }
        System.out.println("Ingrese el DNI del Alumno Seleccionado");
        Scanner scanner = new Scanner(System.in);
        dni_A = scanner.nextInt();
        
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
                    //modificarTelefono_Responsable
                    break;
                }
            }
        } while (num != 0);

    }
    
    private static void modificar_direccion(SessionFactory sessionFactory,int dni_A){
       
        String nuevaDireccion;
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        System.out.println("Ingrese la nueva Direccion");
        Scanner scannerString = new Scanner(System.in);
        nuevaDireccion = scannerString.nextLine();
       
       Query query = session.createQuery("update Alumno set domicilio = :nuevaDireccion where dni_a = :dni_A");
       query.setParameter("dni_A", dni_A);
       query.setParameter("nuevaDireccion",nuevaDireccion);
       int result = query.executeUpdate();
       session.getTransaction().commit();
       session.close();
       
        
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
   
   private static void bajaAlumno(SessionFactory sessionFactory) {
        String nombre,apellido;
        int dni;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        Scanner scannerString = new Scanner(System.in);
        System.out.println("Ingrese el Apellido del Alumno");
        apellido = scannerString.nextLine();
        System.out.println("Ingrese el Nombre del Alumno");
        nombre = scannerString.nextLine();
        
        
       Query q = session.createQuery("from Alumno where apellido = :apellido and nombre = :nombre");
       q.setParameter("nombre",nombre);
       q.setParameter("apellido",apellido);
       List <Alumno> a = q.list();
       for (Alumno alumno : a) {
           System.out.println("Alumno: " + alumno.getApellido() + " " + alumno.getNombre() + " DNI: " + alumno.getDni());
       }
       
       System.out.println("Ingrese el DNI del Alumno que desea dar de Baja");
       Scanner scanner = new Scanner(System.in);
       dni = scanner.nextInt();
       
       Query qu = session.createQuery("delete Alumno where dni_a = :dni_Borrar");
       qu.setParameter("dni_Borrar",dni);
       int result = qu.executeUpdate();
       session.getTransaction().commit();
       System.out.println("Alumno Eliminado");
   }
   
   private static void bajaPreceptor(SessionFactory sessionFactory) {
      String nombre,apellido;
      int dni;
      Session session = sessionFactory.openSession();
      session.beginTransaction();
     
      Scanner scannerString = new Scanner(System.in);
      System.out.println("Ingrese el Apellido del Preceptor:");
      apellido = scannerString.nextLine();
      System.out.println("Ingrese el Nombre del Preceptor:");
      nombre = scannerString.nextLine();
     
     
     Query q = session.createQuery("from Preceptor where apellido = :apellido and nombre = :nombre");
     q.setParameter("nombre",nombre);
     q.setParameter("apellido",apellido);
     List <Preceptor> a = q.list();
     for (Preceptor preceptor : a) {
         System.out.println("Preceptor: " + preceptor.getApellido() + " " + preceptor.getNombre() + " DNI: " + preceptor.getDni());
     }
   
     System.out.println("Ingrese el DNI del Preceptor que desea dar de Baja");
     Scanner scanner = new Scanner(System.in);
     dni = scanner.nextInt();
   
     Query qu = session.createQuery("delete Preceptor where dni_p = :dni_Borrar");
     qu.setParameter("dni_Borrar",dni);
     int result = qu.executeUpdate();
     session.getTransaction().commit();
     System.out.println("Preceptor Eliminado correctamente.");
}
   
   private static void agregarPreceptor(SessionFactory sessionFactory) {

       Session session = sessionFactory.openSession();
       session.beginTransaction();

       String apellidoP, nombreP, domicilioP, contraseniaP, telefonoP;
       int dni_P ;

       Scanner scanner = new Scanner(System.in);

       System.out.println("Agregando Preceptor");

       //Solicitamos la informacion del Alumno
       Scanner scannerString = new Scanner(System.in);
       System.out.println("Ingrese el Apellido");
       apellidoP = scannerString.nextLine();

       System.out.println("Ingrese el Nombre");
       nombreP = scannerString.nextLine();

       System.out.println("Ingrese el DNI");
       dni_P = scanner.nextInt();

       System.out.println("Ingrese el domicilio");
       domicilioP = scannerString.nextLine();

       System.out.println("Ingrese el telefono");
       telefonoP = scanner.nextLine();

       System.out.println("Ingrese la contrasenia");
       contraseniaP = scannerString.nextLine();

       crearPreceptor(sessionFactory, apellidoP, nombreP, dni_P, domicilioP, telefonoP, contraseniaP);
   }

   private static void crearPreceptor(SessionFactory sessionFactory, String apellidoP, String nombreP, int dni_P, String domicilioP, String telefonoP, String contraseniaP) {
       Session session = sessionFactory.openSession();
       session.beginTransaction();

       Preceptor p = new Preceptor();

       p.setApellido(apellidoP);
       p.setNombre(nombreP);
       p.setDni(dni_P);
       p.setDireccion(domicilioP);
       p.setContrasenia(contraseniaP);
       p.setTelefono(telefonoP);

       session.save(p);
       session.getTransaction().commit();
       session.close();
   }
   
   
   private static void modificarAsistencia(SessionFactory sessionFactory){
       
       
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
                    consultarAsistencia(sessionFactory);
                    break;
                }
                case 4:{
                    metodoABM(sessionFactory);
                }
        }
    }while (num!=0);  
    System.out.println("TERMINO");

    }        
}