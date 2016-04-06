package bd.base;
public class Cuenta_detalleBase {

	public Integer id = 0;
	public Integer id_cuenta = 0;
	public String fecha = "";
	public Integer id_concepto = 0;
	public String concepto = "";
	public Float debe = 0f;
	public Float haber = 0f;
	public Integer id_referencia = 0;
	public String fecha_creacion = "";
	public String observaciones = "";

	public Cuenta_detalleBase() {
	}

	public Cuenta_detalleBase(Cuenta_detalleBase cuenta_detallebase) {
		this.id = cuenta_detallebase.getId();
		this.id_cuenta = cuenta_detallebase.getId_cuenta();
		this.fecha = cuenta_detallebase.getFecha();
		this.id_concepto = cuenta_detallebase.getId_concepto();
		this.concepto = cuenta_detallebase.getConcepto();
		this.debe = cuenta_detallebase.getDebe();
		this.haber = cuenta_detallebase.getHaber();
		this.id_referencia = cuenta_detallebase.getId_referencia();
		this.fecha_creacion = cuenta_detallebase.getFecha_creacion();
		this.observaciones = cuenta_detallebase.getObservaciones();
	}

	public Integer getId() {
		return this.id;
	}

	public Cuenta_detalleBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getId_cuenta() {
		return this.id_cuenta;
	}

	public Cuenta_detalleBase setId_cuenta(Integer id_cuenta) {
		this.id_cuenta = id_cuenta;
		return this;
	}

	public String getFecha() {
		return this.fecha;
	}

	public Cuenta_detalleBase setFecha(String fecha) {
		this.fecha = fecha;
		return this;
	}

	public Integer getId_concepto() {
		return this.id_concepto;
	}

	public Cuenta_detalleBase setId_concepto(Integer id_concepto) {
		this.id_concepto = id_concepto;
		return this;
	}

	public String getConcepto() {
		return this.concepto;
	}

	public Cuenta_detalleBase setConcepto(String concepto) {
		this.concepto = concepto;
		return this;
	}

	public Float getDebe() {
		return this.debe;
	}

	public Cuenta_detalleBase setDebe(Float debe) {
		this.debe = debe;
		return this;
	}

	public Float getHaber() {
		return this.haber;
	}

	public Cuenta_detalleBase setHaber(Float haber) {
		this.haber = haber;
		return this;
	}

	public Integer getId_referencia() {
		return this.id_referencia;
	}

	public Cuenta_detalleBase setId_referencia(Integer id_referencia) {
		this.id_referencia = id_referencia;
		return this;
	}

	public String getFecha_creacion() {
		return this.fecha_creacion;
	}

	public Cuenta_detalleBase setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
		return this;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public Cuenta_detalleBase setObservaciones(String observaciones) {
		this.observaciones = observaciones;
		return this;
	}
}