package transaccion;

import bd.Provincia;
import java.util.List;
public class TProvincia extends TransaccionBase<Provincia> {

	@Override
	public List<Provincia> getList() {
		return super.getList("select * from provincia ");
	}

	public Boolean actualizar(Provincia provincia) {
		return super.actualizar(provincia, "id");
	}

	public Provincia getById(Integer id) {
		String query = String.format(
				"select * from provincia where provincia.id = %d ", id);
		return super.getById(query);
	}
}