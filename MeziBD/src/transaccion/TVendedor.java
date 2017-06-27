package transaccion;

import bd.Vendedor;
import java.util.List;
public class TVendedor extends TransaccionBase<Vendedor> {

	@Override
	public List<Vendedor> getList() {
            return super.getList("select * from vendedor ");
	}

	public Boolean actualizar(Vendedor vendedor) {
            return super.actualizar(vendedor, "id");
	}

	public Vendedor getById(Integer id) {
            String query = String.format(
                            "select * from vendedor where vendedor.id = %d ", id);
            return super.getById(query);
	}
        
	public List<Vendedor> getListActivo() {
            return super.getList("select * from vendedor where activo = 1");
	}
}