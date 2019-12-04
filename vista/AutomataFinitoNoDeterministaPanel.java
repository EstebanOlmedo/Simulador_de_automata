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

public class AutomataFinitoNoDeterministaPanel extends JPanel implements ActionListener{
 
    private JPanel paneles [];
    private JButton botones [];
    private JLabel tipo;
    private JTextArea descripcion;
    private JPanel panelPolimorfico;
    private ControlDePeticion control;
    
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
    }
    
    private void iniciarLabels()
    {
        tipo = new JLabel("AUTOMATA FINITO NO DETERMINISTA",(int) CENTER_ALIGNMENT);
        tipo.setFont(new Font("",Font.BOLD,30));
        tipo.setBounds(0,0,870,50);
        descripcion = new JTextArea("");
        descripcion.setFont(new Font("",Font.BOLD,20));
        descripcion.setBounds(10, 70, 850, 100);
        descripcion.setLineWrap(true);
        descripcion.setOpaque(false);
        descripcion.setEditable(false);
        paneles[3].add(tipo);
        paneles[3].add(descripcion);
    }
    
    private void iniciarPaneles()
    {
        paneles = new JPanel[4];
        for(int x = 0; x < paneles.length; x++)
        {
            paneles[x] = new JPanel();
            paneles[x].setLayout(null);
            this.add(paneles[x]);
        }
        paneles[0].setBounds(890, 30, 300, 1000);//botones
        paneles[1].setBounds(10, 770, 870,190 );//lenguaje
        paneles[2].setBounds(10, 220, 870, 540);//dibujo
        paneles[3].setBounds(10, 10, 870, 200);//descripcion
    }
    
    public void iniciarBotones()
    {
        botones = new JButton [6];
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
        botones[0].setText("Generar AF No Determinista");
        botones[1].setText("Evaluar cadena");
        botones[2].setText("Convertir AFN a AFD");
        botones[3].setText("Guardar el AFN en un archivo");
        botones[4].setText("Cargar AFN de un archivo");
        botones[5].setText("Regresar al menu principal");
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == botones[0])
        {
            control.manejarPeticion("GAFND");
            descripcion.setText(control.getAutomataFinitoAPila().getDescripcion());
        }
        else if(ae.getSource() == botones[1])
        {
            control.manejarPeticion("EAFND");
        }
        else if(ae.getSource() == botones[2])
        {
            control.manejarPeticion("CAFND-AFD");
        }
        else if(ae.getSource() == botones[3])
        {
            control.manejarPeticion("PAFND");
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
          