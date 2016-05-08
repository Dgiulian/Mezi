package transaccion;

import bd.Cliente_tipo;
import bd.Contrato;
import bd.Propietario;
import java.util.List;
import utils.OptionsCfg;
public class TPropietario extends TransaccionBase<Propietario> {

	@Override
	public List<Propietario> getList() {
		return super.getList("select * from propietario ");
	}

//	public Boolean actualizar(Propietario propietario) {
//		return super.actualizar(propietario, "id");
//	}

	public Propietario getById(Integer id) {
		String query = String.format(
				"select * from propietario where propietario.id = %d ", id);
		return super.getById(query);
	}
        public int alta(Propietario prop){        
            Cliente_tipo tipo = new Cliente_tipo();
            tipo.setId_cliente(prop.getId());
            tipo.setId_tipo_cliente(Cliente_tipo.PROPIETARIO);
            return new TCliente_tipo().alta(tipo);
        }
        public Integer alta(Integer id_propietario){
            TCliente_tipo tc = new TCliente_tipo();
            String query = String.format("select * from cliente_tipo where cliente_tipo.id_cliente = %d and cliente_tipo.id_tipo_cliente=%d",id_propietario,OptionsCfg.CLIENTE_TIPO_PROPIETARIO);
            Cliente_tipo cliente_tipo = tc.getById(query);
            Integer id =0;
            if(cliente_tipo==null){
                cliente_tipo = new Cliente_tipo();
                cliente_tipo.setId_cliente(id_propietario);
                cliente_tipo.setId_tipo_cliente(OptionsCfg.CLIENTE_TIPO_PROPIETARIO);
                id = tc.alta(cliente_tipo);
            } else id = cliente_tipo.getId();
            return id;
        }
         public boolean baja(Propietario prop){
//            Cliente_tipo tipo = new Cliente_tipo();
//            tipo.setId_cliente(prop.getId());
//            tipo.setId_tipo_cliente(Cliente_tipo.PROPIETARIO);
//            return new TCliente_tipo().alta(tipo);
             return false;
        }
         public boolean baja(Integer id_propietario){
            /* Borra de la tabla cliente_tipo */
            TCliente_tipo tc = new TCliente_tipo();
            String query = String.format("select * from contrato where contrato.id_propietario = %d",id_propietario);
            List<Contrato> list = new TContrato().getList(query);
            if(list.isEmpty()){
                /* Si no existen más contratos de ese propietario, se borra de la tabla cliente_tipo */ 
                query = String.format("select * from cliente_tipo where cliente_tipo.id_cliente = %d and  cliente_tipo.id_tipo_cliente = %d",id_propietario,OptionsCfg.CLIENTE_TIPO_PROPIETARIO);
                System.out.println(query);
                for(Cliente_tipo ct: tc.getList(query)){
                    tc.baja(ct);
                };
            }
            return true;
        }
         public static void main(String[] args){
//            List<Propietario> list = new TPropietario().getList();
//             System.out.println(list);
             
             
         }
}