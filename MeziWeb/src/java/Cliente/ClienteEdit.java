/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import com.google.gson.Gson;
import bd.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import transaccion.TAuditoria;
import transaccion.TCliente;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.Parser;
import utils.PathCfg;

/**
 *
 * @author Diego
 */
public class ClienteEdit extends HttpServlet {

   
    
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
        Cliente cliente = null;
        if(request.getParameter("id")!=null) {
                Integer id = Parser.parseInt(request.getParameter("id"));
                cliente = new TCliente().getById(id);
            if (cliente==null){
                request.setAttribute("titulo", "Error");
                request.setAttribute("mensaje","No se ha encontrado el cliente");
                request.getRequestDispatcher("message.jsp").forward(request, response);
                return;
            } else {
                request.setAttribute("cliente", cliente);
            }
        } //else cliente = new Cliente();          
        
        request.getRequestDispatcher("cliente_edit.jsp").forward(request, response);
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
        Integer carpeta = Parser.parseInt(request.getParameter("carpeta"));        
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String dni = request.getParameter("dni");
        String cuit = request.getParameter("cuit");
        String direccion = request.getParameter("direccion");
        String lugar_trabajo = request.getParameter("lugar_trabajo");
        String telefono = request.getParameter("telefono");
        
        Integer id_localidad    = Parser.parseInt(request.getParameter("id_localidad"));
        Integer id_tipo_persona = Parser.parseInt(request.getParameter("id_tipo_persona"));
        Integer calificacion = Parser.parseInt(request.getParameter("calificacion"));
        String observaciones = request.getParameter("observaciones");
        String modo        = request.getParameter("modo");
        if(modo==null) modo = "";
        try{
            TCliente tc = new TCliente();
            Cliente cliente = tc.getById(id);
            boolean nuevo = false;
            
            if (cliente==null) {
                cliente = new Cliente();                
                nuevo = true;
            }
            cliente.setCarpeta(carpeta);
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setDni(dni);
            cliente.setCuil(cuit);
            cliente.setId_localidad(id_localidad);
            cliente.setId_tipo_persona(id_tipo_persona);
            cliente.setDireccion(direccion);
            cliente.setLugar_trabajo(lugar_trabajo);
            cliente.setTelefono(telefono);
            cliente.setCalificacion(calificacion);
            cliente.setObservaciones(observaciones);
           
            boolean todoOk = true;
            if(nuevo) {
                id= tc.alta(cliente);
                cliente.setId(id);
                todoOk = id!=0;
            } else todoOk = tc.actualizar(cliente);
            if(!todoOk) throw new BaseException ("Error","Ocurri&oacute; un error al guardar el cliente");
            
            HttpSession session = request.getSession();            
            Integer id_usuario_actual = (Integer) session.getAttribute("id_usuario");
            Integer id_tipo_usuario_actual = (Integer) session.getAttribute("id_tipo_usuario");
            TAuditoria.guardar(id_usuario_actual,id_tipo_usuario_actual,OptionsCfg.MODULO_CLIENTE,nuevo?OptionsCfg.ACCION_ALTA:OptionsCfg.ACCION_MODIFICAR,id,tc.auditar(cliente));
            if(modo.equalsIgnoreCase("modal")){
                 response.setContentType("application/json;charset=UTF-8");
               PrintWriter out = response.getWriter();
               JsonRespuesta jr = new JsonRespuesta();
               String jsonResult = null;
               jr.setResult("OK");
               jr.setMessage("Cliente creado correctamente");
               jr.setRecord(cliente);
               out.print(new Gson().toJson(jr));
               out.close();
            } else {
                response.sendRedirect(PathCfg.CLIENTE);
            }
            
        } catch (BaseException ex){
            if(modo.equalsIgnoreCase("modal")){
               response.setContentType("application/json;charset=UTF-8");
               PrintWriter out = response.getWriter();
               JsonRespuesta jr = new JsonRespuesta();
               String jsonResult = null;
               jr.setResult(ex.getResult());
               jr.setMessage(ex.getMessage());
               out.print(new Gson().toJson(jr));
               out.close();
            } else {
                request.setAttribute("titulo", ex.getResult());
                request.setAttribute("mensaje", ex.getMessage());
                request.getRequestDispatcher("message.jsp").forward(request,response);
            }
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
    
  private class ClienteDet extends Cliente{
      ClienteDet(Cliente cliente){
          super(cliente);
      }
  }
}
