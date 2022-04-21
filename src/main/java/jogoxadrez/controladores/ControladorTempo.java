package jogoxadrez.controladores;

import jogoxadrez.modelojogo.RepresentarTabuleiro;
import jogoxadrez.modelojogo.Tabuleiro;

import javax.swing.*;

public class ControladorTempo implements Runnable{
    private RepresentarTabuleiro representarTabuleiro;
    private int tempoGasto = 0;
    private static boolean rodada = true;
    private JProgressBar barraProgresso;

    public ControladorTempo(JProgressBar barraProgresso) {
        super();
        this.barraProgresso = barraProgresso;
    }

    @Override
    public void run() {
        while(rodada){
            try{
                Thread.sleep(100);
                this.tempoGasto += 100;
                this.barraProgresso.setValue(this.tempoGasto);
                if(tempoGasto>= Tabuleiro.TEMPO_JOGADA){
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