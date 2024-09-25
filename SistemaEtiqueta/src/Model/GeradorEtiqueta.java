package Model;

public class GeradorEtiqueta {
    public static String gerarEtiqueta(Produto produto) {
        return "^XA\n" +
                "^CF0,60\n" +
                "^FO50,50^FD" + produto.getDescricao() + "^FS\n" +
                "^FO50,200^FDLata  R$" + String.format("%.2f", produto.getPrecoLata()) + "^FS\n" +
                "^FO50,280^FDCaixa R$" + String.format("%.2f", produto.getPrecoCaixa()) + "^FS\n" +
                "^FO100,450^BCN,100,Y,N,N^FD" + produto.getCodigoBarras() + "^FS\n" +
                "^XZ";
    }
}
