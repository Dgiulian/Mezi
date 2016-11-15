package transaccion;

import bd.Propiedad;
import java.util.HashMap;
import java.util.List;
public class TPropiedad extends TransaccionBase<Propiedad> {

	@Override
	public List<Propiedad> getList() {
		return super.getList("select * from propiedad ");
	}

	public Boolean actualizar(Propiedad propiedad) {
		return super.actualizar(propiedad, "id");
	}

	public Propiedad getById(Integer id) {
		String query = String.format("select * from propiedad where propiedad.id = %d ", id);
		return super.getById(query);
	}
        public static void main(String[] args){
            TPropiedad tp = new TPropiedad();
//            Propiedad propiedad = tp.getById(2);
//            System.out.println(tp.auditar(propiedad));
//            System.out.println(propiedad.auditar());
            HashMap<String,String> mapFiltro = new HashMap<String,String>();
            mapFiltro.put("calle","TUCUMAN");
            mapFiltro.put("numero","15");
            new TPropiedad().getListFiltro(mapFiltro, 1);
            System.out.println(new TPropiedad().getListFiltroCount(mapFiltro));
            
        }
}