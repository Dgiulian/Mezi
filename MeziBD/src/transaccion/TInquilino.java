package transaccion;

import bd.Inquilino;
import java.util.List;
public class TInquilino extends TransaccionBase<Inquilino> {

	@Override
	public List<Inquilino> getList() {
		return super.getList("select * from inquilino ");
	}

	public Boolean actualizar(Inquilino inquilino) {
		return super.actualizar(inquilino, "id");
	}

	public Inquilino getById(Integer id) {
		String query = String.format(
				"select * from inquilino where inquilino.id = %d ", id);
		return super.getById(query);
	}
}