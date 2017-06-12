package bd.base;
public class ReciboBase {

	public Integer id = 0;
	public String fecha = "";
	public Integer numero = 0;
	public Integer id_tipo_recibo = 0;
	public Integer id_contrato = 0;
	public Integer id_cuenta = 0;
	public Integer id_cliente = 0;
	public Integer id_tipo_cliente = 0;
	public Integer id_pago = 0;
	public String  fecha_creacion = "";
	public Integer id_caja = 0;
        public Integer id_estado = 1;
	public ReciboBase() {
	}

	public ReciboBase(ReciboBase recibobase) {
		this.id = recibobase.getId();
		this.fecha = recibobase.getFecha();
		this.numero = recibobase.getNumero();
		this.id_tipo_recibo = recibobase.getId_tipo_recibo();
		this.id_contrato = recibobase.getId_contrato();
		this.id_cuenta = recibobase.getId_cuenta();
		this.id_cliente = recibobase.getId_cliente();
		this.id_tipo_cliente = recibobase.getId_tipo_cliente();
		this.id_pago = recibobase.getId_pago();
		this.fecha_creacion = recibobase.getFecha_creacion();
	}

	public Integer getId() {
		return this.id;
	}

	public ReciboBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getFecha() {
		return this.fecha;
	}

	public ReciboBase setFecha(String fecha) {
		this.fecha = fecha;
		return this;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public ReciboBase setNumero(Integer numero) {
		this.numero = numero;
		return this;
	}

	public Integer getId_tipo_recibo() {
		return this.id_tipo_recibo;
	}

	public ReciboBase setId_tipo_recibo(Integer id_tipo_recibo) {
		this.id_tipo_recibo = id_tipo_recibo;
		return this;
	}

	public Integer getId_contrato() {
		return this.id_contrato;
	}

	public ReciboBase setId_contrato(Integer id_contrato) {
		this.id_contrato = id_contrato;
		return this;
	}

	public Integer getId_cuenta() {
		return this.id_cuenta;
	}

	public ReciboBase setId_cuenta(Integer id_cuenta) {
		this.id_cuenta = id_cuenta;
		return this;
	}

	public Integer getId_cliente() {
		return this.id_cliente;
	}

	public ReciboBase setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
		return this;
	}

	public Integer getId_tipo_cliente() {
		return this.id_tipo_cliente;
	}

	public ReciboBase setId_tipo_cliente(Integer id_tipo_cliente) {
		this.id_tipo_cliente = id_tipo_cliente;
		return this;
	}

	public Integer getId_pago() {
		return this.id_pago;
	}

	public ReciboBase setId_pago(Integer id_pago) {
		this.id_pago = id_pago;
		return this;
	}

	public String getFecha_creacion() {
		return this.fecha_creacion;
	}

	public ReciboBase setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
		return this;
	}
        public Integer getId_caja() {
            return id_caja;
        }

        public void setId_caja(Integer id_caja) {
            this.id_caja = id_caja;
        }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }
        
}