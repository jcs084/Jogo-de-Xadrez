package jogoxadrez.modelopecas;

/**
 * Classe da peça Peao
 */

public class Peao extends Peca{
    private boolean primeiroMovimento = true;
    private final static int casasPermitidas = 1;
    private final static int casasPrimeiroMovimento = 2;

    public Peao(EnumCor cor, int linha, int coluna){
        super(cor, linha, coluna, "figs/PEAO"+cor+".png");
    }

    public Peao(EnumCor cor, int linha, int coluna, String diretorioImagem) {
        super(cor, linha, coluna, diretorioImagem);
    }

    @Override
    public boolean movimentoValido(int linhaDestino, int colunaDestino) {
        /**
         * Método onde se verificará a validade do movimento da peça.
         */
        Peca peca = getTabuleiro().getPeca(linhaDestino, colunaDestino);
        if(isPrimeiroMovimento()){
            if((linhaDestino == (getLinha() - casasPrimeiroMovimento)|| linhaDestino == (getLinha() + casasPrimeiroMovimento) || linhaDestino == (getLinha() - casasPermitidas) || linhaDestino == (getLinha() + casasPermitidas)) && colunaDestino == getColuna()){
                return true;
            }
        }

        if(peca != null && this.getCor() == EnumCor.PRETO){
            if(((linhaDestino > getLinha()) && (colunaDestino > getColuna()) && ((linhaDestino - getLinha()) == casasPermitidas ) && ((colunaDestino - getColuna()) == casasPermitidas))) return true;
            if(((linhaDestino > getLinha()) && (colunaDestino < getColuna()) && ((linhaDestino - getLinha()) == casasPermitidas ) && ((getColuna() - colunaDestino) == casasPermitidas))) return true;
            return false;
        }else if(peca != null && this.getCor() == EnumCor.BRANCO){
            if(((linhaDestino < getLinha()) && (colunaDestino > getColuna()) && ((getLinha() - linhaDestino) == casasPermitidas ) && ((colunaDestino - getColuna()) == casasPermitidas))) return true;
            if(((linhaDestino < getLinha()) && (colunaDestino < getColuna()) && ((getLinha() - linhaDestino) == casasPermitidas ) && ((getColuna() - colunaDestino) == casasPermitidas))) return true;
            return false;
        } else {
            if(colunaDestino == getColuna() && (casasPermitidas == (getLinha() - linhaDestino) || casasPermitidas == (linhaDestino - getLinha()))){
                if(this.getCor() == EnumCor.BRANCO && (casasPermitidas != (getLinha() - linhaDestino))) return false;
                if(this.getCor() == EnumCor.PRETO && (casasPermitidas != (linhaDestino - getLinha()))) return false;
                return true;
            }
        }

        return false;
    }

    public boolean isPrimeiroMovimento() {
        return primeiroMovimento;
    }

    public void setPrimeiroMovimento(boolean primeiroMovimento) {
        this.primeiroMovimento = primeiroMovimento;
    }
}
