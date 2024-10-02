import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        long tempoInicial = System.currentTimeMillis();

        Path path = Paths.get(System.getProperty("user.dir") + "\\src\\resource\\new_calibration_text.txt");
        List<String> calibrations = Files.readAllLines(path);

        int somaFinal = Calibracao.calcularSomaCalibracao(calibrations);

        long tempoFinal = System.currentTimeMillis();
        System.out.println("A soma dos valores é: " + somaFinal);
        System.out.printf("Tempo total: %.3f ms%n", (tempoFinal - tempoInicial) / 1000d);
    }
}
