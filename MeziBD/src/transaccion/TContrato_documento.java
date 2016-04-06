package transaccion;

import bd.Contrato_documento;
import java.util.List;
public class TContrato_documento extends TransaccionBase<Contrato_documento> {

	@Override
	public List<Contrato_documento> getList() {
		return super.getList("select * from contrato_documento ");
	}

	public Boolean actualizar(Contrato_documento contrato_documento) {
		return super.actualizar(contrato_documento, "id");
	}

	public Contrato_documento getById(Integer id) {
		String query = String
				.format("select * from contrato_documento where contrato_documento.id = %d ",
						id);
		return super.getById(query);
	}
}