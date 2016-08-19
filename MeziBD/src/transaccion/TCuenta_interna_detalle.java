package transaccion;

import bd.Cuenta_interna_detalle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class TCuenta_interna_detalle
		extends
			TransaccionBase<Cuenta_interna_detalle> {

	@Override
	public List<Cuenta_interna_detalle> getList() {
		return super.getList("select * from cuenta_interna_detalle ");
	}

	public Boolean actualizar(Cuenta_interna_detalle cuenta_interna_detalle) {
		return super.actualizar(cuenta_interna_detalle, "id");
	}

	public Cuenta_interna_detalle getById(Integer id) {
		String query = String
				.format("select * from cuenta_interna_detalle where cuenta_interna_detalle.id = %d ",
						id);
		return super.getById(query);
	}
    
    /* Dado un id y una fecha, calcula el saldo de la cuenta */
    public float getSaldo(Integer id, String fecha) {
        Float saldo  = 0f;
        String query = String.format("select sum(haber - debe) from cuenta_interna_detalle where cuenta_interna_detalle.id_cuenta = %d and fecha <= '%s'",id,fecha);
        conexion.conectarse();
        ResultSet result = conexion.ejecutarSQLSelect(query);
        try {
            while(result.next()){
                saldo = result.getFloat(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TCuenta_interna_detalle.class.getName()).log(Level.SEVERE, null, ex);
        }
        conexion.desconectarse();
        return saldo;
    }
    public static void main(String[] args){
            TCuenta_interna_detalle tid = new TCuenta_interna_detalle();
            System.out.println(tid.getSaldo(2, "2016-08-01"));
            System.out.println(tid.getSaldo(2, "2016-09-01"));
        
    }
}