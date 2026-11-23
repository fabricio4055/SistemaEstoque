
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner ler = new Scanner(System.in);
        Estoque estoque = new Estoque("Estoque Principal");
        ControleUsuarios controleUsuarios = new ControleUsuarios();
        Produto produto = new Produto();
        Usuario usuario = new Usuario();
        Usuario usuarioLogado = null;
        Usuario autorizador = null;
        String nome = new String();
        String login = new String();
        String senha = new String();
        String resposta = new String();
        int opcao = 0;
        int opcaoMenu = 0;
        int quantidade = 0;
        boolean valido = false;
        boolean autorizado = false;

        //usuários de demonstração (um de cada perfil) para os testes
        controleUsuarios.adicionarUsuario(new Usuario("Administrador Padrão", "admin", "admin123", 1,
                "Qual é o nome do sistema?", "estoque"));
        controleUsuarios.adicionarUsuario(new Usuario("Operador Padrão", "operador", "oper123", 2,
                "Qual é o nome do sistema?", "estoque"));

        //produtos de demonstração
        estoque.adicionarProduto(new Produto("Arroz 5kg", 20, 18.0f, 25.9f));
        estoque.adicionarProduto(new Produto("Feijão Carioca", 50, 6.5f, 9.9f));
        estoque.adicionarProduto(new Produto("Café 500g", 15, 12.0f, 17.5f));

        do {
            System.out.println("\n======= SISTEMA DE GERENCIAMENTO DE ESTOQUE =======");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastro de Usuário");
            System.out.println("3 - Recuperação de Senha");
            System.out.println("0 - Sair");
            System.out.println("Digite uma opção:");
            try {
                opcao = ler.nextInt();
            } catch (Exception e) {
                opcao = -1; //letra ou símbolo: cai no default
            }
            ler.nextLine(); //descarta o restante da linha digitada

            switch (opcao) {
                case 1:
                    System.out.println("\n--- Login ---");
                    System.out.println("Digite o login:");
                    login = ler.nextLine().trim();
                    System.out.println("Digite a senha:");
                    senha = ler.nextLine();
                    usuarioLogado = controleUsuarios.autenticar(login, senha);
                    if (usuarioLogado == null) {
                        System.out.println("Login ou senha inválidos!");
                    } else {
                        System.out.println("\nBem-vindo(a), " + usuarioLogado.getNome() +
                        "! Perfil: " + usuarioLogado.getDescricaoTipo());
                        do {
                            System.out.println("\n======= MENU - " + usuarioLogado.getDescricaoTipo().toUpperCase() +
                            " =======");
                            System.out.println("Usuário: " + usuarioLogado.getNome() +
                            " (" + usuarioLogado.getLogin() + ")");
                            if (usuarioLogado.getTipo() == 1)
                                System.out.println("1 - Adicionar Produto");
                            System.out.println("2 - Atualizar Quantidade (entrada/saída)");
                            System.out.println("3 - Buscar Produto");
                            System.out.println("4 - Listar Produtos");
                            if (usuarioLogado.getTipo() == 1)
                                System.out.println("5 - Listar Usuários");
                            System.out.println("0 - Logout");
                            System.out.println("Digite uma opção:");
                            try {
                                opcaoMenu = ler.nextInt();
                            } catch (Exception e) {
                                opcaoMenu = -1;
                            }
                            ler.nextLine();

                            switch (opcaoMenu) {
                                case 1:
                                    if (usuarioLogado.getTipo() != 1) {
                                        System.out.println("Acesso negado! Somente o Administrador pode cadastrar produtos.");
                                    } else {
                                        System.out.println("\n--- Adicionar Produto ---");
                                        produto = new Produto(); //um objeto novo para cada cadastro
                                        do {
                                            System.out.println("Digite o nome do produto:");
                                            produto.setNome(ler.nextLine().trim());
                                            if (produto.getNome().length() < 3)
                                                System.out.println("Nome inválido! Digite pelo menos 3 caracteres.");
                                            else if (estoque.buscarProduto(produto.getNome()) != null)
                                                System.out.println("Produto já cadastrado! Use a opção 2 para atualizar a quantidade.");
                                        } while (produto.getNome().length() < 3 ||
                                        estoque.buscarProduto(produto.getNome()) != null);

                                        valido = false;
                                        do {
                                            System.out.println("Digite a quantidade:");
                                            try {
                                                produto.setQuantidade(ler.nextInt());
                                                if (produto.getQuantidade() >= 0)
                                                    valido = true;
                                                else
                                                    System.out.println("Quantidade inválida! Digite um valor maior ou igual a zero.");
                                            } catch (Exception e) {
                                                System.out.println("Erro: digite um número inteiro para a quantidade.");
                                            }
                                            ler.nextLine(); //descarta o restante da linha digitada
                                        } while (!valido);

                                        valido = false;
                                        do {
                                            System.out.println("Digite o preço de compra/produção:");
                                            try {
                                                produto.setPreco(ler.nextFloat());
                                                if (produto.getPreco() > 0)
                                                    valido = true;
                                                else
                                                    System.out.println("Preço inválido! Digite um valor maior que zero.");
                                            } catch (Exception e) {
                                                System.out.println("Erro: digite um número válido para o preço de compra.");
                                            }
                                            ler.nextLine();
                                        } while (!valido);

                                        valido = false;
                                        do {
                                            System.out.println("Digite o preço de venda:");
                                            try {
                                                produto.setPrecoVenda(ler.nextFloat());
                                                if (produto.getPrecoVenda() > 0)
                                                    valido = true;
                                                else
                                                    System.out.println("Preço inválido! Digite um valor maior que zero.");
                                            } catch (Exception e) {
                                                System.out.println("Erro: digite um número válido para o preço de venda.");
                                            }
                                            ler.nextLine();
                                        } while (!valido);

                                        estoque.adicionarProduto(produto);
                                        System.out.println("Produto adicionado com sucesso!");
                                    }
                                    break;
                                case 2:
                                    System.out.println("\n--- Atualizar Quantidade ---");
                                    System.out.println("Digite o nome do produto:");
                                    nome = ler.nextLine().trim();
                                    if (estoque.buscarProduto(nome) == null) {
                                        System.out.println("Produto não encontrado.");
                                    } else {
                                        valido = false;
                                        do {
                                            System.out.println("Digite a quantidade (positiva para entrada, negativa para saída):");
                                            try {
                                                quantidade = ler.nextInt();
                                                if (quantidade != 0)
                                                    valido = true;
                                                else
                                                    System.out.println("Quantidade inválida! Digite um valor diferente de zero.");
                                            } catch (Exception e) {
                                                System.out.println("Erro: digite um número inteiro para a quantidade.");
                                            }
                                            ler.nextLine();
                                        } while (!valido);
                                        estoque.atualizarQuantidade(nome, quantidade);
                                    }
                                    break;
                                case 3:
                                    System.out.println("\n--- Buscar Produto ---");
                                    System.out.println("Digite o nome do produto:");
                                    nome = ler.nextLine().trim();
                                    produto = estoque.buscarProduto(nome);
                                    if (produto != null) {
                                        System.out.println("Produto encontrado: " + produto.mostrarDados());
                                    } else {
                                        System.out.println("Produto não encontrado.");
                                    }
                                    break;
                                case 4:
                                    System.out.println("\n--- Listar Produtos ---");
                                    estoque.listarProdutos();
                                    break;
                                case 5:
                                    if (usuarioLogado.getTipo() != 1) {
                                        System.out.println("Acesso negado! Somente o Administrador pode listar os usuários.");
                                    } else {
                                        controleUsuarios.listarUsuarios();
                                    }
                                    break;
                                case 0:
                                    System.out.println("Logout realizado. Até logo, " + usuarioLogado.getNome() + "!");
                                    break;
                                default:
                                    System.out.println("Opção inválida! Escolha uma das opções do menu.");
                            }
                        } while (opcaoMenu != 0);
                        usuarioLogado = null; //encerra a sessão
                    }
                    break;
                case 2:
                    System.out.println("\n--- Cadastro de Usuário ---");
                    usuario = new Usuario(); //um objeto novo para cada cadastro
                    valido = false;
                    do {
                        System.out.println("Tipo de usuário: 1 - Administrador | 2 - Operador");
                        System.out.println("Digite o tipo:");
                        try {
                            usuario.setTipo(ler.nextInt());
                            if (usuario.getTipo() == 1 || usuario.getTipo() == 2)
                                valido = true;
                            else
                                System.out.println("Tipo inválido! Digite 1 ou 2.");
                        } catch (Exception e) {
                            System.out.println("Erro: digite 1 ou 2.");
                        }
                        ler.nextLine();
                    } while (!valido);

                    //administrador só pode ser criado com a autorização de outro administrador
                    autorizado = true;
                    if (usuario.getTipo() == 1) {
                        System.out.println("Para cadastrar um Administrador é necessária a autorização de um administrador.");
                        System.out.println("Digite o login do administrador:");
                        login = ler.nextLine().trim();
                        System.out.println("Digite a senha do administrador:");
                        senha = ler.nextLine();
                        autorizador = controleUsuarios.autenticar(login, senha);
                        if (autorizador == null || autorizador.getTipo() != 1) {
                            System.out.println("Autorização negada! Cadastro cancelado.");
                            autorizado = false;
                        } else {
                            System.out.println("Autorização concedida por " + autorizador.getNome() + ".");
                        }
                    }

                    if (autorizado) {
                        do {
                            System.out.println("Digite o nome completo:");
                            usuario.setNome(ler.nextLine().trim());
                            if (usuario.getNome().length() < 3)
                                System.out.println("Nome inválido! Digite pelo menos 3 caracteres.");
                        } while (usuario.getNome().length() < 3);

                        do {
                            System.out.println("Digite o login (mínimo 3 caracteres, sem espaços):");
                            usuario.setLogin(ler.nextLine().trim());
                            if (usuario.getLogin().length() < 3 || usuario.getLogin().contains(" "))
                                System.out.println("Login inválido! Use pelo menos 3 caracteres e não use espaços.");
                            else if (controleUsuarios.buscarUsuario(usuario.getLogin()) != null)
                                System.out.println("Login já existe! Escolha outro login.");
                        } while (usuario.getLogin().length() < 3 || usuario.getLogin().contains(" ") ||
                        controleUsuarios.buscarUsuario(usuario.getLogin()) != null);

                        do {
                            System.out.println("Digite a senha (de 5 a 15 caracteres):");
                            usuario.setSenha(ler.nextLine());
                            if (usuario.getSenha().length() < 5 || usuario.getSenha().length() > 15)
                                System.out.println("Senha inválida! A senha deve ter de 5 a 15 caracteres.");
                        } while (usuario.getSenha().length() < 5 || usuario.getSenha().length() > 15);

                        do {
                            System.out.println("Digite uma pergunta de segurança (ex.: Qual o nome do seu primeiro animal?):");
                            usuario.setPerguntaSeguranca(ler.nextLine().trim());
                            if (usuario.getPerguntaSeguranca().length() < 5)
                                System.out.println("Pergunta inválida! Digite pelo menos 5 caracteres.");
                        } while (usuario.getPerguntaSeguranca().length() < 5);

                        do {
                            System.out.println("Digite a resposta da pergunta de segurança:");
                            usuario.setRespostaSeguranca(ler.nextLine().trim());
                            if (usuario.getRespostaSeguranca().length() < 2)
                                System.out.println("Resposta inválida! Digite pelo menos 2 caracteres.");
                        } while (usuario.getRespostaSeguranca().length() < 2);

                        controleUsuarios.adicionarUsuario(usuario);
                        System.out.println("Usuário cadastrado com sucesso! Perfil: " + usuario.getDescricaoTipo());
                    }
                    break;
                case 3:
                    System.out.println("\n--- Recuperação de Senha ---");
                    System.out.println("Digite o login:");
                    login = ler.nextLine().trim();
                    usuario = controleUsuarios.buscarUsuario(login);
                    if (usuario == null) {
                        System.out.println("Usuário não encontrado.");
                    } else {
                        System.out.println("Pergunta de segurança: " + usuario.getPerguntaSeguranca());
                        System.out.println("Digite a resposta:");
                        resposta = ler.nextLine().trim();
                        if (usuario.getRespostaSeguranca().equalsIgnoreCase(resposta)) {
                            do {
                                System.out.println("Digite a nova senha (de 5 a 15 caracteres):");
                                senha = ler.nextLine();
                                if (senha.length() < 5 || senha.length() > 15)
                                    System.out.println("Senha inválida! A senha deve ter de 5 a 15 caracteres.");
                            } while (senha.length() < 5 || senha.length() > 15);
                            usuario.setSenha(senha);
                            System.out.println("Senha redefinida com sucesso! Faça login com a nova senha.");
                        } else {
                            System.out.println("Resposta incorreta! Não foi possível redefinir a senha.");
                        }
                    }
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Escolha uma das opções do menu.");
            }
        } while (opcao != 0);

        ler.close();
    }
}
