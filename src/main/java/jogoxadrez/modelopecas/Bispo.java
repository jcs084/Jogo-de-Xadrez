package jogoxadrez.modelopecas;

/**
 * Classe da peça Bispo
 */

public class Bispo extends Peca{

    public Bispo(EnumCor cor, int linha, int coluna){
        super(cor, linha, coluna, "figs/BISPO"+cor+".png");
    }

    public Bispo(EnumCor cor, int linha, int coluna, String diretorioImagem) {
        super(cor, linha, coluna, diretorioImagem);
    }

    @Override
    public boolean movimentoValido(int linhaDestino, int colunaDestino) {
        /**
         * Método onde se verificará a validade do movimento da peça.
         */

        if(((linhaDestino > getLinha()) && (colunaDestino > getColuna()) && ((linhaDestino - getLinha()) == (colunaDestino - getColuna()))) || (((linhaDestino > getLinha())) && (colunaDestino < getColuna()) && ((linhaDestino - getLinha()) == (getColuna() - colunaDestino))) || ((linhaDestino < getLinha()) && (colunaDestino > getColuna()) && ((getLinha() - linhaDestino) == (colunaDestino - getColuna()))) || ((linhaDestino < getLinha()) && (colunaDestino < getColuna()) && ((getLinha() - linhaDestino) == (getColuna() - colunaDestino)))){
            return true;
        }
        return false;
    }
}