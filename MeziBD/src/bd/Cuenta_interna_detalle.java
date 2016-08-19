package bd;

import bd.base.Cuenta_interna_detalleBase;
import utils.Fecha;
public class Cuenta_interna_detalle extends Cuenta_interna_detalleBase {

	public Cuenta_interna_detalle() {
	}

	public Cuenta_interna_detalle(Cuenta_interna_detalle cuenta_interna_detalle) {
		super(cuenta_interna_detalle);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.Cuenta_interna_detalle))
			return false;
		return ((bd.Cuenta_interna_detalle) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
        public Cuenta_interna_detalle setFecha(String fecha) {
            this.fecha = new Fecha(fecha);
            return this;
	}     
}