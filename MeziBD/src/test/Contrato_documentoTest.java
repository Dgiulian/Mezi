/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import bd.Contrato_documento;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class Contrato_documentoTest {
 public static List<Contrato_documento> getList(){
     
     List<Contrato_documento> contrato_documento = new ArrayList<Contrato_documento>();
        Contrato_documento cd = new Contrato_documento();
        cd.setDesde("2017-01-01").setHasta("2017-04-30").setMonto(1000f);
        contrato_documento.add(cd);

        cd = new Contrato_documento();
        cd.setDesde("2017-05-01").setHasta("2017-06-30").setMonto(1250f);
        contrato_documento.add(cd);

        cd = new Contrato_documento();
        cd.setDesde("2017-07-01").setHasta("2017-12-31").setMonto(1500f);
        contrato_documento.add(cd);
     return contrato_documento;
 }   
}
