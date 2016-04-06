package bd.base;
public class ProvinciaBase {

	public Integer id = 0;
	public String descripcion = "";

	public ProvinciaBase() {
	}

	public ProvinciaBase(ProvinciaBase provinciabase) {
		this.id = provinciabase.getId();
		this.descripcion = provinciabase.getDescripcion();
	}

	public Integer getId() {
		return this.id;
	}

	public ProvinciaBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public ProvinciaBase setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		return this;
	}
}