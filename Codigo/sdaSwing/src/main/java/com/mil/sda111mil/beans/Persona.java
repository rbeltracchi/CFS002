/* Clase Persona: 
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
import java.util.Date;

/**
 *
 * @author CF 02
 */
public class Persona implements Serializable {

    //atriutos
    protected String nombre;
    protected String apellido;
    protected int dni;
    protected String direccion;
    protected String email;
    protected Date fechaNacimiento;
    protected String sexo;

    //constructores
    public Persona() {
    }

    public Persona(String email) {
        this.email = email;
    }

    public Persona(String nombre, String apellido, int dni, String direccion, String email, Date fechaNacimiento, String sexo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccion = direccion;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
    }

    //setes
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }


    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    //getes
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getDni() {
        return dni;
    }

    public String getDireccion() {
        return direccion;
    }

    /**
     * 
     * @return Retorna email si ek campo esta completo,
     * sino retorna "No Posee".
     */
    public String getEmail() {
        return email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    
      public String getSexo() {
        return sexo;
    }

    @Override
    public String toString() {
        return "Nombre: " + this.getNombre()
                + " - Apellido: " + this.getApellido()
                + " - DNI: " + this.getDni()
                + " - Direccion: " + this.getDireccion()
                + " - Email: " + (this.getEmail()!=null?this.getEmail():"***")
                + " - Fecha Nac: " + (this.getFechaNacimiento()!=null?this.getFechaNacimiento():"**/**/**")
                + " - Sexo: " + this.getSexo();
    }
}
