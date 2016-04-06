package bd.base;
public class Tipo_servicioBase {

	public Integer id = 0;
	public String nombre = "";
	public String descripcion = "";
	public Integer activo = 0;

	public Tipo_servicioBase() {
	}

	public Tipo_servicioBase(Tipo_servicioBase tipo_serviciobase) {
		this.id = tipo_serviciobase.getId();
		this.nombre = tipo_serviciobase.getNombre();
		this.descripcion = tipo_serviciobase.getDescripcion();
		this.activo = tipo_serviciobase.getActivo();
	}

	public Integer getId() {
		return this.id;
	}

	public Tipo_servicioBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getNombre() {
		return this.nombre;
	}

	public Tipo_servicioBase setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public Tipo_servicioBase setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		return this;
	}

	public Integer getActivo() {
		return this.activo;
	}

	public Tipo_servicioBase setActivo(Integer activo) {
		this.activo = activo;
		return this;
	}
}