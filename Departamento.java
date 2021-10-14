import java.util.ArrayList;

public class Departamento {

    private String nomeDepartamento;
    private double valorLimitePedido;

    //Criado visando possiveis funcionalidades extras
    private ArrayList<Usuario> listaDeUsuarios;

    //Valor minimo para o limite do departamento eh 1000
    public Departamento(String nomeDepartamento, double valorLimitePedido) {
        this.nomeDepartamento = nomeDepartamento;
        this.listaDeUsuarios = new ArrayList<>();

        if (valorLimitePedido < 1000) {
            this.valorLimitePedido = 1000;
        }
        else {
            this.valorLimitePedido = valorLimitePedido;
        }
    }

    //Para setar um no valor limite
    public boolean setValorLimitePedido(double valor) {
        if (valor < 1000) {
            return false;
        }
        else {
            this.valorLimitePedido = valor;
            return true;
        }
    }

    //Serve para preencher a lista de usuario e devolve a contagem de quantos foram adcionados
    public int preencheListaUsuarios(ArrayList<Usuario> lista) {

        int contagem = 0;
        if (!lista.isEmpty()) {

            Usuario auxUsuario;
            for (int i = 0; i < lista.size(); i++) {
                auxUsuario = lista.get(i);
                if (auxUsuario.getDepartamento().equals(nomeDepartamento)) {
                    this.listaDeUsuarios.add(auxUsuario);
                    contagem++;
                }
            }
        }

        return contagem;
    }

    //Server para pegar um usuario que esta associada a esse departamento
    public Usuario usuarioPorMatricula(String matricula) {

        if (!this.listaDeUsuarios.isEmpty()) {
            for (Usuario auxUsuario : this.listaDeUsuarios) {
                if (auxUsuario.getMatricula().equals(matricula)) {
                    return auxUsuario;
                }
            }
        }
        //Se estiver vazio ou nao encontrar
        return null;
    }

    //Verifica se tem tal usuario com a matricula passada
    public boolean verificaUsuario(String matricula) {


        if (!this.listaDeUsuarios.isEmpty()) {
            for (Usuario auxUsuario : this.listaDeUsuarios) {
                if (auxUsuario.getMatricula().equals(matricula)) {
                    return true;
                }
            }
        }
        //Se estiver vazio ou nao encontrar
        return false;

    }

    public ArrayList<Usuario> getListaDeUsuarios() {
        return this.listaDeUsuarios;
    }
}
