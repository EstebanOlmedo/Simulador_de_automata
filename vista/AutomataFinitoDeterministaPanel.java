package vista;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import control.ControlDePeticion;
import control.ControlEvaluadorDeCadena;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class AutomataFinitoDeterministaPanel extends JPanel implements ActionListener{
    
    private JPanel paneles[];
    private JButton botones [];
    private JLabel tipo;
    private JTextArea descripcion;
    private JPanel panelPolimorfico;
    private ControlDePeticion control;
    private VisualizadorDeArchivosPanel visualizador;
    
    public AutomataFinitoDeterministaPanel(JPanel panelPolimorfico,ControlDePeticion control)
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
        visualizador = new VisualizadorDeArchivosPanel(panelPolimorfico,control,"afd",descripcion);
    }
    
    private void iniciarLabels()
    {
        tipo = new JLabel("AUTOMATA FINITO DETERMINISTA", (int) CENTER_ALIGNMENT);
        tipo.setFont(new Font("",Font.BOLD,20));
        tipo.setBounds(0, 0, 570, 30);
        descripcion = new JTextArea();
        descripcion.setFont(new Font("",Font.BOLD,13));
        descripcion.setBounds(10, 40, 550, 50);
        descripcion.setOpaque(false);
        descripcion.setLineWrap(true);
        descripcion.setEditable(false);
        paneles[3].add(tipo);
        paneles[3].add(descripcion);
    }
    
    private void iniciarPaneles()
    {
        paneles = new JPanel[4];
        for (int i = 0; i < paneles.length; i++) 
        {
            paneles[i] = new JPanel();
            paneles[i].setLayout(null);
            this.add(paneles[i]);
        }
        paneles[0].setBounds(590, 60, 200, 500);//botones
        paneles[1].setBounds(10, 480, 570,80);//lenguaje
        paneles[2].setBounds(10, 120, 570, 350);//dibujo
        paneles[3].setBounds(10, 10, 570, 100);//descripcion
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
        botones[0].setBounds(0,10,200,50);
        botones[1].setBounds(0,80,200,50);
        botones[2].setBounds(0,150,200,50);
        botones[3].setBounds(0,220,200,50);
        botones[4].setBounds(0,290,200,50);
        botones[5].setBounds(0,360,200,50);
        botones[0].setText("Generar AF Determinista");
        botones[1].setText("Evaluar cadena");
        botones[2].setText("Convertir AFD a AFP");
        botones[3].setText("Guardar el AFD en un archivo");
        botones[4].setText("Cargar AFD de un archivo");
        botones[5].setText("Regresar al menu principal");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == botones[0])
        {
            control.manejarPeticion("GAFD");
            descripcion.setText(control.getAutomataFinitoDeterminista().getDescripcion());
        }
        else if(ae.getSource() == botones[1])
        {
            control.manejarPeticion("EAFD");
        }
        else if(ae.getSource() == botones[2])
        {
            control.manejarPeticion("CAFD-AFP");
        }
        else if(ae.getSource() == botones[3])
        {
            control.manejarPeticion("PAFD");
        }
        else if(ae.getSource() == botones[4])
        {
            panelPolimorfico.add(visualizador,"archivos");
            ((CardLayout) panelPolimorfico.getLayout()).show(panelPolimorfico,"archivos");
        }
        else if(ae.getSource() == botones[5])
        {
            ((CardLayout) panelPolimorfico.getLayout()).show(panelPolimorfico, "inicio");
        }
    }
}
