package vista;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.Examen;

public class Herramientas {

    public static File obtenerFileParaGuardar() {
        File file = null;
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Seleccione un archivo para guardar...");
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setFileFilter(new FileNameExtensionFilter("Archivo JavaScript Object Notation JSON (*.json)", "json"));
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.showDialog(null, "Guardar");
        if (jfc.getSelectedFile() != null) {
            if (!jfc.getSelectedFile().toString().toLowerCase().endsWith(".json")) {
                file = new File(jfc.getSelectedFile().toString() + ".json");
            } else {
                file = new File(jfc.getSelectedFile().toString());
            }
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "No se pudo crear el archivo."
                            + "\nDescripción del error: " + ex.getMessage());
                }
            }
        }
        return file;
    }

    public static void exportarArchivoJSON(File archivoDondeGuardar, ArrayList<Examen> examenes) {
        Gson gson = new Gson();
        String json = gson.toJson(examenes);
        FileWriter fw = null;
        try {
            fw = new FileWriter(archivoDondeGuardar);
            fw.write(json);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Herramientas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(Herramientas.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }

}
