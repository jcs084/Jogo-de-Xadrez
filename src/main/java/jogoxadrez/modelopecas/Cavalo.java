package jogoxadrez.modelopecas;

/**
 * Classe da peça Cavalo
 */

public class Cavalo extends Peca{

    private final static int maximoCasasHorizontal = 2;
    private final static int maximoCasasVertical = 2;
    private final static int minimoCasasHorizontal = 1;
    private final static int minimoCasasVertical = 1;

    public Cavalo(EnumCor cor, int linha, int coluna){
        super(cor, linha, coluna, "figs/CAVALO"+cor+".png");
    }
    public Cavalo(EnumCor cor, int linha, int coluna, String diretorioImagem) {
        super(cor, linha, coluna, diretorioImagem);
    }

    @Override
    public boolean movimentoValido(int linhaDestino, int colunaDestino) {
        if(((linhaDestino == (getLinha() + maximoCasasHorizontal) || linhaDestino == (getLinha() - maximoCasasHorizontal)) && (colunaDestino == (getColuna() + minimoCasasVertical) || colunaDestino == (getColuna() - minimoCasasVertical))) || (linhaDestino == (getLinha() + minimoCasasHorizontal) || linhaDestino == (getLinha() - minimoCasasHorizontal)) && (colunaDestino == (getColuna() + maximoCasasVertical) || colunaDestino == (getColuna() - maximoCasasVertical))) {
            return true;
        }
        return false;
    }
}