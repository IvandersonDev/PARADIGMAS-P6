import java.util.ArrayList;
import java.util.Scanner;

class Item {
    private final String codigo;
    private final String descricao;
    private final double valor;
    private double acrescimo;
    private double desconto;

    public Item(String codigo, String descricao, double valor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
        this.acrescimo = 0;
        this.desconto = 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getTotal() {
        return valor + acrescimo - desconto;
    }

    public double getAcrescimo() {
        return acrescimo;
    }

    public double getDesconto() {
        return desconto;
    }

    public void aplicarAcrescimo(double valor) {
        this.acrescimo += valor;
    }

    public void aplicarDesconto(double valor) {
        this.desconto += valor;
    }

    public void imprimirItem() {
        System.out.println(
                "Código: " + codigo + ", Descrição: " + descricao + ", Valor: " + valor + ", Acréscimo: " + acrescimo + ", Desconto: " + desconto + ", Total: " + getTotal());

    }
}

class Carrinho {
    private final ArrayList<Item> itens;

    public Carrinho() {
        itens = new ArrayList<>();
    }

    public void inserirItem(Item item) {
        itens.add(item);
    }

    public Item buscarItemPorCodigo(String codigo) {
        for (Item item : itens) {
            if (item.getCodigo().equals(codigo)) {
                return item;
            }
        }
        return null;
    }

    public void aplicarAcrescimoTotal(double acrescimo) {
        if (!itens.isEmpty()) {
            double acrescimoPorItem = acrescimo / itens.size();
            itens.forEach(item -> item.aplicarAcrescimo(acrescimoPorItem));
        }
    }

    public void aplicarDescontoTotal(double desconto) {
        if (!itens.isEmpty()) {
            double descontoPorItem = desconto / itens.size();
            itens.forEach(item -> item.aplicarDesconto(descontoPorItem));
        }
    }

    public double calcularTotalAcrescimos() {
        return itens.stream().mapToDouble(Item::getAcrescimo).sum();
    }

    public double calcularTotalDescontos() {
        return itens.stream().mapToDouble(Item::getDesconto).sum();
    }

    public double calcularValorTotal() {
        return itens.stream().mapToDouble(Item::getTotal).sum();
    }

    public void finalizarVenda() {
        System.out.println("Itens do carrinho:");
        itens.forEach(Item::imprimirItem);
        System.out.println("Desconto total: " + calcularTotalDescontos());
        System.out.println("Acréscimo total: " + calcularTotalAcrescimos());
        System.out.println("Valor total: " + calcularValorTotal());
    }
}

public class SistemaDeControle {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Carrinho carrinho = new Carrinho();

    public static void main(String[] args) {
        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    inserirItem();
                    break;
                case 2:
                    aplicarAcrescimoItem();
                    break;
                case 3:
                    aplicarDescontoItem();
                    break;
                case 4:
                    aplicarAcrescimoTotal();
                    break;
                case 5:
                    aplicarDescontoTotal();
                    break;
                case 6:
                    carrinho.finalizarVenda();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("Menu:");
        System.out.println("1. Inserir item ao carrinho");
        System.out.println("2. Acréscimo de item");
        System.out.println("3. Desconto de item");
        System.out.println("4. Acréscimo total");
        System.out.println("5. Desconto total");
        System.out.println("6. Finalizar venda");
        System.out.print("Escolha uma opção: ");
    }

    private static void inserirItem() {
        System.out.print("Código do item: ");
        String codigo = scanner.nextLine();
        System.out.print("Descrição do item: ");
        String descricao = scanner.nextLine();
        System.out.print("Valor do item: ");
        double valor = scanner.nextDouble();
        Item item = new Item(codigo, descricao, valor);
        carrinho.inserirItem(item);
        System.out.println("Item inserido com sucesso!");
    }

    private static void aplicarAcrescimoItem() {
        System.out.print("Código do item: ");
        String codigo = scanner.nextLine();
        Item item = carrinho.buscarItemPorCodigo(codigo);
        if (item != null) {
            System.out.print("Valor do acréscimo: ");
            double acrescimo = scanner.nextDouble();
            item.aplicarAcrescimo(acrescimo);
            System.out.println("Acréscimo aplicado com sucesso!");
        } else {
            System.out.println("Item não encontrado!");
        }
    }

    private static void aplicarDescontoItem() {
        System.out.print("Código do item: ");
        String codigo = scanner.nextLine();
        Item item = carrinho.buscarItemPorCodigo(codigo);
        if (item != null) {
            System.out.print("Valor do desconto: ");
            double desconto = scanner.nextDouble();
            item.aplicarDesconto(desconto);
            System.out.println("Desconto aplicado com sucesso!");
        } else {
            System.out.println("Item não encontrado!");
        }
    }

    private static void aplicarAcrescimoTotal() {
        System.out.print("Valor total do acréscimo: ");
        double acrescimo = scanner.nextDouble();
        carrinho.aplicarAcrescimoTotal(acrescimo);
        System.out.println("Acréscimo total aplicado com sucesso!");
    }

    private static void aplicarDescontoTotal() {
        System.out.print("Valor total do desconto: ");
        double desconto = scanner.nextDouble();
        carrinho.aplicarDescontoTotal(desconto);
        System.out.println("Desconto total aplicado com sucesso!");
    }
}
