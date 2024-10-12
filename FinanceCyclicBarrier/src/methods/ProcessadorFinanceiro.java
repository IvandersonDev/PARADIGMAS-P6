package methods;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ProcessadorFinanceiro implements Runnable {

    private final String nomeArquivo;
    private final AgregadorFinanceiro agregador;
    private final CyclicBarrier barreira;

    public ProcessadorFinanceiro(String nomeArquivo, AgregadorFinanceiro agregador, CyclicBarrier barreira) {
        this.nomeArquivo = nomeArquivo;
        this.agregador = agregador;
        this.barreira = barreira;
    }

    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(nomeArquivo))) {
            bufferedReader.lines()
                    .skip(1)
                    .forEach(this::processarLinha);

            barreira.await();
        } catch (IOException | InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private void processarLinha(String linha) {
        String[] partes = linha.split(",");

        if (partes.length < 2 || partes[1].trim().isEmpty()) {
            System.out.println("Linha inválida ou vazia encontrada, ignorando...");
            return;
        }

        String data = partes[0];
        String valorStr = partes[1].replace("\"", "");

        Optional<Double> valorOpt = tentarConverterParaDouble(valorStr);

        valorOpt.ifPresentOrElse(
                valor -> {
                    System.out.println("Adicionando valor: " + valor + " para a data: " + data);
                    agregador.adicionarRegistro(data, valor);
                },
                () -> System.out.println("Erro ao converter valor: " + valorStr + ", linha ignorada.")
        );
    }

    private Optional<Double> tentarConverterParaDouble(String valorStr) {
        try {
            return Optional.of(Double.parseDouble(valorStr));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
