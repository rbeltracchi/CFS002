/* Clase Responsable: 
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author CF 02
 */
public class Responsable extends Persona implements Serializable{
    //atributos
    private List<Telefono> telefonos;
    private List<Alumno> aCargo;

    //cosntructores
    public Responsable() {
        this.aCargo = new ArrayList<>();
        this.telefonos = new ArrayList<>();
    }

    public Responsable(List<Telefono> telefonos, List<Alumno> aCargo) {
        this.aCargo = new ArrayList<>();
        this.telefonos = new ArrayList<>();
        this.telefonos = telefonos;
        this.aCargo = aCargo;
    }

    public Responsable(List<Telefono> telefonos, List<Alumno> aCargo, String nombre, String apellido, int dni, String direccion, int telefono, String email, Date fechaNacimiento) {
        super(nombre, apellido, dni, direccion, email, fechaNacimiento);
        this.aCargo = new ArrayList<>();
        this.telefonos = new ArrayList<>();
        this.telefonos = telefonos;
        this.aCargo = aCargo;
    }

    //setes
    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public void setaCargo(List<Alumno> aCargo) {
        this.aCargo = aCargo;
    }

    //getes
    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public List<Alumno> getaCargo() {
        return aCargo;
    }
}