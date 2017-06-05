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

    public Recibo getByNumero(Integer numero) {

        String query = String.format("select * from recibo where recibo.numero = %d ", numero);
        return super.getById(query);
	
    }

    public Recibo getPosterior(Recibo recibo) {
        String query = String.format("select * from recibo\n" +
                "where recibo.id_contrato = %d\n" +
                "and recibo.id_cuenta = %d\n" +
                "and recibo.id_cliente = %d\n" +
                "and recibo.id_tipo_recibo = %d\n"+
                "and recibo.fecha >= '%s'\n"+
                "and recibo.id <> %d\n"+
                "order by recibo.fecha desc"
                ,recibo.getId_contrato(),recibo.getId_cuenta(),recibo.getId_cliente(),recibo.getId_tipo_recibo(),recibo.getFecha(),recibo.getId());
        return this.getById(query);
    }
    public Recibo getAnterior(Recibo recibo) {
       String query = String.format("select * from recibo\n" +
                "where recibo.id_contrato = %d\n" +
                "and recibo.id_cuenta = %d\n" +
                "and recibo.id_cliente = %d\n" +
                "and recibo.id_tipo_recibo = %d\n"+
                "and recibo.fecha <= '%s'\n"+
                "and recibo.id <> %d\n"+
                "order by recibo.fecha"
                ,recibo.getId_contrato(),recibo.getId_cuenta(),recibo.getId_cliente(),recibo.getId_tipo_recibo(),recibo.getFecha(),recibo.getId());
        return this.getById(query);
    }
    public static void main(String[] args){
        TRecibo tr = new TRecibo();
        System.out.println(tr.getNumero());
//        Recibo r = tr.getById(3);
//        System.out.println(tr.getPosterior(r).getFecha());
//        System.out.println(tr.getAnterior(r).getFecha());
    }
}