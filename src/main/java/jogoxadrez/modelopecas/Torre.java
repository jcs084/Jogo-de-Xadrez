package jogoxadrez.modelopecas;

/**
 * Classe da peça Torre
 */
public class Torre extends Peca{
    public Torre(EnumCor cor, int linha, int coluna){
        super(cor, linha, coluna, "figs/TORRE"+cor+".png");
    }
    public Torre(EnumCor cor, int linha, int coluna, String diretorioImagem) {
        super(cor, linha, coluna, diretorioImagem);
    }

    @Override
    public boolean movimentoValido(int linhaDestino, int colunaDestino) {

        if((linhaDestino != getLinha() && colunaDestino == getColuna()) || (linhaDestino == getLinha() && colunaDestino != getColuna())){
            return true;
        }
        return false;
    }
}
