package bd.base;
public class PropietarioBase {

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

	public PropietarioBase() {
	}

	public PropietarioBase(PropietarioBase propietariobase) {
		this.id = propietariobase.getId();
		this.carpeta = propietariobase.getCarpeta();
		this.nombre = propietariobase.getNombre();
		this.apellido = propietariobase.getApellido();
		this.dni = propietariobase.getDni();
		this.cuil = propietariobase.getCuil();
		this.direccion = propietariobase.getDireccion();
		this.id_localidad = propietariobase.getId_localidad();
		this.lugar_trabajo = propietariobase.getLugar_trabajo();
		this.telefono = propietariobase.getTelefono();
		this.id_tipo_persona = propietariobase.getId_tipo_persona();
		this.calificacion = propietariobase.getCalificacion();
		this.observaciones = propietariobase.getObservaciones();
	}

	public Integer getId() {
		return this.id;
	}

	public PropietarioBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getCarpeta() {
		return this.carpeta;
	}

	public PropietarioBase setCarpeta(Integer carpeta) {
		this.carpeta = carpeta;
		return this;
	}

	public String getNombre() {
		return this.nombre;
	}

	public PropietarioBase setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public String getApellido() {
		return this.apellido;
	}

	public PropietarioBase setApellido(String apellido) {
		this.apellido = apellido;
		return this;
	}

	public String getDni() {
		return this.dni;
	}

	public PropietarioBase setDni(String dni) {
		this.dni = dni;
		return this;
	}

	public String getCuil() {
		return this.cuil;
	}

	public PropietarioBase setCuil(String cuil) {
		this.cuil = cuil;
		return this;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public PropietarioBase setDireccion(String direccion) {
		this.direccion = direccion;
		return this;
	}

	public Integer getId_localidad() {
		return this.id_localidad;
	}

	public PropietarioBase setId_localidad(Integer id_localidad) {
		this.id_localidad = id_localidad;
		return this;
	}

	public String getLugar_trabajo() {
		return this.lugar_trabajo;
	}

	public PropietarioBase setLugar_trabajo(String lugar_trabajo) {
		this.lugar_trabajo = lugar_trabajo;
		return this;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public PropietarioBase setTelefono(String telefono) {
		this.telefono = telefono;
		return this;
	}

	public Integer getId_tipo_persona() {
		return this.id_tipo_persona;
	}

	public PropietarioBase setId_tipo_persona(Integer id_tipo_persona) {
		this.id_tipo_persona = id_tipo_persona;
		return this;
	}

	public Integer getCalificacion() {
		return this.calificacion;
	}

	public PropietarioBase setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
		return this;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public PropietarioBase setObservaciones(String observaciones) {
		this.observaciones = observaciones;
		return this;
	}
}