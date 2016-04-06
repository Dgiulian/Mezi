package transaccion;

import bd.Tipo_cliente;
import java.util.List;
public class TTipo_cliente extends TransaccionBase<Tipo_cliente> {

	@Override
	public List<Tipo_cliente> getList() {
		return super.getList("select * from tipo_cliente ");
	}

	public Boolean actualizar(Tipo_cliente tipo_cliente) {
		return super.actualizar(tipo_cliente, "id");
	}

	public Tipo_cliente getById(Integer id) {
		String query = String.format(
				"select * from tipo_cliente where tipo_cliente.id = %d ", id);
		return super.getById(query);
	}
}