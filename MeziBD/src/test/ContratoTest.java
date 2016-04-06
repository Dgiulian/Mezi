/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import bd.Contrato;

/**
 *
 * @author Diego
 */
public class ContratoTest extends Contrato{
    public ContratoTest(){
      this.setId_inquilino(6);
      this.setId_propietario(5);
      this.setId_propiedad(3);
      this.setId_vendedor(3);
      
      this.setGastos_escribania_inquilino(150f);
      this.setGastos_escribania_propietario(200f);
      this.setGastos_sellado_inquilino(150f);
      this.setGastos_sellado_propietario(200f);
      
      
      this.setDeposito_monto(1500f);
      this.setDeposito_cuotas(3);
      this.setDeposito_desde("2016-01-01");
      
      this.setComision_monto_inquilino(1500f);
      this.setComision_cuotas_inquilino(3);
      this.setComision_desde_inquilino("2016-01-01");

      
      this.setComision_monto_propietario(1500f);
      this.setComision_cuotas_propietario(3);
      this.setComision_desde_propietario("2016-01-01");
      
      this.setAsegura_renta(1);
      this.setFecha_inicio("2016-01-01");
      this.setFecha_fin("2016-12-31");
      this.setMeses(12);
      
      this.setNumero(666);    
    }
}
