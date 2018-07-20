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
package com.mil.sda111mil.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author CF 02
 */
public class Alumno extends Persona implements Serializable {

    //atributos
    private List<Asistencia> asistencia;
    private String padreMadreTutor;
    private Curso curso;
    private Responsable responsable;

    //constructores
    public Alumno() {
        this.asistencia = new ArrayList<>();
    }

    public Alumno(List<Asistencia> asistencia, String padreMadreTutor) {//para el login
        this.asistencia = new ArrayList<>();
        this.asistencia = asistencia;
        this.padreMadreTutor = padreMadreTutor;
    }

    public Alumno(List<Asistencia> asistencia, String padreMadreTutor, Curso curso, Responsable responsable, String nombre, String apellido, int dni, String direccion, String email, Date fechaNacimiento, String sexo) {
        super(nombre, apellido, dni, direccion, email, fechaNacimiento, sexo);
        this.asistencia = new ArrayList<>();
        this.asistencia = asistencia;
        this.padreMadreTutor = padreMadreTutor;
        this.curso = curso;
        this.responsable = responsable;
    }

    //setes
    public void setAsistencia(List<Asistencia> asistencia) {
        this.asistencia = asistencia;
    }

    public void setPadreMadreTutor(String padreMadreTutor) {
        this.padreMadreTutor = padreMadreTutor;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    //getes
    public List<Asistencia> getAsistencia() {
        return asistencia;
    }

    public String getPadreMadreTutor() {
        return padreMadreTutor;
    }

    public Curso getCurso() {
        return curso;
    }

    public Responsable getResponsable() {
        return responsable;
    }
 
    @Override
    public String toString() {
        return super.toString()
                + " - Responsable: " + this.getResponsable().getApellido() + ", " + this.getResponsable().getNombre()
                + " - Curso: " + this.getCurso().getCurso() + "º" + this.getCurso().getDivision();
                //+ " - Asistencias: " ;
    }
    
    /**
     * Este método calcula la asistencia total de los alumnos.
     * 0 = presente
     * 1 = tarde (1/2 falta)
     * 2 = ausente (1 falta)
     * @param inicio
     * @param fin
     * @return asistencia total
     */
    public double getAsistenciasTotales(Date inicio, Date fin) {

        double asistenciaTotal = 0;
        for (Asistencia asistencias : this.getAsistencia()) {
            Date d = asistencias.getClaveAsistencia().getFecha();
          
            
            if ((inicio != null && fin != null && ((d.after(inicio) && d.before(fin))
                    || d.getTime()==inicio.getTime() || d.getTime() == fin.getTime())) || (inicio == null || fin == null)) {
                    
            if (asistencias.getAsistencias() == 0) {
                asistenciaTotal = asistenciaTotal + 0;
            }
            if (asistencias.getAsistencias() == 1) {
                asistenciaTotal = asistenciaTotal + 0.5;
            }
            if (asistencias.getAsistencias() == 2) {
                asistenciaTotal = asistenciaTotal + 1;
            }
            }
        }
        
        return asistenciaTotal;

    }    
       
    

    
}
