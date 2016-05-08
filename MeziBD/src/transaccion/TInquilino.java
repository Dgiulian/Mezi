package transaccion;

import bd.Cliente_tipo;
import bd.Contrato;
import bd.Inquilino;
import java.util.List;
import utils.OptionsCfg;
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
        public Integer alta(Integer id_inquilino){
            TCliente_tipo tc = new TCliente_tipo();
            String query = String.format("select * from cliente_tipo where cliente_tipo.id_cliente = %d and cliente_tipo.id_tipo_cliente=%d",id_inquilino,OptionsCfg.CLIENTE_TIPO_INQUILINO);
            Cliente_tipo cliente_tipo = tc.getById(query);
            Integer id =0;
            if(cliente_tipo==null){
                cliente_tipo = new Cliente_tipo();
                cliente_tipo.setId_cliente(id_inquilino);
                cliente_tipo.setId_tipo_cliente(OptionsCfg.CLIENTE_TIPO_INQUILINO);
                id = tc.alta(cliente_tipo);
            } else id = cliente_tipo.getId();
            return id;
        }
        public boolean baja(Integer id_inquilino){
            /* Borra de la tabla cliente_tipo */
            TCliente_tipo tc = new TCliente_tipo();
            String query = String.format("select * from contrato where contrato.id_inquilino = %d",id_inquilino);
            List<Contrato> list = new TContrato().getList(query);
            if(list.isEmpty()){
                /* Si no existen más contratos de ese inquilino, se borra de la tabla cliente_tipo */ 
                query = String.format("select * from cliente_tipo where cliente_tipo.id_cliente = %d and cliente_tipo.id_tipo_cliente = %d",id_inquilino,OptionsCfg.CLIENTE_TIPO_INQUILINO);
                for(Cliente_tipo ct: tc.getList(query)){
                    tc.baja(ct);
                }
            }
            return true;
        }
        public static void main(String[] args){
            String[] arreglo = {"Cero","Uno","Dos","Tres"};
            int i = 0;
            while(!arreglo[i].equals("Dos")) i++;
        }
}