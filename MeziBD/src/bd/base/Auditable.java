/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.base;

import conexion.TransaccionRS;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class Auditable {
    public String auditar(){
        Field[] atributos = this.getClass().getFields();
        StringBuffer query = new StringBuffer();
        
        for (int i = 0; i <= atributos.length - 1; i++) {
            Field field = atributos[i];
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) continue;
            query.append(field.getName());
            query.append(": ");
            try {
                Class tipoClass = atributos[i].getType();
                String tipo = tipoClass.getSimpleName();
                String getNombre = atributos[i].getName();
                getNombre = getNombre.substring(0, 1).toUpperCase() + getNombre.substring(1, getNombre.length());
                Method getter = this.getClass().getMethod("get" + getNombre);
                if (tipo.equals("String") == true) {
                    try {
                        Object valor = getter.invoke(this, new Object[0]);
                        query.append("'" + valor + "'");
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(TransaccionRS.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(TransaccionRS.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvocationTargetException ex) {
                        Logger.getLogger(TransaccionRS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (tipo.equals("Integer") == true || tipo.equals("int") == true
                        || tipo.equals("Float") == true || tipo.equals("float") == true
                        || tipo.equals("Double") == true || tipo.equals("double") == true) {
                    try {
                        Object valor = getter.invoke(this, new Object[0]);
                        query.append(valor);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(TransaccionRS.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(TransaccionRS.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvocationTargetException ex) {
                        Logger.getLogger(TransaccionRS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (i != atributos.length - 1) {
                    query.append("|");
                }
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(TransaccionRS.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(TransaccionRS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }                
        return query.toString();        
    }
}
