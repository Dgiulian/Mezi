package transaccion;

import bd.Cuenta;
import java.util.List;
public class TCuenta extends TransaccionBase<Cuenta> {

	@Override
	public List<Cuenta> getList() {
		return super.getList("select * from cuenta ");
	}

	public Boolean actualizar(Cuenta cuenta) {
		return super.actualizar(cuenta, "id");
	}

	public Cuenta getById(Integer id) {
		String query = String.format(
				"select * from cuenta where cuenta.id = %d ", id);
		return super.getById(query);
	}
        public Cuenta getBydId_cliente(Integer id_cliente,Integer id_propiedad){
            	String query = String.format(
				"select * from cuenta where cuenta.id_cliente = %d and id_propiedad = %d", id_cliente);
		return super.getById(query);
        }
        public Cuenta getBydClienteContrato(Integer id_cliente,Integer id_contrato,Integer id_tipo,Integer id_tipo_cliente){
            	String query = String.format(
				"select * from cuenta where cuenta.id_cliente = %d and cuenta.id_contrato = %d and cuenta.id_tipo = %d and cuenta.id_tipo_cliente = %d", id_cliente,id_contrato,id_tipo,id_tipo_cliente);
                System.out.println(query);
                return super.getById(query);
        }
}