package imposto;

import model.Produto;

public interface Imposto {
    double calcular(Produto produto);
}