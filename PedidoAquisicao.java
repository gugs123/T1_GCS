import java.util.ArrayList;

public class PedidoAquisicao {
    private Usuario usuarioSolicitante;

    private static int cont = 0;
    private Departamento departamentoSolicitante;
    private String dataDoPedido;
    private String dataDeConclusao;
    private int statusDoPedido;
    private int idPedido;
    // status 0 = reprovado
    // status 1 = aberto
    // status 2 = aprovado
    // status 3 = concluído
    private ArrayList<Item> listaItens;
    private double valorTotalPedido;

    public PedidoAquisicao(Usuario usuarioSolicitante, Departamento departamentoSolicitante, String dataDoPedido, ArrayList<Item> listaItens) {
        this.usuarioSolicitante = usuarioSolicitante;
        this.departamentoSolicitante = departamentoSolicitante;
        this.dataDoPedido = dataDoPedido;
        this.dataDeConclusao = "";
        this.statusDoPedido = 1;
        this.listaItens = listaItens;
        this.valorTotalPedido = calculaValorTotalDePedido();
        this.idPedido = cont;
    }

    public Usuario getUsuarioSolicitante() {
        return usuarioSolicitante;
    }

    public Departamento getDepartamentoSolicitante() {
        return departamentoSolicitante;
    }

    public String getDataDoPedido() {
        return dataDoPedido;
    }

    public String getDataDeConclusao() {
        return dataDeConclusao;
    }

    public int getStatusDoPedido() {
        return statusDoPedido;
    }

    public double getValorTotalPedido(){
     return valorTotalPedido;
    }

    public void setListaItens(ArrayList<Item> listaItens) {
        this.listaItens = listaItens;
    }

    public void setUsuarioSolicitante(Usuario usuarioSolicitante) {
        this.usuarioSolicitante = usuarioSolicitante;
    }

    public void setDepartamentoSolicitante(Departamento departamentoSolicitante) {
        this.departamentoSolicitante = departamentoSolicitante;
    }

    public void setDataDoPedido(String dataDoPedido) {
        this.dataDoPedido = dataDoPedido;
    }

    public void setDataDeConclusao(String dataDeConclusao) {
        this.dataDeConclusao = dataDeConclusao;
    }

    public void setStatusDoPedido(int statusDoPedido) {
        this.statusDoPedido = statusDoPedido;
    }

    private double calculaValorTotalDePedido(){

        double valorTotal = 0;
        for (Item produto : this.listaItens) {
            valorTotal+= produto.getTotalDoItem();
        }
        return valorTotal;
    }
}
