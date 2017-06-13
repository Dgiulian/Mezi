/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Contrato;

import bd.Cliente;
import bd.Contrato;
import bd.Contrato_documento;
import bd.Contrato_gasto;
import bd.Contrato_valor;
import bd.Propiedad;
import bd.Propietario;
import bd.Vendedor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TCliente;
import transaccion.TContrato;
import transaccion.TContrato_documento;
import transaccion.TContrato_gasto;
import transaccion.TContrato_valor;
import transaccion.TPropiedad;
import transaccion.TPropietario;
import transaccion.TVendedor;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class ContratoView extends HttpServlet {

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
        
    }

   
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
        Integer id_contrato = Parser.parseInt(request.getParameter("id"));
        Contrato contrato = new TContrato().getById(id_contrato);
        if(contrato==null){
            request.setAttribute("titulo", "Contrato inexistente");
            request.setAttribute("mensaje", "No se encontr&oacute; el contrato");
            request.getRequestDispatcher("message.jsp").forward(request,response);
            return;
        }
        request.setAttribute("contrato",contrato);
        Propiedad propiedad = new TPropiedad().getById(contrato.getId_propiedad());
        if(propiedad!=null) 
            request.setAttribute("propiedad", propiedad);
        if(propiedad!=null) {
            Propietario propietario = new TPropietario().getById(propiedad.getId_propietario());
            if(propietario!=null)
                request.setAttribute("propietario",propietario);
        }
        
        Cliente inquilino = new TCliente().getById(contrato.getId_inquilino());
        if(inquilino!=null)
            request.setAttribute("inquilino",inquilino);
        
        Vendedor vendedor = new TVendedor().getById(contrato.getId_vendedor());
        if (vendedor!=null)
            request.setAttribute("vendedor",vendedor);
        
        Map mapValor = new HashMap<String,String>();
        
        mapValor.put("id_contrato", contrato.getId().toString());
        
        List<Contrato_valor> lstValor = new TContrato_valor().getListFiltro(mapValor);
        List<Contrato_documento> lstDocum = new TContrato_documento().getListFiltro(mapValor);
        List<Contrato_gasto> lstGasto = new TContrato_gasto().getListFiltro(mapValor);
        request.setAttribute("lstValor", lstValor);
        request.setAttribute("lstDocum", lstDocum);
        request.setAttribute("lstGasto", lstGasto);
        request.getRequestDispatcher("contrato_view.jsp").forward(request, response);
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
