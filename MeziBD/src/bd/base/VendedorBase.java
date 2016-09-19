package bd.base;
public class VendedorBase {

	public Integer id = 0;
	public String nombre = "";
	public String apellido = "";
	public Integer activo = 0;
        public Integer id_cuenta = 0;

  

	public VendedorBase() {
	}

	public VendedorBase(VendedorBase vendedorbase) {
		this.id = vendedorbase.getId();
		this.nombre = vendedorbase.getNombre();
		this.apellido = vendedorbase.getApellido();
		this.activo = vendedorbase.getActivo();
	}

	public Integer getId() {
		return this.id;
	}

	public VendedorBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getNombre() {
		return this.nombre;
	}

	public VendedorBase setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public String getApellido() {
		return this.apellido;
	}

	public VendedorBase setApellido(String apellido) {
		this.apellido = apellido;
		return this;
	}

	public Integer getActivo() {
		return this.activo;
	}

	public VendedorBase setActivo(Integer activo) {
		this.activo = activo;
		return this;
	}
        public Integer getId_cuenta() {
            return id_cuenta;
        }

        public void setId_cuenta(Integer id_cuenta) {
            this.id_cuenta = id_cuenta;
        }
}