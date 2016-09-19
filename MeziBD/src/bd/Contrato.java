package bd;

import bd.base.ContratoBase;
public class Contrato extends ContratoBase {

	public Contrato() {
	}

	public Contrato(Contrato contrato) {
		super(contrato);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.Contrato))
			return false;
		return ((bd.Contrato) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}