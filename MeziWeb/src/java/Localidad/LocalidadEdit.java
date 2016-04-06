/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Localidad;

import bd.Localidad;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import transaccion.TAuditoria;
import transaccion.TLocalidad;
import transaccion.TProvincia;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class LocalidadEdit extends HttpServlet {

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
            out.println("<title>Servlet LocalidadEdit</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LocalidadEdit at " + request.getContextPath() + "</h1>");
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
       Integer id_provincia = Parser.parseInt(request.getParameter("id_provincia"));
       String descripcion = request.getParameter("descripcion");
       String activo = request.getParameter("activo");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JsonRespuesta jr = new JsonRespuesta();           

        try{
            if (new TProvincia().getById(id_provincia)==null) throw new BaseException("ERROR","No se encontr&oacute; la provincia");
            
            TLocalidad tl = new TLocalidad();
            Localidad localidad = tl.getById(id);
            boolean nuevo = false;       
            if(localidad==null){
                localidad = new Localidad();
                nuevo = true;
            }
            localidad.setId_provincia(id_provincia);
            localidad.setDescripcion(descripcion);
            if(activo!=null && !activo.equals("")) localidad.setActivo(1);
             else localidad.setActivo(0);
            boolean todoOk;
            if(nuevo){
                id = tl.alta(localidad);
                todoOk = id!=0;
            }else {
                todoOk = tl.actualizar(localidad);
            }
            if(!todoOk) throw new BaseException("ERROR","Ocurri&oacute; un error al guardar la localidad");
            
            jr.setResult("OK");
            jr.setRecord(localidad);
            HttpSession session = request.getSession();
            Integer id_usuario_actual = (Integer) session.getAttribute("id_usuario");
            Integer id_tipo_usuario_actual = (Integer) session.getAttribute("id_tipo_usuario");
            TAuditoria.guardar(id_usuario_actual,id_tipo_usuario_actual,OptionsCfg.MODULO_LOCALIDAD,nuevo?OptionsCfg.ACCION_ALTA:OptionsCfg.ACCION_MODIFICAR,id,tl.auditar(localidad));
            
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
