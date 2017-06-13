package bd;

import bd.base.PropietarioBase;
public class Propietario extends PropietarioBase {

	public Propietario() {
	}

	public Propietario(Propietario propietario) {
		super(propietario);
	}
        public String getApellidoyNombre(){
            return this.getApellido() + ", " + this.getNombre();
        }
}