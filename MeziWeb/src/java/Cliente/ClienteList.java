/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import com.google.gson.Gson;
import bd.Cliente;
import bd.Inquilino;
import bd.Propietario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TCliente;
import transaccion.TInquilino;
import transaccion.TPropietario;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class ClienteList extends HttpServlet {
    Map<Integer,Propietario> mapPropietarios;
    Map<Integer,Inquilino> mapInquilinos;
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
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JsonRespuesta jr = new JsonRespuesta();
        
        Integer pagNro = Parser.parseInt(request.getParameter("pagNro"));
        Integer numResults = Parser.parseInt(request.getParameter("numResults"));
        String codigo = request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        Integer carpeta = Parser.parseInt(request.getParameter("carpeta"));
        
        Integer id_tipo_cliente = Parser.parseInt(request.getParameter("id_tipo_cliente"));
        String dni = request.getParameter("dni");
        
        
        try {
//            List<Cliente> lista ;
            mapPropietarios = new TPropietario().getMap();
            mapInquilinos   = new TInquilino().getMap();
            
            TCliente tc     = new TCliente();
            TInquilino ti   = new TInquilino();
            TPropietario tp = new TPropietario();
            if(numResults>0){
                tp.setNumResults(numResults);
                ti.setNumResults(numResults);
                tc.setNumResults(numResults);
            }
            HashMap<String,String> mapFiltro = new HashMap<String,String> ();
            
            if(nombre!=null && !nombre.equals("")){
                mapFiltro.put("nombre",nombre);
            }
            if(apellido!=null && !apellido.equals("")){
                mapFiltro.put("apellido",apellido);
            }
            if(dni!=null && !dni.equals("")){
                mapFiltro.put("dni",dni);
            }
            if (carpeta!=0){
                mapFiltro.put("carpeta",carpeta.toString());
            }
//            lista =  tp.getListFiltro(mapFiltro);
//           lista = tp.getList();
            List lista = new ArrayList<ClienteDet>();
            Integer totalRecordCount = 0;
            switch (id_tipo_cliente) {
              
              case OptionsCfg.CLIENTE_TIPO_PROPIETARIO: {
                    List<Propietario> lstPropietarios = tp.getListFiltro(mapFiltro,pagNro);
                    tp.setOrderBy(" apellido, nombre");
                    totalRecordCount = tp.getListFiltroCount(mapFiltro);
                     for(Propietario c:lstPropietarios){
                        lista.add(new ClienteDet(c));
                      }
              } break;
              case  OptionsCfg.CLIENTE_TIPO_INQUILINO: { 
                    List<Inquilino> lstInquilinos = ti.getListFiltro(mapFiltro,pagNro);
                    if(numResults>0) ti.setNumResults(numResults);
                    ti.setOrderBy(" apellido, nombre");
                    totalRecordCount = ti.getListFiltroCount(mapFiltro);
                    for(Inquilino c:lstInquilinos){
                        lista.add(new ClienteDet(c));
                    } 
                    break;
              }
             default: {
                tc.setOrderBy(" apellido, nombre");
                List<Cliente> lstClientes = tc.getListFiltro(mapFiltro,pagNro);                
                totalRecordCount = tc.getListFiltroCount(mapFiltro);
                 for(Cliente c:lstClientes){
                            lista.add(new ClienteDet(c));
                       } break;
                }
            }
            if (lista != null) {
                jr.setResult("OK");
                jr.setTotalRecordCount(totalRecordCount);
                jr.setRecordCount(lista.size());
                jr.setRecords(lista);
            } else {
                jr.setResult("ERROR");
                jr.setTotalRecordCount(0);
                jr.setRecordCount(0);
            }            
            
        } catch (Error e) {
            System.out.println("Ha ocurrido un error" + e.getMessage());
        }finally {
            
            out.print(new Gson().toJson(jr));
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
    
    
    private class ClienteDet extends Cliente{
       String tipo_persona = "";
       String tipo_cliente = "";
       
       ClienteDet(Cliente cliente){
        super(cliente);
        completarDatos();
      }
       ClienteDet(Inquilino inquilino){
           super(inquilino);
           completarDatos();
       }
       ClienteDet(Propietario propietario){
           super(propietario);
           completarDatos();
       }
       
       private void completarDatos(){
           switch (this.getId_tipo_persona()){
           case 1: tipo_persona = "Fisica"; break;
           case 2: tipo_persona = "Jur&iacute;dica"; break;
           default: tipo_persona = "";           
       }
        Propietario propietario = mapPropietarios.get(this.getId());
        Inquilino inquilino  = mapInquilinos.get(this.getId());
        if (propietario!=null) tipo_cliente = "Propietario";
        if (inquilino!=null) tipo_cliente +=(tipo_cliente.equals(""))?"Inquilino":", Inquilino";
       }
    }
}
