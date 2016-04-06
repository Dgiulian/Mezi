package transaccion;

import bd.Auditoria;
import java.util.List;
import utils.TFecha;
public class TAuditoria extends TransaccionBase<Auditoria> {

	@Override
	public List<Auditoria> getList() {
		return super.getList("select * from auditoria ");
	}

	public Boolean actualizar(Auditoria auditoria) {
		return super.actualizar(auditoria, "id");
	}

	public Auditoria getById(Integer id) {
		String query = String.format(
				"select * from auditoria where auditoria.id = %d ", id);
		return super.getById(query);
	}
	 
        public static void guardar(Integer id_usuario,Integer id_tipo_usuario,Integer id_modulo, Integer id_accion,Integer id_referencia,String datos){
            Auditoria auditoria = new Auditoria();
            auditoria.setId_usuario(id_usuario);
            auditoria.setId_tipo_usuario(id_tipo_usuario);
            auditoria.setId_modulo(id_modulo);
            auditoria.setId_accion(id_accion);
            auditoria.setId_referencia(id_referencia);
            auditoria.setFecha(TFecha.ahora());
            auditoria.setDatos(datos);
            new TAuditoria().alta(auditoria);
        }    
}