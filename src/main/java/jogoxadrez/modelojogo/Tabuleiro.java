package jogoxadrez.modelojogo;


import jogoxadrez.JogoXadrezApplication;
import jogoxadrez.controladores.ControladorTempo;
import jogoxadrez.modelopecas.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe do tabuleiro de Xadrez
 */
@SuppressWarnings("ALL")
public class Tabuleiro {
    private final Peca[][] pecasExistentes;
    private Peca pecaSelecionada = null;
    private final int linhaPadraoTabuleiro = 8, colunaPadraoTabuleiro = 8; // Tamanho Padrão de um tabuleiro de xadrez (8x8).
    private EnumCor rodada = EnumCor.BRANCO;
    private final ControladorTempo controladorTempo;
    public static final int TEMPO_JOGADA = 60000; // Tempo de jogada de 1 min
    public static final int TEMPO_JOGADA_ACABANDO = 40000; // Tempo para quando a jogada estiver acabando.
    private List<Peca> pecasForaJogo;


    public Tabuleiro(ControladorTempo controladorTempo) {
        this.controladorTempo = controladorTempo;
        this.pecasExistentes = new Peca[getLinhaPadraoTabuleiro()][getColunaPadraoTabuleiro()];
        this.pecasForaJogo = new ArrayList<>();
        adicionarPecasTabuleiro();
    }

    public void addPeca(Peca peca) {
        this.pecasExistentes[peca.getLinha()][peca.getColuna()] = peca;
        peca.setTabuleiro(this);
    }

    //Adiciona todas as peças em suas devidas posições no tabuleiro.
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

    /**
     * Método onde se retorna o estado da peça, se ela está selecionada ou não.
     */
    public boolean selecionaPeca(Peca peca) {
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

    /**
     * Método onde se retorna a vez de cada peça.
     */
    public boolean passarRodada() {
        this.rodada = rodada.equals(EnumCor.BRANCO) ? EnumCor.PRETO : EnumCor.BRANCO;
        JogoXadrezApplication.setLabelRodada(this.rodada);
        controladorTempo.zeraCronometro();
        return rodada.equals(EnumCor.BRANCO);
    }
    /**
     * Método onde se faz o movimento de cada peça.
     */
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

    /**
     * Método onde se realiza a jogada por completo de cada peça.
     */
    public void realizaJogada(int linha, int coluna) {
        Peca peca = this.getPeca(linha, coluna);
        if(this.pecaSelecionada == null){
            if(peca != null && peca.getCor().equals(this.rodada)){
                this.selecionaPeca(peca);
            }
        } else {
            if(this.pecaSelecionada == peca){
                this.selecionaPeca(peca);
            } else {
                if(peca == null){
                    this.movimentarPeca(this.pecaSelecionada, linha, coluna);
                }
                if(peca != null && !peca.getCor().equals(this.pecaSelecionada.getCor())) {
                    peca.setEliminada(true);
                    pecasForaJogo.add(peca);
                    this.movimentarPeca(this.pecaSelecionada, linha, coluna);
                }
            }
        }
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

    public EnumCor getRodada() {
        return rodada;
    }

    public Peca getPecaSelecionada() {
        return pecaSelecionada;
    }

    public void setPecaSelecionada(Peca pecaSelecionada) {
        this.pecaSelecionada = pecaSelecionada;
    }

    public void setPeca(Peca peca){
        this.pecasExistentes[peca.getLinha()][peca.getColuna()] = peca;
        peca.setTabuleiro(this);
    }

    public List<Peca> getPecasForaJogo() {
        return pecasForaJogo;
    }
}