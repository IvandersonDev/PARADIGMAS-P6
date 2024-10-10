import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        long tempoInicial = System.nanoTime();


        Path path = Paths.get(System.getProperty("user.dir") + "\\src\\resource\\new_calibration_text.txt");
        List<String> calibrations = Files.readAllLines(path);

        int somaFinal = Calibracao.calcularSomaCalibracao(calibrations);
        long tempoFinal = System.nanoTime();
        System.out.println("A soma dos valores é: " + somaFinal);
        double tempoTotal = (tempoFinal - tempoInicial) / 1_000_000d;
        System.out.printf("Tempo total: %.3f ms%n", tempoTotal);
    }
}
