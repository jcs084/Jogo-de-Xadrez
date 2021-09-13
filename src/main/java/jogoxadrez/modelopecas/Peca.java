package jogoxadrez.modelopecas;

import jogoxadrez.modelojogo.Tabuleiro;

/**
 * Classe Pai onde todas as peças de tabuleiro herdam dela.
 */
public abstract class Peca {
    private EnumCor cor;
    private int linha, coluna;
    private String diretorioImagem;
    private boolean eliminada = false;
    private boolean selecionada = false;
    private Tabuleiro tabuleiro;

    public Peca(EnumCor cor, int linha, int coluna, String diretorioImagem) {
        this.cor = cor;
        this.linha = linha;
        this.coluna = coluna;
        this.diretorioImagem = diretorioImagem;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public abstract boolean movimentoValido(int linhaDestino, int colunaDestino );
    public EnumCor getCor() {
        return cor;
    }

    public void setCor(EnumCor cor) {
        this.cor = cor;
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

    public String getDiretorioImagem() {
        return diretorioImagem;
    }

    public void setDiretorioImagem(String diretorioImagem) {
        this.diretorioImagem = diretorioImagem;
    }

    public boolean isEliminada() {
        return eliminada;
    }

    public void setEliminada(boolean eliminada) {
        this.eliminada = eliminada;
    }

    public boolean isSelecionada() {
        return selecionada;
    }

    public void setSelecionada(boolean selecionada) {
        this.selecionada = selecionada;
    }
}