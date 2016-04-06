package bd.base;
public class ServicioBase {

	public Integer id = 0;
	public Integer id_propiedad = 0;
	public Integer id_tipo_servicio = 0;
	public String identificacion = "";
	public Integer id_envio = 0;
	public Integer activo = 0;

	public ServicioBase() {
	}

	public ServicioBase(ServicioBase serviciobase) {
		this.id = serviciobase.getId();
		this.id_propiedad = serviciobase.getId_propiedad();
		this.id_tipo_servicio = serviciobase.getId_tipo_servicio();
		this.identificacion = serviciobase.getIdentificacion();
		this.id_envio = serviciobase.getId_envio();
		this.activo = serviciobase.getActivo();
	}

	public Integer getId() {
		return this.id;
	}

	public ServicioBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getId_propiedad() {
		return this.id_propiedad;
	}

	public ServicioBase setId_propiedad(Integer id_propiedad) {
		this.id_propiedad = id_propiedad;
		return this;
	}

	public Integer getId_tipo_servicio() {
		return this.id_tipo_servicio;
	}

	public ServicioBase setId_tipo_servicio(Integer id_tipo_servicio) {
		this.id_tipo_servicio = id_tipo_servicio;
		return this;
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public ServicioBase setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
		return this;
	}

	public Integer getId_envio() {
		return this.id_envio;
	}

	public ServicioBase setId_envio(Integer id_envio) {
		this.id_envio = id_envio;
		return this;
	}

	public Integer getActivo() {
		return this.activo;
	}

	public ServicioBase setActivo(Integer activo) {
		this.activo = activo;
		return this;
	}
}