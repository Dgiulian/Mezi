package bd.base;
public class PropiedadBase {

	public Integer id = 0;
	public String codigo = "";
	public Integer id_tipo_inmueble = 0;
	public String calle = "";
	public Integer id_propietario = 0;
	public String numero = "";
	public Integer piso = 0;
	public String dpto = "";
	public Integer id_provincia = 0;
	public Integer id_localidad = 0;
	public Integer id_barrio = 0;
	public String telefono = "";
	public Float precio = 0f;
	public String comodidades = "";
	public Integer dormitorios = 0;
	public Integer banos = 0;
	public Integer living = 0;
	public Integer patio = 0;
	public Integer garage = 0;
	public Integer cocina = 0;
	public Integer comedor = 0;
	public Integer entrada_auto = 0;
	public Integer pileta = 0;
	public Float sup_terreno = 0f;
	public Float sup_cubierta = 0f;
	public Float latitud = 0f;
	public Float longitud = 0f;
	public String observaciones = "";
	public Integer id_estado = 0;
	public Integer id_operacion = 0;

	public PropiedadBase() {
	}

	public PropiedadBase(PropiedadBase propiedadbase) {
		this.id = propiedadbase.getId();
		this.codigo = propiedadbase.getCodigo();
		this.id_tipo_inmueble = propiedadbase.getId_tipo_inmueble();
		this.calle = propiedadbase.getCalle();
		this.id_propietario = propiedadbase.getId_propietario();
		this.numero = propiedadbase.getNumero();
		this.piso = propiedadbase.getPiso();
		this.dpto = propiedadbase.getDpto();
		this.id_provincia = propiedadbase.getId_provincia();
		this.id_localidad = propiedadbase.getId_localidad();
		this.id_barrio = propiedadbase.getId_barrio();
		this.telefono = propiedadbase.getTelefono();
		this.precio = propiedadbase.getPrecio();
		this.comodidades = propiedadbase.getComodidades();
		this.dormitorios = propiedadbase.getDormitorios();
		this.banos = propiedadbase.getBanos();
		this.living = propiedadbase.getLiving();
		this.patio = propiedadbase.getPatio();
		this.garage = propiedadbase.getGarage();
		this.cocina = propiedadbase.getCocina();
		this.comedor = propiedadbase.getComedor();
		this.entrada_auto = propiedadbase.getEntrada_auto();
		this.pileta = propiedadbase.getPileta();
		this.sup_terreno = propiedadbase.getSup_terreno();
		this.sup_cubierta = propiedadbase.getSup_cubierta();
		this.latitud = propiedadbase.getLatitud();
		this.longitud = propiedadbase.getLongitud();
		this.observaciones = propiedadbase.getObservaciones();
		this.id_estado = propiedadbase.getId_estado();
		this.id_operacion = propiedadbase.getId_operacion();
	}

	public Integer getId() {
		return this.id;
	}

	public PropiedadBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public PropiedadBase setCodigo(String codigo) {
		this.codigo = codigo;
		return this;
	}

	public Integer getId_tipo_inmueble() {
		return this.id_tipo_inmueble;
	}

	public PropiedadBase setId_tipo_inmueble(Integer id_tipo_inmueble) {
		this.id_tipo_inmueble = id_tipo_inmueble;
		return this;
	}

	public String getCalle() {
		return this.calle;
	}

	public PropiedadBase setCalle(String calle) {
		this.calle = calle;
		return this;
	}

	public Integer getId_propietario() {
		return this.id_propietario;
	}

	public PropiedadBase setId_propietario(Integer id_propietario) {
		this.id_propietario = id_propietario;
		return this;
	}

	public String getNumero() {
		return this.numero;
	}

	public PropiedadBase setNumero(String numero) {
		this.numero = numero;
		return this;
	}

	public Integer getPiso() {
		return this.piso;
	}

	public PropiedadBase setPiso(Integer piso) {
		this.piso = piso;
		return this;
	}

	public String getDpto() {
		return this.dpto;
	}

	public PropiedadBase setDpto(String dpto) {
		this.dpto = dpto;
		return this;
	}

	public Integer getId_provincia() {
		return this.id_provincia;
	}

	public PropiedadBase setId_provincia(Integer id_provincia) {
		this.id_provincia = id_provincia;
		return this;
	}

	public Integer getId_localidad() {
		return this.id_localidad;
	}

	public PropiedadBase setId_localidad(Integer id_localidad) {
		this.id_localidad = id_localidad;
		return this;
	}

	public Integer getId_barrio() {
		return this.id_barrio;
	}

	public PropiedadBase setId_barrio(Integer id_barrio) {
		this.id_barrio = id_barrio;
		return this;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public PropiedadBase setTelefono(String telefono) {
		this.telefono = telefono;
		return this;
	}

	public Float getPrecio() {
		return this.precio;
	}

	public PropiedadBase setPrecio(Float precio) {
		this.precio = precio;
		return this;
	}

	public String getComodidades() {
		return this.comodidades;
	}

	public PropiedadBase setComodidades(String comodidades) {
		this.comodidades = comodidades;
		return this;
	}

	public Integer getDormitorios() {
		return this.dormitorios;
	}

	public PropiedadBase setDormitorios(Integer dormitorios) {
		this.dormitorios = dormitorios;
		return this;
	}

	public Integer getBanos() {
		return this.banos;
	}

	public PropiedadBase setBanos(Integer banos) {
		this.banos = banos;
		return this;
	}

	public Integer getLiving() {
		return this.living;
	}

	public PropiedadBase setLiving(Integer living) {
		this.living = living;
		return this;
	}

	public Integer getPatio() {
		return this.patio;
	}

	public PropiedadBase setPatio(Integer patio) {
		this.patio = patio;
		return this;
	}

	public Integer getGarage() {
		return this.garage;
	}

	public PropiedadBase setGarage(Integer garage) {
		this.garage = garage;
		return this;
	}

	public Integer getCocina() {
		return this.cocina;
	}

	public PropiedadBase setCocina(Integer cocina) {
		this.cocina = cocina;
		return this;
	}

	public Integer getComedor() {
		return this.comedor;
	}

	public PropiedadBase setComedor(Integer comedor) {
		this.comedor = comedor;
		return this;
	}

	public Integer getEntrada_auto() {
		return this.entrada_auto;
	}

	public PropiedadBase setEntrada_auto(Integer entrada_auto) {
		this.entrada_auto = entrada_auto;
		return this;
	}

	public Integer getPileta() {
		return this.pileta;
	}

	public PropiedadBase setPileta(Integer pileta) {
		this.pileta = pileta;
		return this;
	}

	public Float getSup_terreno() {
		return this.sup_terreno;
	}

	public PropiedadBase setSup_terreno(Float sup_terreno) {
		this.sup_terreno = sup_terreno;
		return this;
	}

	public Float getSup_cubierta() {
		return this.sup_cubierta;
	}

	public PropiedadBase setSup_cubierta(Float sup_cubierta) {
		this.sup_cubierta = sup_cubierta;
		return this;
	}

	public Float getLatitud() {
		return this.latitud;
	}

	public PropiedadBase setLatitud(Float latitud) {
		this.latitud = latitud;
		return this;
	}

	public Float getLongitud() {
		return this.longitud;
	}

	public PropiedadBase setLongitud(Float longitud) {
		this.longitud = longitud;
		return this;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public PropiedadBase setObservaciones(String observaciones) {
		this.observaciones = observaciones;
		return this;
	}

	public Integer getId_estado() {
		return this.id_estado;
	}

	public PropiedadBase setId_estado(Integer id_estado) {
		this.id_estado = id_estado;
		return this;
	}

	public Integer getId_operacion() {
		return this.id_operacion;
	}

	public PropiedadBase setId_operacion(Integer id_operacion) {
		this.id_operacion = id_operacion;
		return this;
	}
}