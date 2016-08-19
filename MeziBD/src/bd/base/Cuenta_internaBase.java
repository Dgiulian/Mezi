package bd.base;
public class Cuenta_internaBase {

	public Integer id = 0;
	public String nombre = "";
	public Integer id_tipo = 0;
	public Integer id_estado = 0;

	public Cuenta_internaBase() {
	}

	public Cuenta_internaBase(Cuenta_internaBase cuenta_internabase) {
		this.id = cuenta_internabase.getId();
		this.nombre = cuenta_internabase.getNombre();
		this.id_tipo = cuenta_internabase.getId_tipo();
		this.id_estado = cuenta_internabase.getId_estado();
	}

	public Integer getId() {
		return this.id;
	}

	public Cuenta_internaBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getNombre() {
		return this.nombre;
	}

	public Cuenta_internaBase setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public Integer getId_tipo() {
		return this.id_tipo;
	}

	public Cuenta_internaBase setId_tipo(Integer id_tipo) {
		this.id_tipo = id_tipo;
		return this;
	}

	public Integer getId_estado() {
		return this.id_estado;
	}

	public Cuenta_internaBase setId_estado(Integer id_estado) {
		this.id_estado = id_estado;
		return this;
	}
}