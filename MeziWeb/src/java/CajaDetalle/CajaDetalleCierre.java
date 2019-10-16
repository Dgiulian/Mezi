/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CajaDetalle;

import bd.Caja;
import bd.Caja_detalle;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TCaja;
import transaccion.TCaja_detalle;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class CajaDetalleCierre extends HttpServlet {

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
        try {
          
          
          Integer id_caja = Parser.parseInt(request.getParameter("id_caja"));
          Integer id_forma = Parser.parseInt(request.getParameter("id_forma"));
          
          TCaja tc =  new TCaja();          
          TCaja_detalle tcd = new TCaja_detalle();
          HashMap<String,String> mapFiltro = new HashMap<String,String>();
          Caja caja = tc.getById(id_caja);
          if(caja==null) throw new BaseException("ERROR","No se encontr&oacute; la caja");
            /*
             * Chequear:
             *  La caja sea del usuario actual o de Mezi               
             */
             
          mapFiltro.put("id_caja", caja.getId().toString());
                     
          if(id_forma!=0) mapFiltro.put("id_forma", id_forma.toString());
          List<Caja_detalle> listFiltro = tcd.getListFiltro(mapFiltro);
          if(listFiltro==null) throw new BaseException("ERROR","Ocurri&oacute; un error al listar los movimientos de caja");
          
          Float saldo = 0f;
          CierreCaja cierre = new CierreCaja();
          for(Caja_detalle detalle:listFiltro){
              cierre.sumar(detalle.getId_forma(),detalle.getImporte());
              saldo += detalle.getId_tipo() == OptionsCfg.TIPO_INGRESO? detalle.getImporte():-1*detalle.getImporte();
              detalle.setSaldo(saldo);

          }
          jr.setResult("OK");
          jr.setRecord(cierre);
        } catch(BaseException ex){
            jr.setResult(ex.getResult());
            jr.setMessage(ex.getMessage());
        } finally{
            out.print(new Gson().toJson(jr));
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
   private class CierreCaja{
       Float saldo_efectivo = 0f;
       Float saldo_cheques = 0f;
       Float saldo_transferencia = 0f;
       public CierreCaja(){}
       
       public CierreCaja(Float efectivo,Float cheque,Float transferencia){
           this.saldo_efectivo = efectivo;
           this.saldo_cheques = cheque;
           this.saldo_transferencia = transferencia;
       }
       
        public Float getSaldo_efectivo() {
            return saldo_efectivo;
        }

        public void setSaldo_efectivo(Float saldo_efectivo) {
            this.saldo_efectivo = saldo_efectivo;
        }

        public Float getSaldo_cheque() {
            return saldo_cheques;
        }

        public void setSaldo_cheque(Float saldo_cheque) {
            this.saldo_cheques = saldo_cheque;
        }

        public Float getSaldo_transferencia() {
            return saldo_transferencia;
        }

        public void setSaldo_transferencia(Float saldo_transferencia) {
            this.saldo_transferencia = saldo_transferencia;
        }
       public void sumar(Integer id_forma,Float monto){
           switch(id_forma){
               case OptionsCfg.FORMA_EFECTIVO: this.saldo_efectivo += monto; break;
               case OptionsCfg.FORMA_CHEQUE: this.saldo_cheques += monto;  break;
               case OptionsCfg.FORMA_TRANSFERENCIA: this.saldo_transferencia += monto; break;    
           }
           
       }
       
   }
}
