package br.com.balanca.models;

import br.com.balanca.exceptions.ProdutoInvalidoException;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Produto {

    private int codigo;
    private String descricao;
    private String tipo;
    private double valor;


    public void setCodigo(int codigo) throws ProdutoInvalidoException {
        if (codigo <= 0) {
            throw new ProdutoInvalidoException("O código do produto deve ser maior que zero.");
        }
        this.codigo = codigo;
    }

    public void setDescricao(String descricao) throws ProdutoInvalidoException {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new ProdutoInvalidoException("A descrição do produto não pode ser vazia.");
        }
        this.descricao = descricao;
    }

    public void setTipo(String tipo) throws ProdutoInvalidoException {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new ProdutoInvalidoException("O tipo do produto não pode ser vazio.");
        }
        if (!tipo.equals("9") && !tipo.equals("1")) {
            throw new ProdutoInvalidoException("O tipo do produto é inválido. Use '9' ou '1'.");
        }
        this.tipo = tipo;
    }

    public void setValor(double valor) throws ProdutoInvalidoException {
        if (valor <= 0) {
            throw new ProdutoInvalidoException("O valor do produto deve ser maior que zero.");
        }
        this.valor = valor;
    }
}
