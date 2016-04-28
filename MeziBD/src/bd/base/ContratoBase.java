package bd.base;
public class ContratoBase {

	public Integer id = 0;
	public Integer id_inquilino = 0;
	public Integer id_propiedad = 0;
	public Integer id_propietario = 0;
	public Integer id_estado = 0;
	public Integer numero = 0;
	public String fecha_inicio = "";
	public String fecha_fin = "";
	public Integer meses = 0;
	public Float punitorio_monto = 0f;
	public Integer punitorio_desde = 0;
	public Integer id_vendedor = 0;
	public Float comision_vendedor = 0f;
	public Float gastos_escribania_inquilino = 0f;
	public Float gastos_sellado_inquilino = 0f;
	public Float comision_monto_inquilino = 0f;
	public String comision_desde_inquilino = "";
	public Integer comision_cuotas_inquilino = 0;
	public Integer comision_menusal_inquilino = 0;
	public Float gastos_escribania_propietario = 0f;
	public Float gastos_sellado_propietario = 0f;
	public Float comision_monto_propietario = 0f;
	public String comision_desde_propietario = "";
	public Integer comision_cuotas_propietario = 0;
	public Float comision_mensual_propietario = 0f;
	public Float deposito_monto = 0f;
	public String deposito_desde = "";
	public Integer deposito_cuotas = 0;
	public Integer asegura_renta = 0;
	public String observaciones = "";

	public ContratoBase() {
	}

	public ContratoBase(ContratoBase contratobase) {
		this.id = contratobase.getId();
		this.id_inquilino = contratobase.getId_inquilino();
		this.id_propiedad = contratobase.getId_propiedad();
		this.id_propietario = contratobase.getId_propietario();
		this.id_estado = contratobase.getId_estado();
		this.numero = contratobase.getNumero();
		this.fecha_inicio = contratobase.getFecha_inicio();
		this.fecha_fin = contratobase.getFecha_fin();
		this.meses = contratobase.getMeses();
		this.punitorio_monto = contratobase.getPunitorio_monto();
		this.punitorio_desde = contratobase.getPunitorio_desde();
		this.id_vendedor = contratobase.getId_vendedor();
		this.comision_vendedor = contratobase.getComision_vendedor();
		this.gastos_escribania_inquilino = contratobase
				.getGastos_escribania_inquilino();
		this.gastos_sellado_inquilino = contratobase
				.getGastos_sellado_inquilino();
		this.comision_monto_inquilino = contratobase
				.getComision_monto_inquilino();
		this.comision_desde_inquilino = contratobase
				.getComision_desde_inquilino();
		this.comision_cuotas_inquilino = contratobase
				.getComision_cuotas_inquilino();
		this.comision_menusal_inquilino = contratobase
				.getComision_menusal_inquilino();
		this.gastos_escribania_propietario = contratobase
				.getGastos_escribania_propietario();
		this.gastos_sellado_propietario = contratobase
				.getGastos_sellado_propietario();
		this.comision_monto_propietario = contratobase
				.getComision_monto_propietario();
		this.comision_desde_propietario = contratobase
				.getComision_desde_propietario();
		this.comision_cuotas_propietario = contratobase
				.getComision_cuotas_propietario();
		this.comision_mensual_propietario = contratobase
				.getComision_mensual_propietario();
		this.deposito_monto = contratobase.getDeposito_monto();
		this.deposito_desde = contratobase.getDeposito_desde();
		this.deposito_cuotas = contratobase.getDeposito_cuotas();
		this.asegura_renta = contratobase.getAsegura_renta();
		this.observaciones = contratobase.getObservaciones();
	}

	public Integer getId() {
		return this.id;
	}

	public ContratoBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getId_inquilino() {
		return this.id_inquilino;
	}

	public ContratoBase setId_inquilino(Integer id_inquilino) {
		this.id_inquilino = id_inquilino;
		return this;
	}

	public Integer getId_propiedad() {
		return this.id_propiedad;
	}

	public ContratoBase setId_propiedad(Integer id_propiedad) {
		this.id_propiedad = id_propiedad;
		return this;
	}

	public Integer getId_propietario() {
		return this.id_propietario;
	}

	public ContratoBase setId_propietario(Integer id_propietario) {
		this.id_propietario = id_propietario;
		return this;
	}

	public Integer getId_estado() {
		return this.id_estado;
	}

	public ContratoBase setId_estado(Integer id_estado) {
		this.id_estado = id_estado;
		return this;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public ContratoBase setNumero(Integer numero) {
		this.numero = numero;
		return this;
	}

	public String getFecha_inicio() {
		return this.fecha_inicio;
	}

	public ContratoBase setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
		return this;
	}

	public String getFecha_fin() {
		return this.fecha_fin;
	}

	public ContratoBase setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
		return this;
	}

	public Integer getMeses() {
		return this.meses;
	}

	public ContratoBase setMeses(Integer meses) {
		this.meses = meses;
		return this;
	}

	public Float getPunitorio_monto() {
		return this.punitorio_monto;
	}

	public ContratoBase setPunitorio_monto(Float punitorio_monto) {
		this.punitorio_monto = punitorio_monto;
		return this;
	}

	public Integer getPunitorio_desde() {
		return this.punitorio_desde;
	}

	public ContratoBase setPunitorio_desde(Integer punitorio_desde) {
		this.punitorio_desde = punitorio_desde;
		return this;
	}

	public Integer getId_vendedor() {
		return this.id_vendedor;
	}

	public ContratoBase setId_vendedor(Integer id_vendedor) {
		this.id_vendedor = id_vendedor;
		return this;
	}

	public Float getComision_vendedor() {
		return this.comision_vendedor;
	}

	public ContratoBase setComision_vendedor(Float comision_vendedor) {
		this.comision_vendedor = comision_vendedor;
		return this;
	}

	public Float getGastos_escribania_inquilino() {
		return this.gastos_escribania_inquilino;
	}

	public ContratoBase setGastos_escribania_inquilino(
			Float gastos_escribania_inquilino) {
		this.gastos_escribania_inquilino = gastos_escribania_inquilino;
		return this;
	}

	public Float getGastos_sellado_inquilino() {
		return this.gastos_sellado_inquilino;
	}

	public ContratoBase setGastos_sellado_inquilino(
			Float gastos_sellado_inquilino) {
		this.gastos_sellado_inquilino = gastos_sellado_inquilino;
		return this;
	}

	public Float getComision_monto_inquilino() {
		return this.comision_monto_inquilino;
	}

	public ContratoBase setComision_monto_inquilino(
			Float comision_monto_inquilino) {
		this.comision_monto_inquilino = comision_monto_inquilino;
		return this;
	}

	public String getComision_desde_inquilino() {
		return this.comision_desde_inquilino;
	}

	public ContratoBase setComision_desde_inquilino(
			String comision_desde_inquilino) {
		this.comision_desde_inquilino = comision_desde_inquilino;
		return this;
	}

	public Integer getComision_cuotas_inquilino() {
		return this.comision_cuotas_inquilino;
	}

	public ContratoBase setComision_cuotas_inquilino(
			Integer comision_cuotas_inquilino) {
		this.comision_cuotas_inquilino = comision_cuotas_inquilino;
		return this;
	}

	public Integer getComision_menusal_inquilino() {
		return this.comision_menusal_inquilino;
	}

	public ContratoBase setComision_menusal_inquilino(
			Integer comision_menusal_inquilino) {
		this.comision_menusal_inquilino = comision_menusal_inquilino;
		return this;
	}

	public Float getGastos_escribania_propietario() {
		return this.gastos_escribania_propietario;
	}

	public ContratoBase setGastos_escribania_propietario(
			Float gastos_escribania_propietario) {
		this.gastos_escribania_propietario = gastos_escribania_propietario;
		return this;
	}

	public Float getGastos_sellado_propietario() {
		return this.gastos_sellado_propietario;
	}

	public ContratoBase setGastos_sellado_propietario(
			Float gastos_sellado_propietario) {
		this.gastos_sellado_propietario = gastos_sellado_propietario;
		return this;
	}

	public Float getComision_monto_propietario() {
		return this.comision_monto_propietario;
	}

	public ContratoBase setComision_monto_propietario(
			Float comision_monto_propietario) {
		this.comision_monto_propietario = comision_monto_propietario;
		return this;
	}

	public String getComision_desde_propietario() {
		return this.comision_desde_propietario;
	}

	public ContratoBase setComision_desde_propietario(
			String comision_desde_propietario) {
		this.comision_desde_propietario = comision_desde_propietario;
		return this;
	}

	public Integer getComision_cuotas_propietario() {
		return this.comision_cuotas_propietario;
	}

	public ContratoBase setComision_cuotas_propietario(
			Integer comision_cuotas_propietario) {
		this.comision_cuotas_propietario = comision_cuotas_propietario;
		return this;
	}

	public Float getComision_mensual_propietario() {
		return this.comision_mensual_propietario;
	}

	public ContratoBase setComision_mensual_propietario(
			Float comision_mensual_propietario) {
		this.comision_mensual_propietario = comision_mensual_propietario;
		return this;
	}

	public Float getDeposito_monto() {
		return this.deposito_monto;
	}

	public ContratoBase setDeposito_monto(Float deposito_monto) {
		this.deposito_monto = deposito_monto;
		return this;
	}

	public String getDeposito_desde() {
		return this.deposito_desde;
	}

	public ContratoBase setDeposito_desde(String deposito_desde) {
		this.deposito_desde = deposito_desde;
		return this;
	}

	public Integer getDeposito_cuotas() {
		return this.deposito_cuotas;
	}

	public ContratoBase setDeposito_cuotas(Integer deposito_cuotas) {
		this.deposito_cuotas = deposito_cuotas;
		return this;
	}

	public Integer getAsegura_renta() {
		return this.asegura_renta;
	}

	public ContratoBase setAsegura_renta(Integer asegura_renta) {
		this.asegura_renta = asegura_renta;
		return this;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public ContratoBase setObservaciones(String observaciones) {
		this.observaciones = observaciones;
		return this;
	}
}