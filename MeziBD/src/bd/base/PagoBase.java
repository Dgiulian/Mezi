package bd.base;
public class PagoBase {

	public Integer id = 0;
	public Integer id_cuenta = 0;
	public String fecha = "";
	public String fecha_creacion = "";
	public Integer punitorio_aplica = 0;
	public Float punitorio_monto = 0f;
	public Float efectivo = 0f;
	public Float cheque_mnt = 0f;
	public String cheque_ban = "";
	public String cheque_num = "";
	public Float transf_mnt = 0f;
	public String transf_ban = "";
	public String transf_num = "";

	public PagoBase() {
	}

	public PagoBase(PagoBase pagobase) {
		this.id = pagobase.getId();
		this.id_cuenta = pagobase.getId_cuenta();
		this.fecha = pagobase.getFecha();
		this.fecha_creacion = pagobase.getFecha_creacion();
		this.punitorio_aplica = pagobase.getPunitorio_aplica();
		this.punitorio_monto = pagobase.getPunitorio_monto();
		this.efectivo = pagobase.getEfectivo();
		this.cheque_mnt = pagobase.getCheque_mnt();
		this.cheque_ban = pagobase.getCheque_ban();
		this.cheque_num = pagobase.getCheque_num();
		this.transf_mnt = pagobase.getTransf_mnt();
		this.transf_ban = pagobase.getTransf_ban();
		this.transf_num = pagobase.getTransf_num();
	}

	public Integer getId() {
		return this.id;
	}

	public PagoBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getId_cuenta() {
		return this.id_cuenta;
	}

	public PagoBase setId_cuenta(Integer id_cuenta) {
		this.id_cuenta = id_cuenta;
		return this;
	}

	public String getFecha() {
		return this.fecha;
	}

	public PagoBase setFecha(String fecha) {
		this.fecha = fecha;
		return this;
	}

	public String getFecha_creacion() {
		return this.fecha_creacion;
	}

	public PagoBase setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
		return this;
	}

	public Integer getPunitorio_aplica() {
		return this.punitorio_aplica;
	}

	public PagoBase setPunitorio_aplica(Integer punitorio_aplica) {
		this.punitorio_aplica = punitorio_aplica;
		return this;
	}

	public Float getPunitorio_monto() {
		return this.punitorio_monto;
	}

	public PagoBase setPunitorio_monto(Float punitorio_monto) {
		this.punitorio_monto = punitorio_monto;
		return this;
	}

	public Float getEfectivo() {
		return this.efectivo;
	}

	public PagoBase setEfectivo(Float efectivo) {
		this.efectivo = efectivo;
		return this;
	}

	public Float getCheque_mnt() {
		return this.cheque_mnt;
	}

	public PagoBase setCheque_mnt(Float cheque_mnt) {
		this.cheque_mnt = cheque_mnt;
		return this;
	}

	public String getCheque_ban() {
		return this.cheque_ban;
	}

	public PagoBase setCheque_ban(String cheque_ban) {
		this.cheque_ban = cheque_ban;
		return this;
	}

	public String getCheque_num() {
		return this.cheque_num;
	}

	public PagoBase setCheque_num(String cheque_num) {
		this.cheque_num = cheque_num;
		return this;
	}

	public Float getTransf_mnt() {
		return this.transf_mnt;
	}

	public PagoBase setTransf_mnt(Float transf_mnt) {
		this.transf_mnt = transf_mnt;
		return this;
	}

	public String getTransf_ban() {
		return this.transf_ban;
	}

	public PagoBase setTransf_ban(String transf_ban) {
		this.transf_ban = transf_ban;
		return this;
	}

	public String getTransf_num() {
		return this.transf_num;
	}

	public PagoBase setTransf_num(String transf_num) {
		this.transf_num = transf_num;
		return this;
	}
}