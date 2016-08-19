package transaccion;

import bd.Caja_detalle;
import java.util.List;
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
}