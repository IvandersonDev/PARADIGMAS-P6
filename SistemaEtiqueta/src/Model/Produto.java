package Model;

public class Produto {
    private String descricao;
    private double precoLata;
    private double precoCaixa;
    private String codigoBarras;


    public Produto(String descricao, double precoLata, double precoCaixa, String codigoBarras) {
        this.descricao = descricao.length() > 22 ? descricao.substring(0, 22) : descricao;
        this.precoLata = precoLata;
        this.precoCaixa = precoCaixa;
        this.codigoBarras = codigoBarras;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPrecoLata() {
        return precoLata;
    }

    public double getPrecoCaixa() {
        return precoCaixa;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }
}
