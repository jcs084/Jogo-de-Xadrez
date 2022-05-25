import jogoxadrez.controladores.ControladorTempo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import jogoxadrez.modelojogo.*;
import jogoxadrez.modelopecas.*;

import javax.swing.*;

/**
 * Classe de testes das funcionalidades do tabuleiro
 */
public class TesteTabuleiro {
    Peca peao = new Peao(EnumCor.BRANCO, 6,1);
    Peca torre = new Torre(EnumCor.BRANCO, 7,0);
    Peca bispo = new Bispo(EnumCor.BRANCO, 7,2);
    Peca bispo2 = new Bispo(EnumCor.PRETO, 0,2);
    Peca bispo3 = new Bispo(EnumCor.PRETO, 0,5);
    Peca bispo4 = new Bispo(EnumCor.BRANCO, 7,5);
    Peca cavalo = new Cavalo(EnumCor.PRETO, 0,6);
    Peca rainha = new Rainha(EnumCor.PRETO, 0,4);
    Peca rei = new Rei(EnumCor.PRETO, 0,3);
    ControladorTempo controladorTempo = new ControladorTempo(new JProgressBar());
    @Test
    public void testeSelecaoPecas(){
        /**
         * Aqui se testa se determinada peca está selecionada.
         */
        Tabuleiro tabuleiro = new Tabuleiro(controladorTempo);
        this.peao.setSelecionada(false);
        assertTrue(tabuleiro.selecionaPeca(this.peao), "A peça está selecionada!");
        assertFalse(tabuleiro.selecionaPeca(this.peao), "A peça não está mais selecionada!");
    }

    @Test
    public void testePassarRodada(){
        /**
         * Aqui será testado quando que a rodada será do outro jogador. Só funcionará ao retirar o setLabelRodada do tabuleiro, senão dará nullPointerException
         */

        Tabuleiro tabuleiro = new Tabuleiro(controladorTempo);
        tabuleiro.addPeca(peao);
        assertTrue(tabuleiro.passarRodada(), "Agora é a vez das peças pretas!");
        assertFalse(tabuleiro.passarRodada(), "Agora é a vez das peças brancas!");
    }

    @Test
    public void testeMovimentoPecas(){
        /**
         * Aqui será testado se determinada peça pode movimentar.
         */

        Tabuleiro tabuleiro = new Tabuleiro(controladorTempo);
        tabuleiro.addPeca(peao);
        tabuleiro.addPeca(torre);
        tabuleiro.addPeca(bispo);
        tabuleiro.addPeca(bispo2);
        tabuleiro.addPeca(bispo3);
        tabuleiro.addPeca(bispo4);
        tabuleiro.addPeca(cavalo);
        tabuleiro.addPeca(rainha);
        tabuleiro.addPeca(rei);

        //Testando o movimento do peão
        assertTrue(peao.movimentoValido(4,1));
        assertFalse(peao.movimentoValido(1,4), "O peao só pode andar uma casa!");

        //Testando o movimento da torre
        assertTrue(torre.movimentoValido(4,0));
        assertFalse(torre.movimentoValido(3,5), "Só pode movimentar em uma direção por vez!");

        //Testando o movimento do Bispo em 4 etapas, da direita para baixo, da esquerda para baixo, da direita para cima, e da esquerda para cima.
        assertTrue(bispo2.movimentoValido(1,3));
        assertFalse(bispo2.movimentoValido(1,5));

        assertTrue(bispo3.movimentoValido(1,4));
        assertFalse(bispo3.movimentoValido(2,4));

        assertTrue(bispo.movimentoValido(6,3));
        assertFalse(bispo.movimentoValido(1,1));

        assertTrue(bispo4.movimentoValido(5,3));
        assertFalse(bispo4.movimentoValido(1,4));

        //Testando o movimento do Cavalo

        assertTrue(cavalo.movimentoValido(2,7));
        assertFalse(cavalo.movimentoValido(5,4));

        //Testando o Movimento da Rainha
        assertTrue(rainha.movimentoValido(5,4));
        assertFalse(rainha.movimentoValido(5,6));

        //Testando o Movimento do Rei
        //Funciona ao retirar o peão que está na sua frente.
        assertTrue(rei.movimentoValido(1,3));
        assertFalse(rei.movimentoValido(2,5));
    }
}