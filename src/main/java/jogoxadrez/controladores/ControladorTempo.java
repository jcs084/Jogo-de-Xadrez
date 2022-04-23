package jogoxadrez.controladores;

import jogoxadrez.modelojogo.RepresentarTabuleiro;
import jogoxadrez.modelojogo.Tabuleiro;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("ALL")
public class ControladorTempo implements Runnable {
    private RepresentarTabuleiro representarTabuleiro;
    private int tempoGasto = 0;
    private final static boolean rodada = true;
    private final JProgressBar barraProgresso;

    public ControladorTempo(JProgressBar barraProgresso) {
        super();
        this.barraProgresso = barraProgresso;
    }

    @Override
    public void run() {
        while (rodada) {
            try {
                Thread.sleep(1);
                this.tempoGasto += 1;
                this.barraProgresso.setValue(this.tempoGasto);
                if(this.tempoGasto < Tabuleiro.TEMPO_JOGADA / 2) {
                    this.barraProgresso.setForeground(Color.GREEN);
                }
                if (this.tempoGasto > Tabuleiro.TEMPO_JOGADA / 2) {
                    this.barraProgresso.setForeground(Color.YELLOW);
                }
                if (this.tempoGasto > Tabuleiro.TEMPO_JOGADA_ACABANDO) {
                    this.barraProgresso.setForeground(Color.RED);
                }
                if (tempoGasto >= Tabuleiro.TEMPO_JOGADA) {
                    JOptionPane.showMessageDialog(null, "O jogador " + representarTabuleiro.getTabuleiro().getRodada() + " perdeu a vez!");
                    if (representarTabuleiro.getTabuleiro().getPecaSelecionada() != null) {
                        representarTabuleiro.getTabuleiro().getPecaSelecionada().setSelecionada(false);
                        representarTabuleiro.getTabuleiro().setPecaSelecionada(null);
                    }
                    representarTabuleiro.getTabuleiro().passarRodada();
                    representarTabuleiro.desenharTabuleiro();
                    zeraCronometro();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setRepresentarTabuleiro(RepresentarTabuleiro representarTabuleiro) {
        this.representarTabuleiro = representarTabuleiro;
    }

    public void zeraCronometro() {
        this.tempoGasto = 0;
    }
}