/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

import bd.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import transaccion.TAuditoria;
import transaccion.TUsuario;
import utils.BaseException;
import utils.OptionsCfg;
import utils.Parser;
import utils.PathCfg;
import utils.TFecha;

/**
 *
 * @author Diego
 */
public class CambiarPassword extends HttpServlet {

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
        Usuario usuario = null;
        HttpSession session = request.getSession();
        Integer id_usuario_actual = (Integer) session.getAttribute("id_usuario");
        usuario = new TUsuario().getById(id_usuario_actual);
        if (usuario==null) response.sendRedirect(PathCfg.LOGOUT);
        request.setAttribute("usuario", usuario);
        request.getRequestDispatcher("cambiar_password.jsp").forward(request, response);
    }

    
   
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idUsuario = request.getParameter("id");
        String email = request.getParameter("usu_mail");
        String password = request.getParameter("usu_password");
        String password2 = request.getParameter("usu_password2");
        
        try{
            HttpSession session = request.getSession();
            Integer id = (Integer) session.getAttribute("id_usuario");
            TUsuario tu = new TUsuario();
            Usuario usuario = tu.getById(id);
            if (usuario ==null) throw new BaseException("ERROR","");
            String passwordHash = "";
            if(password==null || password2==null) throw new BaseException("ERROR","Debe ingresar el password");
            if(!password.equals(password2)) throw new BaseException("ERROR","El password no coincide con la confirmaci&oacute;n");
            try {
                passwordHash = utils.PasswordHash.createHash(password);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UsuarioEdit.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(UsuarioEdit.class.getName()).log(Level.SEVERE, null, ex);
            }
            usuario.setUsu_password(passwordHash);
            tu.actualizar(usuario);            
            Integer id_tipo_usuario_actual = (Integer) session.getAttribute("id_tipo_usuario");
            TAuditoria.guardar(id,id_tipo_usuario_actual,OptionsCfg.MODULO_USUARIO,OptionsCfg.ACCION_MODIFICAR,usuario.getId(),tu.auditar(usuario));
            response.sendRedirect(PathCfg.USUARIO);
        
        } catch(BaseException ex) {
            request.setAttribute("titulo", ex.getResult());
            request.setAttribute("mensaje",ex.getMessage());
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
