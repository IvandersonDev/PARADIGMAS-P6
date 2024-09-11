import java.util.ArrayList;
import java.util.Scanner;

class SistemaDeControle {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<String> codigos = new ArrayList<>();
    private static final ArrayList<String> descricoes = new ArrayList<>();
    private static final ArrayList<Double> valores = new ArrayList<>();
    private static final ArrayList<Double> acrescimos = new ArrayList<>();
    private static final ArrayList<Double> descontos = new ArrayList<>();

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
                    finalizarVenda();
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

        codigos.add(codigo);
        descricoes.add(descricao);
        valores.add(valor);
        acrescimos.add(0.0);
        descontos.add(0.0);

        System.out.println("Item inserido com sucesso!");
    }

    private static int buscarItemPorCodigo(String codigo) {
        return codigos.indexOf(codigo);
    }

    private static void aplicarAcrescimoItem() {
        System.out.print("Código do item: ");
        String codigo = scanner.nextLine();
        int index = buscarItemPorCodigo(codigo);

        if (index != -1) {
            System.out.print("Valor do acréscimo: ");
            double acrescimo = scanner.nextDouble();
            acrescimos.set(index, acrescimos.get(index) + acrescimo);
            System.out.println("Acréscimo aplicado com sucesso!");
        } else {
            System.out.println("Item não encontrado!");
        }
    }

    private static void aplicarDescontoItem() {
        System.out.print("Código do item: ");
        String codigo = scanner.nextLine();
        int index = buscarItemPorCodigo(codigo);

        if (index != -1) {
            System.out.print("Valor do desconto: ");
            double desconto = scanner.nextDouble();
            descontos.set(index, descontos.get(index) + desconto);
            System.out.println("Desconto aplicado com sucesso!");
        } else {
            System.out.println("Item não encontrado!");
        }
    }

    private static void aplicarAcrescimoTotal() {
        System.out.print("Valor total do acréscimo: ");
        double acrescimoTotal = scanner.nextDouble();

        if (!codigos.isEmpty()) {
            double acrescimoPorItem = acrescimoTotal / codigos.size();
            for (int i = 0; i < acrescimos.size(); i++) {
                acrescimos.set(i, acrescimos.get(i) + acrescimoPorItem);
            }
            System.out.println("Acréscimo total aplicado com sucesso!");
        }
    }

    private static void aplicarDescontoTotal() {
        System.out.print("Valor total do desconto: ");
        double descontoTotal = scanner.nextDouble();

        if (!codigos.isEmpty()) {
            double descontoPorItem = descontoTotal / codigos.size();
            for (int i = 0; i < descontos.size(); i++) {
                descontos.set(i, descontos.get(i) + descontoPorItem);
            }
            System.out.println("Desconto total aplicado com sucesso!");
        }
    }

    private static double calcularTotalAcrescimos() {
        return acrescimos.stream().mapToDouble(Double::doubleValue).sum();
    }

    private static double calcularTotalDescontos() {
        return descontos.stream().mapToDouble(Double::doubleValue).sum();
    }

    private static double calcularValorTotal() {
        double total = 0.0;
        for (int i = 0; i < codigos.size(); i++) {
            total += valores.get(i) + acrescimos.get(i) - descontos.get(i);
        }
        return total;
    }

    private static void finalizarVenda() {
        System.out.println("Itens do carrinho:");
        for (int i = 0; i < codigos.size(); i++) {
            System.out.println(
                    "Código: " + codigos.get(i) + ", Descrição: " + descricoes.get(i) +
                            ", Valor: " + valores.get(i) + ", Acréscimo: " + acrescimos.get(i) +
                            ", Desconto: " + descontos.get(i) + ", Total: " + (valores.get(i) + acrescimos.get(i) - descontos.get(i))
            );
        }

        System.out.println("Desconto total: " + calcularTotalDescontos());
        System.out.println("Acréscimo total: " + calcularTotalAcrescimos());
        System.out.println("Valor total: " + calcularValorTotal());
    }
}
