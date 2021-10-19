import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PedidoAquisicao {

    private Usuario usuarioSolicitante;
    private Departamento departamentoSolicitante;
    private LocalDate dataDoPedido;
    private LocalDate dataDeConclusao;
    private ArrayList<Item> listaItens;
    private double valorTotalPedido;

    private int idPedido;
    private static int cont = 0;

    private int statusDoPedido;
    // statusDoPedido = 0 --> reprovado
    // statusDoPedido = 1 --> aberto
    // statusDoPedido = 2 --> aprovado
    // statusDoPedido = 3 --> concluído

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

    public void setStatusDoPedido(int statusDoPedido) {
        //Nao tem como passar 1 visto que ele ganha o status de aberto logo na sua criacao
        //Nao tem como passar 3, porque sua conclusao so vai ser dada quando for entregue os itens
        if (statusDoPedido == 0 || statusDoPedido == 2) {
            this.statusDoPedido = statusDoPedido;
        }
    }

    //Nesse metodo vai ser determinado a data de conclusa (assim como a passagem do status para concluido), baseado na
    //data de aprovacao, visto que demorar uma semana para chegar os intens solicitados depois de aprovado
    public boolean setStatusDoPedido(int statusDoPedido, String dataAprovadoString) {

        boolean concluidoSucesso = false;
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataAprov = LocalDate.parse(dataAprovadoString,formatoData);

        //Nao tem como passar 1 visto que ele ganha o status de aberto logo na sua criacao
        //Nao tem como passar 3, porque sua conclusao so vai ser dada quando for entregue os itens
        if (statusDoPedido == 0) {
            this.statusDoPedido = statusDoPedido;
        }
        else if (statusDoPedido == 2) {
            if (dataAprov.isAfter(dataDoPedido)) {
                this.statusDoPedido = 3;
                this.dataDeConclusao = dataAprov.plusDays(7);
                concluidoSucesso = true;
            }
        }

        return concluidoSucesso;
    }

    private double calculaValorTotalDePedido(){

        double valorTotal = 0;
        for (Item produto : this.listaItens) {
            valorTotal+= produto.getTotalDoItem();
        }
        return valorTotal;
    }
}
