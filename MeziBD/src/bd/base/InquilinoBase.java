package bd.base;
public class InquilinoBase {

	public Integer id = 0;
	public Integer carpeta = 0;
	public String nombre = "";
	public String apellido = "";
	public String dni = "";
	public String cuil = "";
	public String direccion = "";
	public Integer id_localidad = 0;
	public String lugar_trabajo = "";
	public String telefono = "";
	public Integer id_tipo_persona = 0;
	public Integer calificacion = 0;
	public String observaciones = "";

	public InquilinoBase() {
	}

	public InquilinoBase(InquilinoBase inquilinobase) {
		this.id = inquilinobase.getId();
		this.carpeta = inquilinobase.getCarpeta();
		this.nombre = inquilinobase.getNombre();
		this.apellido = inquilinobase.getApellido();
		this.dni = inquilinobase.getDni();
		this.cuil = inquilinobase.getCuil();
		this.direccion = inquilinobase.getDireccion();
		this.id_localidad = inquilinobase.getId_localidad();
		this.lugar_trabajo = inquilinobase.getLugar_trabajo();
		this.telefono = inquilinobase.getTelefono();
		this.id_tipo_persona = inquilinobase.getId_tipo_persona();
		this.calificacion = inquilinobase.getCalificacion();
		this.observaciones = inquilinobase.getObservaciones();
	}

	public Integer getId() {
		return this.id;
	}

	public InquilinoBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getCarpeta() {
		return this.carpeta;
	}

	public InquilinoBase setCarpeta(Integer carpeta) {
		this.carpeta = carpeta;
		return this;
	}

	public String getNombre() {
		return this.nombre;
	}

	public InquilinoBase setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public String getApellido() {
		return this.apellido;
	}

	public InquilinoBase setApellido(String apellido) {
		this.apellido = apellido;
		return this;
	}

	public String getDni() {
		return this.dni;
	}

	public InquilinoBase setDni(String dni) {
		this.dni = dni;
		return this;
	}

	public String getCuil() {
		return this.cuil;
	}

	public InquilinoBase setCuil(String cuil) {
		this.cuil = cuil;
		return this;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public InquilinoBase setDireccion(String direccion) {
		this.direccion = direccion;
		return this;
	}

	public Integer getId_localidad() {
		return this.id_localidad;
	}

	public InquilinoBase setId_localidad(Integer id_localidad) {
		this.id_localidad = id_localidad;
		return this;
	}

	public String getLugar_trabajo() {
		return this.lugar_trabajo;
	}

	public InquilinoBase setLugar_trabajo(String lugar_trabajo) {
		this.lugar_trabajo = lugar_trabajo;
		return this;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public InquilinoBase setTelefono(String telefono) {
		this.telefono = telefono;
		return this;
	}

	public Integer getId_tipo_persona() {
		return this.id_tipo_persona;
	}

	public InquilinoBase setId_tipo_persona(Integer id_tipo_persona) {
		this.id_tipo_persona = id_tipo_persona;
		return this;
	}

	public Integer getCalificacion() {
		return this.calificacion;
	}

	public InquilinoBase setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
		return this;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public InquilinoBase setObservaciones(String observaciones) {
		this.observaciones = observaciones;
		return this;
	}
}