package bd.base;
public class ParametroBase {

	public Integer id = 0;
	public Integer numero = 0;
	public String codigo = "";
	public String nombre = "";
	public String valor = "";

	public ParametroBase() {
	}

	public ParametroBase(ParametroBase parametrobase) {
		this.id = parametrobase.getId();
		this.numero = parametrobase.getNumero();
		this.codigo = parametrobase.getCodigo();
		this.nombre = parametrobase.getNombre();
		this.valor = parametrobase.getValor();
	}

	public Integer getId() {
		return this.id;
	}

	public ParametroBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public ParametroBase setNumero(Integer numero) {
		this.numero = numero;
		return this;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public ParametroBase setCodigo(String codigo) {
		this.codigo = codigo;
		return this;
	}

	public String getNombre() {
		return this.nombre;
	}

	public ParametroBase setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public String getValor() {
		return this.valor;
	}

	public ParametroBase setValor(String valor) {
		this.valor = valor;
		return this;
	}
}