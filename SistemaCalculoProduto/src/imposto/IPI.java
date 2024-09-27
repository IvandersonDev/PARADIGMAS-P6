package imposto;

import model.Produto;

public class IPI implements Imposto {
    @Override
    public double calcular(Produto produto) {
        if (produto.isIndustrial()) {
            return produto.getValor() * 0.12;
        } else {
            return 0;
        }
    }
}



