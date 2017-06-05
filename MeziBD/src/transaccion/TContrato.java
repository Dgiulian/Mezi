package transaccion;

import bd.Contrato;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class TContrato extends TransaccionBase<Contrato> {

	@Override
	public List<Contrato> getList() {
		return super.getList("select * from contrato ");
	}

	public Boolean actualizar(Contrato contrato) {
		return super.actualizar(contrato, "id");
	}

	public Contrato getById(Integer id) {
		String query = String.format(
				"select * from contrato where contrato.id = %d ", id);
		return super.getById(query);
	}
        public Integer siguienteNumero(){
            Integer numero = 1000;
            String query = " select max(numero) from contrato where contrato.numero > 1000";
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
        
        public static void main(String[ ] args){
            Integer numero = new TContrato().siguienteNumero();
            System.out.println(numero);
        }
}