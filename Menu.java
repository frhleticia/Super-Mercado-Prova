import java.util.Scanner;

public class Menu {
    private final Estoque estoque = new Estoque();
    private final Pedido pedido = new Pedido();
    private int opcaoMenuPrincipal;
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
        System.out.println("""
                Digite 1 para gerenciar pedidos.
                Digite 2 para gerenciar o estoque.
                Digite 0 para sair.""");
        opcaoMenuPrincipal = scannerParaNumero.nextInt();
        if (opcaoMenuPrincipal == 1) {
            int opcaoPedidos;
            do {
                System.out.println("""
                        Digite 1 para adicionar item ao carrinho.
                        Digite 2 para ver o carrinho completo.
                        Digite 3 para realizar o pagamento.
                        Digite 4 para retirar todos os itens do carrinho.
                        Digite -1 para voltar ao menu anterior.""");
                opcaoPedidos = scannerParaNumero.nextInt();
                switch (opcaoPedidos) {
                    case 1:
                        System.out.println("(1) Adicionar item por nome. \n(2) Adicionar item por id.");
                        int opcaoCadastro = scannerParaNumero.nextInt();
                        switch (opcaoCadastro){
                            case 1: {
                                var nomeProdutoParaProcuar = pedido.recebeNomeDoTeclado();
                                var produtoEncontrado = estoque.encontraProdutoPorNome(nomeProdutoParaProcuar);
                                pedido.adicionaItem(produtoEncontrado);
                                break;
                            }
                            case 2: {
                                System.out.println("Digite o id do produto: ");
                                var idProdutoParaProcurar = pedido.recebeQuantidadeDoTeclado();
                                var produtoEncontrado = estoque.encontraProdutoPorId(idProdutoParaProcurar);
                                pedido.adicionaItem(produtoEncontrado);
                                break;
                            }
                            default:
                                System.out.println("Opção inválida.");
                                break;
                        }
                        break;
                    case 2:
                        pedido.imprimePedido();
                        break;
                    case 3:
                            System.out.println("Digite o valor a ser entregue para realizar o pagamento: ");
                            double recebeValorDoCliente = scannerParaNumero.nextDouble();
                            if (pedido.verificarSePodeRealizarPagamento()){
                                double troco = pedido.realizarTransacao(recebeValorDoCliente);
                                if (troco > 0) {
                                    System.out.println("Pagamento realizado com sucesso! Troco: " + troco);
                                    pedido.calcularMenorQuantidadeDeNotas(troco);
                                } else {
                                    System.out.println("Pagamento realizado com sucesso! Não restou troco.");
                                }
                                pedido.limparCarrinho();
                            } else if (pedido.realizarTransacao(recebeValorDoCliente) == -1){
                                System.out.println("Não foi possível efetuar o pagamento. Dinheiro insuficiente.");
                            } else {
                                System.out.println("Não foi possível realizar o pagamento. Carrinho vazio.");
                            }
                        break;
                    case 4:
                        pedido.limparCarrinho();
                        break;
                }
            } while (opcaoPedidos != -1);

        } else if (opcaoMenuPrincipal  == 2) {
            int opcaoEstoque;
            do {
                System.out.println("""
                        Digite 1 para cadastrar um produto novo.
                        Digite 2 para ver o catálogo completo do estoque.
                        Digite 3 para dar baixa em produto (por id).
                        Digite -1 para voltar ao menu anterior.""");
                opcaoEstoque = scannerParaNumero.nextInt();
                switch (opcaoEstoque) {
                    case 1:
                        Produto produto = criaProduto();
                        estoque.cadastraProduto(produto);
                        break;
                    case 2:
                        mostraEstoque();
                        break;
                }
            } while (opcaoEstoque != -1);
        } else {
            System.out.println("Tchau! Volte sempre :)");
        }
    }

    public Produto criaProduto(){
        System.out.println("Digite o id do produto: ");
        int idProduto = scannerParaNumero.nextInt();
        System.out.println("Digite o nome do produto: ");
        String nomeProduto = scannerParaNome.nextLine();
        System.out.println("Digite o preço do produto: ");
        double precoProduto = scannerParaNumero.nextDouble();
        System.out.println("Digite a quantidade de produto no estoque: ");
        int qtdEmEstoqueProduto = scannerParaNumero.nextInt();
        return new Produto(idProduto, nomeProduto, precoProduto, qtdEmEstoqueProduto);
    }
}
