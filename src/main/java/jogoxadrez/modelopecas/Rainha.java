package jogoxadrez.modelopecas;

/**
 * Classe da peça Rainha
 */

public class Rainha extends Peca{

    public Rainha(EnumCor cor, int linha, int coluna){
        super(cor, linha, coluna, "figs/RAINHA"+cor+".png");
    }

    public Rainha(EnumCor cor, int linha, int coluna, String diretorioImagem) {
        super(cor, linha, coluna, diretorioImagem);
    }

    @Override
    public boolean movimentoValido(int linhaDestino, int colunaDestino) {
        if(((linhaDestino > getLinha()) && (colunaDestino > getColuna()) && ((linhaDestino - getLinha()) == (colunaDestino - getColuna()))) || (((linhaDestino > getLinha())) && (colunaDestino < getColuna()) && ((linhaDestino - getLinha()) == (getColuna() - colunaDestino))) || ((linhaDestino < getLinha()) && (colunaDestino > getColuna()) && ((getLinha() - linhaDestino) == (colunaDestino - getColuna()))) || ((linhaDestino < getLinha()) && (colunaDestino < getColuna()) && ((getLinha() - linhaDestino) == (getColuna() - colunaDestino)))){
            return true;
        }
        if((linhaDestino != getLinha() && colunaDestino == getColuna()) || (linhaDestino == getLinha() && colunaDestino != getColuna())){
            return true;
        }
        return false;
    }
}