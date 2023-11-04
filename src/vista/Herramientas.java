package vista;

import com.google.gson.Gson;
import controlador.Consultas;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.Alumno;
import modelo.Examen;
import modelo.VistaMatricula;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Herramientas {

    public static File obtenerFileParaGuardar(String descripcionArchivo, String formato) throws IOException {
        File file = null;
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Seleccione un archivo para guardar...");
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setFileFilter(new FileNameExtensionFilter(descripcionArchivo, formato));
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.showDialog(null, "Guardar");
        if (jfc.getSelectedFile() != null) {
            if (!jfc.getSelectedFile().toString().toLowerCase().endsWith("." + formato)) {
                file = new File(jfc.getSelectedFile().toString() + "." + formato);
            } else {
                file = new File(jfc.getSelectedFile().toString());
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        }
        return file;
    }

    public static void exportarArchivoJSON(File archivoDondeGuardar, ArrayList<Examen> examenes) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(examenes);
        FileWriter fw = new FileWriter(archivoDondeGuardar);
        fw.write(json);
        fw.close();
    }

    public static Document rellenarDocument(Document d) throws SQLException {
        // Raiz
        Element raiz = d.createElement("academia");
        d.appendChild(raiz);
        ArrayList<Alumno> alumnos = Consultas.obtenerAlumnos();
        for (Alumno a : alumnos) {
            //Primer hijo
            Element alumno = d.createElement("alumno");
            alumno.setAttribute("cCodALu", a.getcCodAlu());
            alumno.setAttribute("cNomAlu", a.getcNomAlu());
            raiz.appendChild(alumno);
            ArrayList<VistaMatricula> matriculas = Consultas.obtenerVistaMatriculas(a.getcCodAlu());
            for (VistaMatricula vm : matriculas) {
                //Segundo hijo
                Element matricula = d.createElement("matricula");
                alumno.appendChild(matricula);
                //Atributos del segundo hijo
                Element codigoCurso = d.createElement("codigo_curso");
                codigoCurso.appendChild(d.createTextNode(vm.getcCodCurso()));
                matricula.appendChild(codigoCurso);
                Element nombreCurso = d.createElement("nombre_curso");
                nombreCurso.appendChild(d.createTextNode(vm.getcNomCurso()));
                matricula.appendChild(nombreCurso);
                Element notaMedia = d.createElement("nota_media");
                notaMedia.appendChild(d.createTextNode(String.valueOf(vm.getnNotaMedia())));
                matricula.appendChild(notaMedia);
            }
        }
        return d;
    }
}
