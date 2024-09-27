package imposto;


import model.Produto;

public class ICMS implements Imposto {
    @Override
    public double calcular(Produto produto) {
        return produto.getValor() * 0.18;
    }
}
