package vista;

import control.ControlDePeticion;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
    
    public VisualizadorDeArchivosPanel(JPanel panelPolimorfico,ControlDePeticion control,String peticion)
    {
        super();
        this.panelPolimorfico = panelPolimorfico;
        this.control = control;
        this.peticion = peticion;
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
                    cargarObjeto();
                    JOptionPane.showConfirmDialog(null,archivo.getName()+" Cargado con exito",null,JOptionPane.DEFAULT_OPTION);
                    ((CardLayout) panelPolimorfico.getLayout()).show(panelPolimorfico, peticion);
                }
            }
        }
        else if(ae.getSource() == botonSalir)
        {
            if(archivo == null)
            {
                JOptionPane.showConfirmDialog(null,"No has cargado/guardado ningun archivo",null,JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
            }
            ((CardLayout) panelPolimorfico.getLayout()).show(panelPolimorfico,peticion);
        }
        else if(ae.getSource() == botonGuardar)
        {
            if(control.getAutomataFinitoAPila() != null || control.getAutomataFinitoDeterminista() != null ||
               control.getAutomataFinitoNoDeterminista() != null || control.getAutomataFinitoNoDeterministaEpsilon() != null
               || control.getMaquinaDeTuring() != null)
            {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int opc = fileChooser.showSaveDialog(null);
                if(opc == JFileChooser.APPROVE_OPTION)
                {
                        archivo = fileChooser.getSelectedFile();
                        guardarObjeto();
                        JOptionPane.showConfirmDialog(null,archivo.getName()+" Guardado con exito",null,JOptionPane.DEFAULT_OPTION);
                        ((CardLayout) panelPolimorfico.getLayout()).show(panelPolimorfico,peticion);
                }
            }
            else
            {
                JOptionPane.showConfirmDialog(null,"Aun no haz creado ningun objeto para guardar","ERROR",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    private void cargarObjeto()
    {
        switch(peticion)
        {
            case "afd": control.manejarPeticionDeCargado("AFD",archivo); break;
            case "afn": control.manejarPeticionDeCargado("AFN",archivo); break;
            case "afne": control.manejarPeticionDeCargado("AFNE",archivo); break;
            case "afp": control.manejarPeticionDeCargado("AFP", archivo); break;
            case "mt": control.manejarPeticionDeCargado("CMT",archivo); break;
        }
    }
    
    private void guardarObjeto()
    {
        switch(peticion)
        {
            case "afd": control.manejarPeticionDePersistencia("PAFD", archivo); break;
            case "afn": control.manejarPeticionDePersistencia("PAFN", archivo); break;
            case "afne": control.manejarPeticionDePersistencia("PAFNDE", archivo); break;
            case "afp": control.manejarPeticionDePersistencia("PAFP", archivo); break;
            case "mt": control.manejarPeticionDePersistencia("PMT", archivo); break;
        }
    }
    
}
