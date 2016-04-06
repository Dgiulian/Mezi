package bd.base;
public class Cliente_tipoBase {

	public Integer id = 0;
	public Integer id_cliente = 0;
	public Integer id_tipo_cliente = 0;

	public Cliente_tipoBase() {
	}

	public Cliente_tipoBase(Cliente_tipoBase cliente_tipobase) {
		this.id = cliente_tipobase.getId();
		this.id_cliente = cliente_tipobase.getId_cliente();
		this.id_tipo_cliente = cliente_tipobase.getId_tipo_cliente();
	}

	public Integer getId() {
		return this.id;
	}

	public Cliente_tipoBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getId_cliente() {
		return this.id_cliente;
	}

	public Cliente_tipoBase setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
		return this;
	}

	public Integer getId_tipo_cliente() {
		return this.id_tipo_cliente;
	}

	public Cliente_tipoBase setId_tipo_cliente(Integer id_tipo_cliente) {
		this.id_tipo_cliente = id_tipo_cliente;
		return this;
	}
}