package jogoxadrez;

import jogoxadrez.modelojogo.*;
import jogoxadrez.modelopecas.*;

import javax.swing.*;
import java.awt.*;

/**
 * Classe Principal onde se executará o jogo de xadrez
 */
public class Principal extends JFrame {
    public static final int linhaPadraoPnLateral = 1;
    public static final int ColunaPadraoPnLateral = 10;
    public JButton BT_REINICIAR_JOGO;
    public JButton BT_PASSAR_VEZ;
    private static JLabel labelRodada;

    private Tabuleiro tabuleiro;

    public Principal(){
        criaTabuleiro();
        this.add(new RepresentarTabuleiro(tabuleiro), BorderLayout.CENTER);

        JPanel pnRodada = painelRodada();
        this.add(pnRodada, BorderLayout.SOUTH);

        JPanel pnBotao = criarPainelBotoes();
        this.add(pnBotao, BorderLayout.EAST);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    private JPanel criarPainelBotoes() {
        JPanel pnLateral = new JPanel();
        pnLateral.setLayout(new GridLayout(10, 1));
        BT_REINICIAR_JOGO = new JButton("REINICIAR JOGO");
        BT_PASSAR_VEZ = new JButton("PASSAR A VEZ");
        pnLateral.add(BT_REINICIAR_JOGO);
        pnLateral.add(BT_PASSAR_VEZ);
        return pnLateral;
    }

    private void criaTabuleiro() {
        setTitle("Jogo de Xadrez Ifsc");
        this.setLayout(new BorderLayout());
        this.tabuleiro = new Tabuleiro();
    }

    private JPanel painelRodada() {
        JPanel pnBase = new JPanel();
        labelRodada = new JLabel("Vez de: BRANCO");
        pnBase.add(labelRodada);
        return pnBase;
    }

    public static void setLabelRodada(EnumCor corRodada) {
        labelRodada.setText("Vez de: " + corRodada);
    }

    public static void main(String[] args) {
        new Principal();
    }
}
