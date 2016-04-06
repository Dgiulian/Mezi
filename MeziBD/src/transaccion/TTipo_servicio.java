package transaccion;

import bd.Tipo_servicio;
import java.util.List;
public class TTipo_servicio extends TransaccionBase<Tipo_servicio> {

	@Override
	public List<Tipo_servicio> getList() {
		return super.getList("select * from tipo_servicio ");
	}

	public Boolean actualizar(Tipo_servicio tipo_servicio) {
		return super.actualizar(tipo_servicio, "id");
	}

	public Tipo_servicio getById(Integer id) {
		String query = String.format(
				"select * from tipo_servicio where tipo_servicio.id = %d ", id);
		return super.getById(query);
	}
}