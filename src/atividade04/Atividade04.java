package atividade04;

import java.util.Random;

public class Atividade04 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        var quantidadeDeBuscas = 10000;
        var quantidadeDeValores = 10000;

        var valoresBusca = new int[quantidadeDeBuscas];
        var valoresSequenciais = new int[quantidadeDeValores];
        var valoresAleatorios = new int[quantidadeDeValores];

        // Crio valores sequenciais
        criarValoresSequenciais(valoresSequenciais);
        // Embaralho os valores sequenciais e pego 'quantidadeDeBuscas' primeiros
        // para serem valores aleatorios de busca
        criarValoresBusca(valoresBusca, valoresSequenciais);
        // Embaralho novamente valroes sequenciais para serem valores aleatorios
        criarValoresAleatorios(valoresAleatorios, valoresSequenciais);

        var criaArvoreAVLParametroArray = new boolean[]{true, false};
        var valoresArray = new int[][]{valoresAleatorios, valoresSequenciais};
        var ehValorAleatorio = true;

        for (var criaArvoreAVLParametro : criaArvoreAVLParametroArray) {
            for (var valores : valoresArray) {
                var nomeArvore = criaArvoreAVLParametro ? "ArvoreAVL" : "ArvoreBinariaDeBusca";
                var tipoValores = ehValorAleatorio ? "Aleatorios" : "Sequenciais";

                System.out.println("Iniciando operacao em " + nomeArvore + " com valores " + tipoValores + "...");
                var tempoInicio = System.currentTimeMillis();

                var arvore = new ArvoreBinariaDeBusca(criaArvoreAVLParametro);

                for (var valor : valores) {
                    arvore.inserir(valor);
                }

                for (var valor : valoresBusca) {
                    arvore.buscar(valor);
                }

                var tempoExecucao = System.currentTimeMillis() - tempoInicio;
                var comparacoesChaves = arvore.buscarComparacaoChaves();
                var alturaArvore = arvore.buscarRaiz().buscarAltura();

                System.out.println("Fim operacao em " + nomeArvore + " com valores " + tipoValores + ":");
                System.out.println("-Comparacoes de chaves:" + comparacoesChaves);
                System.out.println("-Altura da arvore:" + alturaArvore);
                System.out.println("-Tempo de execução(ms):" + tempoExecucao);

                ehValorAleatorio = !ehValorAleatorio;
            }
        }
    }

    private static void criarValoresSequenciais(int[] valoresSequenciais) {
        System.out.println("Criando valores sequenciais...");

        for (var i = 0; i < valoresSequenciais.length; i++) {
            valoresSequenciais[i] = i;
        }

        System.out.println("Valores sequenciais criados.");
    }

    private static void criarValoresBusca(int[] valoresAleatoriosBusca, int[] valoresSequenciais) {
        System.out.println("Criando valores aleatorios para busca...");

        var valoresAleatorios = new int[valoresSequenciais.length];

        // Copia valores sequenciais para valores aleatorios
        System.arraycopy(valoresSequenciais, 0, valoresAleatorios, 0, valoresSequenciais.length);

        // Embaralha valores aleatorios
        embaralharArray(valoresAleatorios);

        // Copia primeiros valores aleatorios para valores aleatorios para busca
        System.arraycopy(valoresAleatorios, 0, valoresAleatoriosBusca, 0, valoresAleatoriosBusca.length);

        System.out.println("Valores aleatorios para busca criados.");
    }

    private static void criarValoresAleatorios(int[] valoresAleatorios, int[] valoresSequenciais) {
        System.out.println("Criando valores aleatorios...");

        // Copia valores sequenciais para valores aleatorios
        System.arraycopy(valoresSequenciais, 0, valoresAleatorios, 0, valoresSequenciais.length);
        
        // Embaralha valores aleatorios
        embaralharArray(valoresAleatorios);

        System.out.println("Valores aleatorios criados.");
    }

    private static void embaralharArray(int[] array) {
        var random = new Random();

        for (var i = array.length; i > 1; i--) {
            var j = random.nextInt(i);
            var temp = array[i - 1];

            array[i - 1] = array[j];
            array[j] = temp;
        }
    }
}
