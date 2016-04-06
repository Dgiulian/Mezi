package bd.base;
public class Contrato_gastoBase {

	public Integer id = 0;
	public Integer id_contrato = 0;
	public Integer id_aplica = 0;
	public String concepto = "";
	public Float importe = 0f;

	public Contrato_gastoBase() {
	}

	public Contrato_gastoBase(Contrato_gastoBase contrato_gastobase) {
		this.id = contrato_gastobase.getId();
		this.id_contrato = contrato_gastobase.getId_contrato();
		this.id_aplica = contrato_gastobase.getId_aplica();
		this.concepto = contrato_gastobase.getConcepto();
		this.importe = contrato_gastobase.getImporte();
	}

	public Integer getId() {
		return this.id;
	}

	public Contrato_gastoBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getId_contrato() {
		return this.id_contrato;
	}

	public Contrato_gastoBase setId_contrato(Integer id_contrato) {
		this.id_contrato = id_contrato;
		return this;
	}

	public Integer getId_aplica() {
		return this.id_aplica;
	}

	public Contrato_gastoBase setId_aplica(Integer id_aplica) {
		this.id_aplica = id_aplica;
		return this;
	}

	public String getConcepto() {
		return this.concepto;
	}

	public Contrato_gastoBase setConcepto(String concepto) {
		this.concepto = concepto;
		return this;
	}

	public Float getImporte() {
		return this.importe;
	}

	public Contrato_gastoBase setImporte(Float importe) {
		this.importe = importe;
		return this;
	}
}