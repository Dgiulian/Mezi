package bd.base;
public class ClienteBase {

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

	public ClienteBase() {
	}

	public ClienteBase(ClienteBase clientebase) {
		this.id = clientebase.getId();
		this.carpeta = clientebase.getCarpeta();
		this.nombre = clientebase.getNombre();
		this.apellido = clientebase.getApellido();
		this.dni = clientebase.getDni();
		this.cuil = clientebase.getCuil();
		this.direccion = clientebase.getDireccion();
		this.id_localidad = clientebase.getId_localidad();
		this.lugar_trabajo = clientebase.getLugar_trabajo();
		this.telefono = clientebase.getTelefono();
		this.id_tipo_persona = clientebase.getId_tipo_persona();
		this.calificacion = clientebase.getCalificacion();
		this.observaciones = clientebase.getObservaciones();
	}

	public Integer getId() {
		return this.id;
	}

	public ClienteBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getCarpeta() {
		return this.carpeta;
	}

	public ClienteBase setCarpeta(Integer carpeta) {
		this.carpeta = carpeta;
		return this;
	}

	public String getNombre() {
		return this.nombre;
	}

	public ClienteBase setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public String getApellido() {
		return this.apellido;
	}

	public ClienteBase setApellido(String apellido) {
		this.apellido = apellido;
		return this;
	}

	public String getDni() {
		return this.dni;
	}

	public ClienteBase setDni(String dni) {
		this.dni = dni;
		return this;
	}

	public String getCuil() {
		return this.cuil;
	}

	public ClienteBase setCuil(String cuil) {
		this.cuil = cuil;
		return this;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public ClienteBase setDireccion(String direccion) {
		this.direccion = direccion;
		return this;
	}

	public Integer getId_localidad() {
		return this.id_localidad;
	}

	public ClienteBase setId_localidad(Integer id_localidad) {
		this.id_localidad = id_localidad;
		return this;
	}

	public String getLugar_trabajo() {
		return this.lugar_trabajo;
	}

	public ClienteBase setLugar_trabajo(String lugar_trabajo) {
		this.lugar_trabajo = lugar_trabajo;
		return this;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public ClienteBase setTelefono(String telefono) {
		this.telefono = telefono;
		return this;
	}

	public Integer getId_tipo_persona() {
		return this.id_tipo_persona;
	}

	public ClienteBase setId_tipo_persona(Integer id_tipo_persona) {
		this.id_tipo_persona = id_tipo_persona;
		return this;
	}

	public Integer getCalificacion() {
		return this.calificacion;
	}

	public ClienteBase setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
		return this;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public ClienteBase setObservaciones(String observaciones) {
		this.observaciones = observaciones;
		return this;
	}
}