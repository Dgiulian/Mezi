package transaccion;

import bd.Contrato_gasto;
import java.util.List;
public class TContrato_gasto extends TransaccionBase<Contrato_gasto> {

	@Override
	public List<Contrato_gasto> getList() {
		return super.getList("select * from contrato_gasto ");
	}

	public Boolean actualizar(Contrato_gasto contrato_gasto) {
		return super.actualizar(contrato_gasto, "id");
	}

	public Contrato_gasto getById(Integer id) {
		String query = String.format(
				"select * from contrato_gasto where contrato_gasto.id = %d ",
				id);
		return super.getById(query);
	}
}