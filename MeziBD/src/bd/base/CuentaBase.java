package bd.base;
public class CuentaBase {

	public Integer id = 0;
	public Integer id_cliente = 0;
	public Integer id_tipo_cliente = 0;
	public String descripcion = "";
	public Integer id_contrato = 0;
	public Integer id_usuario = 0;
	public Integer id_tipo = 0;
	public String fecha_creacion = "";
	public String fecha_liquidacion = "";

	public CuentaBase() {
	}

	public CuentaBase(CuentaBase cuentabase) {
		this.id = cuentabase.getId();
		this.id_cliente = cuentabase.getId_cliente();
		this.id_tipo_cliente = cuentabase.getId_tipo_cliente();
		this.descripcion = cuentabase.getDescripcion();
		this.id_contrato = cuentabase.getId_contrato();
		this.id_usuario = cuentabase.getId_usuario();
		this.id_tipo = cuentabase.getId_tipo();
		this.fecha_creacion = cuentabase.getFecha_creacion();
		this.fecha_liquidacion = cuentabase.getFecha_liquidacion();
	}

	public Integer getId() {
		return this.id;
	}

	public CuentaBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getId_cliente() {
		return this.id_cliente;
	}

	public CuentaBase setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
		return this;
	}

	public Integer getId_tipo_cliente() {
		return this.id_tipo_cliente;
	}

	public CuentaBase setId_tipo_cliente(Integer id_tipo_cliente) {
		this.id_tipo_cliente = id_tipo_cliente;
		return this;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public CuentaBase setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		return this;
	}

	public Integer getId_contrato() {
		return this.id_contrato;
	}

	public CuentaBase setId_contrato(Integer id_contrato) {
		this.id_contrato = id_contrato;
		return this;
	}

	public Integer getId_usuario() {
		return this.id_usuario;
	}

	public CuentaBase setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
		return this;
	}

	public Integer getId_tipo() {
		return this.id_tipo;
	}

	public CuentaBase setId_tipo(Integer id_tipo) {
		this.id_tipo = id_tipo;
		return this;
	}

	public String getFecha_creacion() {
		return this.fecha_creacion;
	}

	public CuentaBase setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
		return this;
	}

	public String getFecha_liquidacion() {
		return this.fecha_liquidacion;
	}

	public CuentaBase setFecha_liquidacion(String fecha_liquidacion) {
		this.fecha_liquidacion = fecha_liquidacion;
		return this;
	}
}