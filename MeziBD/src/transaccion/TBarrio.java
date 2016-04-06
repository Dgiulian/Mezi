package transaccion;

import bd.Barrio;
import java.util.List;
public class TBarrio extends TransaccionBase<Barrio> {

	@Override
	public List<Barrio> getList() {
		return super.getList("select * from barrio ");
	}

	public Boolean actualizar(Barrio barrio) {
		return super.actualizar(barrio, "id");
	}

	public Barrio getById(Integer id) {
		String query = String.format("select * from barrio where barrio.id = %d ", id);
		return super.getById(query);
	}
}