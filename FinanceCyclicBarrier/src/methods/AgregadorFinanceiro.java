package methods;

import java.util.HashMap;
import java.util.Map;

public class AgregadorFinanceiro {

    private final Map<String, Double> registrosAgregados = new HashMap<>();
    private double total = 0.0;

    public void adicionarRegistro(String data, double valor) {
        registrosAgregados.merge(data, valor, Double::sum);
        total += valor;
    }

    public Map<String, Double> getRegistrosAgregados() {
        return new HashMap<>(registrosAgregados);
    }

    public double getTotal() {
        return total;
    }

    public void adicionarOutroAgregador(AgregadorFinanceiro outroAgregador) {
        outroAgregador.getRegistrosAgregados().forEach((data, valor) -> {
            registrosAgregados.merge(data, valor, Double::sum);
        });
        total += outroAgregador.getTotal();
    }
}
