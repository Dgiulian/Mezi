package transaccion;

import bd.Localidad;
import java.util.List;
public class TLocalidad extends TransaccionBase<Localidad> {

	@Override
	public List<Localidad> getList() {
		return super.getList("select * from localidad ");
	}

	public Boolean actualizar(Localidad localidad) {
		return super.actualizar(localidad, "id");
	}

	public Localidad getById(Integer id) {
		String query = String.format(
				"select * from localidad where localidad.id = %d ", id);
		return super.getById(query);
	}
}