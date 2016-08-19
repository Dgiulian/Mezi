/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Propiedad;

import bd.Propiedad;
import bd.Propietario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import transaccion.TAuditoria;
import transaccion.TPropiedad;
import transaccion.TPropietario;
import utils.BaseException;
import utils.OptionsCfg;
import utils.Parser;
import utils.PathCfg;

/**
 *
 * @author Diego
 */
public class PropiedadEdit extends HttpServlet {

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
        Integer id = Parser.parseInt(request.getParameter("id"));
        Propiedad propiedad = new TPropiedad().getById(id);
        if(propiedad !=null) request.setAttribute("propiedad", propiedad);        
        request.getRequestDispatcher("propiedad_edit.jsp").forward(request, response);
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
        Integer id               = Parser.parseInt(request.getParameter("id"));
        String codigo            = request.getParameter("codigo");
        Integer id_tipo_inmueble = Parser.parseInt(request.getParameter("id_tipo_inmueble"));
        Integer id_propietario = Parser.parseInt(request.getParameter("id_propietario"));
        Float   precio         = Parser.parseFloat(request.getParameter("precio"));
        String  calle          = request.getParameter("calle");
        String  numero         = request.getParameter("numero");
        Integer piso           = Parser.parseInt(request.getParameter("piso"));
        String  dpto           = request.getParameter("dpto");
        Integer id_localidad   = Parser.parseInt(request.getParameter("id_localidad"));
        Integer id_barrio      = Parser.parseInt(request.getParameter("id_barrio"));
        Integer dormitorios    = Parser.parseInt(request.getParameter("dormitorios"));
        Integer banos          = Parser.parseInt(request.getParameter("banos"));
        
        Integer cocina         = request.getParameter("cocina")!=null?1:0;
        Integer comedor        = request.getParameter("comedor")!=null?1:0;
        Integer living         = request.getParameter("living")!=null?1:0;
        Integer patio          = request.getParameter("patio")!=null?1:0;
        Integer garage         = request.getParameter("garage")!=null?1:0;
        Integer entrada_auto   = request.getParameter("entrada_auto")!=null?1:0;
        Integer pileta         = request.getParameter("pileta")!=null?1:0;
         
        Float   sup_terreno    = Parser.parseFloat(request.getParameter("sup_terreno"));
        Float   sup_cubierta   = Parser.parseFloat(request.getParameter("sup_cubierta"));
        String  observaciones  = request.getParameter("observaciones");
        Float   latitud        = Parser.parseFloat(request.getParameter("latitud"));
        Float   longitud       = Parser.parseFloat(request.getParameter("longitud"));
        String  nomenclatura   = request.getParameter("nomenclatura");
        Integer id_operacion = Parser.parseInt(request.getParameter("id_operacion"));
        try{
            TPropiedad tp = new TPropiedad();
        
        Propiedad propiedad = tp.getById(id);
        Boolean nuevo = false;
        Boolean todoOk = true;
        if(propiedad==null) {
            propiedad = new Propiedad();
            nuevo = true;
        }
        propiedad.setCodigo(codigo);
        propiedad.setId_tipo_inmueble(id_tipo_inmueble);
        propiedad.setId_propietario(id_propietario);
        propiedad.setPrecio(precio);
        propiedad.setCalle(calle);
        propiedad.setNumero(numero);
        propiedad.setPiso(piso);
        propiedad.setDpto(dpto);
        propiedad.setId_localidad(id_localidad);
        propiedad.setId_barrio(id_barrio);
        propiedad.setDormitorios(dormitorios);
        propiedad.setBanos(banos);
        propiedad.setCocina(cocina);
        propiedad.setComedor(comedor);
        propiedad.setLiving(living);
        propiedad.setPatio(patio);
        propiedad.setGarage(garage);
        propiedad.setEntrada_auto(entrada_auto);
        propiedad.setPileta(pileta);
        propiedad.setSup_terreno(sup_terreno);
        propiedad.setSup_cubierta(sup_cubierta);
        propiedad.setObservaciones(observaciones);
        propiedad.setLatitud(latitud);
        propiedad.setLongitud(longitud);
        propiedad.setNomenclatura(nomenclatura);
        propiedad.setId_estado(1); // Disponible
        propiedad.setId_operacion(id_operacion);
        if(nuevo){
            id = tp.alta(propiedad);
            if (id==0) throw new BaseException("ERROR","Ocurri&oacute; un error al dar de alta la propiedad");
            else todoOk = true;
        } else {
            if(!tp.actualizar(propiedad)) throw new BaseException("ERROR","Ocurri&oacute; un error al actualizar la propiedad");
            else todoOk = true;
        }
        if(!todoOk) throw new BaseException("ERROR","Ocurri&oacute; un error al actualizar la propiedad");
        
        TPropietario tprop = new TPropietario();
        Propietario propietario = tprop.getById(propiedad.getId_propietario());
        
        // Hay que chequear que si se modifica el propietario de una propiedad
        // y el anterior no tienen ninguna otra propiedad. Deja de ser propietario
        if(propietario==null){             
            propietario = new Propietario();
            propietario.setId(id_propietario);
            tprop.alta(propietario);
        }
        
        HttpSession session = request.getSession();
        Integer id_usuario_actual = (Integer) session.getAttribute("id_usuario");
        Integer id_tipo_usuario_actual = (Integer) session.getAttribute("id_tipo_usuario");
        TAuditoria.guardar(id_usuario_actual,id_tipo_usuario_actual,OptionsCfg.MODULO_PROPIEDAD,nuevo?OptionsCfg.ACCION_ALTA:OptionsCfg.ACCION_MODIFICAR,id,tp.auditar(propiedad));

        if(nuevo) {
            response.sendRedirect(PathCfg.PROPIEDAD_EDIT + "?id=" + id);
        } else response.sendRedirect(PathCfg.PROPIEDAD);
        
        
        } catch(BaseException ex){
             request.setAttribute("titulo", ex.getResult());
             request.setAttribute("mensaje", ex.getMessage());
             request.getRequestDispatcher("message.jsp").forward(request,response);
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
