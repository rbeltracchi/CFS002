/* Clase Telefono: 
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

/**
 *
 * @author CF 02
 */
public class Telefono {

    //serializable
    public static class Numero implements Serializable {
        //atributos
        private int codigoArea;
        private int nroTelefono;

        //constructores
        public Numero() {
        }

        public Numero(int codigoArea, int nroTelefono) {
            this.codigoArea = codigoArea;
            this.nroTelefono = nroTelefono;
        }
        
        //setes
        public void setCodigoArea(int codigoArea) {
            this.codigoArea = codigoArea;
        }

        public void setNroTelefono(int nroTelefono) {
            this.nroTelefono = nroTelefono;
        }

        //getes
        public int getCodigoArea() {
            return codigoArea;
        }

        public int getNroTelefono() {
            return nroTelefono;
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
            final Numero other = (Numero) obj;
            if (this.codigoArea != other.codigoArea) {
                return false;
            }
            if (this.nroTelefono != other.nroTelefono) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 29 * hash + this.codigoArea;
            hash = 29 * hash + this.nroTelefono;
            return hash;
        }

        @Override
        public String toString() {
            return "("+codigoArea +") " + nroTelefono;
        }
    }
    //atributos
    private Numero numero;
    private int tipo;
    private Responsable responsable;

    //constructores
    public Telefono() {
    }

    public Telefono(Numero numero, int tipo, Responsable responsable) {
        this.numero = numero;
        this.tipo = tipo;
        this.responsable = responsable;
    }
    
    public Telefono(int codigoArea, int nroTelefono,int tipo, Responsable responsable){
        this.tipo = tipo;
        this.responsable = responsable;
        Numero num = new Numero();
        num.setCodigoArea(codigoArea);
        num.setNroTelefono(nroTelefono);
        this.setNumero(num);
        this.setResponsable(responsable);
    }

    //setes
    public void setNumero(Numero numero) {
        this.numero = numero;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }
    
    //getes
    public Numero getNumero() {
        return numero;
    }

    public int getTipo() {
        return tipo;
    }

    public Responsable getResponsable() {
        return responsable;
    }
}
