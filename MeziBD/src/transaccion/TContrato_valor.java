package transaccion;

import bd.Contrato_valor;
import java.util.List;
public class TContrato_valor extends TransaccionBase<Contrato_valor> {

	@Override
	public List<Contrato_valor> getList() {
		return super.getList("select * from contrato_valor ");
	}

	public Boolean actualizar(Contrato_valor contrato_valor) {
		return super.actualizar(contrato_valor, "id");
	}

	public Contrato_valor getById(Integer id) {
		String query = String.format(
				"select * from contrato_valor where contrato_valor.id = %d ",
				id);
		return super.getById(query);
	}
}