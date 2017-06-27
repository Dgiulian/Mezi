package transaccion;

import bd.Cuenta;
import java.util.List;
import utils.OptionsCfg;
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
        public List<Cuenta> getById_contrato(Integer id_contrato){
            String query = String.format(
                            "select * from cuenta where cuenta.id_contrato = %d", id_contrato);
            return super.getList(query);
        }
        public Cuenta getBydClienteContrato(Integer id_cliente,Integer id_contrato,Integer id_tipo,Integer id_tipo_cliente){
            String query = String.format(
                            "select * from cuenta where cuenta.id_cliente = %d and cuenta.id_contrato = %d and cuenta.id_tipo = %d and cuenta.id_tipo_cliente = %d", id_cliente,id_contrato,id_tipo,id_tipo_cliente);
            System.out.println(query);
            return super.getById(query);
        }
        public Cuenta getRelacionada(Cuenta cuenta){
            Integer id_cliente = cuenta.getId();
            Integer id_contrato = cuenta.getId_contrato();
            Integer id_tipo = cuenta.getId_tipo();
            Integer id_tipo_cliente = cuenta.getId_tipo_cliente();
            Integer id_tipo_cliente_rel = 0;
            if(id_tipo_cliente.equals(OptionsCfg.CLIENTE_TIPO_INQUILINO))   id_tipo_cliente_rel = OptionsCfg.CLIENTE_TIPO_PROPIETARIO;
            if(id_tipo_cliente.equals(OptionsCfg.CLIENTE_TIPO_PROPIETARIO)) id_tipo_cliente_rel = OptionsCfg.CLIENTE_TIPO_INQUILINO;
            String query = String.format(
				"select * from cuenta where cuenta.id_contrato = %d and cuenta.id_tipo = %d and cuenta.id_tipo_cliente = %d",id_contrato,id_tipo,id_tipo_cliente_rel, id_cliente);
            System.out.println(query);
            return super.getById(query);
        }
        public static void main(String[] args){
            TCuenta tc = new TCuenta();
            Cuenta cuenta = tc.getById(4);
            Cuenta relacionada = tc.getRelacionada(cuenta);
            System.out.println(relacionada.getId());
        }
}