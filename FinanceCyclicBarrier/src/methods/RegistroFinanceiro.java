package methods;

public class RegistroFinanceiro {
    private String data;
    private double valor;

    public RegistroFinanceiro(String data, double valor) {
        this.data = data;
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }
}