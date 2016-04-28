package transaccion;

import bd.Recibo_detalle;
import java.util.List;
public class TRecibo_detalle extends TransaccionBase<Recibo_detalle> {

	@Override
	public List<Recibo_detalle> getList() {
		return super.getList("select * from recibo_detalle ");
	}

	public Boolean actualizar(Recibo_detalle recibo_detalle) {
		return super.actualizar(recibo_detalle, "id");
	}

	public Recibo_detalle getById(Integer id) {
		String query = String.format(
				"select * from recibo_detalle where recibo_detalle.id = %d ",
				id);
		return super.getById(query);
	}
}