package jogoxadrez.modelojogo;


import jogoxadrez.Principal;
import jogoxadrez.modelopecas.*;

/**
 * Classe do tabuleiro de Xadrez
 */
public class Tabuleiro {
    private Peca[][] pecasExistentes;
    private Peca pecaSelecionada = null;
    private int linhaPadraoTabuleiro = 8, colunaPadraoTabuleiro = 8; // Tamanho Padrão de um tabuleiro de xadrez (8x8).
    private EnumCor rodada = EnumCor.BRANCO;

    public Tabuleiro() {
        this.pecasExistentes = new Peca[getLinhaPadraoTabuleiro()][getColunaPadraoTabuleiro()];
        adicionarPecasTabuleiro();
    }

    public int getLinhaPadraoTabuleiro() {
        return linhaPadraoTabuleiro;
    }

    public int getColunaPadraoTabuleiro() {
        return colunaPadraoTabuleiro;
    }

    public Peca getPeca(int linha, int coluna) {
        return this.pecasExistentes[linha][coluna];
    }

    public void setPeca(Peca peca){
        this.pecasExistentes[peca.getLinha()][peca.getColuna()] = peca;
        peca.setTabuleiro(this);
    }
    public void addPeca(Peca peca) {
        this.pecasExistentes[peca.getLinha()][peca.getColuna()] = peca;
        peca.setTabuleiro(this);
    }

    public void adicionarPecasTabuleiro(){
        Torre torreBranca1 = new Torre(EnumCor.BRANCO, 7, 0);
        Torre torreBranca2 = new Torre(EnumCor.BRANCO, 7, 7);
        this.addPeca(torreBranca1);
        this.addPeca(torreBranca2);

        Torre torrePreta1 = new Torre(EnumCor.PRETO, 0, 0);
        Torre torrePreta2 = new Torre(EnumCor.PRETO, 0, 7);
        this.addPeca(torrePreta1);
        this.addPeca(torrePreta2);

        Bispo bispoBranco1 = new Bispo(EnumCor.BRANCO, 7,2);
        Bispo bispoBranco2 = new Bispo(EnumCor.BRANCO, 7,5);
        this.addPeca(bispoBranco1);
        this.addPeca(bispoBranco2);

        Bispo bispoPreto1 = new Bispo(EnumCor.PRETO, 0,2);
        Bispo bispoPreto2 = new Bispo(EnumCor.PRETO, 0,5);
        this.addPeca(bispoPreto1);
        this.addPeca(bispoPreto2);

        Cavalo cavaloBranco1 = new Cavalo(EnumCor.BRANCO, 7, 1);
        Cavalo cavaloBranco2 = new Cavalo(EnumCor.BRANCO, 7, 6);
        this.addPeca(cavaloBranco1);
        this.addPeca(cavaloBranco2);

        Cavalo cavaloPreto1 = new Cavalo(EnumCor.PRETO, 0, 1);
        Cavalo cavaloPreto2 = new Cavalo(EnumCor.PRETO, 0, 6);
        this.addPeca(cavaloPreto1);
        this.addPeca(cavaloPreto2);

        Rei reiBranco = new Rei(EnumCor.BRANCO, 7,3);
        Rei reiPreto = new Rei(EnumCor.PRETO, 0,3);
        this.addPeca(reiBranco);
        this.addPeca(reiPreto);

        Rainha rainhaBranca = new Rainha(EnumCor.BRANCO, 7,4);
        Rainha rainhaPreta = new Rainha(EnumCor.PRETO, 0,4);
        this.addPeca(rainhaBranca);
        this.addPeca(rainhaPreta);

        int qtdPeao = 8;
        for(int i = 0; i< qtdPeao; i++){
            Peao peaoBranco = new Peao(EnumCor.BRANCO, 6, i);
            this.addPeca(peaoBranco);
            Peao peaoPreto = new Peao(EnumCor.PRETO, 1, i);
            this.addPeca(peaoPreto);
        }
    }

    public boolean selecionaPeca(Peca peca) {
        /**
         * Método onde se retorna o estado da peça, se ela está selecionada ou não.
         */
        if (!peca.isSelecionada()) {
            peca.setSelecionada(true);
            this.pecaSelecionada = peca;
            return true;
        } else {
            peca.setSelecionada(false);
            this.pecaSelecionada = null;
            return false;
        }
    }

    public boolean passarRodada() {
        /**
         * Método onde se retorna true caso seja a vez das peças brancas, e false caso seja a vez das pretas.
         */
        if (rodada.equals(EnumCor.BRANCO)) {
            this.rodada = EnumCor.PRETO;
            Principal.setLabelRodada(this.rodada);
            return true;
        }
        this.rodada = EnumCor.BRANCO;
        Principal.setLabelRodada(this.rodada);
        return false;
    }

    public boolean movimentarPeca(Peca peca, int novaLinha, int novaColuna) {
        if(peca.movimentoValido(novaLinha, novaColuna)){
            this.pecasExistentes[peca.getLinha()][peca.getColuna()] = null;
            peca.setLinha(novaLinha);
            peca.setColuna(novaColuna);
            if(peca instanceof Peao){
                Peao peao = (Peao) peca;
                peao.setPrimeiroMovimento(false);
            }
            this.setPeca(peca);
            this.selecionaPeca(peca);
            this.passarRodada();
            return true;
        }
        return false;
    }

    public void jogada(int linha, int coluna) {
        Peca peca = this.getPeca(linha, coluna);
        if(this.pecaSelecionada == null){
            if(peca != null && peca.getCor().equals(this.rodada)){
                this.selecionaPeca(peca);
            }
        } else {
            if(this.pecaSelecionada == peca){
                this.selecionaPeca(peca);
            } else {
                if(peca == null || !peca.getCor().equals(this.pecaSelecionada.getCor())){
                    this.movimentarPeca(this.pecaSelecionada, linha, coluna);
                }
            }
        }
    }
}