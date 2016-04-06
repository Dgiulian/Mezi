package transaccion;

import bd.Cliente_tipo;
import bd.Propietario;
import java.util.List;
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
         public boolean baja(Propietario prop){
//            Cliente_tipo tipo = new Cliente_tipo();
//            tipo.setId_cliente(prop.getId());
//            tipo.setId_tipo_cliente(Cliente_tipo.PROPIETARIO);
//            return new TCliente_tipo().alta(tipo);
             return false;
        }
         
         public static void main(String[] args){
            List<Propietario> list = new TPropietario().getList();
             System.out.println(list);
         }
}