package transaccion;

import bd.Caja;
import bd.Caja_detalle;
import bd.Cuenta;
import bd.Recibo;
import java.util.List;
import utils.OptionsCfg;
public class TCaja_detalle extends TransaccionBase<Caja_detalle> {

	@Override
	public List<Caja_detalle> getList() {
		return super.getList("select * from caja_detalle ");
	}

	public Boolean actualizar(Caja_detalle caja_detalle) {
		return super.actualizar(caja_detalle, "id");
	}

	public Caja_detalle getById(Integer id) {
		String query = String.format(
				"select * from caja_detalle where caja_detalle.id = %d ", id);
		return super.getById(query);
	}
    public Caja_detalle creaIngresoEfectivo(Caja caja, Cuenta cuenta, Recibo recibo, Float monto) {
        Caja_detalle caja_detalle = new Caja_detalle();
        caja_detalle.setId_caja(caja.getId());
        caja_detalle.setId_cuenta(cuenta.getId());
        caja_detalle.setId_tipo_cuenta(cuenta.getId_tipo_cliente());
        caja_detalle.setId_forma(OptionsCfg.FORMA_EFECTIVO);
        caja_detalle.setId_tipo(OptionsCfg.TIPO_INGRESO);
        if (cuenta.getId_tipo_cliente()==OptionsCfg.CLIENTE_TIPO_PROPIETARIO) {
            caja_detalle.setImporte(-1 * monto);
            caja_detalle.setId_tipo_cuenta(OptionsCfg.CLIENTE_TIPO_PROPIETARIO);
        } else{
            caja_detalle.setImporte(monto);
            caja_detalle.setId_tipo_cuenta(OptionsCfg.CLIENTE_TIPO_INQUILINO);
        }                
        caja_detalle.setConcepto(String.format("RECIBO NRO: %d" , recibo.getNumero()));
        return caja_detalle;
    }
    public Caja_detalle creaIngresoCheque(Caja caja,Cuenta cuenta, Recibo recibo,Float monto) {
        Caja_detalle caja_detalle = new Caja_detalle();
        caja_detalle.setId_caja(caja.getId());        
        caja_detalle.setId_cuenta(cuenta.getId());
        caja_detalle.setId_tipo_cuenta(cuenta.getId_tipo_cliente());
        caja_detalle.setId_forma(OptionsCfg.FORMA_CHEQUE);
        caja_detalle.setId_tipo(OptionsCfg.TIPO_INGRESO);
        if (cuenta.getId_tipo_cliente()==OptionsCfg.CLIENTE_TIPO_PROPIETARIO) {
            caja_detalle.setImporte(monto);
        } else{
            caja_detalle.setImporte(monto);
        }
        caja_detalle.setConcepto(String.format("RECIBO NRO: %d", recibo.getNumero()));
        return caja_detalle;
    }

    public Caja_detalle creaIngresoTransferencia(Caja caja, Cuenta cuenta, Recibo recibo, Float monto) {
        Caja_detalle caja_detalle = new Caja_detalle();
        caja_detalle.setId_caja(caja.getId());        
        caja_detalle.setId_cuenta(cuenta.getId());
        caja_detalle.setId_tipo_cuenta(cuenta.getId_tipo_cliente());
        caja_detalle.setId_forma(OptionsCfg.FORMA_TRANSFERENCIA);
        caja_detalle.setId_tipo(OptionsCfg.TIPO_INGRESO);
        if (cuenta.getId_tipo_cliente()==OptionsCfg.CLIENTE_TIPO_PROPIETARIO) {
            caja_detalle.setImporte(monto);
        } else{
            caja_detalle.setImporte(monto);
        }
        caja_detalle.setConcepto(String.format("RECIBO NRO: %d", recibo.getNumero()));
        return caja_detalle;
    }

    

    public Caja_detalle creaEgresoEfectivo(Caja caja, Cuenta cuenta, Recibo recibo, Float monto) {
        Caja_detalle caja_detalle = new Caja_detalle();
        caja_detalle.setId_caja(caja.getId());
        caja_detalle.setId_cuenta(cuenta.getId());
        caja_detalle.setId_tipo_cuenta(cuenta.getId_tipo_cliente());
        caja_detalle.setId_forma(OptionsCfg.FORMA_EFECTIVO);
        caja_detalle.setId_tipo(OptionsCfg.TIPO_EGRESO);
        if (cuenta.getId_tipo_cliente()==OptionsCfg.CLIENTE_TIPO_PROPIETARIO) {
            caja_detalle.setImporte(monto);
            caja_detalle.setId_tipo_cuenta(OptionsCfg.CLIENTE_TIPO_PROPIETARIO);
        } else{
            caja_detalle.setImporte(-1 * monto);
            caja_detalle.setId_tipo_cuenta(OptionsCfg.CLIENTE_TIPO_INQUILINO);
        }                
        caja_detalle.setConcepto(String.format("RECIBO NRO: %d" , recibo.getNumero()));
        return caja_detalle;
    }

    public Caja_detalle creaEgresoCheque(Caja caja, Cuenta cuenta, Recibo recibo, Float monto) {
        Caja_detalle caja_detalle = new Caja_detalle();
        caja_detalle.setId_caja(caja.getId());        
        caja_detalle.setId_cuenta(cuenta.getId());
        caja_detalle.setId_tipo_cuenta(cuenta.getId_tipo_cliente());
        caja_detalle.setId_forma(OptionsCfg.FORMA_CHEQUE);
        caja_detalle.setId_tipo(OptionsCfg.TIPO_EGRESO);
        if (cuenta.getId_tipo_cliente()==OptionsCfg.CLIENTE_TIPO_PROPIETARIO) {
            caja_detalle.setImporte(monto);
        } else{
            caja_detalle.setImporte(monto);
        }
        caja_detalle.setConcepto(String.format("RECIBO NRO: %d", recibo.getNumero()));
        return caja_detalle;
    }

    public Caja_detalle creaEgresoTransferencia(Caja caja, Cuenta cuenta, Recibo recibo, Float monto) {
        Caja_detalle caja_detalle = new Caja_detalle();
        caja_detalle.setId_caja(caja.getId());        
        caja_detalle.setId_cuenta(cuenta.getId());
        caja_detalle.setId_tipo_cuenta(cuenta.getId_tipo_cliente());
        caja_detalle.setId_forma(OptionsCfg.FORMA_TRANSFERENCIA);
        caja_detalle.setId_tipo(OptionsCfg.TIPO_EGRESO);
        if (cuenta.getId_tipo_cliente()==OptionsCfg.CLIENTE_TIPO_PROPIETARIO) {
            caja_detalle.setImporte(monto);
        } else{
            caja_detalle.setImporte(monto);
        }
        caja_detalle.setConcepto(String.format("RECIBO NRO: %d", recibo.getNumero()));
        return caja_detalle;
    }
    
}