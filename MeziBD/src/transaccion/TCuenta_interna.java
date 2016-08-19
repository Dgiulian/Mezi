package transaccion;

import bd.Cuenta_interna;
import java.util.List;
public class TCuenta_interna extends TransaccionBase<Cuenta_interna> {

	@Override
	public List<Cuenta_interna> getList() {
		return super.getList("select * from cuenta_interna ");
	}

	public Boolean actualizar(Cuenta_interna cuenta_interna) {
		return super.actualizar(cuenta_interna, "id");
	}

	public Cuenta_interna getById(Integer id) {
		String query = String.format(
				"select * from cuenta_interna where cuenta_interna.id = %d ",
				id);
		return super.getById(query);
	}
}