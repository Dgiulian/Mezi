package transaccion;

import bd.Cliente;
import bd.Contrato;
import bd.Contrato_documento;
import bd.Contrato_gasto;
import bd.Contrato_valor;
import bd.Cuenta;
import bd.Cuenta_detalle;
import bd.Propiedad;
import bd.Vendedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.BaseException;
import utils.OptionsCfg;
import utils.TFecha;
public class TContrato extends TransaccionBase<Contrato> {

	@Override
	public List<Contrato> getList() {
		return super.getList("select * from contrato ");
	}

	public Boolean actualizar(Contrato contrato) {
		return super.actualizar(contrato, "id");
	}

	public Contrato getById(Integer id) {
		String query = String.format(
				"select * from contrato where contrato.id = %d ", id);
		return super.getById(query);
	}
        public Integer siguienteNumero(){
            Integer numero = 1000;
            String query = " select max(numero) from contrato where contrato.numero > 1000";
            conexion.conectarse();
            ResultSet rs = conexion.ejecutarSQLSelect(query);
            
            try {
                while (rs.next()){
                    numero = rs.getInt(1) + 1;
                    break;
                }
            } catch (SQLException ex) {
                Logger.getLogger(TContrato.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            conexion.desconectarse();
            return numero;
        }
        public boolean eliminar(Contrato contrato) throws BaseException{
           TContrato           tc  = new TContrato();
           TPropiedad          tp  = new TPropiedad();
           TContrato_valor     tvalor  = new TContrato_valor();
           TContrato_documento tdocum  = new TContrato_documento();
           TContrato_gasto     tgastos  = new TContrato_gasto();
           TCuenta_detalle     tcd = new TCuenta_detalle();
           TCuenta             tcuenta = new TCuenta();
           HashMap<String,String> mapFiltro = new HashMap<String,String>();
           HashMap<String,String> filtroCuenta = new HashMap<String,String>();
           
           if (contrato==null) throw new BaseException("ERROR","No existe el contrato");
           mapFiltro.put("id_contrato",contrato.getId().toString());


           boolean baja = tc.baja(contrato);
           if ( !baja)throw new BaseException("ERROR","Ocurrio un error al eliminar el registro");
            Propiedad propiedad = tp.getById(contrato.getId_propiedad());
            //new TPropietario().baja(contrato.getId_propietario());
            List<Contrato_valor>     lstValor = tvalor.getById_contrato(contrato.getId());
            List<Contrato_documento> lstDocum = tdocum.getById_contrato(contrato.getId());
            List<Contrato_gasto>    lstGastos = tgastos.getById_contrato(contrato.getId());
            List<Cuenta> listaCuenta          = tcuenta.getListFiltro(mapFiltro);
            if(propiedad!=null){
                propiedad.setId_estado(OptionsCfg.PROPIEDAD_DISPONIBLE);
                tp.actualizar(propiedad);
            }
            
            for(Contrato_valor valor:lstValor)     tvalor.baja(valor);
            for(Contrato_documento docum:lstDocum) tdocum.baja(docum);
            for(Contrato_gasto gasto:lstGastos)    tgastos.baja(gasto);
            new TInquilino().baja(contrato.getId_inquilino());
            if(listaCuenta!=null){
                for(Cuenta cuenta: listaCuenta) {
                    filtroCuenta.put("id_cuenta",cuenta.getId().toString());
                    for(Cuenta_detalle cd:tcd.getListFiltro(filtroCuenta)){
                        tcd.baja(cd);
                    }
                    tcuenta.baja(cuenta);

                }
            }
          return true;  
        }
        
        public boolean activar(Contrato contrato) throws BaseException{
           TContrato        tcontrato = new TContrato();
           TContrato_valor     tvalor = new TContrato_valor();
           TContrato_documento tdocu  = new TContrato_documento();
           TContrato_gasto     tgasto = new TContrato_gasto();
           TPropiedad          tpro   = new TPropiedad();
           TInquilino          tinq   = new TInquilino();
           TPropietario        tprop  = new TPropietario();
           Integer id_contrato        = contrato.getId();
           List<Contrato_valor>     lstValor          =  tvalor.getById_contrato(id_contrato);
           List<Contrato_documento> lstDocum           =  tdocu.getById_contrato(id_contrato);
           List<Contrato_gasto>     lstGastos          =  tgasto.getById_contrato(id_contrato);
           ArrayList<Contrato_gasto>     lstGastoInq   =  new ArrayList<Contrato_gasto>();
           ArrayList<Contrato_gasto>     lstGastoProp  =  new ArrayList<Contrato_gasto>();
           if(lstGastos!=null){
               for(Contrato_gasto gasto:lstGastos){
                   Integer gasto_aplica = gasto.getId_aplica();
                   if (gasto_aplica.equals(OptionsCfg.CLIENTE_TIPO_INQUILINO)) 
                        lstGastoInq.add(gasto);
                   else if(gasto_aplica.equals(OptionsCfg.CLIENTE_TIPO_PROPIETARIO)) lstGastoProp.add(gasto);
               }
           }
           
           Propiedad propiedad = tpro.getById(contrato.getId_propiedad());
           if(propiedad==null) throw new BaseException("ERROR","Seleccione la propiedad");
          
           Cliente cliente = new TCliente().getById(contrato.getId_inquilino());
           if(cliente==null) throw new BaseException("ERROR","Seleccione el inquilino");
           Vendedor vendedor = new TVendedor().getById(contrato.getId_vendedor());
           if(vendedor ==null) throw new BaseException("ERROR","Seleccione el vendedor");
          
                           
            Cuenta cc_inquilino_o = new Cuenta();
            cc_inquilino_o.setId_cliente(contrato.getId_inquilino());               
            cc_inquilino_o.setId_contrato(id_contrato);
            cc_inquilino_o.setId_usuario(0);
            cc_inquilino_o.setFecha_creacion(TFecha.ahora(TFecha.formatoBD+ " " + TFecha.formatoHora));
            cc_inquilino_o.setId_tipo(OptionsCfg.CUENTA_OFICIAL);
            cc_inquilino_o.setId_tipo_cliente(OptionsCfg.CLIENTE_TIPO_INQUILINO);

            Cuenta cc_inquilino_no = new Cuenta(cc_inquilino_o);
            cc_inquilino_no.setId_tipo(OptionsCfg.CUENTA_NO_OFICIAL);
            cc_inquilino_no.setId_tipo_cliente(OptionsCfg.CLIENTE_TIPO_INQUILINO);

            Cuenta cc_propietario_o = new Cuenta();
            cc_propietario_o.setId_cliente(contrato.getId_propietario());
            cc_propietario_o.setId_contrato(id_contrato);
            cc_propietario_o.setFecha_creacion(TFecha.ahora(TFecha.formatoBD + " " + TFecha.formatoHora));
            cc_propietario_o.setId_usuario(0);
            cc_propietario_o.setId_tipo(OptionsCfg.CUENTA_OFICIAL);
            cc_propietario_o.setId_tipo_cliente(OptionsCfg.CLIENTE_TIPO_PROPIETARIO);

            Cuenta cc_propietario_no = new Cuenta(cc_propietario_o);
            cc_propietario_no.setId_tipo(OptionsCfg.CUENTA_NO_OFICIAL);
            cc_propietario_no.setId_tipo_cliente(OptionsCfg.CLIENTE_TIPO_PROPIETARIO);

            TCuenta tc = new TCuenta();
            TCuenta_detalle tcd = new TCuenta_detalle();
            int id_cc_inquilino_o    = tc.alta(cc_inquilino_o);
            int id_cc_inquilino_no   = tc.alta(cc_inquilino_no);
            int id_cc_propietario_o  = tc.alta(cc_propietario_o);
            int id_cc_propietario_no = tc.alta(cc_propietario_no);

            List<Cuenta_detalle> detalleInquilino_o    = tcd.detalleInquilino(contrato, lstValor, lstGastoInq);
            List<Cuenta_detalle> detalleInquilino_no   = tcd.detalleInquilino(lstDocum);

            List<Cuenta_detalle> detallePropietario_o  = tcd.detallePropietario(contrato, lstValor, lstGastoProp);
            List<Cuenta_detalle> detallePropietario_no = tcd.detallePropietario(lstDocum);

            if(id_cc_inquilino_o!=0) {
             for(Cuenta_detalle cd:detalleInquilino_o){
                 cd.setId_cuenta(id_cc_inquilino_o);
                 cd.setFecha_creacion(TFecha.ahora(TFecha.formatoBD + " " + TFecha.formatoHora));                    
                 tcd.alta(cd);
             }
            }
            if(id_cc_inquilino_no!=0) {
             for(Cuenta_detalle cd:detalleInquilino_no){
                 cd.setId_cuenta(id_cc_inquilino_no);
                 cd.setFecha_creacion(TFecha.ahora(TFecha.formatoBD + " " + TFecha.formatoHora));                    
                 tcd.alta(cd);
             }
            }

            if(id_cc_propietario_o!=0){
             for(Cuenta_detalle cd:detallePropietario_o){
                 cd.setId_cuenta(id_cc_propietario_o);
                 cd.setFecha_creacion(TFecha.ahora(TFecha.formatoBD + " " + TFecha.formatoHora));                  
                 tcd.alta(cd);
             }
            }
            if(id_cc_propietario_no!=0){
             for(Cuenta_detalle cd:detallePropietario_no){
                 cd.setId_cuenta(id_cc_propietario_no);
                 cd.setFecha_creacion(TFecha.ahora(TFecha.formatoBD + " " + TFecha.formatoHora));                  
                 tcd.alta(cd);
             }
            }
            contrato.setId_estado(OptionsCfg.CONTRATO_ESTADO_ACTIVO);
            tcontrato.actualizar(contrato);
            return true;
        }
        public static void main(String[ ] args){
            Integer numero = new TContrato().siguienteNumero();
            System.out.println(numero);
        }
}