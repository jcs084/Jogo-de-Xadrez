package jogoxadrez;

import jogoxadrez.controladores.ControladorTempo;
import jogoxadrez.modelojogo.*;
import jogoxadrez.modelopecas.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JProgressBar barraProgresso;
    private int tamanhoMinimoBProgresso = 0;
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
        this.threadTempo = new Thread(controladorTempo);
        this.threadTempo.start();
        this.pack();
        this.setVisible(true);
    }

    private JProgressBar criarBarraProgresso() {
        this.tamanhoMaximoBProgresso = Tabuleiro.TEMPO_JOGADA;
        this.barraProgresso.setMinimum(tamanhoMinimoBProgresso);
        this.barraProgresso.setMaximum(tamanhoMaximoBProgresso);
        this.barraProgresso.setForeground(Color.GREEN);
        this.barraProgresso.setBackground(Color.WHITE);
        return this.barraProgresso;
    }

    private void reiniciaJogo(){
        this.BT_REINICIAR_JOGO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorTempo.zeraCronometro();
                tabuleiro = new Tabuleiro(controladorTempo);
                representarTabuleiro.setTabuleiro(tabuleiro);
                representarTabuleiro.desenharTabuleiro();
            }
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