package transaccion;

import bd.Modulo;
import java.util.List;
public class TModulo extends TransaccionBase<Modulo> {

	@Override
	public List<Modulo> getList() {
		return super.getList("select * from modulo ");
	}

	public Boolean actualizar(Modulo modulo) {
		return super.actualizar(modulo, "id");
	}

	public Modulo getById(Integer id) {
		String query = String.format(
				"select * from modulo where modulo.id = %d ", id);
		return super.getById(query);
	}
}