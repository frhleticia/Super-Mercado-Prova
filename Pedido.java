import java.util.ArrayList;
import java.util.Scanner;

public class Pedido {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Item> listaDeItems = new ArrayList<>();
    private double valorTotalDoProduto = 0;
    private Estoque estoque;

    public double realizarPagamento(double recebeValorDoCliente){
        calculaValorTotal();
        if (recebeValorDoCliente < valorTotalDoProduto){
            System.out.println("Não foi possível efetuar o pagamento. Dinheiro insuficiente.");
        }
            return Math.max(recebeValorDoCliente, getValorTotalDoProduto()) - Math.min(recebeValorDoCliente, getValorTotalDoProduto());
    }

    public void calcularMenorQuantidadeDeNotas(double troco) {
        int trocoInt = (int) Math.floor(troco);
        double restoDoTroco = troco - trocoInt;

        int[] notasEmReais = {50, 20, 10, 5, 2};
        int[] menorQtdDeNotas = new int[4];

        for (int i=0; i<5; i++){
            menorQtdDeNotas[i] = trocoInt/notasEmReais[i];
            System.out.print(menorQtdDeNotas[i] + " notas de " + notasEmReais[i] + ", ");
        }
        System.out.println("e " + restoDoTroco + " moedas.");
    }

    public void calculaValorTotal(){
        for (Item item : listaDeItems){
            var produto = item.getProduto();
            valorTotalDoProduto += produto.getPreco();
        }
        this.setValorTotalDoProduto(valorTotalDoProduto);
    }

    public boolean adicionaItemNaLista (Produto produto, int quantidade) {
        Item item = new Item(produto, quantidade);
        listaDeItems.add(item);
        return true;
    }

    public void imprimePedido(){
        if (listaDeItems.isEmpty()){
            System.out.println("Carrinho vazio. Insira itens para visualizar o carrinho.");
        } else {
            for (Item item : listaDeItems) {
                var produto = item.getProduto();
                System.out.println("➥ "+ produto.getNome() +" x"+item.getQuantidade()+
                        "\nValor da unidade: "+ produto.getPreco());
            }
        }
    }

    public void imprimeValorTotal() {
        double valorTotalPedido = 0;
        for (Item item : listaDeItems) {
            var produto = item.getProduto();
            valorTotalPedido += (produto.getPreco() * item.getQuantidade());
        }
        System.out.println("Valor total: " + valorTotalPedido);
    }

    public void adicionaItem(){
        var nomeProdutoParaProcuar = recebeNomeDoTeclado();
        var qtdDeProdutoParaAdicionar = recebeQuantidadeDoTeclado();
        var produtoEncontrado = estoque.encontraProdutoPorNome(nomeProdutoParaProcuar);
        if (estoque.temEstoqueOuNao(produtoEncontrado, qtdDeProdutoParaAdicionar)){
            if (produtoEncontrado != null){
            adicionaItemNaLista(produtoEncontrado, qtdDeProdutoParaAdicionar);
            } else {
            System.out.println("Produto não encontrado.");
            }
        }
        System.out.println("Produto fora de estoque.");
    }

    public String recebeNomeDoTeclado(){
        System.out.println("Digite o nome do produto: ");
        return scanner.next();
    }

    public int recebeQuantidadeDoTeclado(){
        System.out.println("Digite a quantidade de produto: ");
        return scanner.nextInt();
    }

    public void limparCarrinho(){
        listaDeItems.clear();
    }

    public ArrayList<Item> getListaDeItems() {
        return listaDeItems;
    }

    public void setListaDeItems(ArrayList<Item> listaDeItems) {
        this.listaDeItems = listaDeItems;
    }

    public double getValorTotalDoProduto() {
        return valorTotalDoProduto;
    }

    public void setValorTotalDoProduto(double valorTotalDoProduto) {
        this.valorTotalDoProduto = valorTotalDoProduto;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }
}