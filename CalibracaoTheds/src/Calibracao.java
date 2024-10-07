import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Calibracao {

    public static int calcularSomaCalibracao(List<String> calibrations) throws Exception {
        int numThreads = 16;
        int blockSize = calibrations.size() / numThreads;

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<Integer>> results = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            final int start = i * blockSize;
            final int end = (i == numThreads - 1) ? calibrations.size() : (i + 1) * blockSize;
            List<String> subList = calibrations.subList(start, end);

            Callable<Integer> task = () -> {
                int soma = 0;
                for (String line : subList) {
                    soma += valorCalibracao(line);
                }
                return soma;
            };
            results.add(executor.submit(task));
        }

        int somaFinal = 0;
        for (Future<Integer> result : results) {
            somaFinal += result.get();
        }

        executor.shutdown();

        return somaFinal;
    }

    public static int valorCalibracao(String linha) {
        Character primeiroCaractere = null;
        Character ultimoCaractere = null;

        for (char c : linha.toCharArray()) {
            if (Character.isDigit(c)) {
                if (primeiroCaractere == null) {
                    primeiroCaractere = c;
                }
                ultimoCaractere = c;
            }
        }

        if (primeiroCaractere != null && ultimoCaractere != null) {
            return Integer.parseInt(primeiroCaractere.toString() + ultimoCaractere.toString());
        } else {
            return 0;
        }
    }
}
