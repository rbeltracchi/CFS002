/* Clase Horario: 
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

import java.io.Serializable;

/**
 *
 * @author CF 02
 */
public class Horario implements Serializable{

    //atributos
    private int idhorario;
    private int lunes;
    private int martes;
    private int miercoles;
    private int jueves;
    private int viernes;
    private Curso curso;

    //constructores
    public Horario() {
    }

    public Horario(int idhorario, int lunes, int martes, int miercoles, int jueves, int viernes, Curso curso) {
        this.idhorario = idhorario;
        this.lunes = lunes;
        this.martes = martes;
        this.miercoles = miercoles;
        this.jueves = jueves;
        this.viernes = viernes;
        this.curso = curso;
    }

    //setes
    public void setIdhorario(int idhorario) {
        this.idhorario = idhorario;
    }

    public void setLunes(int lunes) {
        this.lunes = lunes;
    }

    public void setMartes(int martes) {
        this.martes = martes;
    }

    public void setMiercoles(int miercoles) {
        this.miercoles = miercoles;
    }

    public void setJueves(int jueves) {
        this.jueves = jueves;
    }

    public void setViernes(int viernes) {
        this.viernes = viernes;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    //getes
    public int getIdhorario() {
        return idhorario;
    }

    public int getLunes() {
        return lunes;
    }

    public int getMartes() {
        return martes;
    }

    public int getMiercoles() {
        return miercoles;
    }

    public int getJueves() {
        return jueves;
    }

    public int getViernes() {
        return viernes;
    }

    public Curso getCurso() {
        return curso;
    }

    @Override
    public String toString() {
        return "Horario{" + "idhorario=" + idhorario + ", lunes=" + lunes + ", martes=" + martes + ", miercoles=" + miercoles + ", jueves=" + jueves + ", viernes=" + viernes + ", curso=" + curso + '}';
    }
    
    
}
