package bd;

import bd.base.Cuenta_internaBase;
public class Cuenta_interna extends Cuenta_internaBase {

	public Cuenta_interna() {
	}

	public Cuenta_interna(Cuenta_interna cuenta_interna) {
		super(cuenta_interna);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.Cuenta_interna))
			return false;
		return ((bd.Cuenta_interna) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}