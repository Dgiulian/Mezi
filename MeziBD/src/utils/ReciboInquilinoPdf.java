/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import bd.Recibo;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfContentByte;
import transaccion.TRecibo;

/**
 *
 * @author Diego
 */
public class ReciboInquilinoPdf extends ReciboPdf {
    public ReciboInquilinoPdf(Recibo recibo){
        super(recibo);
    }
    
    @Override
    public void printTotalPago(PdfContentByte cb,Integer lineStart){        
        if(pago==null) return;
        Float total = pago.getEfectivo() + pago.getCheque_mnt() + pago.getTransf_mnt();        
        addText(cb, 50,lineStart, String.format("La cantidad de: $%.2f",total));
        if(propietario!=null){
            String texto = String.format("PAGO DEL SIGUIENTE DETALLE POR CUENTA Y ORDEN DE ");
            addText(cb, 50,680, texto);            
            texto = String.format("%s (NRO CUIT/CUIL: %s)",propietario.getApellido().toUpperCase() + ", " + propietario.getNombre().toUpperCase(),propietario.getCuil());
            addText(cb, 50,670, texto);
        }
    }
    @Override
    public void printHeader(PdfContentByte cb){
        lineStart = 720;
        if(contrato!=null) {
            addText(cb, 50, lineStart - lineHeight  * 0, String.format("Recibimos de: %s",cliente.getApellido() + ", " + cliente.getNombre()));                
        }
        if ( cliente!=null && propietario!=null){
            //addText(cb, 520,lineStart - lineHeight * 0, "NºC " + contrato.getNumero().toString());        
            addText(cb, 520,lineStart - lineHeight * 0, "NºC: " + cliente.getCarpeta().toString() + "/" + propietario.getCarpeta().toString());        
        }
        if(propiedad!=null) {
                addText(cb, 50,710, "Domicilio: ");
                addText(cb, 110,710, propiedad.getDireccion());
            }
        }
    @Override
    public void printFirma(PdfContentByte cb,Integer lineStart,Integer colStart){
       cb.moveTo(colStart, lineStart);
       cb.lineTo(colStart + 130, lineStart);
       cb.stroke();
       lineStart -= 10;
       addText(cb, colStart+20,lineStart, "P/MEZI INMOBILIARIA");
    }
    @Override
    public void printTitle(PdfContentByte cb){
        this.addTextAligned(cb, 320,740, 14,"Liquidación de inquilino",Element.ALIGN_CENTER);
    }
    public static void main(String[] args){
        String fileName = "c:\\recibo.pdf";
        Recibo recibo;
        recibo = new TRecibo().getById(53);
        ReciboInquilinoPdf recibopdf = new ReciboInquilinoPdf(recibo);
        fileName = "c:\\ReciboDocumento.pdf";
        recibopdf.createPdf(fileName);
        recibopdf.abrir(fileName);
    }
}
