public class Usuario {
    private String nome;
    private String login;
    private String senha;
    private int tipo; //1 - Administrador, 2 - Operador
    private String perguntaSeguranca;
    private String respostaSeguranca;

    public Usuario() {
        nome = new String();
        login = new String();
        senha = new String();
        tipo = 2; //operador
        perguntaSeguranca = new String();
        respostaSeguranca = new String();
    }

    public Usuario(String nome, String login, String senha, int tipo,
            String perguntaSeguranca, String respostaSeguranca) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
        this.perguntaSeguranca = perguntaSeguranca;
        this.respostaSeguranca = respostaSeguranca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getPerguntaSeguranca() {
        return perguntaSeguranca;
    }

    public void setPerguntaSeguranca(String perguntaSeguranca) {
        this.perguntaSeguranca = perguntaSeguranca;
    }

    public String getRespostaSeguranca() {
        return respostaSeguranca;
    }

    public void setRespostaSeguranca(String respostaSeguranca) {
        this.respostaSeguranca = respostaSeguranca;
    }

    public String getDescricaoTipo() {
        if (tipo == 1)
            return "Administrador";
        else
            return "Operador";
    }

    public String mostrarDados() {
        return "Nome: " + nome +
        ", Login: " + login +
        ", Perfil: " + getDescricaoTipo();
    }
}
