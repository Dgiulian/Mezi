package bd;

import bd.base.PagoBase;
public class Pago extends PagoBase {

	public Pago() {
	}

	public Pago(Pago pago) {
		super(pago);
	}

    public Float getTotal() {
        return this.efectivo + this.cheque_mnt + this.transf_mnt;
    }
}