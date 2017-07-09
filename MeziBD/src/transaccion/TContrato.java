package transaccion;

import bd.Cliente;
import bd.Contrato;
import bd.Contrato_documento;
import bd.Contrato_gasto;
import bd.Contrato_valor;
import bd.Cuenta;
import bd.Cuenta_detalle;
import bd.Cuenta_interna;
import bd.Cuenta_interna_detalle;
import bd.Propiedad;
import bd.Vendedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        public Contrato getSolapado(Contrato c){
        
        String query = String.format("select *\n" +
            "from contrato\n" +
            "where contrato.id_propiedad = %d \n" +
            "and contrato.id_inquilino = %d\n" +
            "and contrato.fecha_inicio <= '%s'\n" +
            "and contrato.fecha_fin >='%s'",c.getId_propiedad(),c.getId_inquilino(),c.getFecha_fin(),c.getFecha_inicio());
            System.out.println(query);
            return getById(query);
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
           TContrato           tcontrato  = new TContrato();
           TPropiedad          tpropiedad = new TPropiedad();
           TContrato_valor     tvalor     = new TContrato_valor();
           TContrato_documento tdocum     = new TContrato_documento();
           TContrato_gasto     tgastos    = new TContrato_gasto();
           TCuenta             tcuenta    = new TCuenta();
           TCuenta_detalle     tcuenta_detalle = new TCuenta_detalle();
           
           if (contrato==null) throw new BaseException("ERROR","No existe el contrato");
           if(!contrato.getId_estado().equals(OptionsCfg.CONTRATO_ESTADO_INICIAL)) throw new BaseException("ERROR","Solo se puede eliminar un contrato en estado inicial");
           
           boolean baja = tcontrato.baja(contrato);
           if ( !baja)throw new BaseException("ERROR","Ocurrio un error al eliminar el registro");
            Propiedad propiedad = tpropiedad.getById(contrato.getId_propiedad());
            //new TPropietario().baja(contrato.getId_propietario());
            List<Contrato_valor>     lstValor = tvalor.getById_contrato(contrato.getId());
            List<Contrato_documento> lstDocum = tdocum.getById_contrato(contrato.getId());
            List<Contrato_gasto>    lstGastos = tgastos.getById_contrato(contrato.getId());
            List<Cuenta> listaCuenta          = tcuenta.getById_contrato(contrato.getId());
            if(propiedad!=null){
                propiedad.setId_estado(OptionsCfg.PROPIEDAD_DISPONIBLE);
                tpropiedad.actualizar(propiedad);
            }
            
            for(Contrato_valor valor:lstValor)     tvalor.baja(valor);
            for(Contrato_documento docum:lstDocum) tdocum.baja(docum);
            for(Contrato_gasto gasto:lstGastos)    tgastos.baja(gasto);
            new TInquilino().baja(contrato.getId_inquilino());
            if(listaCuenta!=null){
                for(Cuenta cuenta: listaCuenta) {
                    for(Cuenta_detalle cd:tcuenta_detalle.getById_cuenta(cuenta.getId())){
                        tcuenta_detalle.baja(cd);
                    }
                    tcuenta.baja(cuenta);

                }
            }
          return true;  
        }
        
        public boolean activar(Contrato contrato) throws BaseException{
            TContrato        tcontrato       = new TContrato();
            TContrato_valor     tvalor       = new TContrato_valor();
            TContrato_documento tdocu        = new TContrato_documento();
            TContrato_gasto     tgasto       = new TContrato_gasto();
            TPropiedad          tpropiedad   = new TPropiedad();
            Integer id_contrato              = contrato.getId();
            TCuenta_interna tcuenta_interna  = new TCuenta_interna();
            List<Contrato_valor>      lstValor      = tvalor.getById_contrato(id_contrato);
            List<Contrato_documento>  lstDocum      = tdocu.getById_contrato(id_contrato);
            List<Contrato_gasto>      lstGastos     = tgasto.getById_contrato(id_contrato);
            ArrayList<Contrato_gasto> lstGastoInq   = new ArrayList<Contrato_gasto>();
            ArrayList<Contrato_gasto> lstGastoProp  = new ArrayList<Contrato_gasto>();
            if(lstGastos!=null){
                for(Contrato_gasto gasto:lstGastos){
                    Integer gasto_aplica = gasto.getId_aplica();
                    if (gasto_aplica.equals(OptionsCfg.CLIENTE_TIPO_INQUILINO)) 
                         lstGastoInq.add(gasto);
                    else if(gasto_aplica.equals(OptionsCfg.CLIENTE_TIPO_PROPIETARIO)) lstGastoProp.add(gasto);
                }
            }
            Propiedad propiedad = tpropiedad.getById(contrato.getId_propiedad());
            Cliente cliente = new TCliente().getById(contrato.getId_inquilino());
            Vendedor vendedor = new TVendedor().getById(contrato.getId_vendedor());
           
            if(propiedad==null) throw new BaseException("ERROR","Seleccione la propiedad");
            if(cliente==null) throw new BaseException("ERROR","Seleccione el inquilino");
            if(vendedor ==null) throw new BaseException("ERROR","Seleccione el vendedor");
            Cuenta_interna  cuenta_vendedor = tcuenta_interna.getById(vendedor.getId_cuenta());
            if(cuenta_vendedor==null) throw new BaseException("ERROR","El vendedor no tiene una cuenta interna asignada");
           
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
            //Creamos la comision al vendedor en su cuenta interna
            Cuenta_interna_detalle cuenta_interna_detalle = new Cuenta_interna_detalle();
            cuenta_interna_detalle.setId_cuenta(cuenta_vendedor.getId());
            cuenta_interna_detalle.setFecha(TFecha.ahora(TFecha.formatoBD));
            cuenta_interna_detalle.setHaber(contrato.getComision_vendedor());
            cuenta_interna_detalle.setConcepto(String.format("Comision contrato %d",contrato.getNumero()));
            tcuenta_interna.alta(cuenta_interna_detalle);
            
            
            contrato.setId_estado(OptionsCfg.CONTRATO_ESTADO_ACTIVO);            
            contrato.setFecha_cambio_estado(TFecha.ahora(TFecha.formatoBD_Hora));
            tcontrato.actualizar(contrato);
            return true;
        }
        /*
            Cierra el contrato y pasa la propiedad a estado disponible.
            Si tuviera alguna deuda, se debe saldar antes de finalizar el contrato
        */
        public boolean cerrar(Contrato contrato) throws BaseException{
            TContrato  tcontrato  = new TContrato();
            TPropiedad tpropiedad = new TPropiedad();
            Propiedad propiedad = tpropiedad.getById(contrato.getId_propiedad());
            if(propiedad==null) throw new BaseException("ERROR","No se encontr&oacute; la propiedad");
            propiedad.setId_estado(OptionsCfg.PROPIEDAD_DISPONIBLE);                
            
            contrato.setId_estado(OptionsCfg.CONTRATO_ESTADO_ENTREGA);
            contrato.setFecha_cambio_estado(TFecha.ahora(TFecha.formatoBD_Hora));
            tcontrato.actualizar(contrato);
            tpropiedad.actualizar(propiedad);
            return false;
        }
        /*
            El contrato pasa a estado finalizado. Las cuentas no debe tener ninguna saldo
        */
        public boolean finalizar(Contrato contrato) throws BaseException{
            TContrato tcontrato = new TContrato();            
            contrato.setId_estado(OptionsCfg.CONTRATO_ESTADO_FIN);
            contrato.setFecha_cambio_estado(TFecha.ahora(TFecha.formatoBD_Hora));
            tcontrato.actualizar(contrato);
            return false;
        }
        public Contrato getById_propiedad(Integer id_propiedad){
            String query = String.format("select * from contrato where contrato.id_propiedad = %d and (contrato.id_estado = %d or contrato.id_estado = %d  )",id_propiedad,OptionsCfg.CONTRATO_ESTADO_INICIAL,OptionsCfg.CONTRATO_ESTADO_ACTIVO);
            System.out.println(query);
            return this.getById(query);
        }
        public static void main(String[ ] args){
            TContrato tc = new TContrato();
            Contrato contrato = tc.getById_propiedad(9);
            System.out.println(contrato.getNumero());
            /*Contrato c = new Contrato();
            c.setId_inquilino(12);
            c.setId_propiedad(9);
            c.setFecha_inicio("2016-01-01");
            c.setFecha_fin("2017-01-01");
            Contrato c2 = new TContrato().getSolapado(c);
            System.out.println(c2);
             */
        }
}