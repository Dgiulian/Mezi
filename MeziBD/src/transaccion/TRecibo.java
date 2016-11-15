package transaccion;

import bd.Recibo;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class TRecibo extends TransaccionBase<Recibo> {

	@Override
	public List<Recibo> getList() {
		return super.getList("select * from recibo ");
	}

	public Boolean actualizar(Recibo recibo) {
		return super.actualizar(recibo, "id");
	}

	public Recibo getById(Integer id) {
		String query = String.format(
				"select * from recibo where recibo.id = %d ", id);
		return super.getById(query);
	}
        public int getNumero(){
            int numero = 0;
            conexion.conectarse();
            try {
                CallableStatement cs = conexion.getConexion().prepareCall("{call getNumero()}");
                ResultSet rs = cs.executeQuery();
                while(rs.next()){
                    numero = rs.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TRecibo.class.getName()).log(Level.SEVERE, null, ex);
            }
            conexion.desconectarse();
            return numero;
        }
}