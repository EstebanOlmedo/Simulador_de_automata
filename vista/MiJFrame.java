package vista;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import control.ControlDePeticion;

public class MiJFrame extends JFrame{
    
    private InicioPanel inicio;
    private JPanel panelPolimorfico;
    private AutomataFinitoDeterministaPanel afd;
    private AutomataFinitoNoDeterministaPanel afn;
    private AutomataFinitoNoDeterministaEpsilonPanel afne;
    private AutomataFinitoAPilaPanel afp;
    private MaquinaDeTuringPanel mt;
    private ControlDePeticion peticiones;
    
    public MiJFrame()
    {
        super("Simulador de automatas y maquina de Turing");
        peticiones = new ControlDePeticion();
        inciarComponentes();
    }

    private void inciarComponentes()
    {
        iniciarPaneles();
        panelPolimorfico.add(inicio,"inicio");
        panelPolimorfico.add(afd,"afd");
        panelPolimorfico.add(afn,"afn");
        panelPolimorfico.add(afne,"afne");
        panelPolimorfico.add(afp,"afp");
        panelPolimorfico.add(mt,"mt");
        //this.setSize(ancho,alto);
        this.add(panelPolimorfico);
         this.setResizable(false);
        this.setSize(1200, 1000);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void iniciarPaneles()
    {
        panelPolimorfico = new JPanel();
        panelPolimorfico.setLayout(new CardLayout());
        inicio = new InicioPanel(panelPolimorfico);
        afd = new AutomataFinitoDeterministaPanel(panelPolimorfico,peticiones);
        afn = new AutomataFinitoNoDeterministaPanel(panelPolimorfico,peticiones);
        afne = new AutomataFinitoNoDeterministaEpsilonPanel(panelPolimorfico,peticiones);
        afp = new AutomataFinitoAPilaPanel(panelPolimorfico,peticiones);
        mt = new MaquinaDeTuringPanel(panelPolimorfico,peticiones);
    }
    
    
    
}

