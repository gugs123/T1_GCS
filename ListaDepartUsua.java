import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.stream.Stream;

public class ListaDepartUsua {

    //Tal classe guarda todos as informacoes sobre Departamento, Usuario e PedidoAquisicao nesses Arraylists
    private ArrayList<Departamento> listaDepartamentos;
    private ArrayList<Usuario> listaUsuarios;
    private ArrayList<PedidoAquisicao> listaPedidoAquisicao;

    public ListaDepartUsua() {
      this.listaUsuarios = new ArrayList<>(15);
       preencheUsuarios();
        this.listaDepartamentos = new ArrayList<>();
       preencheDepartamentos();
        this.listaPedidoAquisicao = new ArrayList<>();
    }
    
    // faz a média dos ultimos 30 dias baseadas nas referencias de LocalDate;
     
    public double utlimostrinta(){
    
        ArrayList<PedidoAquisicao>list=new ArrayList<PedidoAquisicao>();
            
       int count=0;
        for(PedidoAquisicao i:listaPedidoAquisicao){
           
            LocalDate dateString = i.getDataDoPedido();
            LocalDate startDate = dateString;
            LocalDate endtDate = LocalDate.now();
            Long range = ChronoUnit.DAYS.between(startDate, endtDate);
              if(range<=30){
             count++;
              }
        }
        double valortotalMes=0;
        for(PedidoAquisicao L: list){
          valortotalMes+=L.getValorTotalPedido();
      
        }
        valortotalMes=valortotalMes/count;
    return valortotalMes;
    }
     public int ultimos30dias(){

        ArrayList<PedidoAquisicao>list=new ArrayList<PedidoAquisicao>();
        int count=0;
        for(PedidoAquisicao l:listaPedidoAquisicao){
            LocalDate dateString = l.getDataDoPedido();
            LocalDate startDate = dateString;
            LocalDate endtDate = LocalDate.now();
            Long range = ChronoUnit.DAYS.between(startDate, endtDate);
         if(range<=30){
             count++;
         }
         
        }
        return count;   
    }
   

    //Serve para iniciar os usuarios
    private void preencheUsuarios(){

        Usuario user0 = new Usuario("Gustavo", "Melo", "0", true, "RH");
        listaUsuarios.add(user0);
        Usuario user1 = new Usuario("Mateus", "Souza", "1", false, "Producao");
        listaUsuarios.add(user1);
        Usuario user2 = new Usuario("Leonardo", "Santos", "2", true, "TI");
        listaUsuarios.add(user2);
        Usuario user3 = new Usuario("Angelo", "Escolto", "3", false, "TI");
        listaUsuarios.add(user3);
        Usuario user4 = new Usuario("Silvio", "Luiz", "4", false, "Manutencao");
        listaUsuarios.add(user4);
        Usuario user5 = new Usuario("Alberto", "Carvalho", "5", false, "Engenharia");
        listaUsuarios.add(user5);
        Usuario user6 = new Usuario("Maria", "Silva", "6", false, "Producao");
        listaUsuarios.add(user6);
        Usuario user7 = new Usuario("Carla", "Perez", "7", false, "Producao");
        listaUsuarios.add(user7);
        Usuario user8 = new Usuario("Julio", "Andrade", "8", false, "TI");
        listaUsuarios.add(user8);
        Usuario user9 = new Usuario("Lucas", "Silveira", "9", false, "RH");
        listaUsuarios.add(user9);
        Usuario user10 = new Usuario("Marcos", "Pereira", "10", false, "Engenharia");
        listaUsuarios.add(user10);
        Usuario user11 = new Usuario("Fabiola", "Borba", "11", false, "Producao");
        listaUsuarios.add(user11);
        Usuario user12 = new Usuario("Jefersson", "Castro", "12", false, "Manutencao");
        listaUsuarios.add(user12);
        Usuario user13 = new Usuario("Nilda", "Silveira", "13", false, "RH");
        listaUsuarios.add(user13);
        Usuario user14 = new Usuario("Jairo", "Bastos", "14", false, "Producao");
        listaUsuarios.add(user14);
    }

    //Serve para iniciar os departamentos
    private void preencheDepartamentos() {

        Departamento depart1 = new Departamento("RH",5600.50);
        depart1.preencheListaUsuarios(this.listaUsuarios);
        this.listaDepartamentos.add(depart1);

        Departamento depart2 = new Departamento("Producao",7800.00);
        depart2.preencheListaUsuarios(this.listaUsuarios);
        this.listaDepartamentos.add(depart2);

        Departamento depart3 = new Departamento("TI",12100.75);
        depart3.preencheListaUsuarios(this.listaUsuarios);
        this.listaDepartamentos.add(depart3);

        Departamento depart4 = new Departamento("Manutencao",6950.20);
        depart4.preencheListaUsuarios(this.listaUsuarios);
        this.listaDepartamentos.add(depart4);

        Departamento depart5 = new Departamento("Engenharia", 10950.00);
        depart5.preencheListaUsuarios(this.listaUsuarios);
        this.listaDepartamentos.add(depart5);
    }

    //Serve para realizar uma busca atraves da variavel matricula
    public Usuario buscaPorMatricula(String matricula) {

        int qtd_users = listaUsuarios.size();
        Usuario user = null;
        for(int i = 0; i < qtd_users; i++){
            user = listaUsuarios.get(i);
            if(user.getMatricula().equals(matricula))
            {
                return user;
            }
        }
        return null;
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return this.listaUsuarios;
    }

    public ArrayList<Departamento> getListaDepartamentos() {
        return this.listaDepartamentos;
    }

    //Serve para buscar um Departamento(classe) pelo nome dele
    public Departamento buscaPorDepartamento(String nomeDepartamento) {

        int qtd_users = listaDepartamentos.size();
        Departamento auxDepart = null;
        for(int i = 0; i < qtd_users; i++){
            auxDepart = listaDepartamentos.get(i);
            if(auxDepart.getNomeDepartamento().equals(nomeDepartamento))
            {
                return auxDepart;
            }
        }

        //Se nao tiver um departamento com o nome passado
        return null;
    }

    public void adicionaPedidoAquisicao(PedidoAquisicao pedido) {
        this.listaPedidoAquisicao.add(pedido);
    }

    public PedidoAquisicao getPedidoAquisicao(int idPedido){
        return listaPedidoAquisicao.get(idPedido);
    }

    public ArrayList<PedidoAquisicao> getListaPedidoAquisicao() {
        return this.listaPedidoAquisicao;
    }

    public int getListaPedidoAquisicaoSize(){
        return this.listaPedidoAquisicao.size();
    }
 
    public String maiorPedidoListado(){
        String aux="";
        double maior=0;
        for(int i=0;i<this.getListaPedidoAquisicaoSize();i++){
        if(getPedidoAquisicao(i).getValorTotalPedido()>maior){
        }
        maior=getPedidoAquisicao(i).getValorTotalPedido();
        PedidoAquisicao compra = getPedidoAquisicao(i);
        aux +="\nNUMERO IDENTIFICADOR: "; aux+=i;
        aux += ". Data de solicitaçao: "; aux += compra.getDataDoPedido();
        if(compra.getQtdItens() > 3) {
            aux += "; "; aux += compra.getQtdItens(); aux += " itens: ";
            aux += compra.getItensStringShortLimitado(3);
            aux += ", entre outros";
        } else {
            aux += "; Itens: "; aux += compra.getItensStringShort();
        }
        aux += "; Valor total: "; aux += compra.getValorTotalPedido();
        aux += "; Status: "; aux += compra.getStatusString();
        if((compra.getStatusDoPedido() != 1) && compra.getDataDeConclusao() != null) {
            aux += "; Data de conclusao: "; aux += compra.getDataDeConclusao();
        }
        aux += ".";
}

return aux;


 }

    public ArrayList<PedidoAquisicao> getListaPedidosFunc(Usuario usuarioLogado){
        ArrayList<PedidoAquisicao> pedidos = new ArrayList<>();
        for(int i = 0; i < this.getListaPedidoAquisicaoSize(); i++){
            if(getPedidoAquisicao(i).getUsuarioSolicitante() == usuarioLogado){
                pedidos.add(getPedidoAquisicao(i));
            }
        }
        return pedidos;
    }
    
    public String getListaPedidosFuncToString(Usuario usuarioLogado){
        String lista = "";

        for(int i = 0; i < this.getListaPedidoAquisicaoSize(); i++){
            if(getPedidoAquisicao(i).getUsuarioSolicitante() == usuarioLogado){
                PedidoAquisicao teste = getPedidoAquisicao(i);
                teste.setIdPedido(i);

                lista += "\nNUMERO ID: "; lista += i;
                lista += ". Solicitacao no dia "; lista += teste.getDataDoPedido();
                lista += " por "; lista += teste.getUsuarioSolicitante().getNome(); lista += " "; lista += teste.getUsuarioSolicitante().getInicialSobrenome();
                if(teste.getQtdItens() > 3) {
                    lista += "; "; lista += teste.getQtdItens(); lista += " itens: ";
                    lista += teste.getItensStringShortLimitado(3);
                    lista += ", entre outros";
                } else {
                    lista += "; Itens: "; lista += teste.getItensStringShort();
                }
                lista += "; Valor total: "; lista += teste.getValorTotalPedido();
                lista += "; Status: "; lista += teste.getStatusString();
                if((teste.getStatusDoPedido() != 1) && teste.getDataDeConclusao() != null) {
                    lista += "; Data de conclusao: "; lista += teste.getDataDeConclusao();
                }
                lista += ".";
            }
        }
        return lista;
    }
  
    public String getListaPedidosStatus(int status){
        String lista = "";

        for(int i = 0; i < this.getListaPedidoAquisicaoSize(); i++){
            if(getPedidoAquisicao(i).getStatusDoPedido() == status){
                PedidoAquisicao teste = getPedidoAquisicao(i);
                lista += "\nNUMERO ID: "; lista += i;
                lista += ". Solicitaçao no dia "; lista += teste.getDataDoPedido();
                lista += " por "; lista += teste.getUsuarioSolicitante().getNome(); lista += " "; lista += teste.getUsuarioSolicitante().getInicialSobrenome();
                if(teste.getQtdItens() > 3) {
                    lista += "; "; lista += teste.getQtdItens(); lista += " itens: ";
                    lista += teste.getItensStringShortLimitado(3);
                    lista += ", entre outros";
                } else {
                    lista += "; Itens: "; lista += teste.getItensStringShort();
                }
                lista += "; Valor total: "; lista += teste.getValorTotalPedido();
                lista += "; Status: "; lista += teste.getStatusString();
                if((teste.getStatusDoPedido() != 1) && teste.getDataDeConclusao() != null) {
                    lista += "; Data de conclusao: "; lista += teste.getDataDeConclusao();
                }
                lista += ".";
            }
        }
        return lista;
    }
}
