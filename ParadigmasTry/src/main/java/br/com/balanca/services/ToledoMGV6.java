package br.com.balanca.services;

import br.com.balanca.exceptions.CodigoProdutoDuplicadoException;
import br.com.balanca.exceptions.DiretorioInvalidoException;
import br.com.balanca.exceptions.ExportacaoFalhaException;
import br.com.balanca.exceptions.FormatoInvalidoException;
import br.com.balanca.exceptions.ProdutoInvalidoException;
import br.com.balanca.exceptions.TipoProdutoInvalidoException; // Importando a exceção
import br.com.balanca.interfaces.IBalanca;
import br.com.balanca.models.Produto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ToledoMGV6 implements IBalanca<Produto> {

    @Override
    public void exportar(List<Produto> produtos, String pastaArquivoTxt) throws DiretorioInvalidoException, ProdutoInvalidoException, FormatoInvalidoException, ExportacaoFalhaException, CodigoProdutoDuplicadoException, TipoProdutoInvalidoException {
        File directory = new File(pastaArquivoTxt);
        if (!directory.exists()) {
            throw new DiretorioInvalidoException("Diretório não existe: " + pastaArquivoTxt);
        }

        Set<Integer> codigos = new HashSet<>();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pastaArquivoTxt + "/ITENSMGV.TXT"))) {
            for (Produto produto : produtos) {
                if (produto.getDescricao() == null || produto.getValor() <= 0) {
                    throw new ProdutoInvalidoException("Produto inválido: " + produto.getDescricao());
                }

                if (!codigos.add(produto.getCodigo())) {
                    throw new CodigoProdutoDuplicadoException("Código duplicado encontrado: " + produto.getCodigo());
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

    private String formatarProduto(Produto produto) throws TipoProdutoInvalidoException {
        String dept = "01";
        String tipo;

        if ("9".equals(produto.getTipo())) {
            tipo = "P";
        } else if ("1".equals(produto.getTipo())) {
            tipo = "U";
        } else {
            throw new TipoProdutoInvalidoException("Tipo de produto inválido: " + produto.getTipo());
        }

        String codigo = String.format("%06d", produto.getCodigo());
        String preco = String.format("%06d", (int) (produto.getValor() * 100));
        String descricao = String.format("%-50s", produto.getDescricao());

        return dept + tipo + codigo + preco + "000" + descricao +
                "0000000000|01|                                                                      0000000000000000000000000|0000|0||";
    }
}
