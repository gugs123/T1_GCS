import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.stream.Stream;
import java.util.Scanner;

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

    public String utlimostrinta(){

        DecimalFormat df = new DecimalFormat("#,###.00");

        ArrayList<PedidoAquisicao>list=new ArrayList<>();

        int count=0;
        for(PedidoAquisicao i:listaPedidoAquisicao){

            LocalDate dateString = i.getDataDoPedido();
            LocalDate startDate = dateString;
            LocalDate endtDate = LocalDate.now();
            Long range = ChronoUnit.DAYS.between(startDate, endtDate);
            if(range<=30){
                count++;
                list.add(i);
            }
        }
        double valortotalMes=0;
        if (!list.isEmpty()) {
            for (PedidoAquisicao L : list) {
                valortotalMes += L.getValorTotalPedido();

            }
            valortotalMes = valortotalMes / count;
        }

        return df.format(valortotalMes);
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

    //quantidade de pedidos concluidos no mes
    public String ContadorCategoriaConcluido(){
        DecimalFormat df = new DecimalFormat("#,###.00");

        double valorPedidos = 0.0;

        for(PedidoAquisicao l:listaPedidoAquisicao){
            int status=l.getStatusDoPedido();
            LocalDate dateString = l.getDataDoPedido();
            LocalDate startDate = dateString;
            LocalDate endtDate = LocalDate.now();
            Long range = ChronoUnit.DAYS.between(startDate, endtDate);
            if(range<=30&&status==3){
                valorPedidos+= l.getValorTotalPedido();
            }}
        if (valorPedidos == 0) {
            return "0";
        }
        return df.format(valorPedidos);
    }

    //quantidade de pedidos aprovados no mes
    public String ContadorCategoriaComprovada(){
        DecimalFormat df = new DecimalFormat("#,###.00");

        double valorPedidos = 0.0;
        for(PedidoAquisicao l:listaPedidoAquisicao){
            int status=l.getStatusDoPedido();
            LocalDate dateString = l.getDataDoPedido();
            LocalDate startDate = dateString;
            LocalDate endtDate = LocalDate.now();
            Long range = ChronoUnit.DAYS.between(startDate, endtDate);
            if(range<=30&&status==2){
                valorPedidos+= l.getValorTotalPedido();
            }}
        if (valorPedidos == 0) {
            return "0";
        }
        return df.format(valorPedidos);
    }

    public String ContadorCategoriaAberto(){
        DecimalFormat df = new DecimalFormat("#,###.00");

        double valorPedidos = 0.0;
        for(PedidoAquisicao l:listaPedidoAquisicao){
            int status=l.getStatusDoPedido();
            LocalDate dateString = l.getDataDoPedido();
            LocalDate startDate = dateString;
            LocalDate endtDate = LocalDate.now();
            Long range = ChronoUnit.DAYS.between(startDate, endtDate);
            if(range<=30&&status==1){
                valorPedidos+= l.getValorTotalPedido();
            }}
        if (valorPedidos == 0) {
            return "0";
        }

        return df.format(valorPedidos);
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



    public void cadastraUsuario(){//nome, sobrenome, matricula, adm, departamento
        Scanner in = new Scanner(System.in);
        String departamento = "";
        int matricula = 0;
        String novaMatricula = "";
        boolean adm = false;
        System.out.println("Insira o primeiro nome do novo usuario: ");
        String nome = in.nextLine();
        System.out.println("Insira o sobrenome do novo usuario: ");
        String sobrenome = in.nextLine();
        Usuario userValidacao = null;
        do{
            boolean quebraloop = true;
            do{
                try{
                    //String faznada = in.nextLine();
                    System.out.println("Insira a matricula do novo usuario: ");
                    novaMatricula = Integer.toString(Integer.parseInt(in.nextLine()));
                    //faznada = in.nextLine();
                    quebraloop = true;
                }catch(NumberFormatException err){
                    System.out.println("Erro! A matricula digitada nao e valida. Tente novamente!");
                    quebraloop = false;
                }
            }while(quebraloop == false);
            //novaMatricula = in.nextLine();// fazer validacao para nao repetir matricula

            for(int i = 0; i < listaUsuarios.size(); i++){
                userValidacao = listaUsuarios.get(i);
                if(userValidacao.getMatricula().equals(novaMatricula))
                {

                    LimpaTela();
                    System.out.println("Matricula já atribuida a um usuario, escolha outra matricula");
                    System.out.println("pressione ENTER para continuar");
                    in.nextLine();
                    break;
                }
                else {userValidacao = null;}
            }
        }while(userValidacao != null);
        boolean quebraloop = false;
        do{

            LimpaTela();
            System.out.println("O novo usuario é administrador? [s/n]");
            System.out.print("Opcao: ");
            String isadm = in.nextLine().toLowerCase();
            switch(isadm){
                case "s": { adm = true; quebraloop = true; break;}
                case "n": { adm = false; quebraloop = true; break;}
                default: {System.out.println("Opcao invalida, pressione ENTER para continuar");in.nextLine();}
            }

        }while(!quebraloop);
        quebraloop = false;
        do{
            LimpaTela();
            System.out.println("Escolha um departamento para o novo usuario");
            System.out.println("1- RH");
            System.out.println("2- Producao");
            System.out.println("3- Manutencao");
            System.out.println("4- Engenharia");
            System.out.println("5- TI");
            System.out.print("Opcao: ");

            String escolha = in.nextLine();
            switch(escolha){
                case "1":{departamento = "RH"; quebraloop = true; break;}
                case "2":{departamento = "Producao"; quebraloop = true; break;}
                case "3":{departamento = "Manutencao"; quebraloop = true; break;}
                case "4":{departamento = "Engenharia"; quebraloop = true; break;}
                case "5":{departamento = "TI"; quebraloop = true; break;}
                default: {System.out.println("Opcao invalida, pressione ENTER para continuar");in.nextLine();}
            }

        }while(!quebraloop);

        Usuario user = new Usuario(nome, sobrenome, novaMatricula, adm, departamento);//nome, sobrenome, matricula, adm, departamento
        listaUsuarios.add(user);

        System.out.println("Novo usuario cadastrado com sucesso, pressione ENTER para continuar");
        in.nextLine();

    }//RH, Produção, Manutençao, Engenharia, TI

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
        PedidoAquisicao maiorPedido = null;
        if (this.getListaPedidoAquisicaoSize() != 0) {
            for (int i = 0; i < this.getListaPedidoAquisicaoSize(); i++) {
                if (getPedidoAquisicao(i).getValorTotalPedido() > maior) {
                    maior = getPedidoAquisicao(i).getValorTotalPedido();
                    maiorPedido = getPedidoAquisicao(i);
                }
            }

            aux += "\nNUMERO IDENTIFICADOR: ";
            aux += maiorPedido.getIdPedido();
            aux += ". Data de solicitaçao: ";
            aux += maiorPedido.getDataDoPedido();
            if (maiorPedido.getQtdItens() > 3) {
                aux += "; ";
                aux += maiorPedido.getQtdItens();
                aux += " itens: ";
                aux += maiorPedido.getItensStringShortLimitado(3);
                aux += ", entre outros";
            } else {
                aux += "; Itens: ";
                aux += maiorPedido.getItensStringShort();
            }
            aux += "; Valor total: ";
            aux += maiorPedido.getValorTotalPedido();
            aux += "; Status: ";
            aux += maiorPedido.getStatusString();
            if ((maiorPedido.getStatusDoPedido() != 1) && maiorPedido.getDataDeConclusao() != null) {
                aux += "; Data de conclusao: ";
                aux += maiorPedido.getDataDeConclusao();
            }
            aux += ".";

        }
        else {
            aux = "Nao existe pedidos para realizar a comparacao.";
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
