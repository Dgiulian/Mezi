package bd;

import bd.base.CuentaBase;

public class Cuenta extends CuentaBase {    
	public Cuenta() {
        this.fecha_liquidacion = "0000-00-00";
	}

	public Cuenta(Cuenta cuenta) {
		super(cuenta);
                this.fecha_liquidacion = "0000-00-00";
	}
}