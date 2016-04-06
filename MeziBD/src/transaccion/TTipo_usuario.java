package transaccion;

import bd.Tipo_usuario;
import java.util.List;
public class TTipo_usuario extends TransaccionBase<Tipo_usuario> {

	@Override
	public List<Tipo_usuario> getList() {
		return super.getList("select * from tipo_usuario ");
	}

	public Boolean actualizar(Tipo_usuario tipo_usuario) {
		return super.actualizar(tipo_usuario, "id");
	}

	public Tipo_usuario getById(Integer id) {
		String query = String.format(
				"select * from tipo_usuario where tipo_usuario.id = %d ", id);
		return super.getById(query);
	}
}