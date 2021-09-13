package jogoxadrez.modelojogo;

import jogoxadrez.modelopecas.Peca;

import javax.swing.*;

/**
 * Classe onde representará o visual de cada peça.
 */
public class RepresentarPeca extends JLabel{
    private Peca peca;

    public RepresentarPeca(Peca peca ){
        this.peca = peca;
        this.setIcon(new ImageIcon(peca.getDiretorioImagem()));
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }
}
