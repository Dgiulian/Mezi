/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vendedor;

import bd.Vendedor;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import transaccion.TAuditoria;
import transaccion.TVendedor;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class VendedorEdit extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VendedorEdit</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VendedorEdit at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
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
        Integer id = Parser.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String activo = request.getParameter("activo");
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JsonRespuesta jr = new JsonRespuesta();           

        try{
            TVendedor tv = new TVendedor();
            Vendedor vendedor = tv.getById(id);
            boolean nuevo = false;
            if(vendedor == null){
                vendedor = new Vendedor();
                nuevo = true;
            }
            vendedor.setNombre(nombre);
            vendedor.setApellido(apellido);
            
            if(activo!=null && !activo.equalsIgnoreCase("")){
                vendedor.setActivo(1);
            } else vendedor.setActivo(0);
             
            boolean todoOk;
            if(nuevo){
                id = tv.alta(vendedor);
                todoOk = id!=0;
            }else{
                todoOk=tv.actualizar(vendedor);
            }
             if(!todoOk)  throw new BaseException("ERROR","Ocurri&oacute; un error al guardar el vendedor"); 
             jr.setResult("OK");
             jr.setRecord(vendedor);
             HttpSession session = request.getSession();
             Integer id_usuario_actual = (Integer) session.getAttribute("id_usuario");
             Integer id_tipo_usuario_actual = (Integer) session.getAttribute("id_tipo_usuario");
             TAuditoria.guardar(id_usuario_actual,id_tipo_usuario_actual,OptionsCfg.MODULO_VENDEDOR,nuevo?OptionsCfg.ACCION_ALTA:OptionsCfg.ACCION_MODIFICAR,id,tv.auditar(vendedor)); 
           
        } catch(BaseException ex){
            jr.setResult(ex.getResult());
            jr.setMessage(ex.getMessage());
        } finally {   
            String jsonResult = new Gson().toJson(jr);
            out.print(jsonResult);
            out.close();
        }
        
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
