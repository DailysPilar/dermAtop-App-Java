package javafxapplication2;

/**
 *
 * @author hlopez
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import javax.swing.JOptionPane;
import org.jpl7.Query;
import org.jpl7.Term;
//import org.jpl7.Query;
//import org.jpl7.Term;

/**
 * Se encarga de gestionar la consulta de las bases de conocimiento.
 */
public class Conexion {

    String consulta;
    Query q;
    private Map<String, Term> solucion;

 public Conexion() {
        String nombreArchivo = "ProyectoDA.pl"; // Nombre del archivo Prolog
        InputStream input = getClass().getClassLoader().getResourceAsStream("resources/" + nombreArchivo);
        
        if (input == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el archivo Prolog: " + nombreArchivo, "ERROR", JOptionPane.ERROR);
            return;
        }

        // Guardar temporalmente el archivo en el sistema de archivos
        try {
            File tempFile = File.createTempFile("ProyectoDA", ".pl");
            tempFile.deleteOnExit(); // Eliminar al cerrar la aplicación
            
            // Copiar el InputStream al archivo temporal
            try (FileOutputStream out = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = input.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
            }

            // Consultar el archivo temporal
            String consulta = "consult('" + tempFile.getAbsolutePath().replace("\\", "\\\\") + "')";
            q = new Query(consulta);
            if (!q.hasSolution()) {
                JOptionPane.showMessageDialog(null, "No se pudo consultar la base de conocimiento: " + nombreArchivo, "ERROR", JOptionPane.ERROR);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public String obtenerdiagnostico(String valores,int edad) {
        consulta = "diagnostico(['"+valores+"'],"+edad+",Solucion).";
        q = new Query(consulta);

        String resultado="";

        if (!q.hasSolution()) {
            resultado += "No se encontraron\n";
        } else {
           while (q.hasMoreSolutions()) {
            solucion = q.nextSolution();
           resultado += solucion.get("Solucion")+ "\n";
        }
           
        }
        resultado=resultado.replace("'", "");
        resultado=resultado.replace(", ", ",\n");
        return resultado;
    }

    public String obtenergravedad(int a, int b, int c) {
        consulta = "scorad("+a+","+b+","+c+",Score,Gravedad).";
        q = new Query(consulta);

        String resultado = "";
        
        if (!q.hasSolution()) {
            resultado += "No se encontraron\n";
        } else {
             while (q.hasMoreSolutions()) {
            solucion = q.nextSolution();
            resultado += "Valor de SCORAD = "+solucion.get("Score")+ "\n Presenta una DA " + solucion.get("Gravedad")+"\n";
        }
           
        }
        resultado=resultado.replace("'", " ");
        return resultado;
    }
   
    public String obtenertratamiento(int edad) {
        consulta = "recomendar_tratamiento("+edad+",Tratamiento).";
        q = new Query(consulta);
        
        String resultado = "";
        
        if (!q.hasSolution()) {
            resultado += "No se encontraron\n";
        } else {
            while (q.hasMoreSolutions()) {
               solucion = q.nextSolution();
               String valor=solucion.get("Tratamiento").toString();
             valor=valor.replace("[", "");
             valor=valor.replace("]", "");
             char[] caracteres = valor.toCharArray();
             valor="";
             int contadorcomilla=0;
             for (int i = 0; i < caracteres.length; i++) {
               if(caracteres[i] ==',' && contadorcomilla==0){
                     valor+='\n'; 
                     continue;
               }
               if (caracteres[i] =='\''&& contadorcomilla==0) {
                   
                  contadorcomilla=1;
                  continue;
               }
               if (caracteres[i] =='\''&& contadorcomilla==1) {
      
                 contadorcomilla=0;
                 continue;
               }
               valor+=caracteres[i];
             }
               
               resultado += "Tratamiento: \n\n"+valor+ "\n";
             }
           
        }
        return resultado;
    }
  
    public String[] obtenerscorad() {
      consulta = "scorad(20,32,5,Score,_).";
        q = new Query(consulta);

        String resultado[] = new String[2];
        
        resultado[0] = "Consulta\n\n?- " + consulta;
        resultado[1] = "";

        if (!q.hasSolution()) {
            resultado[1] += "No se encontraron\n";
        } else {
             while (q.hasMoreSolutions()) {
            solucion = q.nextSolution();
         
            resultado[1] += "Score = "+solucion.get("Score")+"\n";
        }
           
        }
        return resultado;
    }
}
