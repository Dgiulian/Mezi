package bd.base;
public class Recibo_detalleBase {

	public Integer id = 0;
	public Integer id_recibo = 0;
	public Integer id_cuenta_detalle = 0;
	public String fecha = "";
	public Integer id_concepto = 0;
	public String concepto = "";
	public Float debe = 0f;
	public Float haber = 0f;
	public Float saldo = 0f;

	public Recibo_detalleBase() {
	}

	public Recibo_detalleBase(Recibo_detalleBase recibo_detallebase) {
		this.id = recibo_detallebase.getId();
		this.id_recibo = recibo_detallebase.getId_recibo();
		this.id_cuenta_detalle = recibo_detallebase.getId_cuenta_detalle();
		this.fecha = recibo_detallebase.getFecha();
		this.id_concepto = recibo_detallebase.getId_concepto();
		this.concepto = recibo_detallebase.getConcepto();
		this.debe = recibo_detallebase.getDebe();
		this.haber = recibo_detallebase.getHaber();
		this.saldo = recibo_detallebase.getSaldo();
	}

	public Integer getId() {
		return this.id;
	}

	public Recibo_detalleBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getId_recibo() {
		return this.id_recibo;
	}

	public Recibo_detalleBase setId_recibo(Integer id_recibo) {
		this.id_recibo = id_recibo;
		return this;
	}

	public Integer getId_cuenta_detalle() {
		return this.id_cuenta_detalle;
	}

	public Recibo_detalleBase setId_cuenta_detalle(Integer id_cuenta_detalle) {
		this.id_cuenta_detalle = id_cuenta_detalle;
		return this;
	}

	public String getFecha() {
		return this.fecha;
	}

	public Recibo_detalleBase setFecha(String fecha) {
		this.fecha = fecha;
		return this;
	}

	public Integer getId_concepto() {
		return this.id_concepto;
	}

	public Recibo_detalleBase setId_concepto(Integer id_concepto) {
		this.id_concepto = id_concepto;
		return this;
	}

	public String getConcepto() {
		return this.concepto;
	}

	public Recibo_detalleBase setConcepto(String concepto) {
		this.concepto = concepto;
		return this;
	}

	public Float getDebe() {
		return this.debe;
	}

	public Recibo_detalleBase setDebe(Float debe) {
		this.debe = debe;
		return this;
	}

	public Float getHaber() {
		return this.haber;
	}

	public Recibo_detalleBase setHaber(Float haber) {
		this.haber = haber;
		return this;
	}

	public Float getSaldo() {
		return this.saldo;
	}

	public Recibo_detalleBase setSaldo(Float saldo) {
		this.saldo = saldo;
		return this;
	}
}