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
public class ReciboPropietarioPdf extends ReciboPdf {
    
    public ReciboPropietarioPdf(Recibo recibo){
        super(recibo);
    }
    @Override
     public void printTotalPago(PdfContentByte cb,Integer lineStart){
        Float total = pago.getEfectivo() + pago.getCheque_mnt() + pago.getTransf_mnt();
        addText(cb, 110,lineStart, String.format("%.2f",total));
        addText(cb, 100,680, String.format("PAGO DEL SIGUIENTE DETALLE DE ALQUILERES: %s",propiedad.getDireccion()));
    }
    @Override
    public void printHeader(PdfContentByte cb){
        lineStart = 720;
        addText(cb, 110,lineStart - lineHeight * 0, "INMOBILIARIA MEZI");
        addText(cb, 520,lineStart - lineHeight * 0, "NºC " + contrato.getNumero().toString());
        addText(cb, 110,710, "RODHE 902");
    }
    @Override
    public void printFirma(PdfContentByte cb,Integer lineStart,Integer colStart){
       cb.moveTo(colStart, lineStart);
       cb.lineTo(colStart + 130, lineStart);
       cb.stroke();
       lineStart -= 10;
       addTextAligned(cb, colStart+60,lineStart, 8,this.propietario.getApellidoyNombre().toUpperCase(),Element.ALIGN_CENTER);
    }
     @Override
    public void printTitle(PdfContentByte cb){
        this.addTextAligned(cb, 320,740, 12,"Liquidación de propietario",Element.ALIGN_CENTER);
    }
        public static void main(String[] args){
        String fileName = "c:\\recibo.pdf";
        Recibo recibo;
        recibo = new TRecibo().getById(5);
        ReciboPropietarioPdf recibopdf = new ReciboPropietarioPdf(recibo);
        fileName = "c:\\ReciboDocumento.pdf";
        recibopdf.createPdf(fileName);
        recibopdf.abrir(fileName);
    }
}
