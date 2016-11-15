/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package meziimport;

import bd.Cliente;
import bd.Propiedad;
import bd.Propietario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import transaccion.TCliente;
import transaccion.TInquilino;
import transaccion.TPropiedad;
import transaccion.TPropietario;
import utils.OptionsCfg;

/**
 *
 * @author Diego
 */
public class MeziImport {

    /**
     * @param args the command line arguments
     */
    private final String DELIMITER = ";";
    private boolean guardar = true;
    public static void main(String[] args) {
        // TODO code application logic here
        if(args.length < 1) {
            System.out.println("Ingrese el directorio que contiene los archivos de importación");
            System.out.println(args.length);
        }
        String directorio = args[0];
        new MeziImport().loadInquilinos(directorio + "INQUILINOS.csv");
        new MeziImport().loadPropietarios(directorio + "PROPIETARIOS.csv");
        new MeziImport().loadPropiedades(directorio + "PROPIEDAD.csv");
    }
    private void loadInquilinos(String fileName){
        String linea = "";
        Integer cant = 0;
        TCliente tc =  new TCliente();
        TInquilino ti = new TInquilino();
        try(BufferedReader br = new BufferedReader( new InputStreamReader(new FileInputStream(fileName),"UTF-8"))){
            br.readLine();
            while((linea = br.readLine())!=null){
                cant++; 
                Integer i = 0;
                linea = linea.replace("\"", "");
                String[] tokens = linea.split(DELIMITER);
                String Dnipta        = tokens[i++];
                String dniinq        = tokens[i++];
                String codpda        = tokens[i++];
                String apellido      = tokens[i++];
                String nombre        = tokens[i++];
                String nc            = tokens[i++];
                String obs           = tokens[i++];                
                Cliente cliente;
                
                cliente = tc.getByDocumento(dniinq);
                if(cliente!=null){
                    System.out.println(String.format("El inquilino %s ya se encuentra cargado en el sistema",dniinq));
                  continue;  
                } 
                 cliente = new Cliente();
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setDni(dniinq);
                cliente.setCarpeta(Parser.parseInt(nc));
                cliente.setId_localidad(2384);
                cliente.setObservaciones(obs);
                if(guardar) {
                    Integer id_cliente = tc.alta(cliente);
                    //ti.alta(id_cliente);
                }
            }
        }catch (IOException ex){
            System.out.println("");
        }catch(java.lang.ArrayIndexOutOfBoundsException ex){
           System.out.println(String.format("Ocurrió un error al leer la linea \n %s",linea));
        }
        System.out.println(String.format("Se cargaron %d inquilinos",cant));
    }
     private void loadPropiedades(String fileName){
        String linea = "";
        Integer cant = 0;
        TPropiedad tp =  new TPropiedad();
        TCliente tc   = new TCliente();
        try(BufferedReader br = new BufferedReader( new InputStreamReader(new FileInputStream(fileName),"UTF-8"))){
            br.readLine();
            while((linea = br.readLine())!=null){
                cant++;
                linea = linea.replace("\"", "");
                Integer i = 0;
                String[] tokens = linea.split(DELIMITER);
                String dnipta       = tokens[i++];
                String codpda       = tokens[i++];
                String direccion    = tokens[i++];
                String nro          = tokens[i++];
                String inmueble     = tokens[i++];
                String precio       = tokens[i++];
                String comodidad    = tokens[i++];
                String caract       = tokens[i++];
                String supterreno   = tokens[i++];
                String supcubierta  = tokens[i++];
                String antigconst   = tokens[i++];
                String barrio       = tokens[i++];
                String localidad    = tokens[i++];
                String formapago    = tokens[i++];
                String obs          = tokens[i++];
                String tipo         = tokens[i++];
                String ocu          = tokens[i++];
                String fecha        = tokens[i++];
                String artefactos   = tokens[i++];
                String ubicacioncha = tokens[i++];
                String hectareas    = tokens[i++];
                String plantaciones = tokens[i++];
                String construccion = tokens[i++];
                String herramientas = tokens[i++];
                String tel          = tokens[i++];
                String impresion    = tokens[i++];
                String fecingre     = tokens[i++];
                String ocupada      = tokens[i++];
                String cartel       = tokens[i++];
                String llave        = tokens[i++];
                String foto         = tokens[i++];
                String alquilada    = tokens[i++];
//                String localidad = "";
                Integer  id_tipo_inmueble = 0;
                Integer id_localidad      = 0;
                
                if (inmueble.equalsIgnoreCase("Casa")) id_tipo_inmueble = 1;
                else if (inmueble.equalsIgnoreCase("Departamento") || inmueble.equalsIgnoreCase("dpto") ) id_tipo_inmueble = 2;
                else if (inmueble.equalsIgnoreCase("Terreno")) id_tipo_inmueble = 3;
                else if (inmueble.equalsIgnoreCase("Local comercial") || inmueble.equalsIgnoreCase("Salón") || inmueble.equalsIgnoreCase("Salon") )id_tipo_inmueble = 4;
                else if (inmueble.equalsIgnoreCase("Chacra") )id_tipo_inmueble = 5;
                else if (inmueble.equalsIgnoreCase("Galpón") )id_tipo_inmueble = 6;
                
                if(localidad.equalsIgnoreCase("Las grutas")) continue;
                else id_localidad = 2384;
                
                Cliente cliente = tc.getByDocumento(dnipta);
                Propiedad propiedad = new Propiedad();
                propiedad.setCalle(direccion);
                propiedad.setNumero(nro);
                propiedad.setCodigo(codpda);
                propiedad.setId_tipo_inmueble(id_tipo_inmueble);
                propiedad.setPrecio(Parser.parseFloat(precio));
//                propiedad.setSup_cubierta(Parser.parseFloat(supcubierta));
//                propiedad.setSup_terreno(Parser.parseFloat(supterreno));
                propiedad.setId_localidad(id_localidad);
                if(!"".equals(comodidad))  obs += String.format("Comodidades: %s\n",comodidad);
                if(!"".equals(caract))     obs += String.format("Caracteristicas: %s\n",caract);
                if(!"".equals(artefactos)) obs += String.format("Artefactos: %s\n",artefactos);
                
                propiedad.setObservaciones(obs);
                propiedad.setId_estado(OptionsCfg.PROPIEDAD_DISPONIBLE);
                System.out.println(id_tipo_inmueble);
                if (cliente!=null){
                    if(guardar) {
                        new TPropietario().alta(cliente);
                        propiedad.setId_propietario(cliente.getId());
                    }
                }
                if(guardar) tp.alta(propiedad);
            }
        }catch (IOException ex){
            System.out.println("");
        } catch(java.lang.ArrayIndexOutOfBoundsException ex){
            System.out.println(String.format("Ocurrió un error al leer la linea \n %s",linea));
            System.out.println(String.format("Split: %d", linea.split(DELIMITER).length));
            
        }
         System.out.println(String.format("Se cargaron %d propiedades", cant));
    }
    private void loadPropietarios(String fileName){
        System.out.println("Cargando propietarios");
        String linea = "";
        Integer cant = 0;
        TCliente tc =  new TCliente();
        TPropietario tp = new TPropietario();
        try(BufferedReader br = new BufferedReader( new InputStreamReader(new FileInputStream(fileName),"UTF-8"))){
            br.readLine();
            while((linea = br.readLine())!=null){
                cant++;
                linea = linea.replace("\"","");
                Integer i = 0;
                String[] tokens = linea.split(DELIMITER);
                String ncpta         = tokens[i++];
                String apellido      = tokens[i++];
                String nombre        = tokens[i++];
                String direccion     = tokens[i++];
                String nro           = tokens[i++];
                String tel           = tokens[i++];
                String bcoycta       = tokens[i++];
                String cuit          = tokens[i++];
                String condicion     = tokens[i++];
                String dni           = tokens[i++];
                String obs           = tokens[i++];
                String ctacte        = tokens[i++];
                String cajaahorro    = tokens[i++];
                String impuesto      = tokens[i++];
                String documentacion = tokens[i++];
                String usuari        = tokens[i++];
                Cliente cliente ;
                if(!"".equals(dni)) {
                    cliente = tc.getByDocumento(dni);
                    if(cliente!=null){
                        System.out.println(String.format("El propietario %s ya se encuentra cargado en el sistema",dni));
                      continue;  
                    } 
                }
                cliente = new Cliente();
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setDni(dni);
                cliente.setCarpeta(Parser.parseInt(ncpta));
                cliente.setId_localidad(2384);
                
                cliente.setDireccion(direccion);
                cliente.setTelefono(tel);
                //cliente.setObservaciones(obs);
                if(guardar){
                    Integer id_cliente = tc.alta(cliente);
                    tp.alta(id_cliente);
                }
            }
        } 
        catch (IOException ex){
            System.out.println("");
        }catch(java.lang.ArrayIndexOutOfBoundsException ex){
            System.out.println(String.format("Ocurrió un error al leer la linea \n %s",linea));
            
        }
         System.out.println(String.format("Se cargaron %d propietarios", cant));
    }
}
