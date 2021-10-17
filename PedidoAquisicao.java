import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PedidoAquisicao {

    private Usuario usuarioSolicitante;
    private static int cont = 0;
    private Departamento departamentoSolicitante;
    private LocalDate dataDoPedido;
    private LocalDate dataDeConclusao;
    private int statusDoPedido;

    private int idPedido;
    // status 0 = reprovado
    // status 1 = aberto
    // status 2 = aprovado
    // status 3 = concluído

    private ArrayList<Item> listaItens;
    private double valorTotalPedido;

    public PedidoAquisicao(Usuario usuarioSolicitante, Departamento departamentoSolicitante, LocalDate dataDoPedido, ArrayList<Item> listaItens) {
        this.usuarioSolicitante = usuarioSolicitante;
        this.departamentoSolicitante = departamentoSolicitante;
        this.dataDoPedido = dataDoPedido;
        this.statusDoPedido = 1;
        this.listaItens = listaItens;
        this.valorTotalPedido = calculaValorTotalDePedido();

        //Incrementa o contador
        this.idPedido = cont;
        cont++;
    }

    public Usuario getUsuarioSolicitante() {
        return usuarioSolicitante;
    }

    public Departamento getDepartamentoSolicitante() {
        return departamentoSolicitante;
    }

    public LocalDate getDataDoPedido() {
        return dataDoPedido;
    }

    public LocalDate getDataDeConclusao() {
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

    public void setDataDoPedido(String dataDoPedidoString) {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataDoPedido = LocalDate.parse(dataDoPedidoString,formatoData);
    }

    public void setDataDeConclusao(String dataConclusaoString) {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataDeConclusao = LocalDate.parse(dataConclusaoString,formatoData);
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
