/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cuenta;

import bd.Cliente;
import bd.Cuenta;
import bd.Parametro;
import bd.Recibo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TCliente;
import transaccion.TCuenta;
import transaccion.TParametro;
import transaccion.TRecibo;
import utils.BaseException;
import utils.CuentaPdf;
import utils.OptionsCfg;
import utils.ReciboPdf;
import utils.TFecha;

/**
 *
 * @author Diego
 */
public class CuentaPrint extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cuenta cuenta = null;
        Parametro parametro = new TParametro().getByCodigo(OptionsCfg.CUENTA_PATH);
        try{
            if(request.getParameter("id")==null) throw new BaseException("Error de remito", "Debe seleccionar el remito");
            Integer id = Integer.parseInt(request.getParameter("id"));
            String  fecha_desde = TFecha.formatearFechaVistaBd(request.getParameter("fecha_desde"));
            String  fecha_hasta = TFecha.formatearFechaVistaBd(request.getParameter("fecha_hasta"));
            cuenta = new TCuenta().getById(id);
            if (cuenta==null)
                throw new BaseException( "Error" ,"No se ha encontrado el la cuenta");                
            
            CuentaPdf cuentaPdf = new CuentaPdf(cuenta,fecha_desde,fecha_hasta);
            Cliente cliente = new TCliente().getById(cuenta.getId_cliente());            
            String fileName = String.format("consultaCC_%d.pdf",cliente.getCarpeta());
            
            String filePath;
            if(parametro!=null) 
                 filePath = parametro.getValor();
            else filePath = "c:\\";
            
            filePath += File.separator + fileName;
            boolean generado = cuentaPdf.createPdf(filePath);
            if (generado) {
                //remitoPdf.imprimir(fileName);             
                // reads input file from an absolute path        
                File downloadFile = new File(filePath);
                FileInputStream inStream = new FileInputStream(downloadFile);

                // if you want to use a relative path to context root:
                String relativePath = getServletContext().getRealPath("");
                System.out.println("relativePath = " + relativePath);

                // obtains ServletContext
                ServletContext context = getServletContext();

                // gets MIME type of the file
                String mimeType = context.getMimeType(filePath);
                if (mimeType == null) {
                    // set to binary type if MIME mapping not found
                    mimeType = "application/octet-stream";
                }
                System.out.println("MIME type: " + mimeType);

                // modifies response
                response.setContentType(mimeType);
                response.setContentLength((int) downloadFile.length());

                // forces download
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
                response.setHeader(headerKey, headerValue);

                // obtains response's output stream
                OutputStream outStream = response.getOutputStream();

                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
                }

                inStream.close();
                outStream.close();// obtains response's output stream

            }        
         } catch (BaseException ex){
                request.setAttribute("titulo", ex.getResult());
                request.setAttribute("mensaje",ex.getMessage());
                request.getRequestDispatcher("message.jsp").forward(request, response);
                return;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
