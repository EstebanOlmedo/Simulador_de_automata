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

public class AutomataFinitoAPilaPanel extends JPanel implements ActionListener{
 
    private JPanel paneles[];
    private JButton botones [];
    private JLabel tipo;
    private JTextArea descripcion;
    private JPanel panelPolimorfico;
    private ControlDePeticion control;
            
    public AutomataFinitoAPilaPanel(JPanel panelPolimorfico,ControlDePeticion control)
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
        tipo = new JLabel("AUTOMATA FINITO A PILA",(int) CENTER_ALIGNMENT);
        tipo.setFont(new Font("",Font.BOLD,20));
        tipo.setBounds(0,0,570,30);
        descripcion = new JTextArea();
        descripcion.setFont(new Font("",Font.BOLD,13));
        descripcion.setBounds(10, 40, 550, 50);
        descripcion.setLineWrap(true);
        descripcion.setOpaque(false);
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
        paneles[0].setBounds(590, 100, 200, 400);//botones
        paneles[1].setBounds(10, 480, 570,80);//lenguaje
        paneles[2].setBounds(10, 120, 570, 350);//dibujo
        paneles[3].setBounds(10, 10, 570,100);//descripcion
    }
    
    public void iniciarBotones()
    {
        botones = new JButton[5];
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
        botones[0].setText("Generar AF A Pila");
        botones[1].setText("Evaluar cadena");
        botones[2].setText("Guardar el AFP en un archivo");
        botones[3].setText("Cargar AFP de un archivo");
        botones[4].setText("Regresar al menu principal");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == botones[0])
        {
            control.manejarPeticion("GAFP");
            descripcion.setText(control.getAutomataFinitoAPila().getDescripcion());
        }
        else if(ae.getSource() == botones[1])
        {
            control.manejarPeticion("EAFP");
        }
        else if(ae.getSource() == botones[2])
        {
            control.manejarPeticion("PAFP");
        }
        else if(ae.getSource() == botones[3])
        {
            
        }
        else if(ae.getSource() == botones[4])
        {
            ((CardLayout) panelPolimorfico.getLayout()).show(panelPolimorfico, "inicio");
        }
    }
    
    
    
}
    