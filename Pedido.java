import java.util.ArrayList;
import java.util.Scanner;

public class Pedido {
    Scanner scanner = new Scanner(System.in);
    private final ArrayList<Item> listaDeItems = new ArrayList<>();
    private double valorTotalDoPedido = 0;
    private Estoque estoque;

    public void calculaValorTotal(){
        for (Item item : listaDeItems) {
            item.defineValorTotal();
            valorTotalDoPedido += item.getValorDoItem();
        }
        this.setValorTotalDoPedido(valorTotalDoPedido);
    }

    public boolean verificarSeCarrinhoEstaVazio(){
        return listaDeItems.isEmpty();
    }

    public double realizarTransacao(double recebeValorDoCliente) {
        calculaValorTotal();
        if (recebeValorDoCliente < valorTotalDoPedido) {
            System.out.println("Não foi possível efetuar o pagamento. Dinheiro insuficiente.");
            return -1;
        } else {
            return recebeValorDoCliente - valorTotalDoPedido;
        }
    }
    public void realizarPagamento(double troco){
        if (!verificarSeCarrinhoEstaVazio()){
            if (troco > 0) {
                System.out.println("Pagamento realizado com sucesso! Troco: " + troco);
                calcularMenorQuantidadeDeNotas(troco);
            } else {
                System.out.println("Pagamento realizado com sucesso! Não restou troco.");
            }
            limparCarrinho();
        } else {
            System.out.println("Não foi possível realizar o pagamento. Carrinho vazio.");
        }
    }

    public void calcularMenorQuantidadeDeNotas(double troco) {
        int trocoInt = (int) Math.floor(troco);
        int[] notasEmReais = {50, 20, 10, 5, 2};
        for (int nota : notasEmReais){
            int totalMenorQtdDeNotas = trocoInt / nota;
            trocoInt = trocoInt % nota;
            System.out.print(totalMenorQtdDeNotas+" nota(s) de "+nota+", ");
        }
        System.out.println("respondendo a letra c) Desenvolver um método onde recebe o valor do troco e \ncalcule a menor quantidade de notas.\n");
    }

    public boolean adicionaItemNaLista (Produto produto, int quantidade) {
        Item item = new Item(produto, quantidade);
        return listaDeItems.add(item);
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
        imprimeValorTotal();
    }

    public void imprimeValorTotal() {
        calculaValorTotal();
        System.out.printf("Valor total: %.2f\n", getValorTotalDoPedido());
    }

    public void adicionaItem(Produto produtoEncontrado){
        if (produtoEncontrado == null) {
            System.out.println("Produto não encontrado.");
            return; //faz o código parar aqui
        }

        System.out.println("Digite a quantidade: ");
        var qtdDeProdutoParaAdicionar = recebeQuantidadeDoTeclado();

        if (estoque.temEstoqueOuNao(produtoEncontrado, qtdDeProdutoParaAdicionar)) {
            if (adicionaItemNaLista(produtoEncontrado, qtdDeProdutoParaAdicionar)) {
                estoque.darBaixaEmEstoquePorNome(produtoEncontrado.getNome(), qtdDeProdutoParaAdicionar);
            }
        } else {
            System.out.println("Não foi possível adicionar. Produto fora de estoque.");
            }
    }

    public String recebeNomeDoTeclado(){
        System.out.println("Digite o nome do produto: ");
        return scanner.next();
    }

    public int recebeQuantidadeDoTeclado(){
        return scanner.nextInt();
    }

    public void limparCarrinho(){
        listaDeItems.clear();
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
