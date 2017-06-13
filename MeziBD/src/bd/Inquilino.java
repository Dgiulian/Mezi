package bd;

import bd.base.InquilinoBase;
public class Inquilino extends InquilinoBase {

	public Inquilino() {
	}

	public Inquilino(Inquilino inquilino) {
		super(inquilino);
	}
        public String getApellidoyNombre(){
            return this.getApellido() + ", " + this.getNombre();
        }
}