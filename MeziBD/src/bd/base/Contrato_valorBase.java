package bd.base;
public class Contrato_valorBase {

	public Integer id = 0;
	public Integer id_contrato = 0;
	public String desde = "";
	public String hasta = "";
	public Float monto = 0f;

	public Contrato_valorBase() {
	}

	public Contrato_valorBase(Contrato_valorBase contrato_valorbase) {
		this.id = contrato_valorbase.getId();
		this.id_contrato = contrato_valorbase.getId_contrato();
		this.desde = contrato_valorbase.getDesde();
		this.hasta = contrato_valorbase.getHasta();
		this.monto = contrato_valorbase.getMonto();
	}

	public Integer getId() {
		return this.id;
	}

	public Contrato_valorBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getId_contrato() {
		return this.id_contrato;
	}

	public Contrato_valorBase setId_contrato(Integer id_contrato) {
		this.id_contrato = id_contrato;
		return this;
	}

	public String getDesde() {
		return this.desde;
	}

	public Contrato_valorBase setDesde(String desde) {
		this.desde = desde;
		return this;
	}

	public String getHasta() {
		return this.hasta;
	}

	public Contrato_valorBase setHasta(String hasta) {
		this.hasta = hasta;
		return this;
	}

	public Float getMonto() {
		return this.monto;
	}

	public Contrato_valorBase setMonto(Float monto) {
		this.monto = monto;
		return this;
	}
}