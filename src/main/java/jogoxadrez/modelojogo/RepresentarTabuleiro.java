package jogoxadrez.modelojogo;

import jogoxadrez.modelopecas.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Classe onde se representará visualmente o tabuleiro do jogo de xadrez.
 */
public class RepresentarTabuleiro extends JPanel implements MouseListener {
    private Tabuleiro tabuleiro;
    public static final JPanel painelPecaEliminada = new JPanel();

    public RepresentarTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        this.desenharTabuleiro();
    }

    /**
     * Método onde desenha o tabuleiro.
     */
    public void desenharTabuleiro(){
        painelPecaEliminada.removeAll();
        this.removeAll();
        this.setLayout(new GridLayout(tabuleiro.getLinhaPadraoTabuleiro(),tabuleiro.getColunaPadraoTabuleiro()));
        for(int x = 0; x < tabuleiro.getLinhaPadraoTabuleiro(); x++){
            for (int y = 0; y < tabuleiro.getColunaPadraoTabuleiro(); y++){
                RepresentarCelula representarCelula;
                Peca peca = this.tabuleiro.getPeca(x,y);
                if(peca != null){
                    representarCelula = new RepresentarCelula(new RepresentarPeca(peca));
                } else{
                    representarCelula = new RepresentarCelula(x,y);
                }
                if((x + y) % 2 == 1){
                    representarCelula.setBackground(Color.BLACK);
                } else representarCelula.setBackground(Color.WHITE);
                this.add(representarCelula);
                representarCelula.addMouseListener(this);
            }
        }
        for(Peca pecaEliminada : this.tabuleiro.getPecasForaJogo()){
            painelPecaEliminada.add(new RepresentarPeca(pecaEliminada)); // adiciona todas as peças eliminadas no painel.
        }
        this.revalidate();
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        RepresentarCelula representarCelula = (RepresentarCelula) e.getSource();
        this.tabuleiro.realizaJogada(representarCelula.getLinha(), representarCelula.getColuna());
        this.desenharTabuleiro();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}