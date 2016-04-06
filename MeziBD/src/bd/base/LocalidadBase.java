package bd.base;
public class LocalidadBase {

	public Integer id = 0;
	public Integer id_provincia = 0;
	public String descripcion = "";
	public Integer activo = 0;

	public LocalidadBase() {
	}

	public LocalidadBase(LocalidadBase localidadbase) {
		this.id = localidadbase.getId();
		this.id_provincia = localidadbase.getId_provincia();
		this.descripcion = localidadbase.getDescripcion();
		this.activo = localidadbase.getActivo();
	}

	public Integer getId() {
		return this.id;
	}

	public LocalidadBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getId_provincia() {
		return this.id_provincia;
	}

	public LocalidadBase setId_provincia(Integer id_provincia) {
		this.id_provincia = id_provincia;
		return this;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public LocalidadBase setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		return this;
	}

	public Integer getActivo() {
		return this.activo;
	}

	public LocalidadBase setActivo(Integer activo) {
		this.activo = activo;
		return this;
	}
}