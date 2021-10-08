import java.util.ArrayList;

public class PedidoAquisicao {
    private Usuario usuarioSolicitante;

    private Departamento departamentoSolicitante;
    private String dataDoPedido;
    private String dataDeConclusao;
    private String statusDoPedido;
    private ArrayList<Item> listaItens;
    private int valorTotalPedido;

    public PedidoAquisicao(Usuario usuarioSolicitante, Departamento departamentoSolicitante, String dataDoPedido, String
            dataDeConclusao, String statusDoPedido, ArrayList<Item> listaItens, int valorTotalPedido) {
        this.usuarioSolicitante = usuarioSolicitante;
        this.departamentoSolicitante = departamentoSolicitante;
        this.dataDoPedido = dataDoPedido;
        this.dataDeConclusao = dataDeConclusao;
        this.statusDoPedido = statusDoPedido;
        this.listaItens = listaItens;
        this.valorTotalPedido = calculaValorTotalDePedido();
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

    public String getStatusDoPedido() {
        return statusDoPedido;
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

    public void setStatusDoPedido(String statusDoPedido) {
        this.statusDoPedido = statusDoPedido;
    }

    private int calculaValorTotalDePedido(){
        //todo
        return 0;
    }
}