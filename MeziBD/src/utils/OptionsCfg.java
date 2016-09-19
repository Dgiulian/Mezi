/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Diego
 */
public class OptionsCfg {
   
    
    public static final Integer MODULO_CLIENTE    = 1;
    public static final Integer MODULO_PROPIEDAD  = 2;
    public static final Integer MODULO_LOCALIDAD  = 3;
    public static final Integer MODULO_BARRIO     = 4;
    public static final Integer MODULO_CONTRATO   = 5;
    public static final Integer MODULO_SERVICIO   = 6;
    public static final Integer MODULO_VENDEDOR   = 7;
    public static final Integer MODULO_CUENTA     = 8;    
    public static final Integer MODULO_USUARIO    = 10;
    public static final Integer MODULO_CAJA       = 11;
    public static final Integer MODULO_CAJA_DETALLE           = 12;
    public static final Integer MODULO_CUENTA_INTERNA         = 13;
    public static final Integer MODULO_CUENTA_INTERNA_DETALLE = 14;
    
    
    public static final int ACCION_ALTA = 1;
    public static final int ACCION_BAJA = 2;
    public static final int ACCION_MODIFICAR = 3;
    
    public static final Integer PROPIEDAD_DISPONIBLE = 1;
    public static final Integer PROPIEDAD_ALQUILADA = 2;
    
    public static final int CLIENTE_TIPO_INQUILINO = 1;
    public static final int CLIENTE_TIPO_PROPIETARIO = 2;
    
    public static final int CUENTA_OFICIAL = 1;
    public static final int CUENTA_NO_OFICIAL = 2;
    
    public static final int CUENTA_VENDEDOR = 1;
    public static final int CUENTA_OTRO     = 2;
    
    
    public static final int CONCEPTO_ALQUILER         = 1;
    public static final int CONCEPTO_DOCUMENTO        = 2;
    public static final int CONCEPTO_GASTO            = 3;
    public static final int CONCEPTO_GASTO_ESCRIBANIA = 4;
    public static final int CONCEPTO_GASTO_SELLADO    = 5;
    public static final int CONCEPTO_GASTO_COMISION   = 6;
    
    public static final int CONCEPTO_PUNITORIO        = 7;
    public static final int CONCEPTO_AJUSTE           = 8;
    public static final int CONCEPTO_PAGO             = 9;
    public static final int CONCEPTO_SALDO            = 10;
    public static final int CONCEPTO_COMISION_ADMINISTRACION = 12;
    
    public static final int CONTRATO_ESTADO_ACTIVO    = 1;
    public static final int CONTRATO_ESTADO_FIN = 2;
   
    public static final String RECIBO_IMAGE = "fondo_recibo";
    public static final String CUENTA_IMAGE = "fondo_cuenta";
    
    public static final String RECIBO_PATH  = "recibo_path";
    public static final String CUENTA_PATH  = "cuenta_path";
    
    public static final int CAJA_ABIERTA = 1;
    public static final int CAJA_CERRADA = 2;
    
    public static final int FORMA_EFECTIVO      = 1;
    public static final int FORMA_CHEQUE        = 2;
    public static final int FORMA_TRANSFERENCIA = 3;

    
    public static final int TIPO_INGRESO     = 1;
    public static final int TIPO_EGRESO      = 2;
    
    public static final int GARANTIA_RECIBO     = 1;
    public static final int GARANTIA_ESCRITURA  = 2;
    
    public static ArrayList<Option> getPerfiles(){
        ArrayList<Option> lista = new ArrayList();
        lista.add(new Option(1,"ADM","Administrador"));
        lista.add(new Option(2,"GES","Gestor"));
        lista.add(new Option(3,"OPE","Operador"));                
        return lista;
    }
    public static HashMap<Integer,Option> getMapAcciones(){
        HashMap<Integer,Option> mapa = new HashMap();
        mapa.put(ACCION_ALTA,      new Option(ACCION_ALTA,String.format("%3d",ACCION_ALTA),"Alta"));
        mapa.put(ACCION_BAJA,      new Option(ACCION_BAJA,String.format("%3d",ACCION_BAJA),"Baja"));
        mapa.put(ACCION_MODIFICAR, new Option(ACCION_MODIFICAR,String.format("%3d",ACCION_MODIFICAR),"Modificar"));
        return mapa;
    }
    
//    public static HashMap<Integer,Option> getMapModulos(){
//        HashMap<Integer,Option> mapa = new HashMap();
//        mapa.put(MODULO_ACTIVO,         new Option(MODULO_ACTIVO,String.format("%3d",MODULO_ACTIVO),"Activo"));
//        mapa.put(MODULO_ACTIVOHISTORIA, new Option(MODULO_ACTIVOHISTORIA,String.format("%3d",MODULO_ACTIVOHISTORIA),"Activohistoria"));
//        mapa.put(MODULO_AUDITORIA,      new Option(MODULO_AUDITORIA,String.format("%3d",MODULO_AUDITORIA),"Auditoria"));
//        mapa.put(MODULO_CERTIFICADO,    new Option(MODULO_CERTIFICADO,String.format("%3d",MODULO_CERTIFICADO),"Certificado"));
//        mapa.put(MODULO_CLIENTE,        new Option(MODULO_CLIENTE,String.format("%3d",MODULO_CLIENTE),"Cliente"));
//        mapa.put(MODULO_COMPRA,         new Option(MODULO_COMPRA,String.format("%3d",MODULO_COMPRA),"Compra"));
//        mapa.put(MODULO_CONTRATO,       new Option(MODULO_CONTRATO,String.format("%3d",MODULO_CONTRATO),"Contrato"));
//        mapa.put(MODULO_FITERS,         new Option(MODULO_FITERS,String.format("%3d",MODULO_FITERS),"Fiters"));
//        mapa.put(MODULO_LOCALIDAD,      new Option(MODULO_LOCALIDAD,String.format("%3d",MODULO_LOCALIDAD),"Localidad"));
//        mapa.put(MODULO_PRETICKET,      new Option(MODULO_PRETICKET,String.format("%3d",MODULO_PRETICKET),"Preticket"));
//        mapa.put(MODULO_PROVEEDOR,      new Option(MODULO_PROVEEDOR,String.format("%3d",MODULO_PROVEEDOR),"Proveedor"));
//        mapa.put(MODULO_REMITO,         new Option(MODULO_REMITO,String.format("%3d",MODULO_REMITO),"Remito"));
//        mapa.put(MODULO_RUBRO,          new Option(MODULO_RUBRO,String.format("%3d",MODULO_RUBRO),"Rubro"));
//        mapa.put(MODULO_SITE,           new Option(MODULO_SITE,String.format("%3d",MODULO_SITE),"Site"));
//        mapa.put(MODULO_SUBRUBRO,       new Option(MODULO_SUBRUBRO,String.format("%3d",MODULO_SUBRUBRO),"Subrubro"));
//        mapa.put(MODULO_USUARIO,        new Option(MODULO_USUARIO,String.format("%3d",MODULO_USUARIO),"Usuario"));
//        mapa.put(MODULO_CONFIGURACION,  new Option(MODULO_CONFIGURACION,String.format("%3d",MODULO_CONFIGURACION),"Configuracion"));
//        mapa.put(MODULO_CORRECTIVO,  new Option(MODULO_CORRECTIVO,String.format("%3d",MODULO_CORRECTIVO),"Correctivo"));
//        return mapa;
//    }
    
   public static ArrayList<Option> getEstadosPropiedad(){
        ArrayList<Option> lista = new ArrayList();
        lista.add(new Option(PROPIEDAD_DISPONIBLE,"Disponible"));
        lista.add(new Option(PROPIEDAD_ALQUILADA, "Alquilada"));        
        return lista;
   }
   public static HashMap<Integer,Option> getMap(List<Option> lista){
        HashMap<Integer,Option> mapa = new HashMap<Integer,Option>();
        for(Option o:lista){
            mapa.put(o.getId(), o);
        }
        return mapa;
   } 
   public static ArrayList<Option> getEstadosContrato(){
        ArrayList<Option> lista = new ArrayList();
        lista.add(new Option(CONTRATO_ESTADO_ACTIVO,"Activo"));
        lista.add(new Option(CONTRATO_ESTADO_FIN, "Fin"));        
        return lista;
   }
   public static ArrayList<Option> getEstadosCaja(){
        ArrayList<Option> lista = new ArrayList();
        lista.add(new Option(CAJA_ABIERTA,"Abierta"));
        lista.add(new Option(CAJA_CERRADA, "Cerrada"));        
        return lista;
   }
    public static ArrayList<Option> getFormaPago(){
        ArrayList<Option> lista = new ArrayList();
        lista.add(new Option(FORMA_EFECTIVO,"Efectivo"));
        lista.add(new Option(FORMA_CHEQUE, "Cheque"));        
        lista.add(new Option(FORMA_TRANSFERENCIA, "Transferencia"));        
        return lista;
   }
      public static ArrayList<Option> getTipoMovimiento(){
        ArrayList<Option> lista = new ArrayList();
        lista.add(new Option(TIPO_INGRESO,"Ingreso"));
        lista.add(new Option(TIPO_EGRESO, "Egreso"));        
        
        return lista;
   }
      public static ArrayList<Option> getTiposCuentaInterna(){
        ArrayList<Option> lista = new ArrayList();
        lista.add(new Option(CUENTA_VENDEDOR,"Vendedor"));
        lista.add(new Option(CUENTA_OTRO, "Otros"));                
        return lista;
   }
    public static ArrayList<Option> getGarantias(){
        ArrayList<Option> lista = new ArrayList();
        lista.add(new Option(GARANTIA_RECIBO,"Recibo"));
        lista.add(new Option(GARANTIA_ESCRITURA, "Escritura"));        
        return lista;
   }
    public static class Option{
        Integer id ;
        String codigo;
        String descripcion;    
        public Option(Integer id,String descripcion){
            this.id = id;
            this.codigo = String.format("%03d",id);
            this.descripcion = descripcion;            
        }
        public Option(Integer id,String codigo,String descripcion) {
            this.id = id;
            this.codigo = codigo;
            this.descripcion = descripcion;
        }
        public Integer getId(){
            return this.id;
        }
        public String getCodigo(){
            return this.codigo;
        }
        public String getDescripcion(){
            return this.descripcion;
        }
                
    }
    
    static enum Tipo {CLIENTE,PROPIETARIO};
    public static void main(String[] args){
        
        Integer id_tipo = 1;
       
        switch(id_tipo){
            case OptionsCfg.CLIENTE_TIPO_INQUILINO: System.out.println("Tipo Inquilino"); break;
            case OptionsCfg.CLIENTE_TIPO_PROPIETARIO: System.out.println("Tipo Propietario"); break;
        }
    }
}
