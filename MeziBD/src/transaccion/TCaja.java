package transaccion;

import bd.Caja;
import java.util.List;
import utils.OptionsCfg;
public class TCaja extends TransaccionBase<Caja> {

	@Override
	public List<Caja> getList() {
		return super.getList("select * from caja ");
	}

	public Boolean actualizar(Caja caja) {
		return super.actualizar(caja, "id");
	}

	public Caja getById(Integer id) {
		String query = String.format("select * from caja where caja.id = %d ",
				id);
		return super.getById(query);
	}

    public Caja buscarCajaAbierta(Integer id_usuario) {
        String query = String.format("select * from caja where caja.id_usuario = %d and caja.id_estado = %d",id_usuario,OptionsCfg.CAJA_ABIERTA);
        System.out.println(query);
        conexion.conectarse();
        conexion.ejecutarSQLSelect(query);
        Caja caja = this.getById(query);
        conexion.desconectarse();
        return caja;
    }

    public Caja buscarCaja(Integer id_usuario,String fecha) {
        String query = String.format("select * from caja where caja.id_usuario = %d and caja.fecha = '%s'",id_usuario,fecha);
        System.out.println(query);
        conexion.conectarse();
        conexion.ejecutarSQLSelect(query);
        Caja caja = this.getById(query);
        conexion.desconectarse();
        return caja;
    }
}