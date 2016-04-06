/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Barrio;

import bd.Barrio;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import transaccion.TAuditoria;
import transaccion.TBarrio;
import transaccion.TLocalidad;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class BarrioEdit extends HttpServlet {

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
            out.println("<title>Servlet BarrioEdit</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BarrioEdit at " + request.getContextPath() + "</h1>");
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
       request.getRequestDispatcher("barrio_edit.jsp").forward(request,response);
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
        Integer id_localidad = Parser.parseInt(request.getParameter("id_localidad"));
        String nombre = request.getParameter("nombre");
        String nombre_municipal = request.getParameter("nombre_municipal");
        String activo = request.getParameter("activo");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JsonRespuesta jr = new JsonRespuesta();           

        try{
            if (new TLocalidad().getById(id_localidad)==null) throw new BaseException("ERROR","No se encontr&oacute; la localidad");
            
            TBarrio tb = new TBarrio();
            Barrio barrio = tb.getById(id);
            boolean nuevo = false;
            if( barrio ==null){
                barrio = new Barrio();
                nuevo = true;            
            }
            barrio.setId_localidad(id_localidad);
            barrio.setNombre(nombre);
            barrio.setNombre_municipal(nombre_municipal);
            if(activo!=null && !activo.equalsIgnoreCase("")){
                barrio.setActivo(1);
            } else barrio.setActivo(0);
            
            boolean todoOk;
            if (nuevo ){
                id = tb.alta(barrio);
                todoOk = id!=0;
            } else {
                todoOk = tb.actualizar(barrio);
            }
            if(!todoOk) throw new BaseException("ERROR","Ocurri&oacute; un error al guardar el barrio");
            jr.setResult("OK");
            jr.setRecord(barrio);
                
            HttpSession session = request.getSession();
            Integer id_usuario_actual = (Integer) session.getAttribute("id_usuario");
            Integer id_tipo_usuario_actual = (Integer) session.getAttribute("id_tipo_usuario");
            TAuditoria.guardar(id_usuario_actual,id_tipo_usuario_actual,OptionsCfg.MODULO_CLIENTE,nuevo?OptionsCfg.ACCION_ALTA:OptionsCfg.ACCION_MODIFICAR,id,tb.auditar(barrio));
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
