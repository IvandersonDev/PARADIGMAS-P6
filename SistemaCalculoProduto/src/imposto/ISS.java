package imposto;

import model.Produto;

public class ISS implements Imposto {
    @Override
    public double calcular(Produto produto) {
        return produto.getValor() * 0.22;
    }
}