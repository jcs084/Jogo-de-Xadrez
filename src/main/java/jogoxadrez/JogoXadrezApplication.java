package jogoxadrez;

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
    private int linhaPainelBotao = 10;
    private int colunaPainelBotao = 1;
    private Tabuleiro tabuleiro;

    public JogoXadrezApplication(){
        criaTabuleiro();
        this.add(new RepresentarTabuleiro(this.tabuleiro), BorderLayout.CENTER);

        this.add(painelRodada(), BorderLayout.SOUTH);

        this.add(criarPainelBotoes(), BorderLayout.EAST);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    private void criaTabuleiro() {
        setTitle("Jogo de Xadrez Ifsc");
        this.setLayout(new BorderLayout());
        this.tabuleiro = new Tabuleiro();
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