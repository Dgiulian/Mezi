package bd;

import bd.base.PropiedadBase;
public class Propiedad extends PropiedadBase {
        
	public Propiedad() {
	}

	public Propiedad(Propiedad propiedad) {
		super(propiedad);
	}
        public String getDireccion(){
            String direccion = "";
            direccion += String.format("%s %s",this.getCalle(),this.getNumero());
            if(this.getId_tipo_inmueble()==2){
                if(this.getPiso()==0) direccion += " PB ";
                else direccion += String.format("Piso: %d  ",this.getPiso());
                direccion += String.format("Dpto: %s",this.getDpto());
            }            
            return direccion;
        }
}