package bd.base;
public class Caja_detalleBase {

	public Integer id = 0;
	public Integer id_caja = 0;
	public Integer id_forma = 0;
	public Integer id_tipo = 0;
	public Integer id_cuenta = 0;
	public Integer id_tipo_cuenta = 0;
        public Integer id_concepto = 0;
	public String concepto = "";
	public Float importe = 0f;
	public Float saldo = 0f;

	public Caja_detalleBase() {
	}

	public Caja_detalleBase(Caja_detalleBase caja_detallebase) {
		this.id = caja_detallebase.getId();
		this.id_caja = caja_detallebase.getId_caja();
		this.id_forma = caja_detallebase.getId_forma();
		this.id_tipo = caja_detallebase.getId_tipo();
		this.id_cuenta = caja_detallebase.getId_cuenta();
		this.concepto = caja_detallebase.getConcepto();
		this.importe = caja_detallebase.getImporte();
		this.saldo = caja_detallebase.getSaldo();

	}

	public Integer getId() {
		return this.id;
	}

	public Caja_detalleBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getId_caja() {
		return this.id_caja;
	}

	public Caja_detalleBase setId_caja(Integer id_caja) {
		this.id_caja = id_caja;
		return this;
	}

	public Integer getId_forma() {
		return this.id_forma;
	}

	public Caja_detalleBase setId_forma(Integer id_forma) {
		this.id_forma = id_forma;
		return this;
	}

	public Integer getId_tipo() {
		return this.id_tipo;
	}

	public Caja_detalleBase setId_tipo(Integer id_tipo) {
		this.id_tipo = id_tipo;
		return this;
	}

	public Integer getId_cuenta() {
		return this.id_cuenta;
	}

	public Caja_detalleBase setId_cuenta(Integer id_cuenta) {
		this.id_cuenta = id_cuenta;
		return this;
	}

	public String getConcepto() {
		return this.concepto;
	}

	public Caja_detalleBase setConcepto(String concepto) {
		this.concepto = concepto;
		return this;
	}

	public Float getImporte() {
		return this.importe;
	}

	public Caja_detalleBase setImporte(Float importe) {
		this.importe = importe;
		return this;
	}

	public Float getSaldo() {
		return this.saldo;
	}

	public Caja_detalleBase setSaldo(Float saldo) {
		this.saldo = saldo;
		return this;
	}
        public Integer getId_tipo_cuenta() {
            return id_tipo_cuenta;
        }

        public void setId_tipo_cuenta(Integer id_tipo_cuenta) {
            this.id_tipo_cuenta = id_tipo_cuenta;
        }
        public Integer getId_concepto() {
            return id_concepto;
        }

        public void setId_concepto(Integer id_concepto) {
            this.id_concepto = id_concepto;
        }

}