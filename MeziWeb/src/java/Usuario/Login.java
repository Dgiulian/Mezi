/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

import bd.Caja;
import bd.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import transaccion.TCaja;
import transaccion.TUsuario;
import utils.TFecha;

/**
 *
 * @author Diego
 */
public class Login extends HttpServlet {
    
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
        
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
       String email = request.getParameter("email");
       String pass  = request.getParameter("password");
       String ref = request.getParameter("ref");
        System.out.println(ref); 
        request.setAttribute("email", email);
        
        TUsuario tu = new TUsuario();        
        boolean error = false;
        Usuario u = tu.getByEmail(email);
        if (u != null) {
            boolean valida = tu.validaPassword(u, pass);
            
            response.addCookie(new Cookie("email",""));
            if (valida){
                
                //response.sendRedirect(request.getContextPath());
                boolean activo = u.getUsu_activo() != 0;
                if(!activo) {
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
                    request.setAttribute("titulo","Error de Login - Usuario no activo");
                    requestDispatcher.forward(request,response);     
                    return;
                }else {
                    Cookie cookie =  new Cookie("email",email); //Cambiar esto!!        
                    response.addCookie(cookie);
                    HttpSession session = request.getSession(true);
                    session.setAttribute("logged",true);
                    session.setAttribute("email", email);
                    session.setAttribute("id_usuario", u.getId());
                    session.setAttribute(("id_tipo_usuario"), u.getId_tipo_usuario());
                    Caja  caja = new TCaja().buscarCaja(u.getId(), TFecha.ahora(TFecha.formatoBD));
                    if(caja!=null) session.setAttribute("id_caja", caja.getId());
                    
                    System.out.println(u.getId_tipo_usuario());
                    if(ref!=null)
                        response.sendRedirect(ref);
                    else response.sendRedirect(request.getContextPath());
                }
            }
            else{                
                error = true;
            }
        }else{
            error = true;
            
        }
        if (error){
            ArrayList<String> errores = new ArrayList<String>();
            errores.add("Usuario o Password no valido<br>");
            request.setAttribute("errores", errores );
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");        
            requestDispatcher.forward(request,response);       
            return;
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
