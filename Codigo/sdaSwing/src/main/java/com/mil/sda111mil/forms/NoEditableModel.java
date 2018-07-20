/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mil.sda111mil.forms;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CF02
 */
public class NoEditableModel extends DefaultTableModel {

    NoEditableModel(Object object, String[] titulos) {
        super(null,titulos);
    }
    
    @Override
    public boolean isCellEditable (int row, int column)
   {
       return false;
   }
}
