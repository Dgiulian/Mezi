package bd.base;
public class Tipo_clienteBase {

	public Integer id = 0;
	public String descripcion = "";

	public Tipo_clienteBase() {
	}

	public Tipo_clienteBase(Tipo_clienteBase tipo_clientebase) {
		this.id = tipo_clientebase.getId();
		this.descripcion = tipo_clientebase.getDescripcion();
	}

	public Integer getId() {
		return this.id;
	}

	public Tipo_clienteBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public Tipo_clienteBase setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		return this;
	}
}