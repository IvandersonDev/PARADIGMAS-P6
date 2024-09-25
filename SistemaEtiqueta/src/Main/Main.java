package Main;

import Model.GeradorEtiqueta;
import Model.Produto;

public class Main {
    public static void main(String[] args) {

        Produto produto = new Produto("CERVEJA LAGER HEINEKEN", 6.0, 35.0, "78996683");

        String etiquetaZPL = GeradorEtiqueta.gerarEtiqueta(produto);

        System.out.println(etiquetaZPL);
    }
}
