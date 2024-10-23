package br.com.balanca;

import br.com.balanca.enums.TipoBalanca;
import br.com.balanca.exceptions.*;
import br.com.balanca.factory.BalancaFactory;
import br.com.balanca.interfaces.IBalanca;
import br.com.balanca.models.Produto;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            Produto produto1 = new Produto();
            produto1.setCodigo(101);
            produto1.setDescricao("MACARRAO ESPAGUETE 500G");
            produto1.setTipo("1");
            produto1.setValor(4.99);

            Produto produto2 = new Produto();
            produto2.setCodigo(102);
            produto2.setDescricao("ARROZ INTEGRAL 1KG");
            produto2.setTipo("1");
            produto2.setValor(8.49);

            Produto produto3 = new Produto();
            produto3.setCodigo(103);
            produto3.setDescricao("FEIJAO CARIOCA 1KG");
            produto3.setTipo("1");
            produto3.setValor(7.89);

            Produto produto4 = new Produto();
            produto4.setCodigo(104);
            produto4.setDescricao("CARNE BOVINA KG");
            produto4.setTipo("9");
            produto4.setValor(39.90);

            Produto produto5 = new Produto();
            produto5.setCodigo(105);
            produto5.setDescricao("PAPEL HIGIENICO 12UN");
            produto5.setTipo("1");
            produto5.setValor(15.90);

            Produto produto6 = new Produto();
            produto6.setCodigo(106);
            produto6.setDescricao("LEITE INTEGRAL 1L");
            produto6.setTipo("1");
            produto6.setValor(4.79);

            Produto produto7 = new Produto();
            produto7.setCodigo(107);
            produto7.setDescricao("QUEIJO MUÇARELA KG");
            produto7.setTipo("9");
            produto7.setValor(28.90);


            List<Produto> produtos = new ArrayList<>();
            produtos.add(produto1);
            produtos.add(produto2);
            produtos.add(produto3);
            produtos.add(produto4);
            produtos.add(produto5);
            produtos.add(produto6);
            produtos.add(produto7);


            IBalanca<Produto> balancaFilizola = BalancaFactory.getBalanca(TipoBalanca.FINIZOLA_SMART);
            balancaFilizola.exportar(produtos, "C:/Users/Usuario/Downloads/teste");

            IBalanca<Produto> balancaToledo = BalancaFactory.getBalanca(TipoBalanca.TOLEDO_MGV6);
            balancaToledo.exportar(produtos, "C:/Users/Usuario/Downloads/teste");

            IBalanca<Produto> balancaUrano = BalancaFactory.getBalanca(TipoBalanca.URANO_INTEGRA);
            balancaUrano.exportar(produtos, "C:/Users/Usuario/Downloads/teste");

            System.out.println("Arquivos gerados com sucesso!");
        } catch (ProdutoInvalidoException | TipoBalancaNaoSuportadoException | DiretorioInvalidoException | ExportacaoFalhaException | FormatoInvalidoException | CodigoProdutoDuplicadoException e) {
            System.err.println("Erro: " + e.getMessage());
        } catch (TipoProdutoInvalidoException e) {
            throw new RuntimeException(e);
        }
    }
}
