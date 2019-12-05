package vista;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import control.ControlDePeticion;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import control.ControlDibujarDiagrama;
import java.awt.Color;
import javax.swing.JOptionPane;
import logica.AutomataFinitoNoDeterminista;

public class AutomataFinitoNoDeterministaPanel extends JPanel implements ActionListener{
 
    private JPanel paneles [];
    private JButton botones [];
    private JLabel tipo;
    private JTextArea descripcion;
    private JPanel panelPolimorfico;
    private ControlDePeticion control;
    private VisualizadorDeArchivosPanel visualizador;
    private JTextArea alfabeto;
    private JTextArea transiciones;
    private JTextArea mensaje;
    
    public AutomataFinitoNoDeterministaPanel(JPanel panelPolimorfico,ControlDePeticion control)
    {
        super();
        this.panelPolimorfico = panelPolimorfico;
        this.control = control;
        this.setLayout(null);
        iniciarComponentes();
    }  
    
    public void iniciarComponentes()
    {
        iniciarPaneles();
        iniciarBotones();
        iniciarLabels();
        visualizador = new VisualizadorDeArchivosPanel(panelPolimorfico, control, "afn");
    }
    
    private void iniciarLabels()
    {
        tipo = new JLabel("AUTOMATA FINITO DETERMINISTA", (int) CENTER_ALIGNMENT);
        tipo.setFont(new Font("",Font.BOLD,20));
        tipo.setBounds(0, 0, 570, 30);
        descripcion = new JTextArea();
        descripcion.setFont(new Font("",Font.BOLD,17));
        descripcion.setBounds(10, 40, 550, 50);
        descripcion.setOpaque(false);
        descripcion.setLineWrap(true);
        descripcion.setEditable(false);
        alfabeto = new JTextArea();
        alfabeto.setFont(new Font("",Font.BOLD,15));
        alfabeto.setBounds(0, 0, 570, 50);
        alfabeto.setEditable(false);
        alfabeto.setLineWrap(true);
        alfabeto.setOpaque(false);
        transiciones = new JTextArea();
        transiciones.setFont(new Font("",Font.BOLD,13));
        transiciones.setBounds(0,60,570,120);
        transiciones.setEditable(false);
        transiciones.setLineWrap(true);
        transiciones.setOpaque(false);
        mensaje = new JTextArea("SI HAZ CARGADO O\nCONVERTIDO UN AUTOMATA O MAQUINA DE TURING DA\nCLICK AL BOTON\nACTUALIZAR");
        mensaje.setFont(new Font("",Font.BOLD,12));
        mensaje.setEditable(false);
        mensaje.setLineWrap(true);
        mensaje.setOpaque(false);
        mensaje.setBounds(0, 0, 200, 80);
        paneles[3].add(tipo);
        paneles[3].add(descripcion);
        paneles[1].add(alfabeto);
        paneles[1].add(transiciones);
        paneles[4].add(mensaje);
    }
    
    private void iniciarPaneles()
    {
        paneles = new JPanel[5];
        for (int i = 0; i < paneles.length; i++) 
        {
            paneles[i] = new JPanel();
            paneles[i].setLayout(null);
            this.add(paneles[i]);
        }
        paneles[0].setBounds(590, 100, 200, 500);//botones
        paneles[1].setBounds(10, 380, 570,180);//lenguaje
        paneles[2].setBounds(10, 120, 570,250);//dibujo
        paneles[3].setBounds(10, 10, 570,100);//descripcion
        paneles[4].setBounds(590, 10, 200,80);//mensaje
    }
    
    public void iniciarBotones()
    {
        botones = new JButton[6];
        for(int x = 0; x < botones.length; x++)
        {
            botones[x] = new JButton();
            botones[x].setBorder(BorderFactory.createRaisedBevelBorder());
            botones[x].setFont(new Font("",Font.BOLD,10));
            botones[x].addActionListener(this);
            paneles[0].add(botones[x]);
        }
        botones[0].setBounds(0,0,200,50);
        botones[1].setBounds(0,70,200,50);
        botones[2].setBounds(0,140,200,50);
        botones[3].setBounds(0,210,200,50);
        botones[4].setBounds(0,280,200,50);
        botones[5].setBounds(0,350,200,50);
        botones[0].setText("Generar AF No Determinista");
        botones[1].setText("Evaluar cadena");
        botones[2].setText("Convertir AFN a AFD");
        botones[3].setText("Guardar/Cargar AFN de un archivo");
        botones[4].setText("Actualizar");
        botones[5].setText("Regresar al menu principal");
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == botones[0])
        {
            control.manejarPeticion("GAFND");
            actualizarInformacion();
        }
        else if(ae.getSource() == botones[1])
        {
            if(control.getAutomataFinitoNoDeterminista() != null)
                control.manejarPeticion("EAFND");
            else
                JOptionPane.showConfirmDialog(null, "No hay un automata generado", "ERROR", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
        }
        else if(ae.getSource() == botones[2])
        {
            control.manejarPeticion("CAFND-AFD");
        }
        else if(ae.getSource() == botones[3])
        {
            panelPolimorfico.add(visualizador, "archivos");
            ((CardLayout)panelPolimorfico.getLayout()).show(panelPolimorfico, "archivos");
        }
        else if(ae.getSource() == botones[4])
        {  
            actualizarInformacion();
        }
        else if(ae.getSource() == botones[5])
        {
            ((CardLayout) panelPolimorfico.getLayout()).show(panelPolimorfico, "inicio");
        }
    }
    
    private void actualizarInformacion()
    {
        if(control.getAutomataFinitoNoDeterminista() != null)
        {
            String transicion = "Transiciones:\n"+(((AutomataFinitoNoDeterminista)control.getAutomataFinitoNoDeterminista()).getTransiciones());
            descripcion.setText(control.getAutomataFinitoNoDeterminista().getDescripcion());
            alfabeto.setText(control.getAutomataFinitoNoDeterminista().getLenguaje());
            transiciones.setText(transicion);
            ControlDibujarDiagrama cd = new ControlDibujarDiagrama(control.getAutomataFinitoNoDeterminista(),null, new DibujadorDeDiagrama());
            cd.dibujarAutomata();
            cd.getDibujador().setBounds(0, 0, 570, 350);
            paneles[2].removeAll();
            paneles[2].add(cd.getDibujador());
            updateUI();
        }
        else
        {
            JOptionPane.showConfirmDialog(null, "No hay un automata generado", "ERROR", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}
          
