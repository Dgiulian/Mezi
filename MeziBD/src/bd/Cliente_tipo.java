package bd;

import bd.base.Cliente_tipoBase;
public class Cliente_tipo extends Cliente_tipoBase {
    public static final Integer PROPIETARIO = 1;
    public static final Integer INQUILINO = 2;
    
    public Cliente_tipo() {
    }

    public Cliente_tipo(Cliente_tipo cliente_tipo) {
            super(cliente_tipo);
    }
}