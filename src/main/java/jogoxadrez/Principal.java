package jogoxadrez;

import jogoxadrez.modelojogo.*;
import jogoxadrez.modelopecas.*;

import javax.swing.*;
import java.awt.*;

/**
 * Classe Principal onde se executará o jogo de xadrez
 */
public class Principal extends JFrame {
    private static JLabel labelRodada;

    private Tabuleiro tabuleiro;

    public Principal(){
        setTitle("Jogo de Xadrez Ifsc");
        this.setLayout(new BorderLayout());
        this.tabuleiro = new Tabuleiro();
        this.add(new RepresentarTabuleiro(tabuleiro), BorderLayout.CENTER);

        JPanel pnBase = new JPanel();
        labelRodada = new JLabel("Vez de: BRANCO");
        pnBase.add(labelRodada);
        this.add(pnBase, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void setLabelRodada(EnumCor corRodada) {
        labelRodada.setText("Vez de: " + corRodada);
    }

    public static void main(String[] args) {
        new Principal();
    }
}
