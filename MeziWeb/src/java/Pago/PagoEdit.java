/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pago;

import bd.Caja;
import bd.Caja_detalle;
import bd.Contrato;
import bd.Cuenta;
import bd.Cuenta_detalle;
import bd.Pago;
import bd.Recibo;
import bd.Recibo_detalle;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.joda.time.LocalDate;
import transaccion.TCaja;
import transaccion.TCaja_detalle;
import transaccion.TContrato;
import transaccion.TCuenta;
import transaccion.TCuenta_detalle;
import transaccion.TPago;
import transaccion.TRecibo;
import transaccion.TRecibo_detalle;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.Parser;
import utils.TFecha;

/**
 *
 * @author Diego
 */
public class PagoEdit extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
            out.println("<title>Servlet PagoEdit</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PagoEdit at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
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
        /*
         * Cargar el pago
         * Cargar los punitorios
         * Cargar el detalle de pago en la cuenta
         * Cargar el saldo si hubiera
         * Crear el recibo del inquilino
         * Cargar el detalle del recibo. Copiando el detalle de la cuenta con los punitorios, pagos y saldos.
         */
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String strFecha = TFecha.convertirFecha(request.getParameter("fecha"), TFecha.formatoVista, TFecha.formatoBD);

        Integer tipo = Parser.parseInt(request.getParameter("tipo"));
        Integer id_cuenta = Parser.parseInt(request.getParameter("id_cuenta"));
        Float liqEfeMnt = Parser.parseFloat(request.getParameter("liqEfeMnt"));
        Float liqChkMnt = Parser.parseFloat(request.getParameter("liqChkMnt"));
        String liqChkBan = request.getParameter("liqChkBan");
        String liqChkNum = request.getParameter("liqChkNum");
        String liqChkVto = TFecha.formatearFechaVistaBd(request.getParameter("liqChkVto"));
        Float liqTraMnt = Parser.parseFloat(request.getParameter("liqTraMnt"));
        String liqTraNum = request.getParameter("liqTraNum");

        HttpSession sesion = request.getSession(false);
        Integer id_usuario = (Integer) sesion.getAttribute("id_usuario");
        Integer id_caja = (Integer) sesion.getAttribute("id_caja");

        JsonRespuesta jr = new JsonRespuesta();
        TCuenta tc = new TCuenta();
        TCuenta_detalle tcd = new TCuenta_detalle();
        TPago tp = new TPago();
        Pago p;
        Cuenta cuenta;
        Cuenta_detalle cd;
        Integer id_pago;
        TRecibo tr = new TRecibo();
        TRecibo_detalle trd = new TRecibo_detalle();
        TContrato tcr = new TContrato();
        ArrayList<Cuenta_detalle> listaDetalle = new ArrayList();
        ArrayList<Cuenta_detalle> listaPunitorio = new ArrayList();
        List<Cuenta_detalle> lista;
        TCaja tcaja = new TCaja();
        TCaja_detalle tcaja_detalle = new TCaja_detalle();
        // HashMap<String,String> filtroCuenta    = new HashMap<String,String>();
        Float saldo = 0f;
        try {

            cuenta = tc.getById(id_cuenta);

            if (cuenta == null) {
                throw new BaseException("ERROR", "Debe indicar la cuenta a liquidar");
            }
            Caja caja = tcaja.getById(id_caja);
            if (caja == null || caja.getId_estado() != OptionsCfg.CAJA_ABIERTA) {
                throw new BaseException("ERROR", "No existe una caja abierta.");
            }

//            filtroCuenta.put("id_cuenta", cuenta.getId().toString());
            p = new Pago();
            p.setId_cuenta(cuenta.getId());
            p.setFecha(strFecha);
            p.setFecha_creacion(TFecha.ahora(TFecha.formatoBD_Hora));
            p.setEfectivo(liqEfeMnt);

            p.setCheque_mnt(liqChkMnt);
            p.setCheque_ban(liqChkBan);
            p.setCheque_num(liqChkNum);
            if (!"".equals(liqChkVto)) {
                p.setCheque_vto(liqChkVto);
            }

            p.setTransf_mnt(liqTraMnt);
            p.setTransf_num(liqTraNum);
            id_pago = tp.alta(p);
            if (id_pago == 0) {
                throw new BaseException("ERROR", "Ocurri&oacute; un error al guardar el pago. Intentelo nuevamente.");
            }

            Recibo recibo = new Recibo();
            recibo.setId_pago(id_pago);
            recibo.setId_cuenta(cuenta.getId());
            recibo.setId_contrato(cuenta.getId_contrato());
            recibo.setFecha(p.getFecha());
            recibo.setId_tipo_cliente(cuenta.getId_tipo_cliente());
            recibo.setId_tipo_recibo(OptionsCfg.RECIBO_PAGO);
            recibo.setFecha_creacion(TFecha.ahora(TFecha.formatoBD_Hora));
            recibo.setId_cliente(cuenta.getId_cliente());
            recibo.setNumero(tr.getNumero());
            recibo.setId_caja(id_caja);
            Integer id_recibo = tr.alta(recibo);

            if (p.getEfectivo() > 0) {
                Caja_detalle caja_detalle = tcaja_detalle.creaIngresoEfectivo(caja, cuenta, recibo, p.getEfectivo());
                tcaja_detalle.alta(caja_detalle);
            }
            if (p.getCheque_mnt() > 0) {
                Caja_detalle caja_detalle = tcaja_detalle.creaIngresoCheque(caja, cuenta, recibo, p.getCheque_mnt());
                tcaja_detalle.alta(caja_detalle);
            }
            if (p.getTransf_mnt() > 0) {
                Caja_detalle caja_detalle = tcaja_detalle.creaIngresoTransferencia(caja, cuenta, recibo, p.getTransf_mnt());
                tcaja_detalle.alta(caja_detalle);
            }
            tcd.setOrderBy("fecha");
            lista = tcd.getById_cuenta(cuenta.getId());

            if (lista == null) {
                throw new BaseException("ERROR", "Ocurri&oacute; un error al editar la cuenta");
            }
            Contrato contrato = tcr.getById(cuenta.getId_contrato());
            Float punitorio_porc = contrato.getPunitorio_monto() / 100;
            String ult_liquidacion;
            if (cuenta.getFecha_liquidacion() == null || cuenta.getFecha_liquidacion().equals("")) {
                ult_liquidacion = new LocalDate(contrato.getFecha_inicio()).minusDays(1).toString();
            } else {
                ult_liquidacion = cuenta.getFecha_liquidacion();
            }
            /* Si es la primer liquidación, tomamos como ultima liquidacion, el inicio del contrato */

            LocalDate fecha_liquidacion = new LocalDate(ult_liquidacion);
            LocalDate fecha_consulta;
            if (strFecha == null || strFecha.equals("")) {
                fecha_consulta = new LocalDate();
            } else {
                fecha_consulta = new LocalDate(strFecha);
            }

            Integer punitorio_desde = contrato.getPunitorio_desde();
            LocalDate today = new LocalDate();
            for (Cuenta_detalle cuenta_detalle : lista) { // Calculo los punitorios
                LocalDate fecha = new LocalDate(cuenta_detalle.getFecha());
                if (ult_liquidacion != null && fecha.isBefore(fecha_liquidacion)) {
                    continue;
                }
                if (fecha.isEqual(fecha_liquidacion) && !cuenta_detalle.getId_concepto().equals(OptionsCfg.CONCEPTO_SALDO)) {
                    continue;
                }

                if (fecha.isAfter(fecha_consulta)) {
                    continue; // No se considera nada posterior a la fecha de consulta
                }
                if (fecha.isAfter(today)) {
                    continue;
                }
                if (cuenta.getId_tipo_cliente().equals(OptionsCfg.CLIENTE_TIPO_PROPIETARIO)) {
                    saldo += cuenta_detalle.getHaber() - cuenta_detalle.getDebe();
                } else {
                    saldo += cuenta_detalle.getDebe() - cuenta_detalle.getHaber();
                }

                if (cuenta.getId_tipo_cliente().equals(OptionsCfg.CLIENTE_TIPO_INQUILINO)) {
                    int days = Days.daysBetween(fecha, today).getDays() - 1;
                    if (days < punitorio_desde) {
                        continue;
                    }
                    Cuenta_detalle punitorio = tcd.calcularPunitorio(cuenta_detalle, 1, punitorio_porc, new LocalDate());
                    if (punitorio != null) {
                        listaPunitorio.add(punitorio);
                        saldo += punitorio.getDebe();
                    }
                }
            }

            Float total = liqEfeMnt + liqChkMnt + liqTraMnt;

            //Guardo el pago en la cuenta
            cd = new Cuenta_detalle();
            cd.setId_cuenta(cuenta.getId());
            cd.setFecha(strFecha);
            cd.setFecha_creacion(TFecha.ahora(TFecha.formatoBD));
            cd.setConcepto(String.format("Pago Recibo Nº %d Fecha: %s", recibo.getNumero(), request.getParameter("fecha")));
            cd.setId_concepto(OptionsCfg.CONCEPTO_PAGO);

            cd.setId_referencia(recibo.getId_pago());
            if (cuenta.getId_tipo_cliente().equals(OptionsCfg.CLIENTE_TIPO_PROPIETARIO)) {
                cd.setDebe(total);
            } else {
                cd.setHaber(total);
            }
            listaPunitorio.add(cd);

            saldo = saldo - total;
            if (saldo != 0) { // Si existe un saldo. Lo cargamos en la cuenta.
                Cuenta_detalle det_saldo = new Cuenta_detalle();
                det_saldo.setId_cuenta(id_cuenta);
                det_saldo.setFecha(cd.getFecha());
                det_saldo.setFecha_creacion(cd.getFecha_creacion());
                det_saldo.setId_referencia(recibo.getId_pago());
                det_saldo.setId_concepto(OptionsCfg.CONCEPTO_SALDO);
                det_saldo.setConcepto("Saldo");
                if (saldo > 0) {
                    det_saldo.setDebe(Math.abs(saldo));
                } else {
                    det_saldo.setHaber(Math.abs(saldo));
                }
                listaPunitorio.add(det_saldo);
            }
            cuenta.setFecha_liquidacion(strFecha);
            tc.actualizar(cuenta);
            for (Cuenta_detalle det_pago : listaPunitorio) {
                det_pago.setId_cuenta(id_cuenta);
                det_pago.setFecha_creacion(TFecha.ahora());
                int id = tcd.alta(det_pago);
            }

            // Una vez guardados los punitorios, recorro nuevamente para crear el detall del recibo            
            tcd.setOrderBy("fecha,id_concepto");
            lista = tcd.getById_cuenta(cuenta.getId());

            Float saldo_recibo = 0f;
            for (Cuenta_detalle cuenta_detalle : lista) { // Guardo el detalle del recibo
                LocalDate fecha_det = new LocalDate(cuenta_detalle.getFecha());
                if (ult_liquidacion != null && fecha_det.isBefore(fecha_liquidacion)) {
                    continue;
                }
                if (fecha_det.isAfter(fecha_consulta)) {
                    continue;
                }

                if (cuenta.getId_tipo_cliente().equals(OptionsCfg.CLIENTE_TIPO_PROPIETARIO)) {
                    saldo_recibo += cuenta_detalle.getHaber() - cuenta_detalle.getDebe();
                } else {
                    saldo_recibo += cuenta_detalle.getDebe() - cuenta_detalle.getHaber();
                }
                if (cuenta_detalle.getId_concepto().equals(OptionsCfg.CONCEPTO_PAGO)) {
                    saldo_recibo = 0f;
                }

                Recibo_detalle rd = new Recibo_detalle();
                rd.setId_recibo(id_recibo);
                rd.setConcepto(cuenta_detalle.getConcepto());
                rd.setFecha(cuenta_detalle.getFecha());
                rd.setDebe(cuenta_detalle.getDebe());
                rd.setHaber(cuenta_detalle.getHaber());
                rd.setId_concepto(cuenta_detalle.getId_concepto());
                rd.setSaldo(saldo_recibo);
                trd.alta(rd);
            }
            if (id_pago != 0) {
                jr.setResult("OK");
                recibo.setId(id_recibo);
                jr.setRecord(recibo);
            } else {
                throw new BaseException("ERROR", "Ocurr&oacute; un error al aplicar el pago");
            }
        } catch (BaseException ex) {
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
