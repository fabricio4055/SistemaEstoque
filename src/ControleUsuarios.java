
import java.util.ArrayList;

public class ControleUsuarios {
    private ArrayList<Usuario> listaUsuarios; //usuários guardados em memória

    public ControleUsuarios() {
        listaUsuarios = new ArrayList<>();
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public void adicionarUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
    }

    //retorna o usuário com o login informado ou null se não existir
    public Usuario buscarUsuario(String login) {
        for (Usuario usu : listaUsuarios) {
            if (usu.getLogin().equalsIgnoreCase(login)) {
                return usu;
            }
        }
        return null;
    }

    //retorna o usuário se login e senha estiverem corretos ou null se não estiverem
    public Usuario autenticar(String login, String senha) {
        Usuario usuario = buscarUsuario(login);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return usuario;
        }
        return null;
    }

    public void listarUsuarios() {
        if (listaUsuarios.size() > 0) {
            System.out.println("\n--- Usuários cadastrados ---");
            for (Usuario usu : listaUsuarios) {
                System.out.println(usu.mostrarDados());
            }
        } else {
            System.out.println("Não existem usuários cadastrados!");
        }
    }
}
