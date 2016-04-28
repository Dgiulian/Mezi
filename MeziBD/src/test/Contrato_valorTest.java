/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import bd.Contrato_valor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class Contrato_valorTest {
    public static List<Contrato_valor> getList(){
        List<Contrato_valor>  contrato_valor =  new ArrayList<Contrato_valor>();
        Contrato_valor cv = new Contrato_valor();
            cv.setDesde("2016-01-01").setHasta("2016-04-30").setMonto(10000f);            
            contrato_valor.add(cv);
            
            cv = new Contrato_valor();
            cv.setDesde("2016-05-01").setHasta("2016-06-30").setMonto(12500f);
            contrato_valor.add(cv);
            
            cv = new Contrato_valor();
            cv.setDesde("2016-07-01").setHasta("2016-12-31").setMonto(15000f);
            contrato_valor.add(cv);
            return contrato_valor;
    }
}
