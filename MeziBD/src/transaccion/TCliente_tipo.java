package transaccion;

import bd.Cliente_tipo;
import java.util.List;
public class TCliente_tipo extends TransaccionBase<Cliente_tipo> {

	@Override
	public List<Cliente_tipo> getList() {
		return super.getList("select * from cliente_tipo ");
	}

	public Boolean actualizar(Cliente_tipo cliente_tipo) {
		return super.actualizar(cliente_tipo, "id");
	}

	public Cliente_tipo getById(Integer id) {
		String query = String.format(
				"select * from cliente_tipo where cliente_tipo.id = %d ", id);
		return super.getById(query);
	}
}