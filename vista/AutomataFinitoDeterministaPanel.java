package vista;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import control.ControlDePeticion;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public class AutomataFinitoDeterministaPanel extends JPanel implements ActionListener{
    
    private JPanel paneles[];
    private JButton botones [];
    private JLabel tipo;
    private JTextArea descripcion;
    private JPanel panelPolimorfico;
    private ControlDePeticion control;
    private JFileChooser archivos;
    
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
    }
    
    private void iniciarLabels()
    {
        tipo = new JLabel("AUTOMATA FINITO DETERMINISTA", (int) CENTER_ALIGNMENT);
        tipo.setFont(new Font("",Font.BOLD,30));
        tipo.setBounds(0, 0, 870, 50);
        descripcion = new JTextArea();
        descripcion.setFont(new Font("",Font.BOLD,20));
        descripcion.setBounds(10, 70, 850, 100);
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
        paneles[0].setBounds(890, 30, 300, 1000);//botones
        paneles[1].setBounds(10, 770, 870,190 );//lenguaje
        paneles[2].setBounds(10, 220, 870, 540);//dibujo
        paneles[3].setBounds(10, 10, 870, 200);//descripcion
    }
    
    public void iniciarBotones()
    {
        botones = new JButton[6];
        for(int x = 0; x < botones.length; x++)
        {
            botones[x] = new JButton();
            botones[x].setBorder(BorderFactory.createRaisedBevelBorder());
            botones[x].setFont(new Font("",Font.BOLD,17));
            botones[x].addActionListener(this);
            paneles[0].add(botones[x]);
        }
        botones[0].setBounds(0,10,300,140);
        botones[1].setBounds(0,160,300,140);
        botones[2].setBounds(0,310,300,140);
        botones[3].setBounds(0,460,300,140);
        botones[4].setBounds(0,610,300,140);
        botones[5].setBounds(0,760,300,140);
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
            
        }
        else if(ae.getSource() == botones[5])
        {
            ((CardLayout) panelPolimorfico.getLayout()).show(panelPolimorfico, "inicio");
        }
    }
}
