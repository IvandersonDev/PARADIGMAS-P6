
import methods.AgregadorFinanceiro;
import methods.ProcessadorFinanceiro;

import java.io.File;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

public class Main {

    public static void main(String[] args) {
        String[] nomesArquivos = {
                "C:/Users/Usuario/Downloads/FinanceCyclicBarrier/src/resource/receitas.csv",
                "C:/Users/Usuario/Downloads/FinanceCyclicBarrier/src/resource/despesas.csv",
                "C:/Users/Usuario/Downloads/FinanceCyclicBarrier/src/resource/provisao.csv"
        };

        if (!verificarArquivos(nomesArquivos)) {
            return;
        }

        int numThreads = nomesArquivos.length;
        CyclicBarrier barreira = new CyclicBarrier(numThreads + 1);

        AgregadorFinanceiro[] agregadores = iniciarAgregadores(nomesArquivos, barreira);

        aguardarThreads(barreira);

        String[] tipos = {"Receitas", "Despesas", "Provisões"};
        exibirTodosResultados(agregadores, tipos);

        double somaTotal = calcularSomaTotal(agregadores);
        System.out.println("Soma total de todas as planilhas: " + somaTotal);
    }

    private static boolean verificarArquivos(String[] nomesArquivos) {
        for (String nomeArquivo : nomesArquivos) {
            File arquivo = new File(nomeArquivo);
            if (!arquivo.exists()) {
                System.out.println("Arquivo não encontrado: " + nomeArquivo);
                return false;
            }
        }
        return true;
    }

    private static AgregadorFinanceiro[] iniciarAgregadores(String[] nomesArquivos, CyclicBarrier barreira) {
        AgregadorFinanceiro[] agregadores = new AgregadorFinanceiro[nomesArquivos.length];

        for (int i = 0; i < nomesArquivos.length; i++) {
            agregadores[i] = new AgregadorFinanceiro();
            new Thread(new ProcessadorFinanceiro(nomesArquivos[i], agregadores[i], barreira)).start();
        }

        return agregadores;
    }

    private static void aguardarThreads(CyclicBarrier barreira) {
        try {
            barreira.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private static void exibirTodosResultados(AgregadorFinanceiro[] agregadores, String[] tipos) {
        for (int i = 0; i < agregadores.length; i++) {
            exibirResultados(tipos[i], agregadores[i]);
        }
    }

    private static void exibirResultados(String titulo, AgregadorFinanceiro agregador) {
        System.out.println(titulo + ":");
        agregador.getRegistrosAgregados().forEach((data, total) ->
                System.out.println(data + ", " + total));
        System.out.println("Total " + titulo.toLowerCase() + ": " + agregador.getTotal());
        System.out.println();
    }

    private static double calcularSomaTotal(AgregadorFinanceiro[] agregadores) {
        double somaTotal = 0;
        for (AgregadorFinanceiro agregador : agregadores) {
            somaTotal += agregador.getTotal();
        }
        return somaTotal;
    }
}
