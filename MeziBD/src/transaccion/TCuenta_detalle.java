package transaccion;

import bd.Contrato;
import bd.Contrato_documento;
import bd.Contrato_gasto;
import bd.Contrato_valor;
import bd.Cuenta;
import bd.Cuenta_detalle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import java.util.List;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import utils.OptionsCfg;
import utils.TFecha;

public class TCuenta_detalle extends TransaccionBase<Cuenta_detalle> {
        
	@Override
	public List<Cuenta_detalle> getList() {
		return super.getList("select * from cuenta_detalle ");
	}

	public Boolean actualizar(Cuenta_detalle cuenta_detalle) {
		return super.actualizar(cuenta_detalle, "id");
	}

	public Cuenta_detalle getById(Integer id) {
		String query = String.format(
				"select * from cuenta_detalle where cuenta_detalle.id = %d ",
				id);
		return super.getById(query);
	}
        public List<Cuenta_detalle> getById_cuenta(Integer id_cuenta){
            String query = String.format("select * from cuenta_detalle where cuenta_detalle.id_cuenta = %d",id_cuenta);
            query += this.getOrderBy();
            return super.getList(query);
        }
        
        
public List<Cuenta_detalle> crearDetalleInquilino(Contrato contrato){
    ArrayList<Cuenta_detalle> lstDetalle = new ArrayList<Cuenta_detalle>();
    String  fecha            = contrato.getComision_desde_inquilino();
    Float   gasto_escribania = contrato.getGastos_escribania_inquilino();
    Float   gasto_sellado    = contrato.getGastos_sellado_inquilino();
    Float   comision_monto   = contrato.getComision_monto_inquilino();
    Integer comision_cuotas  = contrato.getComision_cuotas_inquilino();
    
    if(gasto_escribania>0f)
        lstDetalle.add(detalleEscribania(gasto_escribania,fecha));
    if(gasto_sellado>0f){
        lstDetalle.add(detalleSellado(gasto_sellado,fecha));
    }
    if(comision_monto>0f){
       lstDetalle.addAll(detalleComision(comision_monto,comision_cuotas,fecha));
    }
    Integer cuota = contrato.getLlave_cuotas();
    if (cuota<=0) cuota = 1;
    if(contrato.getLlave_monto() > 0) {
        Float monto = contrato.getLlave_monto() / cuota ;
        LocalDate llave_desde = new LocalDate(contrato.getLlave_desde());
        for(int i = 1;i<=cuota;i++){
            Cuenta_detalle cd = new Cuenta_detalle();
            String concepto = "Fondo entrega llaves" ;
            if(cuota>1) concepto += String.format(" cuota %d",i);
            cd.setConcepto(concepto);
            cd.setDebe(monto);
            cd.setFecha(TFecha.formatearFecha(llave_desde.toDate(), TFecha.formatoBD));
            cd.setId_concepto(OptionsCfg.CONCEPTO_FONDO_ENTREGA_LLAVES);
            cd.setId_referencia(i);
            llave_desde = llave_desde.plusMonths(1).withDayOfMonth(1);
            lstDetalle.add(cd);
        }
    }
    return lstDetalle;
}
public List<Cuenta_detalle> crearDetallePropietario(Contrato contrato){
    ArrayList<Cuenta_detalle> lstDetalle = new ArrayList<Cuenta_detalle>();
    String  fecha            = contrato.getComision_desde_propietario();
    Float   gasto_escribania = contrato.getGastos_escribania_propietario();
    Float   gasto_sellado    = contrato.getGastos_sellado_propietario();
    Float   comision_monto   = contrato.getComision_monto_propietario();
    Integer comision_cuotas  = contrato.getComision_cuotas_propietario();
    
    if(gasto_escribania>0f)
        lstDetalle.add(detalleEscribania(gasto_escribania,fecha));
    if(gasto_sellado>0f){
        lstDetalle.add(detalleSellado(gasto_sellado,fecha));
    }
    if(comision_monto>0f){
       lstDetalle.addAll(detalleComision(comision_monto,comision_cuotas,fecha));
    }
    return lstDetalle;
}


public List<Cuenta_detalle> crearDetalle(Contrato contrato,Contrato_valor[] lstValores,Integer id_tipo){
    ArrayList<Cuenta_detalle> lstDetalle = new ArrayList<Cuenta_detalle>();
    int i = 0;
    for(Contrato_valor valor:lstValores){        
        LocalDate desde = new LocalDate(valor.getDesde());
        LocalDate hasta = new LocalDate(valor.getHasta());        
        Integer mes_desde = desde.getMonthOfYear();
        Integer mes_hasta = hasta.getMonthOfYear();

        for(LocalDate fecha = desde;fecha.isBefore(hasta);fecha = fecha.plusMonths(1).withDayOfMonth(1)){
            Cuenta_detalle cd = new Cuenta_detalle();
            //LocalDate fecha = desde.plusMonths(i).withDayOfMonth(1);
            i+=1;
            Float monto = valor.getMonto();
            String concepto = String.format("Mes Alquiler %s",OptionsCfg.MESES[fecha.getMonthOfYear() - 1]);
            
            cd.setConcepto(concepto);
            cd.setId_concepto(OptionsCfg.CONCEPTO_ALQUILER);
            
            if (id_tipo== OptionsCfg.CLIENTE_TIPO_PROPIETARIO) cd.setHaber(monto);
            else if (id_tipo== OptionsCfg.CLIENTE_TIPO_INQUILINO) cd.setDebe(monto);            
            cd.setId_referencia(i);
            cd.setFecha(TFecha.formatearFecha(fecha.toDate(), TFecha.formatoBD));
            lstDetalle.add(cd);
             
             if (id_tipo== OptionsCfg.CLIENTE_TIPO_PROPIETARIO) {
             Cuenta_detalle cd_adm = new Cuenta_detalle(); 
              cd_adm.setConcepto("Comisión administración");
              cd_adm.setId_concepto(OptionsCfg.CONCEPTO_COMISION_ADMINISTRACION);
              cd_adm.setDebe(monto * contrato.getComision_mensual_propietario() / 100); // <--- Ver esto
              cd_adm.setId_referencia(i);
              cd_adm.setFecha(TFecha.formatearFecha(fecha.toDate(), TFecha.formatoBD));
              lstDetalle.add(cd_adm);
             }
            
            //else if (id_tipo== OptionsCfg.CLIENTE_TIPO_INQUILINO) cd.setDebe(monto);
            
        }
        System.out.println(" ");
    }
    return lstDetalle;
}
public List<Cuenta_detalle> crearDetalle(Contrato_documento[] lstDocum,Integer id_tipo){
    ArrayList<Cuenta_detalle> lstDetalle = new ArrayList<Cuenta_detalle>();
    for(Contrato_documento docum:lstDocum){
        LocalDate desde = new LocalDate(docum.getDesde());
        LocalDate hasta = new LocalDate(docum.getHasta());
        Integer mes_desde = desde.getMonthOfYear();
        Integer mes_hasta = hasta.getMonthOfYear();
        int i=0;
        for(LocalDate fecha = desde;fecha.isBefore(hasta);fecha = fecha.plusMonths(1).withDayOfMonth(1)){
            Cuenta_detalle cd = new Cuenta_detalle();
//            LocalDate fecha = desde.plusMonths(i).withDayOfMonth(1);
            Float monto = docum.getMonto();

            String concepto = String.format("Documento mes %s",fecha.getMonthOfYear());
            cd.setConcepto(concepto);
            cd.setId_concepto(OptionsCfg.CONCEPTO_DOCUMENTO);
            cd.setId_referencia(++i);
            if (id_tipo== OptionsCfg.CLIENTE_TIPO_PROPIETARIO) cd.setHaber(monto);
            else if (id_tipo== OptionsCfg.CLIENTE_TIPO_INQUILINO) cd.setDebe(monto);
            cd.setFecha(TFecha.formatearFecha(fecha.toDate(), TFecha.formatoBD));
            lstDetalle.add(cd);            
        }
    }
    return lstDetalle;
}
public List<Cuenta_detalle> crearDetalle(Contrato_gasto[] lstGasto, String desde){
    ArrayList<Cuenta_detalle> lstDetalle = new ArrayList<Cuenta_detalle>();
    for(Contrato_gasto gasto:lstGasto){
        Integer cuota = gasto.getCuotas();
        if (cuota<=0) cuota = 1;
        Float monto = gasto.getImporte() / cuota ;
        LocalDate fecha = new LocalDate(desde);
        for(int i = 1;i<=cuota;i++){
            Cuenta_detalle cd = new Cuenta_detalle();
            String concepto = gasto.getConcepto() ;
            if(cuota>1) concepto += String.format(" Cuota %d",i);
            cd.setConcepto(concepto);
            cd.setDebe(monto);
            cd.setId_referencia(i);
            cd.setFecha(TFecha.formatearFecha(fecha.toDate(), TFecha.formatoBD));
            cd.setId_concepto(OptionsCfg.CONCEPTO_GASTO);
            fecha = fecha.plusMonths(1).withDayOfMonth(1);
            lstDetalle.add(cd);
        }            
    }
    return lstDetalle;
}

public Cuenta_detalle detalleEscribania(Float monto,String fecha){
    Cuenta_detalle d_escribania = new Cuenta_detalle();
    d_escribania.setConcepto("Gastos escribanía");
    d_escribania.setDebe(monto);
    d_escribania.setFecha(fecha);
    d_escribania.setId_concepto(OptionsCfg.CONCEPTO_GASTO_ESCRIBANIA);;
    return d_escribania;
}
public Cuenta_detalle detalleSellado(Float monto,String fecha){
    Cuenta_detalle d_sellado = new Cuenta_detalle();
    d_sellado.setConcepto("Gastos sellado");
    d_sellado.setDebe(monto);
    d_sellado.setFecha(fecha);
    d_sellado.setId_concepto(OptionsCfg.CONCEPTO_GASTO_SELLADO);
    return d_sellado;
}
public List<Cuenta_detalle> detalleComision(Float monto,Integer cuotas,String desde){
    ArrayList<Cuenta_detalle> lstDetalle = new ArrayList<Cuenta_detalle>();    
        LocalDate fecha = new LocalDate(desde);
        for(int i=1;i<=cuotas;i++){
            Cuenta_detalle d_comision = new Cuenta_detalle();            
            d_comision.setConcepto(String.format("Comisión cuota %s",i));
            d_comision.setDebe( monto / cuotas);
            d_comision.setFecha(TFecha.formatearFecha(fecha.toDate(), TFecha.formatoBD));
            d_comision.setId_concepto(OptionsCfg.CONCEPTO_GASTO_COMISION);
            d_comision.setId_referencia(i);
            fecha = fecha.plusMonths(1).withDayOfMonth(1);            
            lstDetalle.add(d_comision);
        }
        return lstDetalle;
}

 private static ArrayList<Contrato_valor> getValores(){
     ArrayList lstValor =  new ArrayList<Contrato_valor>();
    Contrato_valor  valor = new Contrato_valor();
    valor.setDesde("2016-01-01");
    valor.setHasta("2016-06-30");
    valor.setMonto(1000f);
            
    lstValor.add(valor);
            
    valor = new Contrato_valor();
    valor.setDesde("2016-07-01");
    valor.setHasta("2016-12-31");
    valor.setMonto(1200f);
    lstValor.add(valor);
    return lstValor;
 }
  private static ArrayList<Contrato_documento> getDocumentos(){
     ArrayList lstValor =  new ArrayList<Contrato_documento>();
    Contrato_documento  documento = new Contrato_documento();
    documento.setDesde("2016-01-01");
    documento.setHasta("2016-06-30");
    documento.setMonto(500f);
            
    lstValor.add(documento);
            
    documento = new Contrato_documento();
    documento.setDesde("2016-07-01");
    documento.setHasta("2016-12-31");
    documento.setMonto(700f);
    lstValor.add(documento);
    return lstValor;
 }
  private static ArrayList<Contrato_gasto> getGastos(){
     ArrayList lstValor =  new ArrayList<Contrato_gasto>();
    Contrato_gasto  gasto = new Contrato_gasto();
    gasto.setConcepto("Concepto XX");
    gasto.setImporte(400f);            
    lstValor.add(gasto);
//    gasto = new Contrato_gasto();

//    gasto.setImporte(700f);
//    lstValor.add(gasto);
    return lstValor;
 }
  
  
  
private static Contrato getContrato(){
      Contrato contrato = new Contrato();
      contrato.setGastos_escribania_inquilino(150f);
      contrato.setGastos_escribania_propietario(200f);
      contrato.setGastos_sellado_inquilino(150f);
      contrato.setGastos_sellado_propietario(200f);
      
      
      contrato.setDeposito_monto(1500f);
      contrato.setDeposito_cuotas(3);
      contrato.setDeposito_desde("2016-01-01");
      
      contrato.setComision_monto_inquilino(1500f);
      contrato.setComision_cuotas_inquilino(3);
      contrato.setComision_desde_inquilino("2016-01-01");

      
      contrato.setComision_monto_propietario(1500f);
      contrato.setComision_cuotas_propietario(3);
      contrato.setComision_desde_propietario("2016-01-01");
      
      contrato.setAsegura_renta(1);
      contrato.setFecha_inicio("2016-01-01");
      contrato.setFecha_fin("2016-12-31");
      contrato.setMeses(12);
      
      contrato.setNumero(666);      
      return contrato;
  }
  public List<Cuenta_detalle> detalleInquilino(Contrato contrato, List<Contrato_valor> lstValor,  List<Contrato_gasto> lstGasto){
       ArrayList<Cuenta_detalle> lstDetalle = new ArrayList<Cuenta_detalle>();
       lstDetalle.addAll(this.crearDetalle(contrato,lstValor.toArray(new Contrato_valor[lstValor.size()]),OptionsCfg.CLIENTE_TIPO_INQUILINO));
       lstDetalle.addAll(this.crearDetalleInquilino(contrato));
       //lstDetalle.addAll(this.crearDetalle(lstDocum.toArray(new Contrato_documento[lstDocum.size()]), OptionsCfg.CLIENTE_TIPO_INQUILINO));
       lstDetalle.addAll(this.crearDetalle(lstGasto.toArray(new Contrato_gasto[lstGasto.size()]),contrato.getFecha_inicio()));
       return lstDetalle;
  }
  public List<Cuenta_detalle> detalleInquilino( List<Contrato_documento> lstDocum){
       ArrayList<Cuenta_detalle> lstDetalle = new ArrayList<Cuenta_detalle>();
       lstDetalle.addAll(this.crearDetalle(lstDocum.toArray(new Contrato_documento[lstDocum.size()]),OptionsCfg.CLIENTE_TIPO_INQUILINO));
       return lstDetalle;
  }
  public List<Cuenta_detalle> detallePropietario(Contrato contrato,List<Contrato_valor> lstValor, List<Contrato_gasto> lstGasto){
       ArrayList<Cuenta_detalle> lstDetalle = new ArrayList<Cuenta_detalle>();
       lstDetalle.addAll(this.crearDetalle(contrato,lstValor.toArray(new Contrato_valor[lstValor.size()]),OptionsCfg.CLIENTE_TIPO_PROPIETARIO));
       lstDetalle.addAll(this.crearDetalle(lstGasto.toArray(new Contrato_gasto[lstGasto.size()]),contrato.getFecha_inicio()));
       lstDetalle.addAll(this.crearDetallePropietario(contrato));
       return lstDetalle;
  }
  public List<Cuenta_detalle> detallePropietario( List<Contrato_documento> lstDocum){
       ArrayList<Cuenta_detalle> lstDetalle = new ArrayList<Cuenta_detalle>();
       lstDetalle.addAll(this.crearDetalle(lstDocum.toArray(new Contrato_documento[lstDocum.size()]),OptionsCfg.CLIENTE_TIPO_PROPIETARIO));
       return lstDetalle;
  } Contrato                      contrato = getContrato();
//            ArrayList<Contrato_valor>     lstValor = getValores();
//            ArrayList<Contrato_documento> lstDocum = getDocumentos();
//            ArrayList<Contrato_gasto>     lstGasto = getGastos();
//            TCuenta_detalle tcd = new TCuenta_detalle();
//            ArrayList<Cuenta_detalle> lstDetalle = new ArrayList<Cuenta_detalle>();
//            
//            lstDetalle = (ArrayList<Cuenta_detalle>) tcd.detalleInquilino(contrato, lstValor, lstDocum, lstGasto);
//            
//            lstDetalle.addAll(tcd.crearDetallePropietario(contrato));
//            
//            for(Cuenta_detalle d:lstDetalle){
//                System.out.print(d.getFecha());
//                System.out.print(" ");
//                System.out.print(d.getConcepto());
//                System.out.print(" ");
//                System.out.print(d.getDebe());
//                System.out.print(" ");
//                System.out.print(d.getHaber());
//                System.out.println("");
//                
//            }
      public boolean puedeAjustar(Cuenta cuenta, String fecha) {
        String query = String.format("select *\n" +
            "  from cuenta_detalle\n" +
            " where cuenta_detalle.id_cuenta = %d\n" +
            "   and cuenta_detalle.id_concepto = %d\n" +
            "   and cuenta_detalle.fecha >= '%s' ",cuenta.getId(),OptionsCfg.CONCEPTO_PAGO,fecha);
        return this.getList(query).isEmpty();
    }
      public Cuenta_detalle calcularPunitorio(Cuenta_detalle cd,Integer punitorio_desde,Float porc){
          Cuenta_detalle punitorio = null;
          if(cd==null) return null;
          LocalDate fecha = new LocalDate(cd.getFecha());
          LocalDate today = new LocalDate();
          if (!cd.getId_concepto().equals(OptionsCfg.CONCEPTO_ALQUILER) &&
              !cd.getId_concepto().equals(OptionsCfg.CONCEPTO_DOCUMENTO)) return null;
          
          int days = Days.daysBetween(fecha, today).getDays() - 1;
          if (days >=punitorio_desde) {
            float monto_punitorio = (float)Math.floor((double)days * porc * cd.getDebe()) ;
            punitorio = new Cuenta_detalle();
            punitorio.setFecha(cd.getFecha());
            punitorio.setConcepto(String.format("Punitorio %s (%d dias)",OptionsCfg.MESES[fecha.getMonthOfYear() - 1],days));
            punitorio.setId_concepto(OptionsCfg.CONCEPTO_PUNITORIO);
            punitorio.setDebe(monto_punitorio);
            }
          return punitorio;
      }
      public List<Cuenta_detalle> calcularPunitorios(Cuenta cuenta, Contrato contrato, LocalDate fecha_desde, LocalDate fecha_hasta){
        // No se considera punitorios para los propietarios
        if (cuenta.getId_tipo_cliente().equals(OptionsCfg.CLIENTE_TIPO_PROPIETARIO)) return new ArrayList<Cuenta_detalle>();
        
        Integer punitorio_desde = contrato.getPunitorio_desde();
        Float punitorio_porc    = contrato.getPunitorio_monto() / 100;
        LocalDate fecha_inicio  = new LocalDate(contrato.getFecha_inicio());
        
        List<Cuenta_detalle> listaDetalle = new ArrayList<Cuenta_detalle>();
         
        List<Cuenta_detalle> lista = this.getByRangoFecha(cuenta,fecha_desde,fecha_hasta);

        LocalDate today = new LocalDate();
        LocalDate fecha_liquidacion = new LocalDate(cuenta.getFecha_liquidacion());

        for(Cuenta_detalle cd:lista) {
              LocalDate fecha = new LocalDate(cd.getFecha());              
              /* fecha.isEqual(fecha_inicio) && */
              listaDetalle.add(cd);
              if(  fecha.isEqual(fecha_inicio) && fecha.isBefore(fecha_liquidacion)) continue;
              if(fecha.isAfter(today)) continue; // No se consideran punitorios de fecha posterior a hoy
              int days = Days.daysBetween(fecha, today).getDays() - 1;
              if (days <punitorio_desde) continue;
              Cuenta_detalle punitorio = this.calcularPunitorio(cd,1,punitorio_porc);
              if(punitorio!=null) listaDetalle.add(punitorio);
          }
          return listaDetalle;
        }
      
      public List<Cuenta_detalle> getByRangoFecha(Cuenta cuenta,LocalDate fecha_desde, LocalDate fecha_hasta){
          String query = String.format("select * from cuenta_detalle where cuenta_detalle.id_cuenta = %d and cuenta_detalle.fecha >= '%s' and cuenta_detalle.fecha <= '%s' order by fecha,id_concepto ",cuenta.getId(),fecha_desde, fecha_hasta);
          //System.out.println(query);
          return this.getList(query);
      }
     
      public void ordenarLista(ArrayList<Cuenta_detalle> lista){
        Collections.sort(lista, new Comparator<Cuenta_detalle>(){
            @Override
            public int compare(Cuenta_detalle o1, Cuenta_detalle o2){
                if(o1.getFecha().equals(o2.getFecha()))
                    return o1.getId_concepto().compareTo(o2.getId_concepto());;
                return o1.getFecha().compareTo(o2.getFecha());
            }
        });
     
     } 
public static void main(String[] args){
    
    HashMap<String,String> mapFiltro = new HashMap<String,String>();
    TCuenta tc = new TCuenta();
    TCuenta_detalle     tcd = new TCuenta_detalle();
    Cuenta cuenta = tc.getById(21);
    Contrato contrato = new TContrato().getById(cuenta.getId_contrato());
    
    
    tcd.setOrderBy(" fecha,id_concepto ");
    cuenta.setFecha_liquidacion(contrato.getFecha_inicio());
    
    //List<Cuenta_detalle> lstDetalle = tcd.getById_cuenta(cuenta.getId());
    
    List<Cuenta_detalle> punitorios = tcd.calcularPunitorios(cuenta, contrato, new LocalDate("2017-01-01"), new LocalDate());
    
    for(Cuenta_detalle p:punitorios){
        System.out.print(p.getFecha());
        System.out.print(" ");
        System.out.print(p.getConcepto());
        System.out.print(" ");
        System.out.print(p.getDebe());
        System.out.print(" ");
        System.out.print(p.getHaber());
        System.out.println("");
    }
    //List<Cuenta_detalle> listaDetalle = tcd.getListFiltro(mapFiltro);
            //  boolean puede = tcd.puedeAjustar(new TCuenta().getById(9), "2017-06-14");
            //System.out.println(puede);
//            Cuenta cuenta = new TCuenta().getById(27);
//            System.out.print("Fecha Liquidacion: ");
//            System.out.println(cuenta.getFecha_liquidacion());
            //            Contrato                      contrato = getContrato();
            //            ArrayList<Contrato_valor>     lstValor = getValores();
            //            ArrayList<Contrato_documento> lstDocum = getDocumentos();
            //            ArrayList<Contrato_gasto>     lstGasto = getGastos();
            //            TCuenta_detalle tcd = new TCuenta_detalle();
            //            ArrayList<Cuenta_detalle> lstDetalle = new ArrayList<Cuenta_detalle>();
            //
            //            lstDetalle = (ArrayList<Cuenta_detalle>) tcd.detalleInquilino(contrato, lstValor, lstDocum, lstGasto);
            //
            //            lstDetalle.addAll(tcd.crearDetallePropietario(contrato));
            //
          /*  for(Cuenta_detalle d:lstDetalle){
                System.out.print(d.getFecha());
                System.out.print(" ");
                System.out.print(d.getConcepto());
                System.out.print(" ");
                System.out.print(d.getDebe());
                System.out.print(" ");
                System.out.print(d.getHaber());
                System.out.println("");

            } */
}        


}