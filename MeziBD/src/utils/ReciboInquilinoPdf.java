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
        Float total = pago.getEfectivo() + pago.getCheque_mnt() + pago.getTransf_mnt();
        
        addText(cb, 110,lineStart, String.format("%.2f",total));
        addText(cb, 100,680, String.format("PAGO DEL SIGUIENTE DETALLE POR CUENTA Y ORDEN DE %s",propietario.getApellido().toUpperCase() + ", " + propietario.getNombre().toUpperCase()));
    }
    @Override
    public void printHeader(PdfContentByte cb){
        lineStart = 720;
        addText(cb, 110,lineStart - lineHeight * 0, cliente.getApellido() + ", " + cliente.getNombre());
        addText(cb, 520,lineStart - lineHeight * 0, "NºC " + contrato.getNumero().toString());
        addText(cb, 110,710, propiedad.getDireccion());
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
        this.addTextAligned(cb, 320,740, 12,"Liquidación de inquilino",Element.ALIGN_CENTER);
    }
    public static void main(String[] args){
        String fileName = "c:\\recibo.pdf";
        Recibo recibo;
        recibo = new TRecibo().getById(4);
        ReciboInquilinoPdf recibopdf = new ReciboInquilinoPdf(recibo);
        fileName = "c:\\ReciboDocumento.pdf";
        recibopdf.createPdf(fileName);
        recibopdf.abrir(fileName);
    }
}
