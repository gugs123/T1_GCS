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

    public static void LimpaTela(){
        try
        {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else{
                System.out.print("\033[H\033[2J");  
                System.out.flush();
            }
        }
        catch (final Exception e )
        {}
    }  

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

    public ArrayList<Item> getListaItem(){
        return this.listaItens;
    }

    public String getStatusString(){
        if(statusDoPedido == 0) return "foi reprovado";
        else if(statusDoPedido == 1) return "esta em aberto";
        else if(statusDoPedido == 2) return "foi aprovado";
        else if(statusDoPedido == 3) return "foi concluido";
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
                itensString += "; valor unitario: ";
                itensString += listaItens.get(i).getValorUnitario();
            }
            else {
                itensString += "\n";
                itensString += i;
                itensString += ". ";
                itensString += listaItens.get(i).getDescricaoItem();
                itensString += "; quantidade: ";
                itensString += listaItens.get(i).getQuantidade();
                itensString += "; valor unitario: ";
                itensString += listaItens.get(i).getValorUnitario();
            }
        }
        return itensString;
    }

    public String getItensStringShort(){
        String itensString = "";
        for(int i = 0; i < listaItens.size(); i++){
            if(i == 0) {
                itensString += listaItens.get(i).getDescricaoItem();
            } else {
                itensString += ", ";
                itensString += listaItens.get(i).getDescricaoItem();
            }
        }
        return itensString;
    }

    public String getItensStringShortLimitado(int limite){
        String itensString = "";
        for(int i = 0; i < limite; i++){
            if(i == 0) {
                itensString += listaItens.get(i).getDescricaoItem();
            } else {
                itensString += ", ";
                itensString += listaItens.get(i).getDescricaoItem();
            }
        }
        return itensString;
    }

    public double getValorTotalPedido(){
        return valorTotalPedido;
    }

    public int getQtdItens(){
        return listaItens.size();
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
        try {
            DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataAprov = LocalDate.parse(dataAprovadoString, formatoData);

            //Nao tem como passar 1 visto que ele ganha o status de aberto logo na sua criacao
            //Nao tem como passar 3, porque sua conclusao so vai ser dada quando for entregue os itens
            if (statusDoPedido == 0) {
                this.statusDoPedido = statusDoPedido;
            } else if (statusDoPedido == 2) {
                if (dataAprov.isAfter(dataDoPedido) || dataAprov.isEqual(dataDoPedido)) {
                    this.statusDoPedido = 3;
                    this.dataDeConclusao = dataAprov.plusDays(7);
                    concluidoSucesso = true;
                }
            }
        }
        catch (Exception e1) {
            //catch vazio, visto que eh apenas usado para nao parar o codigo em si
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

    public String pedidoToString(int idPedido){
        if(getDataDeConclusao() != null){
            return "PEDIDO NUMERO " + idPedido 
            + "\nSOLICITADO POR " + getUsuarioSolicitante().getNome() + " " + getUsuarioSolicitante().getInicialSobrenome()
            + "\nDO DEPARTAMENTO " + getDepartamentoSolicitante().getNomeDepartamento()
            + "\nNO DIA " + getDataDoPedido()
            + "\nO PEDIDO " + getStatusString().toUpperCase()
            + "\nDATA DE CONCLUSAO: " + getDataDeConclusao()
            + "\nITENS INCLUSOS: " + "\n" + getItensString()
            + "\n.................................\nVALOR TOTAL: " + getValorTotalPedido()
            + "\n";
        }
        else{
            return "PEDIDO NUMERO " + idPedido
            + "\nSOLICITADO POR " + getUsuarioSolicitante().getNome() + " " + getUsuarioSolicitante().getInicialSobrenome()
            + "\nDO DEPARTAMENTO " + getDepartamentoSolicitante().getNomeDepartamento()
            + "\nNO DIA " + getDataDoPedido()
            + "\nO PEDIDO " + getStatusString().toUpperCase()
            + "\nITENS INCLUSOS: " + "\n" + getItensString()
            + "\n.................................\nVALOR TOTAL: " + getValorTotalPedido()
            + "\n";
        }
    }

    public void setIdPedido(int id) {
        this.idPedido = id;
    }

    public int getIdPedido() {
        return this.idPedido;
    }
}
