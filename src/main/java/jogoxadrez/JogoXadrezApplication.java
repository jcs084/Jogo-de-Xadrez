package jogoxadrez;

import jogoxadrez.controladores.ControladorTempo;
import jogoxadrez.modelojogo.*;
import jogoxadrez.modelopecas.*;

import javax.swing.*;
import java.awt.*;

/**
 * Classe Principal onde se executará o jogo de xadrez
 */
public class JogoXadrezApplication extends JFrame {
    private JButton BT_REINICIAR_JOGO;
    private JButton BT_PASSAR_VEZ;

    private static JLabel labelRodada;

    private static Thread threadTempo;

    private int linhaPainelBotao = 10;
    private int colunaPainelBotao = 1;

    private Tabuleiro tabuleiro;

    private ControladorTempo controladorTempo;

    private RepresentarTabuleiro representarTabuleiro;

    public JogoXadrezApplication(){
        criaTabuleiro();
    }

    private void criaTabuleiro() {
        setTitle("Jogo de Xadrez");
        this.setLayout(new BorderLayout());
        this.controladorTempo = new ControladorTempo();
        this.tabuleiro = new Tabuleiro(controladorTempo);
        this.representarTabuleiro = new RepresentarTabuleiro(this.tabuleiro);
        this.controladorTempo.setRepresentarTabuleiro(this.representarTabuleiro);
        this.add(representarTabuleiro, BorderLayout.CENTER);

        this.add(painelRodada(), BorderLayout.SOUTH);

        this.add(criarPainelBotoes(), BorderLayout.EAST);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Controla o tempo limite de jogada
        this.threadTempo = new Thread(controladorTempo);
        this.threadTempo.start();

        this.pack();
        this.setVisible(true);
    }

    private JPanel criarPainelBotoes() {
        JPanel pnBotao = new JPanel();
        pnBotao.setLayout(new GridLayout(this.linhaPainelBotao, this.colunaPainelBotao));
        this.BT_REINICIAR_JOGO = new JButton("REINICIAR JOGO");
        this.BT_PASSAR_VEZ = new JButton("PASSAR A VEZ");
        pnBotao.add(this.BT_REINICIAR_JOGO);
        pnBotao.add(this.BT_PASSAR_VEZ);
        return pnBotao;
    }

    private JPanel painelRodada() {
        JPanel pnRodada = new JPanel();
        this.labelRodada = new JLabel("Vez de: BRANCO");
        pnRodada.add(this.labelRodada);
        return pnRodada;
    }

    public static void setLabelRodada(EnumCor corRodada) {
        labelRodada.setText("Vez de: " + corRodada);
    }

    public static void main(String[] args) {
        new JogoXadrezApplication();
    }
}