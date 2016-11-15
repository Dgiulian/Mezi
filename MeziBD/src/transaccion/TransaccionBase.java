/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package transaccion;

import conexion.Conexion;
import conexion.TransaccionRS;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public abstract class TransaccionBase<E> {
    protected String orderBy = " ";
    protected Integer numResults = 25;

    
    protected Conexion conexion;
    Class<E> clase;

    public TransaccionBase() {
        this.clase = clase;
        this.clase = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        conexion = new Conexion();
    }

    public int alta(Object obj) {
        int id = 0;
        id = new TransaccionRS().altaObjetoAutonumerico(obj);
        return id;
    }

    public boolean baja(Object obj) {
        boolean todoOk = false;
        todoOk = new TransaccionRS().eliminarObjeto(obj);
        return todoOk;
    }

    public boolean actualizar(Object obj, String campo_id) {
        return new TransaccionRS().actualizarObjeto(obj, campo_id);
    }

    public List<E> getList(String query) {
        List<E> lista = null;
        try {            
            conexion.conectarse();
            ResultSet rs = conexion.ejecutarSQLSelect(query);
            lista = new TransaccionRS().recuperarLista(this.clase.getCanonicalName(), rs);
            rs.close();            
        } catch (SQLException ex) {
            Logger.getLogger(TransaccionBase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.desconectarse();
            return lista;
        }
    }
    
    public abstract List<E> getList() ;
    
    public List<E> getListFilter(Object object, String extendSQL) {
        String sql = new TransaccionRS().recuperarListaDefault(object, extendSQL);
        if (sql != null && !sql.equalsIgnoreCase("")) {
            return this.getList(sql);
        }
        return null;
    }

    public E getById(String query) {
        E obj = null;
        conexion.conectarse();
        ResultSet rs = conexion.ejecutarSQLSelect(query);
        TransaccionRS t = new TransaccionRS();
        List listaUsuario = t.recuperarLista(this.clase.getCanonicalName(), rs);
        conexion.desconectarse();

        if (listaUsuario != null && listaUsuario.size() > 0) {
            obj = (E) listaUsuario.get(0);
        }
        return obj;
    }

    public E recuperarInstancia(Map<String, String[]> map) {
        TransaccionRS t = new TransaccionRS();
        Object object = t.altaObjeto(this.clase.getCanonicalName(), map);
        return (E) object;
    }
    public List<E> getListFiltro(Map<String,String> filtro){
        return this.getListFiltro(filtro,0);
    }
    private String getWhere(Map<String,String> filtro){
        String where =  " where True ";
        try {
            Class claseGenerada = Class.forName(this.clase.getCanonicalName().trim());
            Object objeto = claseGenerada.newInstance();
            
            for (String key : filtro.keySet()) {
                Object value = filtro.get(key);
                try{
                    Field campo = null;
                    try{
                        campo = claseGenerada.getDeclaredField(key);
                    } catch(NoSuchFieldException ex){
                        campo = claseGenerada.getField(key);
                    }
                    //getDeclaredField(key);
                    Class<?> type = campo.getType();

                if (type.isAssignableFrom(String.class)){
//                    where += String.format(" and %s = '%s'",key,value) ;
                    where += String.format(" and lower(%s) like lower('%%%s%%')",key,value) ;
                }else if (type.isAssignableFrom(Integer.class)){
                    if (value != null)
                        where += String.format(" and %s = %s",key,value) ;
                };
                } catch (NoSuchFieldException ex) {
                    //Si no existe el campo ignoramos la excepción
                     Logger.getLogger(this.clase.getCanonicalName()).log(Level.SEVERE, null, ex);
                }
                catch (SecurityException ex) {
                    Logger.getLogger(this.clase.getCanonicalName()).log(Level.SEVERE, null, ex);
                }
            }            
        }catch (InstantiationException ex) {
            Logger.getLogger(this.clase.getCanonicalName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(this.clase.getCanonicalName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(this.clase.getCanonicalName()).log(Level.SEVERE, null, ex);
        }
        return where;
    }
    private String getTabla(){
        return this.clase.getCanonicalName().replace(this.clase.getPackage().getName()+".", "").toLowerCase();
    }
    private String getLimit(Integer pagNro,Integer num_results){
        String limit = "";
        if (pagNro > 0) {
            pagNro = pagNro - 1;
            limit = String.format(" limit %d,%d ",num_results * pagNro,num_results);
        }
        return limit;
    }
    
    public List<E> getListFiltro(Map<String,String> filtro,Integer pagNro){
        String order = this.getOrderBy();
        String where = this.getWhere(filtro);
        String tabla = this.getTabla();        
        String limit = this.getLimit(pagNro,this.getNumResults());        
        String query = "select * from " + tabla  + " " + where + " " + order + " " + limit;        
        System.out.println(query);
        return this.getList(query);
    }
    public Integer getListFiltroCount(Map<String,String> filtro){        
        String where = this.getWhere(filtro);
        String tabla = this.getTabla();                
        String query = "select count(*) from " + tabla  + " " + where;
        System.out.println(query);
        
        
        
        return this.getInteger(query);
    }
    protected String getByDateRangeQuery(String fecha_desde,String hora_desde,String fecha_hasta, String hora_hasta){
        return "";
    };
    
    public List<E> getByDateRange(String fecha_desde,String hora_desde,String fecha_hasta, String hora_hasta){
        if (hora_desde == null) hora_desde = "00:00:00";
        if (hora_hasta == null) hora_hasta = "24:59:59";
        String query = this.getByDateRangeQuery(fecha_desde, hora_desde, fecha_hasta, hora_hasta);
        System.out.println(query);
        return this.getList(query);
    }
    /*
     ** Devuelve el criterio de ordenamiento de la tabla
     ** Cada clase que herede puede implementar este método 
     */
    public String getOrderBy(){
        if (this.orderBy.trim().equals("")) return "";
        else return " order by " + this.orderBy;
    }
    public TransaccionBase setOrderBy(String orderBy){
        this.orderBy = orderBy;
        return this;
    }
     
    
    public HashMap<Integer,E> getMap(){        
        HashMap<Integer,E> mapa = new HashMap<Integer,E>();   
        try {
            Method getterId = this.clase.getMethod("getId");
            Object id;
            List<E> list = this.getList();
            if (list==null) return mapa;
            for (E e : list) {
                id = getterId.invoke(e, new Object[0]);
                mapa.put(Integer.valueOf(String.valueOf(id)),e);
            }
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TransaccionBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TransaccionBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(TransaccionBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(TransaccionBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(TransaccionBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return mapa;        
    }
    public String auditar(Object objeto){
        Field[] atributos = objeto.getClass().getFields();
        StringBuilder query = new StringBuilder();
        
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
                Method getter = objeto.getClass().getMethod("get" + getNombre);
                if (tipo.equals("String") == true) {
                    try {
                        Object valor = getter.invoke(objeto, new Object[0]);
//                        query.append("'" + valor + "'");
                        query.append("\"").append(valor).append("\"");
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
                        Object valor = getter.invoke(objeto, new Object[0]);
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
    public Integer getInteger(String query) {
        Integer result = null;
        try {            
            conexion.conectarse();
            ResultSet rs = conexion.ejecutarSQLSelect(query);
            if(rs!=null){
                while(rs.next()){
                    result = rs.getInt(1);
                    break;
                }
            }
            rs.close();            
        } catch (SQLException ex) {
            Logger.getLogger(TransaccionBase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.desconectarse();
            return result;
        }
    }
    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }
}
