/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import bd.Contrato;
import utils.OptionsCfg;

/**
 *
 * @author Diego
 */
public class ContratoTest extends Contrato{
    public ContratoTest(){
            this.setNumero(555);
            this.setFecha_inicio("2017-01-01");
            this.setFecha_fin("2017-12-31");
            this.setMeses(12);
            this.setAsegura_renta(1);
            this.setPunitorio_monto(0.017f);
            this.setPunitorio_desde(10);
            this.setComision_vendedor(50f);
            this.setGastos_escribania_inquilino(100f);
            
            this.setGastos_sellado_inquilino(150f);
            this.setComision_monto_inquilino(100f);
            this.setComision_desde_inquilino(this.getFecha_inicio());
            this.setComision_cuotas_inquilino(2);
            
            this.setGastos_escribania_propietario(250f);
            this.setGastos_sellado_propietario(150f);
            this.setComision_monto_propietario(50f);
            this.setComision_desde_propietario(this.getFecha_inicio());
            this.setComision_cuotas_propietario(2);
            
            this.setDeposito_monto(200f);
            this.setDeposito_desde(this.getFecha_inicio());
            this.setDeposito_cuotas(2);
            this.setObservaciones("Observaciones");
            this.setId_propiedad(9);
            this.setId_inquilino(12);
            this.setId_vendedor(5);
            this.setLlave_monto(200f);
            this.setLlave_desde(this.getFecha_inicio());
            this.setLlave_cuotas(2);            
            this.setGarante_1_dni("garante_1_dni");
this.setGarante_1_nombre("garante_1_nombre");
this.setGarante_1_telefono("garante_1_telefono");
this.setGarante_1_id_garantia(OptionsCfg.GARANTIA_ESCRITURA);

this.setGarante_2_dni("garante_2_dni");
this.setGarante_2_nombre("garante_2_nombre");
this.setGarante_2_telefono("garante_2_telefono");
this.setGarante_2_id_garantia(OptionsCfg.GARANTIA_RECIBO);

this.setGarante_3_dni("garante_3_dni");
this.setGarante_3_nombre("garante_3_nombre");
this.setGarante_3_telefono("garante_3_telefono");
this.setGarante_3_id_garantia(OptionsCfg.GARANTIA_ESCRITURA);
            
    }
}
