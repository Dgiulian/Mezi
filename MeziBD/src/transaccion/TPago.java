package transaccion;

import bd.Pago;
import java.util.List;
public class TPago extends TransaccionBase<Pago> {

	@Override
	public List<Pago> getList() {
		return super.getList("select * from pago ");
	}

	public Boolean actualizar(Pago pago) {
		return super.actualizar(pago, "id");
	}

	public Pago getById(Integer id) {
		String query = String.format("select * from pago where pago.id = %d ",
				id);
		return super.getById(query);
	}
}