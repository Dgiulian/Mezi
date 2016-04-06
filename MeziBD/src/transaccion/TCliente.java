package transaccion;

import bd.Cliente;
import java.util.List;
public class TCliente extends TransaccionBase<Cliente> {

	@Override
	public List<Cliente> getList() {
		return super.getList("select * from cliente ");
	}

	public Boolean actualizar(Cliente cliente) {
		return super.actualizar(cliente, "id");
	}

	public Cliente getById(Integer id) {
		String query = String.format(
				"select * from cliente where cliente.id = %d ", id);
		return super.getById(query);
	}
        
        public Cliente getByDocumento(String documento) {
		String query = String.format(
				"select * from cliente where cliente.dni like '%s' or cliente.cuil like '%s'", documento, documento);
                System.out.println(query);
		return super.getById(query);
	}
}