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
    // status 0 = reprovado
    // status 1 = aberto
    // status 2 = aprovado
    // status 3 = concluído
    // status 4 = deletado
    private int idPedido;

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
        if(usuarioSolicitante == null) System.out.println("");
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

    public String getStatusString(){
        if(statusDoPedido == 0) return "foi reprovado";
        else if(statusDoPedido == 1) return "está em aberto";
        else if(statusDoPedido == 2) return "foi aprovado";
        else if(statusDoPedido == 3) return "foi concluído";
        else return "pedido não entrado";
    }

    public String getItensString(){
        String itensString = "";
        for(int i = 0; i < listaItens.size(); i++){
            if(i == 0) {
                itensString += i;
                itensString += ". ";
                itensString += listaItens.get(i).getDescricaoItem();
                itensString += "; quantidade: ";
                itensString += listaItens.get(i).getQuantidade();
                itensString += "; valor unitário: ";
                itensString += listaItens.get(i).getValorUnitario();
            }
            else {
                itensString += "\n";
                itensString += i;
                itensString += ". ";
                itensString += listaItens.get(i).getDescricaoItem();
                itensString += "; quantidade: ";
                itensString += listaItens.get(i).getQuantidade();
                itensString += "; valor unitário: ";
                itensString += listaItens.get(i).getValorUnitario();
            }
        }
        return itensString;
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

    public String pedidoToString(int idPedido){
        if(getDataDeConclusao() != null){
            return "PEDIDO NÚMERO " + idPedido 
            + "\nSOLICITADO POR " + getUsuarioSolicitante().getNome() + " " + getUsuarioSolicitante().getInicialSobrenome()
            + "\nDO DEPARTAMENTO " + getDepartamentoSolicitante().getNomeDepartamento()
            + "\nNO DIA " + getDataDoPedido()
            + "\nO PEDIDO " + getStatusString().toUpperCase()
            + "\nDATA DE CONCLUSÃO: " + getDataDeConclusao()
            + "\nITENS INCLUSOS: " + "\n" + getItensString();
        }
        else{
            return "PEDIDO NÚMERO " + idPedido 
            + "\nSOLICITADO POR " + getUsuarioSolicitante().getNome() + " " + getUsuarioSolicitante().getInicialSobrenome()
            + "\nDO DEPARTAMENTO " + getDepartamentoSolicitante().getNomeDepartamento()
            + "\nNO DIA " + getDataDoPedido()
            + "\nO PEDIDO " + getStatusString().toUpperCase()
            + "\nITENS INCLUSOS: " + "\n" + getItensString();
        }
    }
}
