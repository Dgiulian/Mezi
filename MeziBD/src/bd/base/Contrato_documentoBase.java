package bd.base;
public class Contrato_documentoBase {

	public Integer id = 0;
	public Integer id_contrato = 0;
	public String desde = "";
	public String hasta = "";
	public Float monto = 0f;

	public Contrato_documentoBase() {
	}

	public Contrato_documentoBase(Contrato_documentoBase contrato_documentobase) {
		this.id = contrato_documentobase.getId();
		this.id_contrato = contrato_documentobase.getId_contrato();
		this.desde = contrato_documentobase.getDesde();
		this.hasta = contrato_documentobase.getHasta();
		this.monto = contrato_documentobase.getMonto();
	}

	public Integer getId() {
		return this.id;
	}

	public Contrato_documentoBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getId_contrato() {
		return this.id_contrato;
	}

	public Contrato_documentoBase setId_contrato(Integer id_contrato) {
		this.id_contrato = id_contrato;
		return this;
	}

	public String getDesde() {
		return this.desde;
	}

	public Contrato_documentoBase setDesde(String desde) {
		this.desde = desde;
		return this;
	}

	public String getHasta() {
		return this.hasta;
	}

	public Contrato_documentoBase setHasta(String hasta) {
		this.hasta = hasta;
		return this;
	}

	public Float getMonto() {
		return this.monto;
	}

	public Contrato_documentoBase setMonto(Float monto) {
		this.monto = monto;
		return this;
	}
}