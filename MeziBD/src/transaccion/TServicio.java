package transaccion;

import bd.Servicio;
import java.util.List;
public class TServicio extends TransaccionBase<Servicio> {

	@Override
	public List<Servicio> getList() {
		return super.getList("select * from servicio ");
	}

	public Boolean actualizar(Servicio servicio) {
		return super.actualizar(servicio, "id");
	}

	public Servicio getById(Integer id) {
		String query = String.format(
				"select * from servicio where servicio.id = %d ", id);
		return super.getById(query);
	}
}