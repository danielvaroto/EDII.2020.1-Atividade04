package atividade04;

public class ArvoreBinariaDeBusca {

    public class NoArvoreBinariaDeBusca {

        private final int valor;
        private int altura;
        private NoArvoreBinariaDeBusca noEsquerdo, noDireito;

        private NoArvoreBinariaDeBusca(int valor, int altura) {
            this.valor = valor;
            this.altura = altura;
        }

        public int buscarValor() {
            return this.valor;
        }

        public int buscarAltura() {
            return this.altura;
        }
    }

    private NoArvoreBinariaDeBusca raiz;
    private int comparacaoChaves;
    private final boolean balancearAVL;

    public ArvoreBinariaDeBusca(boolean balancearAVL) {
        this.balancearAVL = balancearAVL;
    }

    public NoArvoreBinariaDeBusca buscarRaiz() {
        return this.raiz;
    }

    public int buscarComparacaoChaves() {
        return this.comparacaoChaves;
    }

    public NoArvoreBinariaDeBusca buscar(int valor) {
        return buscar(this.raiz, valor);
    }

    private NoArvoreBinariaDeBusca buscar(NoArvoreBinariaDeBusca no, int valor) {
        if (no == null) {
            return null;
        }

        if (acrescerComparacaoChaves() && valor < no.valor) {
            return buscar(no.noEsquerdo, valor);
        }

        if (acrescerComparacaoChaves() && valor > no.valor) {
            return buscar(no.noEsquerdo, valor);
        }

        return no;
    }

    public void inserir(int valor) {
        this.raiz = inserir(this.raiz, valor);
    }

    private NoArvoreBinariaDeBusca inserir(NoArvoreBinariaDeBusca no, int valor) {
        if (no == null) {
            return new NoArvoreBinariaDeBusca(valor, 1);
        }

        if (acrescerComparacaoChaves() && valor < no.valor) {
            no.noEsquerdo = inserir(no.noEsquerdo, valor);
        } else if (acrescerComparacaoChaves() && valor > no.valor) {
            no.noDireito = inserir(no.noDireito, valor);
        } else {
            return no;
        }

        no.altura = 1 + Math.max(buscaraltura(no.noEsquerdo), buscaraltura(no.noDireito));

        if (this.balancearAVL) {
            return balancearArvoreAVL(no, valor);
        }

        return no;
    }

    private NoArvoreBinariaDeBusca balancearArvoreAVL(NoArvoreBinariaDeBusca no, int valor) {
        int balanceamento = buscaraltura(no.noEsquerdo) - buscaraltura(no.noDireito);

        if (balanceamento > 1 && acrescerComparacaoChaves() && valor < no.noEsquerdo.valor) {
            return rotacionarDireita(no);
        }

        if (balanceamento < -1 && acrescerComparacaoChaves() && valor > no.noDireito.valor) {
            return rotacionarEsquerda(no);
        }

        if (balanceamento > 1 && acrescerComparacaoChaves() && valor > no.noEsquerdo.valor) {
            no.noEsquerdo = rotacionarEsquerda(no.noEsquerdo);
            return rotacionarDireita(no);
        }

        if (balanceamento < -1 && acrescerComparacaoChaves() && valor < no.noDireito.valor) {
            no.noDireito = rotacionarDireita(no.noDireito);
            return rotacionarEsquerda(no);
        }

        return no;
    }

    private int buscaraltura(NoArvoreBinariaDeBusca no) {
        return (no == null) ? 0 : no.altura;
    }

    private NoArvoreBinariaDeBusca rotacionarDireita(NoArvoreBinariaDeBusca x) {
        NoArvoreBinariaDeBusca y = x.noEsquerdo;
        NoArvoreBinariaDeBusca z = y.noDireito;

        y.noDireito = x;
        x.noEsquerdo = z;

        x.altura = Math.max(buscaraltura(x.noEsquerdo), buscaraltura(x.noDireito)) + 1;
        y.altura = Math.max(buscaraltura(y.noEsquerdo), buscaraltura(y.noDireito)) + 1;

        return y;
    }

    private NoArvoreBinariaDeBusca rotacionarEsquerda(NoArvoreBinariaDeBusca x) {
        NoArvoreBinariaDeBusca y = x.noDireito;
        NoArvoreBinariaDeBusca z = y.noEsquerdo;

        y.noEsquerdo = x;
        x.noDireito = z;

        x.altura = Math.max(buscaraltura(x.noEsquerdo), buscaraltura(x.noDireito)) + 1;
        y.altura = Math.max(buscaraltura(y.noEsquerdo), buscaraltura(y.noDireito)) + 1;

        return y;
    }

    private boolean acrescerComparacaoChaves() {
        this.comparacaoChaves++;

        return true;
    }
}
