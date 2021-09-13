package jogoxadrez.modelopecas;

/**
 * Classe da peça Rei
 */

public class Rei extends Peca{
    private final static int maximoCasasPermitidas = 1;
    private final static int minimoCasasPermitidas = 0;

    public Rei(EnumCor cor, int linha, int coluna){
        super(cor, linha, coluna, "figs/REI"+cor+".png");
    }

    public Rei(EnumCor cor, int linha, int coluna, String diretorioImagem) {
        super(cor, linha, coluna, diretorioImagem);
    }

    @Override
    public boolean movimentoValido(int linhaDestino, int colunaDestino) {
        /**
         * Método onde se verificará a validade do movimento da peça.
         *
         * O rei só pode mover 1 casa por vez mas em qualquer direção.
         */
        Peca peca = getTabuleiro().getPeca(linhaDestino, colunaDestino);
        int linhaTeste = getLinha();
        int colunaTeste = getColuna();
        int diferencaLinha = (linhaTeste - linhaDestino);
        int diferencaLinha2 = linhaDestino - linhaTeste;
        int diferencaColuna=  colunaTeste - colunaDestino;
        int diferencaColuna2 = colunaDestino - colunaTeste;
        if(peca == null){
            if(colunaDestino == getColuna() && (getLinha() - linhaDestino == maximoCasasPermitidas || linhaDestino - getLinha() == maximoCasasPermitidas)) return true;
            if(linhaDestino == getLinha() && (getColuna() - colunaDestino == maximoCasasPermitidas || colunaDestino - getColuna() == maximoCasasPermitidas)) return true;
            if(linhaDestino < getLinha() && colunaDestino > getColuna() && (getLinha() - linhaDestino == maximoCasasPermitidas) && (colunaDestino - getColuna() == maximoCasasPermitidas)) return true;
            if(linhaDestino < getLinha() && colunaDestino < getColuna() && (getLinha() - linhaDestino == maximoCasasPermitidas) && (getColuna() - colunaDestino == maximoCasasPermitidas)) return true;
            if(linhaDestino > getLinha() && colunaDestino > getColuna() && (linhaDestino - getLinha() == maximoCasasPermitidas) && (colunaDestino - getColuna() == maximoCasasPermitidas)) return true;
            if(linhaDestino > getLinha() && colunaDestino < getColuna() && (linhaDestino - getLinha() == maximoCasasPermitidas) && (getColuna() - colunaDestino == maximoCasasPermitidas)) return true;
        }
        return false;
    }
}