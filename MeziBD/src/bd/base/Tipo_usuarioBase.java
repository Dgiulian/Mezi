package bd.base;
public class Tipo_usuarioBase {

	public Integer id = 0;
	public String codigo = "";
	public String descripcion = "";
	public Integer activo = 0;

	public Tipo_usuarioBase() {
	}

	public Tipo_usuarioBase(Tipo_usuarioBase tipo_usuariobase) {
		this.id = tipo_usuariobase.getId();
		this.codigo = tipo_usuariobase.getCodigo();
		this.descripcion = tipo_usuariobase.getDescripcion();
		this.activo = tipo_usuariobase.getActivo();
	}

	public Integer getId() {
		return this.id;
	}

	public Tipo_usuarioBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public Tipo_usuarioBase setCodigo(String codigo) {
		this.codigo = codigo;
		return this;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public Tipo_usuarioBase setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		return this;
	}

	public Integer getActivo() {
		return this.activo;
	}

	public Tipo_usuarioBase setActivo(Integer activo) {
		this.activo = activo;
		return this;
	}
}