package bd.base;
public class ModuloBase {

	public Integer id = 0;
	public Integer codigo = 0;
	public String nombre = "";
	public String descripcion = "";

	public ModuloBase() {
	}

	public ModuloBase(ModuloBase modulobase) {
		this.id = modulobase.getId();
		this.codigo = modulobase.getCodigo();
		this.nombre = modulobase.getNombre();
		this.descripcion = modulobase.getDescripcion();
	}

	public Integer getId() {
		return this.id;
	}

	public ModuloBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public ModuloBase setCodigo(Integer codigo) {
		this.codigo = codigo;
		return this;
	}

	public String getNombre() {
		return this.nombre;
	}

	public ModuloBase setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public ModuloBase setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		return this;
	}
}