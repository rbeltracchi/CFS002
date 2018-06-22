/* Clase Preceptor: 
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
public class Preceptor extends Persona implements Serializable{
    //atributos
    private List <Curso> cursos = new ArrayList();
    private boolean activo;
    private int telefono;
    private String contrasenia;

    //constructores
    public Preceptor() {
        this.cursos = new ArrayList<>();
    }

    public Preceptor(List<Curso> cursos, boolean activo) {
        this.cursos = new ArrayList<>();
        this.cursos = cursos;
        this.activo = activo;
    }

    public Preceptor(String email, String contrasenia) { // para el loguin
        super(email);
        this.cursos = new ArrayList<>();
        this.contrasenia = contrasenia;
    }

    public Preceptor(List<Curso> cursos, boolean activo, String nombre, String apellido, int dni, String direccion, int telefono, String contrasenia, String email, Date fechaNacimiento) {
        super(nombre, apellido, dni, direccion,email, fechaNacimiento);
        this.cursos = new ArrayList<>();
        this.cursos = cursos;
        this.activo = activo;
    }

    //setes
    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    //getes
    public List<Curso> getCursos() {
        return cursos;
    }

    public boolean isActivo() {
        return activo;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getContrasenia() {
        return contrasenia;
    }   

    @Override
    public String toString() {
        return "Preceptor{" + "cursos=" + cursos + ", activo=" + activo + ", telefono=" + telefono + ", contrasenia=" + contrasenia + '}';
    }
    
    /**
     * Este método muestra los cursos de un preceptor.
     */
    public void mostrarCursos() {
        System.out.println("Los cursos asignados son:");
        for (Curso c : this.cursos) {  
            if (c != null) {  
                System.out.println(Integer.toString(c.getCurso()) + " - " + c.getDivision());
            }
        }
    }
}