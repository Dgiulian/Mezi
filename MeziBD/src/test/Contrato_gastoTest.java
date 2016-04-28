/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import bd.Contrato_gasto;
import java.util.ArrayList;
import java.util.List;
import utils.OptionsCfg;

/**
 *
 * @author Diego
 */
public class Contrato_gastoTest {
    public static List<Contrato_gasto> getList(){
        List<Contrato_gasto>  contrato_gasto =  new ArrayList<Contrato_gasto>();
        Contrato_gasto cg =  new Contrato_gasto();
        cg.setConcepto("Transporte");
        cg.setId_aplica(OptionsCfg.CLIENTE_TIPO_INQUILINO);
        cg.setCuotas(1);
        cg.setImporte(78.5f);
        contrato_gasto.add(cg);

        cg =  new Contrato_gasto();
        cg.setConcepto("Facturas");
        cg.setId_aplica(OptionsCfg.CLIENTE_TIPO_PROPIETARIO);
        cg.setImporte(55.78f);
        cg.setCuotas(2);
        contrato_gasto.add(cg);

        cg =  new Contrato_gasto();
        cg.setConcepto("Sanguchitos");
        cg.setId_aplica(OptionsCfg.CLIENTE_TIPO_INQUILINO);
        cg.setImporte(105.78f);
        cg.setCuotas(2);
        contrato_gasto.add(cg);
        return contrato_gasto;
    }
}
