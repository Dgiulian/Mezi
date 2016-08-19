package bd.base;

import utils.Fecha;
public class Cuenta_interna_detalleBase {

	public Integer id = 0;
	public Integer id_cuenta = 0;
	public Fecha fecha = null;
	public String concepto = "";
	public Float debe = 0f;
	public Float haber = 0f;
	public Float saldo = 0f;

	public Cuenta_interna_detalleBase() {
	}

	public Cuenta_interna_detalleBase(
			Cuenta_interna_detalleBase cuenta_interna_detallebase) {
		this.id = cuenta_interna_detallebase.getId();
		this.id_cuenta = cuenta_interna_detallebase.getId_cuenta();
		this.fecha = cuenta_interna_detallebase.getFecha();
		this.concepto = cuenta_interna_detallebase.getConcepto();
		this.debe = cuenta_interna_detallebase.getDebe();
		this.haber = cuenta_interna_detallebase.getHaber();
		this.saldo = cuenta_interna_detallebase.getSaldo();
	}

	public Integer getId() {
		return this.id;
	}

	public Cuenta_interna_detalleBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getId_cuenta() {
		return this.id_cuenta;
	}

	public Cuenta_interna_detalleBase setId_cuenta(Integer id_cuenta) {
		this.id_cuenta = id_cuenta;
		return this;
	}

	public Fecha getFecha() {
		return this.fecha;
	}

	public Cuenta_interna_detalleBase setFecha(Fecha fecha) {
		this.fecha = fecha;
		return this;
	}

	public String getConcepto() {
		return this.concepto;
	}

	public Cuenta_interna_detalleBase setConcepto(String concepto) {
		this.concepto = concepto;
		return this;
	}

	public Float getDebe() {
		return this.debe;
	}

	public Cuenta_interna_detalleBase setDebe(Float debe) {
		this.debe = debe;
		return this;
	}

	public Float getHaber() {
		return this.haber;
	}

	public Cuenta_interna_detalleBase setHaber(Float haber) {
		this.haber = haber;
		return this;
	}

	public Float getSaldo() {
		return this.saldo;
	}

	public Cuenta_interna_detalleBase setSaldo(Float saldo) {
		this.saldo = saldo;
		return this;
	}
}