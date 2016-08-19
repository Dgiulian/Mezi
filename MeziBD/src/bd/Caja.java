package bd;

import bd.base.CajaBase;
import utils.Fecha;
public class Caja extends CajaBase {

	public Caja() {}

	public Caja(Caja caja) {
            super(caja);
	}
        
	public CajaBase setFecha(String fecha) {
            this.fecha = new Fecha(fecha);
            return this;
	}        
}