# Sistema de Gerenciamento de Estoque

Projeto Java desenvolvido para a disciplina de **Linguagem de Programação I**, como parte do Projeto de Extensão (Curricularização), do curso de Informática para Negócios da Fatec São José do Rio Preto.

O sistema funciona no **terminal**, com menus em texto. Ele tem login e logout, dois perfis de usuário (Administrador e Operador), cadastro de usuários, recuperação de senha e controle de estoque (cadastro, entrada, saída, busca e listagem de produtos).

Os dados ficam guardados em memória (`ArrayList`) enquanto o programa está aberto. **Não é necessário banco de dados nem biblioteca externa.**

## Classe principal

`src/App.java` (contém o método `main`).

## Estrutura do projeto

```
SistemaEstoque/
├── README.md
├── .gitignore               (ignora a pasta bin, gerada ao compilar)
├── .vscode/
│   └── settings.json        (configuração padrão de projeto Java do VS Code)
└── src/
    ├── App.java             (menus, leitura e validação dos dados)
    ├── Produto.java         (dados de um produto)
    ├── Estoque.java         (lista de produtos e operações do estoque)
    ├── Usuario.java         (dados de um usuário)
    └── ControleUsuarios.java (lista de usuários, busca e autenticação)
```

## Como executar

### Pelo VS Code (terminal integrado)

1. Instale a extensão **Extension Pack for Java** e um JDK (versão 8 ou superior).
2. Abra a pasta `SistemaEstoque` (Arquivo > Abrir Pasta).
3. Abra `src/App.java` e clique em **Run** acima do método `main`. O programa roda no terminal integrado.

Também é possível compilar e executar pelo terminal integrado (Terminal > Novo Terminal):

```bash
javac -encoding UTF-8 -d bin src/*.java
java -cp bin App
```

No Prompt de Comando do Windows, use `src\*.java` no lugar de `src/*.java`.

### Baixando pelo GitHub

```bash
git clone https://github.com/fabricio4055/SistemaEstoque.git
cd SistemaEstoque
javac -encoding UTF-8 -d bin src/*.java
java -cp bin App
```

## Credenciais de demonstração

O programa já inicia com dois usuários e três produtos de demonstração para facilitar os testes:

| Perfil | Login | Senha | Pergunta de segurança | Resposta |
|---|---|---|---|---|
| Administrador | `admin` | `admin123` | Qual é o nome do sistema? | `estoque` |
| Operador | `operador` | `oper123` | Qual é o nome do sistema? | `estoque` |

Produtos já cadastrados: `Arroz 5kg`, `Feijão Carioca` e `Café 500g`.

> São credenciais fictícias, apenas para demonstração.

## Menus

### Tela inicial

```
1 - Login
2 - Cadastro de Usuário
3 - Recuperação de Senha
0 - Sair
```

### Menu do Administrador

```
1 - Adicionar Produto
2 - Atualizar Quantidade (entrada/saída)
3 - Buscar Produto
4 - Listar Produtos
5 - Listar Usuários
0 - Logout
```

### Menu do Operador

```
2 - Atualizar Quantidade (entrada/saída)
3 - Buscar Produto
4 - Listar Produtos
0 - Logout
```

O Operador não vê as opções 1 e 5. Se digitar uma delas, o sistema responde **"Acesso negado!"**, porque a permissão também é verificada no código, não só no menu.

## Permissões

| Funcionalidade | Administrador | Operador |
|---|:---:|:---:|
| Adicionar produto | ✅ | ❌ |
| Entrada e saída de estoque | ✅ | ✅ |
| Buscar produto | ✅ | ✅ |
| Listar produtos | ✅ | ✅ |
| Listar usuários | ✅ | ❌ |
| Autorizar o cadastro de um novo Administrador | ✅ | ❌ |

## Como testar

### Administrador
1. Na tela inicial, escolha `1` e entre com `admin` / `admin123`.
2. Opção `1`: cadastre um produto (ex.: `Leite Integral`, quantidade `12`, preços `4,10` e `5,30`).
3. Opção `2`: digite o nome do produto e uma quantidade positiva (entrada) ou negativa (saída). Tente uma saída maior que o estoque: o sistema não permite.
4. Opções `3` e `4`: busque e liste os produtos.
5. Opção `5`: liste os usuários.
6. Opção `0`: faça logout.

### Operador
1. Entre com `operador` / `oper123`.
2. Veja que o menu não mostra as opções `1` e `5`. Digite `1` para conferir o "Acesso negado!".
3. Use as opções `2`, `3` e `4` (entrada/saída, busca e listagem).
4. Opção `0`: logout.

### Cadastro de usuário
1. Na tela inicial, escolha `2`.
2. **Operador:** escolha o tipo `2` e preencha nome, login, senha, pergunta e resposta de segurança.
3. **Administrador:** escolha o tipo `1`. O sistema pede o login e a senha de um administrador já cadastrado (ex.: `admin` / `admin123`). Sem essa autorização, o cadastro é cancelado.
4. Logins repetidos não são aceitos (sem diferenciar maiúsculas e minúsculas).

### Recuperação de senha
1. Na tela inicial, escolha `3` e digite o login (ex.: `operador`).
2. Responda à pergunta de segurança (`estoque`).
3. Digite a nova senha (de 5 a 15 caracteres) e faça login com ela.

## Regras de validação

| Dado | Regra |
|---|---|
| Nome do produto | Mínimo de 3 caracteres; não pode repetir |
| Quantidade inicial | Número inteiro, maior ou igual a zero |
| Preços | Número maior que zero |
| Entrada/saída | Número inteiro diferente de zero; a saída não pode deixar o estoque negativo, e a entrada não pode ultrapassar o limite do tipo `int` (2.147.483.647) |
| Nome do usuário | Mínimo de 3 caracteres |
| Login | Mínimo de 3 caracteres, sem espaços, não pode repetir |
| Senha | De 5 a 15 caracteres |
| Pergunta / resposta de segurança | Mínimo de 5 / 2 caracteres |

Entradas inválidas mostram uma mensagem e o sistema pede o dado novamente.

## Limitações

- **Interface textual:** toda a interação acontece no terminal, por menus em texto. Não há interface gráfica (ela não faz parte do escopo de LP1).
- **Dados em memória:** usuários, produtos e senhas alteradas ficam salvos apenas enquanto o programa está aberto. Ao sair, tudo volta aos dados de demonstração.
- **Senha visível:** a senha aparece na tela enquanto é digitada.
- **Preços com casas decimais:** o separador segue o idioma do sistema operacional. Em um computador em português (Brasil), use **vírgula** (ex.: `10,50`); em inglês, **ponto** (ex.: `10.50`). Se o formato estiver errado, o programa avisa e pede o valor de novo.
- Se os acentos aparecerem errados no Prompt de Comando do Windows, use o terminal integrado do VS Code ou execute `chcp 65001` antes de rodar o programa.
