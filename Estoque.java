import java.util.ArrayList;

public class Estoque {
    private int id = 1;
    private final ArrayList<Produto> listaDeProdutos = new ArrayList<>();

    public void inicializaEstoque() {
        new Estoque();
    }

    public Produto encontraProdutoPorNome(String nome) {
        for (Produto produto : listaDeProdutos) {
            if (nome.equals(produto.getNome())) {
                return produto;
            }
        }
        return null;
    }

    public Produto encontraProdutoPorId(int id) {
        for (Produto produto : listaDeProdutos) {
            if (id == produto.getId()) {
                return produto;
            }
        }
        return null;
    }

    public boolean cadastraProduto(Produto produto) {
        for (Produto verificaId : listaDeProdutos){
            if (verificaId.getId() == produto.getId()){
                System.out.println("Não foi possível cadastrar produto. Já existe um produto com este id.");
                return false;
            }
        }
        return listaDeProdutos.add(produto);
    }

    public void imprimeCatalogoDoEstoque() {
        if (listaDeProdutos.isEmpty()){
            System.out.println("Estoque vazio.");
        } else {
            for (Produto produto : listaDeProdutos) {
                System.out.println("➥ Produto n°" + getPosicaoDoProdutoNaLista(produto) +
                        "\n" + produto.getNome() +
                        "\nId: " + produto.getId() +
                        "\nPreço: " + produto.getPreco() +
                        "\nQuantidade em estoque: " + produto.getQuantidadeEmEstoque());
            }
        }
    }

    public boolean darBaixaEmEstoquePorNome(String nome, int quantidadeParaDarBaixa) {
        var produto = encontraProdutoPorNome(nome);
        if (temEstoqueOuNao(produto, quantidadeParaDarBaixa)) {
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - quantidadeParaDarBaixa);
            return true;
        } else {
            return false;
        }
    }

    public boolean darBaixaEmEstoquePorId(int id, int quantidadeParaDarBaixa) {
        var produto = encontraProdutoPorId(id);
            if (temEstoqueOuNao(produto, quantidadeParaDarBaixa)) {
                produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - quantidadeParaDarBaixa);
                return true;
            }
        return false;
    }

    public int getQuantidadeAtualEmEstoque(Produto produto) {
        for (Produto produtoProcurado : listaDeProdutos) {
            if (produto.equals(produtoProcurado)) {
                return produtoProcurado.getQuantidadeEmEstoque();
            }
        }
        return 0;
    }

    public int getPosicaoDoProdutoNaLista(Produto produto) {
        return listaDeProdutos.indexOf(produto)+1;
    }

    public boolean temEstoqueOuNao(Produto produto, int quantidadeParaDarBaixa) {
        var qtdAtualEmEstoque = getQuantidadeAtualEmEstoque(produto);
        return qtdAtualEmEstoque >= quantidadeParaDarBaixa;
    }
}
