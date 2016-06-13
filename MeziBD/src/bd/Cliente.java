package bd;

import bd.base.ClienteBase;
public class Cliente extends ClienteBase {
        public Cliente() {
	}

	public Cliente(Cliente cliente) {
		super(cliente);
	}
        public Cliente(Inquilino inquilino) {		
		this.id = inquilino.getId();
		this.carpeta = inquilino.getCarpeta();
		this.nombre = inquilino.getNombre();
		this.apellido = inquilino.getApellido();
		this.dni = inquilino.getDni();
		this.cuil = inquilino.getCuil();
		this.direccion = inquilino.getDireccion();
		this.id_localidad = inquilino.getId_localidad();
		this.lugar_trabajo = inquilino.getLugar_trabajo();
		this.telefono = inquilino.getTelefono();
		this.id_tipo_persona = inquilino.getId_tipo_persona();
//		this.calificacion = inquilino.getCalificacion();
//		this.observaciones = inquilino.getObservaciones();
	}
        public Cliente(Propietario propietario) {
		this.id = propietario.getId();
		this.carpeta = propietario.getCarpeta();
		this.nombre = propietario.getNombre();
		this.apellido = propietario.getApellido();
		this.dni = propietario.getDni();
		this.cuil = propietario.getCuil();
		this.direccion = propietario.getDireccion();
		this.id_localidad = propietario.getId_localidad();
		this.lugar_trabajo = propietario.getLugar_trabajo();
		this.telefono = propietario.getTelefono();
		this.id_tipo_persona = propietario.getId_tipo_persona();
		this.calificacion = propietario.getCalificacion();
		this.observaciones = propietario.getObservaciones();
	}
        public String getApellidoyNombre(){
            return this.getApellido() + ", " + this.getNombre();
        }
}