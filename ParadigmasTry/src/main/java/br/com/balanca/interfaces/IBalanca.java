package br.com.balanca.interfaces;

import br.com.balanca.exceptions.*;

import java.util.List;

public interface IBalanca<T> {
    void exportar(List<T> produtos, String pastaArquivoTxt) throws DiretorioInvalidoException,TipoBalancaNaoSuportadoException, ProdutoInvalidoException,TipoProdutoInvalidoException, CodigoProdutoDuplicadoException, FormatoInvalidoException, ExportacaoFalhaException;
}
