public class Pilhas {
    private int[] array;
    private int capacidade;
    private int topVermelha;
    private int topPreta;

    public Pilhas(int capacidadeInicial) {
        capacidade = capacidadeInicial;
        array = new int[capacidade];
        topVermelha = -1;
        topPreta = capacidade;
    }

    public void pushVermelha(int valor) {
        if (topVermelha + 1 == topPreta) redimensionar(capacidade * 2);
        array[++topVermelha] = valor;
    }

    public void pushPreta(int valor) {
        if (topPreta - 1 == topVermelha) redimensionar(capacidade * 2);
        array[--topPreta] = valor;
    }

    public int popVermelha() {
        if (topVermelha < 0) throw new RuntimeException("Pilha vermelha vazia!");
        int valor = array[topVermelha--];
        verificarReducao();
        return valor;
    }

    public int popPreta() {
        if (topPreta >= capacidade) throw new RuntimeException("Pilha preta vazia!");
        int valor = array[topPreta++];
        verificarReducao();
        return valor;
    }

    public int peekVermelha() {
        if (topVermelha < 0) throw new RuntimeException("Pilha vermelha vazia!");
        return array[topVermelha];
    }

    public int peekPreta() {
        if (topPreta >= capacidade) throw new RuntimeException("Pilha preta vazia!");
        return array[topPreta];
    }

    private void redimensionar(int novaCapacidade) {
        int[] novoArray = new int[novaCapacidade];
        int tamanhoVermelha = topVermelha + 1;
        int tamanhoPreta = capacidade - topPreta;

        for (int i = 0; i < tamanhoVermelha; i++) {
            novoArray[i] = array[i];
        }

        int novoTopPreta = novaCapacidade - tamanhoPreta;
        for (int i = 0; i < tamanhoPreta; i++) {
            novoArray[novoTopPreta + i] = array[topPreta + i];
        }

        topPreta = novoTopPreta;
        capacidade = novaCapacidade;
        array = novoArray;
    }

    private void verificarReducao() {
        int total = (topVermelha + 1) + (capacidade - topPreta);
        if (total <= capacidade / 3 && capacidade > 2) {
            redimensionar(capacidade / 2);
        }
    }
}
