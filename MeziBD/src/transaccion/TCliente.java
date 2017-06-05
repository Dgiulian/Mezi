package transaccion;

import bd.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        public Integer siguienteNumero(){
            Integer numero = 1000;
            String query = " select max(carpeta) from cliente where cliente.carpeta > 3000";
            conexion.conectarse();
            ResultSet rs = conexion.ejecutarSQLSelect(query);
            
            try {
                while (rs.next()){
                    numero = rs.getInt(1) + 1;
                    break;
                }
            } catch (SQLException ex) {
                Logger.getLogger(TContrato.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            conexion.desconectarse();
            return numero;
        }
        public static void main(String[] args){
            HashMap<String,String> mapFiltro = new HashMap<String,String>();
//            mapFiltro.put("nombre","ANGELA");
            new TCliente().getListFiltro(mapFiltro, 1);
            System.out.println(new TCliente().getListFiltroCount(mapFiltro));
        }

    public Cliente getByCarpeta(Integer carpeta) {
        String query = String.format( "select * from cliente where cliente.carpeta = %d ", carpeta);
        return super.getById(query);
    }
}