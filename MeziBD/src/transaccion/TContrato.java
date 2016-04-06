package transaccion;

import bd.Contrato;
import java.util.List;
public class TContrato extends TransaccionBase<Contrato> {

	@Override
	public List<Contrato> getList() {
		return super.getList("select * from contrato ");
	}

	public Boolean actualizar(Contrato contrato) {
		return super.actualizar(contrato, "id");
	}

	public Contrato getById(Integer id) {
		String query = String.format(
				"select * from contrato where contrato.id = %d ", id);
		return super.getById(query);
	}
}