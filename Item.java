public class Item {
    private final Produto produto;
    private final int quantidade;
    private double valorDoItem;

    public Item(Produto produto, int quantidade){
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public void defineValorTotal(){
        valorDoItem = produto.getPreco() * this.quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorDoItem() {
        return valorDoItem;
    }
}
