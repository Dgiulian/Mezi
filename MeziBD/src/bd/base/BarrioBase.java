package bd.base;
public class BarrioBase {

	public Integer id = 0;
	public Integer id_localidad = 0;
	public String nombre = "";
	public String nombre_municipal = "";
	public Integer activo = 0;

	public BarrioBase() {
	}

	public BarrioBase(BarrioBase barriobase) {
		this.id = barriobase.getId();
		this.id_localidad = barriobase.getId_localidad();
		this.nombre = barriobase.getNombre();
		this.nombre_municipal = barriobase.getNombre_municipal();
		this.activo = barriobase.getActivo();
	}

	public Integer getId() {
		return this.id;
	}

	public BarrioBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getId_localidad() {
		return this.id_localidad;
	}

	public BarrioBase setId_localidad(Integer id_localidad) {
		this.id_localidad = id_localidad;
		return this;
	}

	public String getNombre() {
		return this.nombre;
	}

	public BarrioBase setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public String getNombre_municipal() {
		return this.nombre_municipal;
	}

	public BarrioBase setNombre_municipal(String nombre_municipal) {
		this.nombre_municipal = nombre_municipal;
		return this;
	}

	public Integer getActivo() {
		return this.activo;
	}

	public BarrioBase setActivo(Integer activo) {
		this.activo = activo;
		return this;
	}
}