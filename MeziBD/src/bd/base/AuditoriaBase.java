package bd.base;
public class AuditoriaBase {

	public Integer id = 0;
	public Integer id_usuario = 0;
	public Integer id_tipo_usuario = 0;
	public Integer id_modulo = 0;
	public Integer id_accion = 0;
	public Integer id_referencia = 0;
	public String fecha = "";
	public String datos = "";

	public AuditoriaBase() {
	}

	public AuditoriaBase(AuditoriaBase auditoriabase) {
		this.id = auditoriabase.getId();
		this.id_usuario = auditoriabase.getId_usuario();
		this.id_tipo_usuario = auditoriabase.getId_tipo_usuario();
		this.id_modulo = auditoriabase.getId_modulo();
		this.id_accion = auditoriabase.getId_accion();
		this.id_referencia = auditoriabase.getId_referencia();
		this.fecha = auditoriabase.getFecha();
		this.datos = auditoriabase.getDatos();
	}

	public Integer getId() {
		return this.id;
	}

	public AuditoriaBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getId_usuario() {
		return this.id_usuario;
	}

	public AuditoriaBase setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
		return this;
	}

	public Integer getId_tipo_usuario() {
		return this.id_tipo_usuario;
	}

	public AuditoriaBase setId_tipo_usuario(Integer id_tipo_usuario) {
		this.id_tipo_usuario = id_tipo_usuario;
		return this;
	}

	public Integer getId_modulo() {
		return this.id_modulo;
	}

	public AuditoriaBase setId_modulo(Integer id_modulo) {
		this.id_modulo = id_modulo;
		return this;
	}

	public Integer getId_accion() {
		return this.id_accion;
	}

	public AuditoriaBase setId_accion(Integer id_accion) {
		this.id_accion = id_accion;
		return this;
	}

	public Integer getId_referencia() {
		return this.id_referencia;
	}

	public AuditoriaBase setId_referencia(Integer id_referencia) {
		this.id_referencia = id_referencia;
		return this;
	}

	public String getFecha() {
		return this.fecha;
	}

	public AuditoriaBase setFecha(String fecha) {
		this.fecha = fecha;
		return this;
	}

	public String getDatos() {
		return this.datos;
	}

	public AuditoriaBase setDatos(String datos) {
		this.datos = datos;
		return this;
	}
}