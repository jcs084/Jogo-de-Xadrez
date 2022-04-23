package jogoxadrez;

import jogoxadrez.controladores.ControladorTempo;
import jogoxadrez.modelojogo.RepresentarTabuleiro;
import jogoxadrez.modelojogo.Tabuleiro;
import jogoxadrez.modelopecas.EnumCor;

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

    private final int linhaPainelBotao = 10;
    private final int colunaPainelBotao = 1;

    private Tabuleiro tabuleiro;
    private ControladorTempo controladorTempo;
    private RepresentarTabuleiro representarTabuleiro;
    private JProgressBar barraProgresso;
    private final int tamanhoMinimoBProgresso = 0;
    private int tamanhoMaximoBProgresso;

    public JogoXadrezApplication(){
        criaTabuleiro();
    }

    private void criaTabuleiro() {
        setTitle("Jogo de Xadrez");
        this.setLayout(new BorderLayout());
        this.barraProgresso = new JProgressBar();
        criarBarraProgresso();

        this.controladorTempo = new ControladorTempo(this.barraProgresso);
        this.tabuleiro = new Tabuleiro(controladorTempo);
        this.representarTabuleiro = new RepresentarTabuleiro(this.tabuleiro);
        this.controladorTempo.setRepresentarTabuleiro(this.representarTabuleiro);

        //Adiciona o tabuleiro na tela.
        this.add(representarTabuleiro, BorderLayout.CENTER);

        //Adiciona os paineis e botoes na tela.
        this.add(painelRodada(), BorderLayout.NORTH);
        this.add(criarPainelBotoes(), BorderLayout.EAST);
        this.add(criarBarraProgresso(), BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Controla o tempo limite de jogada
        threadTempo = new Thread(controladorTempo);
        threadTempo.start();
        this.pack();
        this.setVisible(true);
    }

    private JProgressBar criarBarraProgresso() {
        this.tamanhoMaximoBProgresso = Tabuleiro.TEMPO_JOGADA;
        this.barraProgresso.setMinimum(tamanhoMinimoBProgresso);
        this.barraProgresso.setMaximum(tamanhoMaximoBProgresso);
        this.barraProgresso.setBackground(Color.WHITE);
        return this.barraProgresso;
    }

    private void reiniciaJogo(){
        this.BT_REINICIAR_JOGO.addActionListener(e -> {
            controladorTempo.zeraCronometro();
            tabuleiro = new Tabuleiro(controladorTempo);
            representarTabuleiro.setTabuleiro(tabuleiro);
            representarTabuleiro.desenharTabuleiro();
        });

    }

    private JPanel criarPainelBotoes() {
        JPanel pnBotao = new JPanel();
        pnBotao.setLayout(new GridLayout(this.linhaPainelBotao, this.colunaPainelBotao));
        this.BT_REINICIAR_JOGO = new JButton("REINICIAR JOGO");
        this.BT_PASSAR_VEZ = new JButton("PASSAR A VEZ");
        reiniciaJogo();
        pnBotao.add(this.BT_REINICIAR_JOGO);
        pnBotao.add(this.BT_PASSAR_VEZ);
        return pnBotao;
    }

    private JPanel painelRodada() {
        JPanel pnRodada = new JPanel();
        labelRodada = new JLabel("Vez de: BRANCO");
        pnRodada.add(labelRodada);
        return pnRodada;
    }

    public static void setLabelRodada(EnumCor corRodada) {
        labelRodada.setText("Vez de: " + corRodada);
    }

    public static void main(String[] args) {
        new JogoXadrezApplication();
    }
}