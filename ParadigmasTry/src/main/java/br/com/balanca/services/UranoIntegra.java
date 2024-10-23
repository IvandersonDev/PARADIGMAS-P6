package br.com.balanca.services;

import br.com.balanca.exceptions.DiretorioInvalidoException;
import br.com.balanca.exceptions.ExportacaoFalhaException;
import br.com.balanca.exceptions.FormatoInvalidoException;
import br.com.balanca.exceptions.ProdutoInvalidoException;
import br.com.balanca.interfaces.IBalanca;
import br.com.balanca.models.Produto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UranoIntegra implements IBalanca<Produto> {

    @Override
    public void exportar(List<Produto> produtos, String pastaArquivoTxt) throws DiretorioInvalidoException, ProdutoInvalidoException, FormatoInvalidoException, ExportacaoFalhaException {
        File directory = new File(pastaArquivoTxt);
        if (!directory.exists()) {
            throw new DiretorioInvalidoException("Diretório não existe: " + pastaArquivoTxt);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pastaArquivoTxt + "/PRODUTOS.TXT"))) {
            for (Produto produto : produtos) {
                if (produto.getDescricao() == null || produto.getValor() <= 0) {
                    throw new ProdutoInvalidoException("Produto inválido: " + produto.getDescricao());
                }

                String linha;
                try {
                    linha = formatarProduto(produto);
                } catch (Exception e) {
                    throw new FormatoInvalidoException("Erro na formatação do produto: " + produto.getDescricao());
                }

                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new ExportacaoFalhaException("Erro ao exportar produtos: " + e.getMessage());
        }
    }

    private String formatarProduto(Produto produto) {
        String codigo = String.format("%06d", produto.getCodigo());
        String flag = "*";
        String tipo = "9".equals(produto.getTipo()) ? "0" : "6"; // 9 coloquei como se fosse para o peso
        String descricao = String.format("%-20s", produto.getDescricao());
        String preco = String.format("%09.2f", produto.getValor()).replace(".", ",");

        return codigo + flag + tipo + descricao + preco + "00000D";
    }
}
