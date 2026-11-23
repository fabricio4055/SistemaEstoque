public class Produto {
    private String nome;
    private int quantidade;
    private float preco; //preço de compra/produção
    private float precoVenda;

    public Produto() {
        nome = new String();
        quantidade = 0;
        preco = 0;
        precoVenda = 0;
    }

    public Produto(String nome, int quantidade, float preco, float precoVenda) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.precoVenda = precoVenda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public float getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(float precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String mostrarDados() {
        return "Nome: " + nome +
        ", Quantidade: " + quantidade +
        ", Preço de compra: R$ " + preco +
        ", Preço de venda: R$ " + precoVenda;
    }
}
