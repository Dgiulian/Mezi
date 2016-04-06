package transaccion;

import bd.Parametro;
import java.util.List;
public class TParametro extends TransaccionBase<Parametro> {

	@Override
	public List<Parametro> getList() {
		return super.getList("select * from parametro ");
	}

	public Boolean actualizar(Parametro parametro) {
		return super.actualizar(parametro, "id");
	}

	public Parametro getById(Integer id) {
		String query = String.format(
				"select * from parametro where parametro.id = %d ", id);
		return super.getById(query);
	}
        public Parametro getByCodigo(String codigo){
            String query = String.format("select * from parametro where lower(parametro.codigo) = '%s' " ,codigo.toLowerCase());
            System.out.println(query);
            return super.getById(query);
        }
}