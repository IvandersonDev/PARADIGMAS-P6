import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Calibracao {

    private static int somaFinal = 0;

    public static int calcularSomaCalibracao(List<String> calibrations) throws Exception {
        int numThreads = 16;
        int blockSize = calibrations.size() / numThreads;

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        CountDownLatch latch = new CountDownLatch(numThreads);

        for (int i = 0; i < numThreads; i++) {
            final int start = i * blockSize;
            final int end = (i == numThreads - 1) ? calibrations.size() : (i + 1) * blockSize;
            List<String> subList = calibrations.subList(start, end);

            executor.submit(() -> {
                try {
                    int somaParcial = 0;
                    for (String line : subList) {
                        somaParcial += valorCalibracao(line);
                    }
                    synchronized (Calibracao.class) {
                        somaFinal += somaParcial;
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

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
