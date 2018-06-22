/* Clase Calendario: 
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
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author CF 02
 */
public class Calendario {

    //serializable
    static class DiasNoHabiles implements Serializable {

        //atributos
        private Date fechaDesde;
        private Date fechaHasta;

        //constructores
        public DiasNoHabiles() {
        }

        public DiasNoHabiles(Date fechaDesde, Date fechaHasta) {
            this.fechaDesde = fechaDesde;
            this.fechaHasta = fechaHasta;
        }

        //setes
        public void setFechaDesde(Date fechaDesde) {
            this.fechaDesde = fechaDesde;
        }

        public void setFechaHasta(Date fechaHasta) {
            this.fechaHasta = fechaHasta;
        }

        //getes
        public Date getFechaDesde() {
            return fechaDesde;
        }

        public Date getFechaHasta() {
            return fechaHasta;
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
            final DiasNoHabiles other = (DiasNoHabiles) obj;
            if (this.fechaDesde != other.fechaDesde) {
                return false;
            }
            if (this.fechaHasta != other.fechaHasta) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 83 * hash + Objects.hashCode(this.fechaDesde);
            hash = 83 * hash + Objects.hashCode(this.fechaHasta);
            return hash;
        }

        @Override
        public String toString() {
            return "DiasNoHabiles{" + "fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + '}';
        }
    }
    //atributos
    private DiasNoHabiles diasNoHabiles;
    private String descripcion;

    //construcores
    public Calendario() {
    }

    public Calendario(DiasNoHabiles diasNoHabiles, String descripcion) {
        this.diasNoHabiles = diasNoHabiles;
        this.descripcion = descripcion;
    }

    //setes
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDiasNoHabiles(DiasNoHabiles diasNoHabiles) {
        this.diasNoHabiles = diasNoHabiles;
    }

    //getes
    public DiasNoHabiles getDiasNoHabiles() {
        return diasNoHabiles;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "Calendario{" + "diasNoHabiles=" + diasNoHabiles + ", descripcion=" + descripcion + '}';
    }
    
}
