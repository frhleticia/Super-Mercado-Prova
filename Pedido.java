import java.util.ArrayList;
import java.util.Scanner;

public class Pedido {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Item> listaDeItems = new ArrayList<>();
    private double valorTotalDoPedido = 0;
    private Estoque estoque;

    public void calculaValorTotal(){
        valorTotalDoPedido = 0;
        for (Item item : listaDeItems) {
            var produto = item.getProduto();
            valorTotalDoPedido += produto.getPreco() * item.getQuantidade();
        }
        this.setValorTotalDoPedido(valorTotalDoPedido);
    }

    public boolean verificarSePodeRealizarPagamento(double recebeValorDoCliente){
        if (listaDeItems.isEmpty()) {
            System.out.println("Não foi possível realizar o pagamento. Carrinho vazio.");
        } else {
            if (recebeValorDoCliente >= valorTotalDoPedido) {
                return true;
            } else {
                System.out.println("Não foi possível efetuar o pagamento. Dinheiro insuficiente.");
            }
        }
        return false;
    }

    public double realizarTransacao(double recebeValorDoCliente) {
            calculaValorTotal();
            if (recebeValorDoCliente < valorTotalDoPedido) {
                return -1;
            } else {
                return recebeValorDoCliente - valorTotalDoPedido;
            }
    }

    public void calcularMenorQuantidadeDeNotas(double troco) {
        int trocoInt = (int) Math.floor(troco);
        double moedas = troco - trocoInt;

        int[] notasEmReais = {50, 20, 10, 5, 2};

        System.out.println("Método que recebe o valor do troco e calcula a menor quantidade de notas: ");
        for (int nota : notasEmReais){
            int totalMenorQtdDeNotas = trocoInt / nota;
            trocoInt = trocoInt % nota;
            System.out.print(totalMenorQtdDeNotas+" nota(s) de "+nota+" ");
        }
        System.out.println("\n");
    }

    public boolean adicionaItemNaLista (Produto produto, int quantidade) {
        if (estoque.temEstoqueOuNao(produto, quantidade)) {
            Item item = new Item(produto, quantidade);
            listaDeItems.add(item);
            estoque.darBaixaEmEstoquePorNome(produto.getNome(), quantidade);
            return true;
        } else {
            System.out.println("Não foi possível adicionar. Produto fora de estoque.");
            return false;
        }
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
        calculaValorTotal();
        System.out.printf("Valor total: %.2f\n", valorTotalDoPedido);
    }

    public void adicionaItem(){
        var nomeProdutoParaProcuar = recebeNomeDoTeclado();
        var qtdDeProdutoParaAdicionar = recebeQuantidadeDoTeclado();
        var produtoEncontrado = estoque.encontraProdutoPorNome(nomeProdutoParaProcuar);
            if (produtoEncontrado != null){
            adicionaItemNaLista(produtoEncontrado, qtdDeProdutoParaAdicionar);
            } else {
            System.out.println("Produto não encontrado.");
            }
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

    public double getValorTotalDoPedido() {
        return valorTotalDoPedido;
    }

    public void setValorTotalDoPedido(double valorTotalDoPedido) {
        this.valorTotalDoPedido = valorTotalDoPedido;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }
}
