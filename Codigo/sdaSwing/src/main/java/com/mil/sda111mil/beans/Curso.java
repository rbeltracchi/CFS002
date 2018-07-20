/* Clase Curso: 
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
import java.util.List;

/**
 *
 * @author CF 02
 */
public class Curso implements Serializable{
    //atributos
    private int idcurso;
    private int curso;
    private String division;
    private int turno;
    private List<Alumno> alumnos;
    private Preceptor preceptor;
    private Horario idHorario;

    //constructores
    public Curso() {
        this.alumnos = new ArrayList<>();
    }

    public Curso(int curso, String division, int turno, List<Alumno> alumnos, Preceptor preceptor, Horario idHorario) {
        this.alumnos = new ArrayList<>();
        this.curso = curso;
        this.division = division;
        this.turno = turno;
        this.alumnos = alumnos;
        this.preceptor = preceptor;
        this.idHorario= idHorario;
    }   

    //setes
    public void setIdcurso(int idcurso) {
        this.idcurso = idcurso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public void setPreceptor(Preceptor preceptor) {
        this.preceptor = preceptor;       
    }

    public void setIdHorario(Horario idHorario) {
        this.idHorario = idHorario;
    }
    
    

    //getes
    public int getIdcurso() {
        return idcurso;
    }

    public int getCurso() {
        return curso;
    }

    public String getDivision() {
        return division;
    }

    public int getTurno() {
        return turno;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public Preceptor getPreceptor() {
        return preceptor;
    }

    @Override
    public String toString() {
        return String.valueOf(this.getCurso()) + "º " + this.getDivision();
    }

    public Horario getIdHorario() {
        return idHorario;
    }
    
    

}
