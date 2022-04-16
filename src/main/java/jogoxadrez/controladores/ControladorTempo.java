package jogoxadrez.controladores;

import jogoxadrez.modelojogo.RepresentarTabuleiro;
import jogoxadrez.modelojogo.Tabuleiro;
import jogoxadrez.modelopecas.Peca;

import javax.swing.*;

public class ControladorTempo implements Runnable{
    private RepresentarTabuleiro representarTabuleiro;
    private int tempoGasto = 0;
    private static boolean rodada = true;

    public ControladorTempo(RepresentarTabuleiro representarTabuleiro) {
        super();
        this.representarTabuleiro = representarTabuleiro;
    }

    @Override
    public void run() {
        while(rodada){
            try{
                Thread.sleep(1000);
                tempoGasto += 1000;
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

    public void zeraCronometro() {
        this.tempoGasto = 0;
    }
}