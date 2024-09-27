package imposto;


import model.Produto;

public class PIS implements Imposto {
    @Override
    public double calcular(Produto produto) {
        return produto.getValor() * 0.0065;
    }
}