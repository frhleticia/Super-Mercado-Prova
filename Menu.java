import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Estoque estoque = new Estoque();
    private Item item;
    private Pedido pedido = new Pedido();
    private int opcaoMenuPrincipal;
    private int opcaoEstoque;
    Scanner scannerParaNumero = new Scanner(System.in);
    Scanner scannerParaNome = new Scanner(System.in);


    public void controlaMenu() {
        pedido.setEstoque(estoque);
        do {
            switchCase();
        } while (opcaoMenuPrincipal != 0);
    }

    public void mostraEstoque() {
        estoque.imprimeCatalogoDoEstoque();
    }

    public void switchCase() {
        System.out.println("Digite 1 para gerenciar pedidos, 2 para gerenciar o estoque, ou 0 para sair. ");
        opcaoMenuPrincipal = scannerParaNumero.nextInt();
        if (opcaoMenuPrincipal == 1) {
            int opcaoPedidos;
            do {
                System.out.println("Digite 1 para adicionar item ao carrinho.\n" +
                        "Digite 2 para ver o carrinho completo.\n" +
                        "Digite 3 para realizar o pagamento.\n" +
                        "Digite 4 para retirar todos os itens do carrinho.\n" +
                        "Digite -1 para voltar ao menu anterior.");
                opcaoPedidos = scannerParaNumero.nextInt();
                switch (opcaoPedidos) {
                    case 1:
                        pedido.adicionaItem();
                        break;
                    case 2:
                        pedido.imprimePedido();
                        pedido.imprimeValorTotal();
                        break;
                    case 3:
                        ArrayList<Item> carrinhoDoCliente = pedido.getListaDeItems();
                        if (carrinhoDoCliente.isEmpty()){
                            System.out.println("Não foi possível realizar o pagamento.");
                        } else {
                            System.out.println("Digite o valor a ser entregue: ");
                            double recebeValorDoCliente = scannerParaNumero.nextDouble();
                            double troco = pedido.realizarPagamento(recebeValorDoCliente);
                            if (troco > 0) {
                                System.out.println("Pagamento realizado com sucesso! Troco: " + troco);
                            } else {
                                System.out.println("Pagamento realizado com sucesso! Não restou troco.");
                            }
                            pedido.limparCarrinho();
                            pedido.calcularMenorQuantidadeDeNotas(troco);
                        }
                        break;
                    case 4:
                        pedido.limparCarrinho();
                        break;
                }
            } while (opcaoPedidos != -1);

        } else if (opcaoMenuPrincipal  == 2) {
            do {
                System.out.println("Digite 1 para cadastrar um produto novo.\n" +
                        "Digite 2 para ver o catálogo completo do estoque.\n" +
                        "Digite -1 para voltar ao menu anterior.");
                opcaoEstoque = scannerParaNumero.nextInt();
                switch (opcaoEstoque) {
                    case 1:
                        System.out.println("Digite o id do produto: ");
                        int idProduto = scannerParaNumero.nextInt();
                        System.out.println("Digite o nome do produto: ");
                        String nomeProduto = scannerParaNome.nextLine();
                        System.out.println("Digite o preço do produto: ");
                        double precoProduto = scannerParaNumero.nextDouble();
                        System.out.println("Digite a quantidade de produto no estoque: ");
                        int qtdEmEstoqueProduto = scannerParaNumero.nextInt();
                        Produto produto = new Produto(idProduto, nomeProduto, precoProduto, qtdEmEstoqueProduto);
                        estoque.cadastraProduto(produto);
                        break;
                    case 2:
                        mostraEstoque();
                        break;
                    case 3:

                        break;
                    case 4:
                        break;
                }
            } while (opcaoEstoque != -1);
        } else {
            System.out.println("Opção inválida. Tente novamente.");
        }
    }
}