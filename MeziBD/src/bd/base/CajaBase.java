package bd.base;

import utils.Fecha;
public class CajaBase {

	public Integer id = 0;
	public Integer id_usuario = 0;
	public Integer id_estado = 0;
	public Fecha fecha = null;
	public String motivo_diferencia = "";
	public Float efectivo_anterior = 0f;
	public Float transferencia_anterior = 0f;
	public Float efectivo_cierre = 0f;
	public Float cheque_anterior = 0f;
	public Float cheque_cierre = 0f;
	public Float transferencia_cierre = 0f;

	public CajaBase() {
	}

	public CajaBase(CajaBase cajabase) {
		this.id = cajabase.getId();
		this.id_usuario = cajabase.getId_usuario();
		this.id_estado = cajabase.getId_estado();
		this.fecha = cajabase.getFecha();
		this.motivo_diferencia = cajabase.getMotivo_diferencia();
		this.efectivo_anterior = cajabase.getEfectivo_anterior();
		this.transferencia_anterior = cajabase.getTransferencia_anterior();
		this.efectivo_cierre = cajabase.getEfectivo_cierre();
		this.cheque_anterior = cajabase.getCheque_anterior();
		this.cheque_cierre = cajabase.getCheque_cierre();
		this.transferencia_cierre = cajabase.getTransferencia_cierre();
	}

	public Integer getId() {
		return this.id;
	}

	public CajaBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getId_usuario() {
		return this.id_usuario;
	}

	public CajaBase setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
		return this;
	}

	public Integer getId_estado() {
		return this.id_estado;
	}

	public CajaBase setId_estado(Integer id_estado) {
		this.id_estado = id_estado;
		return this;
	}

	public Fecha getFecha() {
		return this.fecha;
	}

	public CajaBase setFecha(Fecha fecha) {
		this.fecha = fecha;
		return this;
	}

	public String getMotivo_diferencia() {
		return this.motivo_diferencia;
	}

	public CajaBase setMotivo_diferencia(String motivo_diferencia) {
		this.motivo_diferencia = motivo_diferencia;
		return this;
	}

	public Float getEfectivo_anterior() {
		return this.efectivo_anterior;
	}

	public CajaBase setEfectivo_anterior(Float efectivo_anterior) {
		this.efectivo_anterior = efectivo_anterior;
		return this;
	}

	public Float getTransferencia_anterior() {
		return this.transferencia_anterior;
	}

	public CajaBase setTransferencia_anterior(Float transferencia_anterior) {
		this.transferencia_anterior = transferencia_anterior;
		return this;
	}

	public Float getEfectivo_cierre() {
		return this.efectivo_cierre;
	}

	public CajaBase setEfectivo_cierre(Float efectivo_cierre) {
		this.efectivo_cierre = efectivo_cierre;
		return this;
	}

	public Float getCheque_anterior() {
		return this.cheque_anterior;
	}

	public CajaBase setCheque_anterior(Float cheque_anterior) {
		this.cheque_anterior = cheque_anterior;
		return this;
	}

	public Float getCheque_cierre() {
		return this.cheque_cierre;
	}

	public CajaBase setCheque_cierre(Float cheque_cierre) {
		this.cheque_cierre = cheque_cierre;
		return this;
	}

	public Float getTransferencia_cierre() {
		return this.transferencia_cierre;
	}

	public CajaBase setTransferencia_cierre(Float transferencia_cierre) {
		this.transferencia_cierre = transferencia_cierre;
		return this;
	}
}