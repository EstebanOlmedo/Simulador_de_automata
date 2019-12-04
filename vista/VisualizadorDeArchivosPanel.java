package vista;

import control.ControlDePeticion;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class VisualizadorDeArchivosPanel extends JPanel implements ActionListener{

    private JTextField texto;
    private JButton botonAbrir;
    private JButton botonSalir;
    private JButton botonGuardar;
    private File archivo;
    private ControlDePeticion control;
    private JPanel panelPolimorfico;
    private String peticion;
    private JTextArea descripcion;
    
    public VisualizadorDeArchivosPanel(JPanel panelPolimorfico,ControlDePeticion control,String peticion,JTextArea descripcion)
    {
        super();
        this.panelPolimorfico = panelPolimorfico;
        this.control = control;
        this.peticion = peticion;
        this.descripcion = descripcion;
        iniciarComponentes();
    }

    private void iniciarComponentes()
    {
        texto = new JTextField(35);
        botonAbrir = new JButton("Cargar Archivo");
        botonSalir = new JButton("Salir");
        botonGuardar = new JButton("Guardar Objeto");
        botonAbrir.addActionListener(this);
        botonSalir.addActionListener(this);
        botonGuardar.addActionListener(this);
        this.add(texto);
        this.add(botonAbrir);
        this.add(botonGuardar);
        this.add(botonSalir);
        this.setVisible(false);
        this.setLayout(new FlowLayout());
        this.setSize(400, 110);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource() == botonAbrir)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            FileNameExtensionFilter filtroArchivos = new FileNameExtensionFilter("Automatas y Maquina de Turing", "afd", "afn","afne","afp","mt"); 
            fileChooser.setFileFilter(filtroArchivos);
            int result = fileChooser.showOpenDialog(this);
            if (result != JFileChooser.CANCEL_OPTION) 
            {
                archivo = fileChooser.getSelectedFile();
                if ((archivo == null) || (archivo.getName().equals("")) || archivo.isDirectory())
                {
                    archivo = null;
                } 
                else
                {
                    texto.setText(archivo.getName());
                }
            }
        }
        else if(ae.getSource() == botonSalir)
        {
            if(archivo != null)
            {
                JOptionPane.showConfirmDialog(null,archivo.getName()+" Cargado con exito",null,JOptionPane.DEFAULT_OPTION);
                switch(peticion)
                {
                    case "afd":
                        control.manejarPeticionDeCargado("AFD",archivo);
                        descripcion.setText(control.getAutomataFinitoDeterminista().getDescripcion());
                        break;
                    case "afn":
                        control.manejarPeticionDeCargado("AFD",archivo);
                        descripcion.setText(control.getAutomataFinitoNoDeterminista().getDescripcion());
                        break;
                    case "afne":
                        control.manejarPeticionDeCargado("AFD",archivo);
                        descripcion.setText(control.getAutomataFinitoNoDeterministaEpsilon().getDescripcion());
                        break;
                    case "afp":
                        control.manejarPeticionDeCargado("AFD",archivo);
                        descripcion.setText(control.getAutomataFinitoAPila().getDescripcion());
                        break;
                    case "mt":
                        control.manejarPeticionDeCargado("CMT",archivo);
                        descripcion.setText(control.getMaquinaDeTuring().getDescripcion());
                        break;
                }
            }
            else
            {
                JOptionPane.showConfirmDialog(null,"No has cargado ningun archivo",null,JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
            }
            ((CardLayout) panelPolimorfico.getLayout()).show(panelPolimorfico,peticion);
        }
        else if(ae.getSource() == botonGuardar)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int opc = fileChooser.showSaveDialog(null);
	    if(opc == JFileChooser.APPROVE_OPTION)
	    {
		    archivo = fileChooser.getSelectedFile();
		    switch(peticion)
		    {
			    case "afd": control.manejarPeticionDePersistencia("PAFD", archivo); break;
			    case "afn": control.manejarPeticionDePersistencia("PAFN", archivo); break;
			    case "afne": control.manejarPeticionDePersistencia("PAFNDE", archivo); break;
			    case "afp": control.manejarPeticionDePersistencia("PAFP", archivo); break;
			    case "mt": control.manejarPeticionDePersistencia("PMT", archivo); break;
		    }
                    JOptionPane.showConfirmDialog(null,archivo.getName()+" Guardado con exito",null,JOptionPane.DEFAULT_OPTION);
	    }
	    //archivo = fileChooser.getSelectedFile();
        }
    }
    
    public File getFile()
    {
        return archivo;
    }
    
}
