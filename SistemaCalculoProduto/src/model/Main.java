package model;

import factory.ImpostoFactory;
import factory.TipoImposto;
import imposto.Imposto;


public class Main {
    public static void main(String[] args) throws Exception {

        Produto produto = new Produto();
        produto.setCodigo(1);
        produto.setDescricao("Milho");
        produto.setValor(100);
        produto.setIndustrial(false);

        Imposto imposto = ImpostoFactory.getImposto(TipoImposto.TIPOICMS);

        double impostoCalculado = imposto.calcular(produto);
        produto.setImpostoCalculado(impostoCalculado);

        System.out.println("Produto: " + produto.getDescricao());
        System.out.println("Valor: R$ " + produto.getValor());
        System.out.println("Imposto (ICMS): R$ " + produto.getImpostoCalculado());
        System.out.println("Total: R$ " + (produto.getValor() + produto.getImpostoCalculado()));
    }
}
