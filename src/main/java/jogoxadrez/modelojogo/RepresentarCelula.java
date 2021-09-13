package jogoxadrez.modelojogo;


import javax.swing.*;
import java.awt.*;

/**
 * Classe representando cada celula existente no tabuleiro.
 */
public class RepresentarCelula extends JPanel {

    private RepresentarPeca pecaDesenhada;
    private final static int expessuraRepresentacaoPeca = 4; // A expessura da borda ao selecionar uma peça.
    private int linha, coluna;

    public RepresentarCelula(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public RepresentarCelula(RepresentarPeca pecaDesenhada){
        this.pecaDesenhada = pecaDesenhada;
        this.linha = pecaDesenhada.getPeca().getLinha();
        this.coluna = pecaDesenhada.getPeca().getColuna();
        this.add(pecaDesenhada);
        if((pecaDesenhada.getPeca() != null ) && pecaDesenhada.getPeca().isSelecionada()){
            this.setBorder(BorderFactory.createLineBorder(Color.red, expessuraRepresentacaoPeca));
        }
    }

    public RepresentarPeca getPecaDesenhada() {
        return pecaDesenhada;
    }

    public void setPecaDesenhada(RepresentarPeca pecaDesenhada) {
        this.pecaDesenhada = pecaDesenhada;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
}
