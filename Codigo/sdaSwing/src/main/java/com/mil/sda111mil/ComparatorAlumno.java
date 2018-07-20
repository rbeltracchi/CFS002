/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mil.sda111mil;

import com.mil.sda111mil.beans.Alumno;
import java.util.Comparator;

/**
 *
 * @author usuario
 */
public class ComparatorAlumno implements Comparator<Alumno> {
    @Override
    public int compare(Alumno a, Alumno b) {
       
        if (a.getApellido().compareTo(b.getApellido())<0)
            return -1;
        else  if (a.getApellido().compareTo(b.getApellido())>0)
            return 1; 
         if (a.getNombre().compareTo(b.getNombre())<0)
            return -1;
        else  if (a.getNombre().compareTo(b.getNombre())>0)
            return 1;        
        return 0;
         
    }
}
    
