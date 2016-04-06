/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicio;

import bd.Propiedad;
import bd.Servicio;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import transaccion.TAuditoria;
import transaccion.TPropiedad;
import transaccion.TServicio;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class ServicioEdit extends HttpServlet {

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
        Integer id_propiedad = Parser.parseInt(request.getParameter("id_propiedad"));
        Integer id_tipo_servicio = Parser.parseInt(request.getParameter("id_tipo_servicio"));
        Integer id_envio = Parser.parseInt(request.getParameter("id_envio"));
        String identificacion = request.getParameter("identificacion");
        String activo = request.getParameter("activo");
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JsonRespuesta jr = new JsonRespuesta();           

        try{
           Propiedad p = new TPropiedad().getById(id_propiedad);
           if (p ==null) throw new BaseException("ERROR","No se encontr&oacute; la propiedad");
           TServicio ts = new TServicio();
           Servicio servicio =  ts.getById(id) ;
           boolean nuevo = false;
            if (servicio==null) {
                servicio = new Servicio();
                nuevo = true;
            }
            servicio.setId_propiedad(id_propiedad);
            servicio.setId_tipo_servicio(id_tipo_servicio);
            servicio.setIdentificacion(identificacion);
            servicio.setId_envio(id_envio);
            if(activo!=null && !activo.equalsIgnoreCase("")){
                servicio.setActivo(1);
            } else servicio.setActivo(0);
            
           boolean todoOk;
           if(nuevo){
               id = ts.alta(servicio);
               todoOk = id!=0;
           } else {
               todoOk = ts.actualizar(servicio);
           }
           if(!todoOk) throw new BaseException("ERROR","Ocurri&oacute; un error al guardar el servicio");
           
           jr.setResult("OK");
           jr.setRecord(servicio);
           HttpSession session = request.getSession();
           Integer id_usuario_actual = (Integer) session.getAttribute("id_usuario");
           Integer id_tipo_usuario_actual = (Integer) session.getAttribute("id_tipo_usuario");
           TAuditoria.guardar(id_usuario_actual,id_tipo_usuario_actual,OptionsCfg.MODULO_SERVICIO,nuevo?OptionsCfg.ACCION_ALTA:OptionsCfg.ACCION_MODIFICAR,id,ts.auditar(servicio));
           
           
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
