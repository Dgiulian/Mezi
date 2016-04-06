package bd.base;
public class UsuarioBase {

	public Integer id = 0;
	public Integer id_tipo_usuario = 0;
	public String usu_mail = "";
	public String usu_password = "";
	public String usu_fcreacion = "";
	public Integer usu_activo = 0;
	public String usu_hash_activa = "";

	public UsuarioBase() {
	}

	public UsuarioBase(UsuarioBase usuariobase) {
		this.id = usuariobase.getId();
		this.id_tipo_usuario = usuariobase.getId_tipo_usuario();
		this.usu_mail = usuariobase.getUsu_mail();
		this.usu_password = usuariobase.getUsu_password();
		this.usu_fcreacion = usuariobase.getUsu_fcreacion();
		this.usu_activo = usuariobase.getUsu_activo();
		this.usu_hash_activa = usuariobase.getUsu_hash_activa();
	}

	public Integer getId() {
		return this.id;
	}

	public UsuarioBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getId_tipo_usuario() {
		return this.id_tipo_usuario;
	}

	public UsuarioBase setId_tipo_usuario(Integer id_tipo_usuario) {
		this.id_tipo_usuario = id_tipo_usuario;
		return this;
	}

	public String getUsu_mail() {
		return this.usu_mail;
	}

	public UsuarioBase setUsu_mail(String usu_mail) {
		this.usu_mail = usu_mail;
		return this;
	}

	public String getUsu_password() {
		return this.usu_password;
	}

	public UsuarioBase setUsu_password(String usu_password) {
		this.usu_password = usu_password;
		return this;
	}

	public String getUsu_fcreacion() {
		return this.usu_fcreacion;
	}

	public UsuarioBase setUsu_fcreacion(String usu_fcreacion) {
		this.usu_fcreacion = usu_fcreacion;
		return this;
	}

	public Integer getUsu_activo() {
		return this.usu_activo;
	}

	public UsuarioBase setUsu_activo(Integer usu_activo) {
		this.usu_activo = usu_activo;
		return this;
	}

	public String getUsu_hash_activa() {
		return this.usu_hash_activa;
	}

	public UsuarioBase setUsu_hash_activa(String usu_hash_activa) {
		this.usu_hash_activa = usu_hash_activa;
		return this;
	}
}