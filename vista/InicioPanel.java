package vista;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class InicioPanel extends JPanel implements ActionListener{

    private JButton [] botones;
    private JPanel panelPolimorfico;
    
    public InicioPanel(JPanel panelPolimorfico)
    {   
        super();
        this.panelPolimorfico = panelPolimorfico;
        iniciarComponentes();
    }
    
    private void iniciarComponentes()
    {
        botones = new JButton[6];
        botones[0] = new JButton("Automata Finito Determinista");
        botones[1] = new JButton("Automata Finito No Determinista");
        botones[2] = new JButton("Automata Finito No Determinista Epsilon");
        botones[3] = new JButton("Automata Finito A Pila");
        botones[4] = new JButton("Maquina De Turing");
        botones[5] = new JButton("Salir de la aplicacion");
        anadirDiseno();
        this.setLayout(new FlowLayout((int) CENTER_ALIGNMENT, 50, 20));
        for(int x = 0; x < botones.length; x++)
            this.add(botones[x]);
        this.setSize(800,600);
    }
    
    public void hacerVisible(boolean visible)
    {
        this.setVisible(visible);
    }
    
    private void anadirDiseno()
    {
        for(int x = 0; x < botones.length; x++)
        {
            botones[x].setFont(new Font("",Font.BOLD,14));
            botones[x].setBorder(BorderFactory.createRaisedBevelBorder());
            botones[x].addActionListener(this);
            botones[x].setPreferredSize(new Dimension(330,150));
        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        CardLayout cardLayout = (CardLayout) panelPolimorfico.getLayout();
        if(ae.getSource() == botones[0])
            cardLayout.show(panelPolimorfico,"afd");
        if(ae.getSource() == botones[1])
            cardLayout.show(panelPolimorfico, "afn");
        if(ae.getSource() == botones[2])
            cardLayout.show(panelPolimorfico, "afne");
        if(ae.getSource() == botones[3])
            cardLayout.show(panelPolimorfico, "afp");
        if(ae.getSource() == botones[4])
            cardLayout.show(panelPolimorfico, "mt");
        if(ae.getSource() == botones[5])
        {
            int op = JOptionPane.showConfirmDialog(null,"¿Realmente deseas salir?","Confirmar salida",JOptionPane.YES_NO_OPTION);
            if(op == 0)
            {
                System.exit(0);
            }
        }
    }
}
