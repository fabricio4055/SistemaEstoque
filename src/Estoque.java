
import java.util.ArrayList;

public class Estoque {
    private String nome;
    private ArrayList<Produto> listaProdutos; //produtos guardados em memória

    public Estoque() {
        nome = new String();
        listaProdutos = new ArrayList<>();
    }

    public Estoque(String nome) {
        this.nome = nome;
        listaProdutos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(ArrayList<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public void adicionarProduto(Produto produto) {
        listaProdutos.add(produto);
    }

    //retorna o produto com o nome informado ou null se não existir
    public Produto buscarProduto(String nomeProduto) {
        for (Produto prod : listaProdutos) {
            if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
                return prod;
            }
        }
        return null;
    }

    //quantidade positiva = entrada, quantidade negativa = saída
    public void atualizarQuantidade(String nomeProduto, int quantidade) {
        Produto produto = buscarProduto(nomeProduto);
        if (produto == null) {
            System.out.println("Produto não encontrado.");
        } else if (quantidade < 0 && produto.getQuantidade() + quantidade < 0) {
            System.out.println("Quantidade insuficiente em estoque! Disponível: " +
            produto.getQuantidade());
        } else if (quantidade > 0 && produto.getQuantidade() + quantidade < 0) {
            //a soma passou do maior valor que o tipo int consegue guardar
            System.out.println("Quantidade inválida! O total ultrapassa o limite permitido.");
        } else {
            produto.setQuantidade(produto.getQuantidade() + quantidade);
            System.out.println("Quantidade atualizada com sucesso! Nova quantidade: " +
            produto.getQuantidade());
        }
    }

    public void listarProdutos() {
        if (listaProdutos.size() > 0) {
            System.out.println("\n--- Produtos no " + nome + " ---");
            for (Produto prod : listaProdutos) {
                System.out.println(prod.mostrarDados());
            }
        } else {
            System.out.println("Não existem produtos cadastrados!");
        }
    }
}
