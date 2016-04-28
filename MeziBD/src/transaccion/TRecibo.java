package transaccion;

import bd.Recibo;
import java.util.List;
public class TRecibo extends TransaccionBase<Recibo> {

	@Override
	public List<Recibo> getList() {
		return super.getList("select * from recibo ");
	}

	public Boolean actualizar(Recibo recibo) {
		return super.actualizar(recibo, "id");
	}

	public Recibo getById(Integer id) {
		String query = String.format(
				"select * from recibo where recibo.id = %d ", id);
		return super.getById(query);
	}
}