/* Clase Asistencia: 
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
import java.util.Objects;

/**
 *
 * @author CF 02
 */
public class Asistencia {

    //serializable
    public static class ClaveAsistencia implements Serializable {

        //atributos
        private int turno;
        private Date fecha;
        private Alumno alumno;

        //constructores
        public ClaveAsistencia() {
        }

        public ClaveAsistencia(int turno, Date fecha, Alumno alumno) {
            this.turno = turno;
            this.fecha = fecha;
            this.alumno = alumno;
        }

        //setes
        public void setTurno(int turno) {
            this.turno = turno;
        }

        public void setFecha(Date fecha) {
            this.fecha = fecha;
        }

        public void setAlumno(Alumno alumno) {
            this.alumno = alumno;
        }

        //getes
        public int getTurno() {
            return turno;
        }

        public Date getFecha() {
            return fecha;
        }

        public Alumno getAlumno() {
            return alumno;
        }

        //sobreescritos
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final ClaveAsistencia other = (ClaveAsistencia) obj;
            if (this.turno != other.turno) {
                return false;
            }
            if (!Objects.equals(this.fecha, other.fecha)) {
                return false;
            }
            if (!Objects.equals(this.alumno, other.alumno)) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 37 * hash + Objects.hashCode(this.fecha);
            hash = 37 * hash + this.turno;
            hash = 37 * hash + Objects.hashCode(this.alumno);
            return hash;
        }

        @Override
        public String toString() {
            return "ClaveAsistencia{" + "turno=" + turno + ", fecha=" + fecha + "alumno" + alumno + "}";
        }
    }
    //atributos
    private ClaveAsistencia claveAsistencia;
    private int asistencias;

    //constructores
    public Asistencia() {
    }

    public Asistencia(ClaveAsistencia claveAsistencia, int asistencias) {
        this.claveAsistencia = claveAsistencia;
        this.asistencias = asistencias;
    }

    //setes
    public void setClaveAsistencia(ClaveAsistencia claveAsistencia) {
        this.claveAsistencia = claveAsistencia;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    //getes
    public ClaveAsistencia getClaveAsistencia() {
        return claveAsistencia;
    }

    public int getAsistencias() {
        return asistencias;
    }

    @Override
    public String toString() {
        return "{" + claveAsistencia.getFecha() + " -- " + claveAsistencia.getTurno() + "  " + " , asistencias=" + asistencias + '}';
    }

}
